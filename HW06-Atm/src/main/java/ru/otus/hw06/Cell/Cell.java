package ru.otus.hw06.Cell;

import ru.otus.hw06.Bill.Bill;

public interface Cell {
    int      getQuantity();
    void     addQuantity(int q);
    void     decQuantity(int q);
    Bill     getBill();
    int      getNominal();
    int      give(int amount);
    int      getBalance();
}
