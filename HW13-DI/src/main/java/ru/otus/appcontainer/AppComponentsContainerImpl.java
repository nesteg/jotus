package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    private static final char PKG_SEPARATOR = '.';
    private static final char DIR_SEPARATOR = '/';
    private static final String CLASS_FILE_SUFFIX = ".class";

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws Exception {
        processConfig(initialConfigClass);
    }

    public AppComponentsContainerImpl(Class<?>... initialConfigClasses) throws Exception {
        for (var initialConfigClass : initialConfigClasses) {
            processConfig(initialConfigClass);
        }
    }

    public AppComponentsContainerImpl(String packageName) throws Exception {
        var configClasses = getClassesFromPackage(packageName);
        if (configClasses.size() > 1) {
            sortConfigsByOrder(configClasses);
        }
        for (var initialConfigClass :configClasses) {
            processConfig(initialConfigClass);
        }
    }

    private void processConfig(Class<?> configClass) throws Exception {
        checkConfigClass(configClass);
        var methods = configClass.getDeclaredMethods();
        if (methods.length > 1) {
            sortMethodsByOrder(methods);
        }
        var constructor = configClass.getConstructor(null);
        var objConfig = constructor.newInstance();
        for (var method : methods) {
            ArrayList<Object> objs = new ArrayList<>();
            var annotation = method.getDeclaredAnnotation(AppComponent.class);
            var name = annotation.name();
            var parameters = method.getParameters();
            for (var parameter : parameters) {
                var clazz = parameter.getType();
                for (var component : appComponents) {
                    if (clazz.isAssignableFrom(component.getClass())) {
                        objs.add(component);
                        break;
                    }
                }
            }
            var obj = method.invoke(objConfig, objs.toArray());
            appComponents.add(obj);
            appComponentsByName.put(name, obj);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        for (var component : appComponents) {
            if (componentClass.isAssignableFrom(component.getClass())) {
                return (C)component;
            }
        }
        return null;
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C)appComponentsByName.get(componentName);
    }

    private List<Class<?>> getClassesFromPackage(String packageName) throws Exception {
        ArrayList<Class<?>> listClass = new ArrayList<>();
        var resName = packageName.replace(PKG_SEPARATOR, DIR_SEPARATOR);
        var scannedUrl = getClass().getClassLoader().getResource(resName);
        var jarUrl = scannedUrl.toExternalForm();
        if (jarUrl.matches("^jar:(.+)")) { //Ищем в jar файле ресурсы
            Pattern pattern = Pattern.compile("(.+)([/])([^/]+)$");
            JarURLConnection urlcon = (JarURLConnection) (scannedUrl.toURI().toURL().openConnection());
            try (JarFile jar = urlcon.getJarFile()) {
                Enumeration<JarEntry> entries = jar.entries();
                while (entries.hasMoreElements()) {
                    String entry = entries.nextElement().getName();
                    if (entry.contains(resName)) {
                        Matcher matcher = pattern.matcher(entry);
                        if (matcher.find()) {
                            var resource = matcher.group(3);
                            Class<?> clazz = getClazz(resource, packageName);
                            listClass.add(clazz);
                        }
                    }
                }
            }
        } else {//Ищем из среды разработки
            var scannedDir = new File(scannedUrl.getFile());
            for (File file : scannedDir.listFiles()) {
                String resource = StringUtils.difference(scannedDir.getPath(), file.getName());
                Class<?> clazz = getClazz(resource, packageName);
                listClass.add(clazz);
            }
        }
        return listClass;
    }

    private void sortMethodsByOrder(Method[] methods) {
        Arrays.sort(methods, new Comparator<Method>() {
            @Override
            public int compare(Method m1, Method m2) {
                var annotation1 = m1.getDeclaredAnnotation(AppComponent.class);
                var annotation2 = m2.getDeclaredAnnotation(AppComponent.class);
                if (annotation1.order() < annotation2.order()) {
                    return -1;
                } else {
                    return (annotation1.order() == annotation2.order()) ? 0 : 1;
                }
            }
        });
    }

    private void sortConfigsByOrder(List<Class<?>> configs) {
       Collections.sort(configs, new Comparator<Class<?>>() {
            @Override
            public int compare(Class<?> cl1, Class<?> cl2) {
                var annotation1 = cl1.getDeclaredAnnotation(AppComponentsContainerConfig.class);
                var annotation2 = cl2.getDeclaredAnnotation(AppComponentsContainerConfig.class);
                if (annotation1.order() < annotation2.order()) {
                    return -1;
                } else {
                    return (annotation1.order() == annotation2.order()) ? 0 : 1;
                }
            }
        });
    }

    private Class<?> getClazz(String resource,String packageName) throws Exception{
        int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
        String className = resource.substring(0, endIndex);
        return Class.forName(String.join(String.valueOf(PKG_SEPARATOR), packageName, className));
    }
}
