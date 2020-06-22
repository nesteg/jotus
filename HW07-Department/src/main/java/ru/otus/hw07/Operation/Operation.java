package ru.otus.hw07.Operation;

import ru.otus.hw07.Cell.Cell;

import java.util.function.BiConsumer;

public class Operation {
    public final static BiConsumer<Cell, Integer> OPERATION_INCREASE = Cell::addQuantity;
    public final static BiConsumer<Cell, Integer> OPERATION_DEGREASE = Cell::decQuantity;
}
