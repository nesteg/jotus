package ru.otus;

/**
        *
        * To start the application:
        * ./gradlew build
        * java -jar ./HW04-gradle/build/libs/Otus-0.1.jar
        *
        * To build:
        * ./gradlew build
        */

public class GcMain {

    public static void main(String... args) throws InterruptedException {
        var log = new GcLog();
        var listener=new GcListener(new GcAnalyser(),log);
        new GcMonitor().installListener(listener);
        var serve = new Service();
        log.setStarttime(System.currentTimeMillis());
        serve.run();
    }
}
