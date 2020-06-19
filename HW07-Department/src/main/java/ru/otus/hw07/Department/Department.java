package ru.otus.hw07.Department;

import ru.otus.hw07.Atm.AtmDefault;

import java.util.Objects;

public class Department implements Receiver {
    private int balance;
    private Handler chain;
    private final Balance subject;

    public Department(Balance subject) {
        this.subject = subject;
    }

    public void addAtm(AtmDefault atm){
        if (chain == null){
            chain = atm;
        }else {
            chain.add(atm);
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalanceZero() {
        this.balance = 0;
    }

    public void attachToObservableBalance(){
        if (!Objects.isNull(balance)){
            chain.handler(Request.BALANCE);
        }
    }

    public void snapShot(){
        if (!Objects.isNull(chain)){
            chain.handler(Request.SNAPSHORT);
        }
    }

    public void reset(){
        if (!Objects.isNull(chain)){
            chain.handler(Request.RESET);
        }
    }



    @Override
    public void addBalance(int balance) {
        this.balance+=balance;
    }

    @Override
    public void attachToSubject(Listener listener) {
        if (!Objects.isNull(subject)) {
            subject.attach(listener);
        }
    }
}
