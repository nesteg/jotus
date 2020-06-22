package ru.otus.hw07.Container;

import ru.otus.hw07.Bill.Bill;
import ru.otus.hw07.Cell.Cell;
import ru.otus.hw07.Operation.Operation;

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
