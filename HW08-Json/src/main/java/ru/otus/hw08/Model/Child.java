package ru.otus.hw08.Model;

import lombok.Value;

@Value
public class Child {
    private int[] numbers;
    private float value;
    private String description;
    private char ch;
    final int quantity =7;
    transient double price;
}
