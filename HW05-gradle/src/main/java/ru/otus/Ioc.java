package ru.otus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class Ioc {

    private Ioc(){}
    public static Media createTV() {
        var handler = new MediaInvocationHandler(new Tv());
        handler.initialize();
        return (Media)Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[]{Media.class},handler);
    }


    static class MediaInvocationHandler implements InvocationHandler  {

        final private Media media;
        final private Set<Method> methods;

        {
            methods = new HashSet<>();
        }

        MediaInvocationHandler(Media media)  {
            if (media == null) throw new NullPointerException("media can't null");
            this.media = media;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (methods.contains(method)) {
                System.out.print("executed method:" + method.getName() + ", param:");
                if (args != null && args.length != 0) {
                    System.out.print(args[0]);
                    Arrays.stream(args).skip(1).forEach(x -> System.out.print(", " + x));
                }
                System.out.println();
            }
            return method.invoke(media, args);
        }

        public void initialize(){
            initMethods();
        }

        private void initMethods(){
            var mediaIface = Arrays.stream(media.getClass().getInterfaces())
                    .filter(i->i.equals(Media.class))
                    .findFirst();
            if (mediaIface.isPresent()) {
                for (var method : mediaIface.get().getDeclaredMethods()) {
                    if (method.isAnnotationPresent(Log.class)) {
                        methods.add(method);
                    }
                }
            }
        }

    }
}
