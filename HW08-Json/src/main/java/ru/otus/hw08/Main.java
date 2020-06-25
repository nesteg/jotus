package ru.otus.hw08;


import com.google.gson.Gson;

import ru.otus.hw08.Converter.*;
import ru.otus.hw08.Model.*;

import java.util.Arrays;

/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar ./HW08-Json/build/libs/Otus-0.1.jar
 *
 * To build:
 * ./gradlew build
 */



public class Main {

    public static void main(String... args) throws IllegalAccessException {
        int[] numbers = {2054,6789,18765};
        Child child = new Child(numbers,5.39E+1f,null,'A',99d);
        Root root = new Root();
        root.setId(1L);
        root.setChilds(Arrays.asList(child));
        root.setName("Main root");
        root.setBalance(1.000E+3d);
        root.setZip((short)30123);
        var converter = new JsonConverter();
        String json = converter.toJson(4L);
        System.out.println(json);
        String json0 = new Gson().toJson(4L);
        System.out.println(json0);

    }
}
