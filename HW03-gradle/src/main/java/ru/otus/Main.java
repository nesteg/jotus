package ru.otus;

import java.lang.reflect.*;
import java.util.ArrayList;

class ExampleTest{
    @Before
    public void StartUpOne() {
    }

    @Before
    public void StartUpTwo() {
    }

    @After
    public void PullUp() {
    }

    @Test
    public void MethodSucess() {
    }

    @Test
    public void MethodSucessTwo() {
    }

    @Test
    public void MethodFailure() {
        if (0==0) throw new IllegalArgumentException();
        System.out.println("");
    }
}

class Worker {
    final ArrayList<Method> before = new ArrayList<>();
    final ArrayList<Method> test = new ArrayList<>();
    final ArrayList<Method> after = new ArrayList<>();

    private void prepare(Class<?> clazz){
        var ms = clazz.getMethods();
        for (var m : ms) {
            var b = m.getDeclaredAnnotation(Before.class);
            if (b!=null) {
                before.add(m);
            }
            var  a = m.getDeclaredAnnotation(After.class);
            if (a!=null) {
                after.add(m);
            }
            var t = m.getDeclaredAnnotation(Test.class);
            if (t!=null) {
                test.add(m);
            }
        }
    }

    private void run(Class<?> clazz) {
        int success = 0;
        int failure = 0;
        int sum = 0;
        for (var t : test) {
            sum++;
            try {
                var obj =clazz.getDeclaredConstructor().newInstance();
                for (var b:before){
                    b.invoke(obj);
                }
                t.invoke(obj);
                for (var a:after){
                    a.invoke(obj);
                }
            } catch (Exception e) {
                failure ++;
                continue;
            }
            success++;
        }//for
        System.out.format("Всего = %d, С ошибкой = %d,Успешно = %d\n",sum,failure,success);
    }

    public void run(String clName) throws ClassNotFoundException {
        var clazz = Class.forName(clName);
        prepare(clazz);
        run(clazz);
    }
}


/**
        *
        * To start the application:
        * ./gradlew build
        * java -jar ./HW03-gradle/build/libs/Otus-0.1.jar
        *
        * To build:
        * ./gradlew build
        */

public class Main {

    public static void main(String... args) throws ClassNotFoundException {
        var wrk = new Worker();
        wrk.run("ru.otus.ExampleTest");
    }
}
