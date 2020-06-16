package ru.otus.hw06.Operation;

import ru.otus.hw06.Cell.Cell;

import java.util.function.BiConsumer;

public class Operation {
    public static BiConsumer<Cell, Integer> OPERATION_INCREASE = Cell::addQuantity;
    public static BiConsumer<Cell, Integer> OPERATION_DEGREASE = Cell::decQuantity;
}
