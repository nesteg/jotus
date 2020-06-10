package ru.otus;

/**
        *
        * To start the application:
        * ./gradlew build
        * java -jar ./HW05-gradle/build/libs/Otus-0.1.jar
        *
        * To build:
        * ./gradlew build
        */

public class Aspect {

    public static void main(String... args) {
        try {
            var tv = Ioc.createTV();
            System.out.println(tv.Info());
            tv.Open("url");
            tv.Start(0, (float) 1.5);
            tv.Stop(true);
            tv.Close();
        }catch(Exception e){

        }
    }
}
