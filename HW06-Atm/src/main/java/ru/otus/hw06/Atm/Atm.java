package ru.otus.hw06.Atm;

import ru.otus.hw06.Bill.Bill;
import ru.otus.hw06.Exceptions.AtmHasNotEnoughBillException;

import java.util.List;

public interface Atm {

    void accept(List<Bill> pack);
    List<Bill> give(int amount) throws AtmHasNotEnoughBillException;
    int getBalance();
}
