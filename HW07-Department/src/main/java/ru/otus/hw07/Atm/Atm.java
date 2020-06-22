package ru.otus.hw07.Atm;

import ru.otus.hw07.Bill.Bill;
import ru.otus.hw07.Exceptions.AtmHasNotEnoughBillException;

import java.util.List;

public interface Atm {

    void accept(List<Bill> pack);
    List<Bill> give(int amount) throws AtmHasNotEnoughBillException;
    int getBalance();
}
