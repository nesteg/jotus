package ru.otus.hw07.Department;

public class CommandBalance implements Command {
    private Balance balance;

    public CommandBalance(Balance balance) {
        this.balance = balance;
    }

    @Override
    public void execute(){
        balance.collectBalance();
    }
}
