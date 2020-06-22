package ru.otus.hw07.Exceptions;

public class AtmHasNotEnoughBillException extends RuntimeException{
    public AtmHasNotEnoughBillException(String message){
        super(message);
    }
}
