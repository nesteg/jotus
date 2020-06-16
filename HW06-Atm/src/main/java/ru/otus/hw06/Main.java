package ru.otus.hw06;

import ru.otus.hw06.Bill.Bill;
import ru.otus.hw06.Atm.AtmDefault;
import ru.otus.hw06.Container.ContainerImpl;
import ru.otus.hw06.Exceptions.AtmHasNotEnoughBillException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar ./HW06-gradle/build/libs/Otus-0.1.jar
 *
 * To build:
 * ./gradlew build
 */



public class Main {

    public static void main(String... args){
        var pack = new ArrayList<>(Arrays.asList(
                Bill.FIFTY,
                Bill.ONE_HUNDRED,
                Bill.ONE_HUNDRED,
                Bill.FIVE_THOUSAND,
                Bill.FIFTY,
                Bill.ONE_THOUSAND));
        var atm = new AtmDefault(new ContainerImpl(Arrays.asList(Bill.values())));
        atm.accept(pack);
        System.out.println(atm.getBalance());
        try {
            var gived = atm.give(250);
            gived.forEach(e->System.out.println(e.getNominal()));
        }catch(AtmHasNotEnoughBillException e){
            System.out.println(e.getMessage());
        } finally{
            System.out.println(atm.getBalance());
        }
    }
}
