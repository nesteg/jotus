package ru.otus;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Worker {
    final ArrayList<Method> befores = new ArrayList<>();
    final ArrayList<Method> tests = new ArrayList<>();
    final ArrayList<Method> afters = new ArrayList<>();
    private int success = 0;
    private int failure = 0;
    private int sum = 0;

    private void prepare(Class<?> clazz){
        var ms = clazz.getMethods();
        for (var m : ms) {
            var b = m.getDeclaredAnnotation(Before.class);
            if (b!=null) {
                befores.add(m);
            }
            var  a = m.getDeclaredAnnotation(After.class);
            if (a!=null) {
                afters.add(m);
            }
            var t = m.getDeclaredAnnotation(Test.class);
            if (t!=null) {
                tests.add(m);
            }
        }
    }

    private void run(Class<?> clazz) {

        for (var test : tests) {
            sum++;
            try {
                var obj =clazz.getDeclaredConstructor().newInstance();
                for (var before:befores){
                    before.invoke(obj);
                }
                test.invoke(obj);
                for (var after:afters){
                    after.invoke(obj);
                }
            } catch (Exception e) {
                failure ++;
                continue;
            }
            success++;
        }//for
    }

    public void run(String clName) throws ClassNotFoundException {
        var clazz = Class.forName(clName);
        prepare(clazz);
        run(clazz);
        printResult();
    }

    private void  printResult() {
        System.out.format("Всего = %d, С ошибкой = %d,Успешно = %d\n",sum,failure,success);
    }
}
