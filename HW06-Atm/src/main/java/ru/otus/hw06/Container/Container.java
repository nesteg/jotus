package ru.otus.hw06.Container;

import ru.otus.hw06.Bill.Bill;
import ru.otus.hw06.Cell.Cell;
import ru.otus.hw06.Operation.Operation;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public interface Container {
    void add(Cell cell);
    void update(Map<Bill,Integer> group, BiConsumer<Cell, Integer> op);
    Optional<Map<Bill,Integer>> give(int amount);
    int getBalance();
    void clear();

}
