package ru.otus.hw06.Exceptions;

public class AtmHasNotEnoughBillException extends RuntimeException{
    public AtmHasNotEnoughBillException(String message){
        super(message);
    }
}
