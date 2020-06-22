package ru.otus.hw07.Bill;

public enum Bill {
    FIFTY(50),
    ONE_HUNDRED(100),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    FIVE_THOUSAND(5000);

    private int nominal;

    Bill (int nominal) {
        this.nominal = nominal;
    }


    public int getNominal() {
        return nominal;
    }


    static Bill[] getNominals() {
        return values();
    }


    public Bill self() {
        return this;
    }
}
