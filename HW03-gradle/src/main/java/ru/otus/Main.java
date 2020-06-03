package ru.otus;

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
