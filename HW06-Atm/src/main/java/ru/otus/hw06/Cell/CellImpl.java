package ru.otus.hw06.Cell;

import ru.otus.hw06.Bill.Bill;

public class CellImpl implements Cell {

    private  int quantity;
    private final Bill bill;

    public CellImpl(Bill bill){
        this.bill=bill;
        this.quantity=0;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void addQuantity(int q) {
        quantity += q;
    }

    @Override
    public void decQuantity( int q) {
        quantity -= q;
    }

    @Override
    public Bill getBill() {
        return bill;
    }

    @Override
    public int getNominal() {
        return bill.getNominal();
    }

    @Override
    public int give(int amount) {
        return  threshold(amount / getNominal());
    }

    @Override
    public int getBalance() {
        return bill.getNominal() * quantity;
    }

    private int threshold(int q){
        return quantity >= q ? q : quantity;
    }

}
