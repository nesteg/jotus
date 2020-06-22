package ru.otus.hw07;

import ru.otus.hw07.Bill.Bill;
import ru.otus.hw07.Department.AtmDefault;
import ru.otus.hw07.Container.ContainerImpl;
import ru.otus.hw07.Department.Balance;
import ru.otus.hw07.Department.CommandBalance;
import ru.otus.hw07.Department.Department;
import ru.otus.hw07.Department.Status;
import ru.otus.hw07.Exceptions.AtmHasNotEnoughBillException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar ./hw07-gradle/build/libs/Otus-0.1.jar
 *
 * To build:
 * ./gradlew build
 */



public class Main {

    public static void main(String... args) {
        var balance = new Balance();
        var department = new Department(balance);
        var atmOne = new AtmDefault(department, new ContainerImpl(List.of(Bill.values())));
        atmOne.setStatus(Status.On);
        var atmTwo = new AtmDefault(department, new ContainerImpl(List.of(Bill.values())));
        atmTwo.setStatus(Status.On);
        var atmThree = new AtmDefault(department, new ContainerImpl(List.of(Bill.values())));
        department.addAtm(atmOne);
        department.addAtm(atmTwo);
        department.addAtm(atmThree);
        department.attachToObservableBalance();
        var command = new CommandBalance(balance);
        command.execute();
        var beforeBalance = department.getBalance();
        atmOne.accept(new ArrayList<>(Arrays.asList(
                Bill.FIFTY,
                Bill.ONE_HUNDRED,
                Bill.ONE_HUNDRED,
                Bill.FIVE_THOUSAND,
                Bill.FIFTY,
                Bill.ONE_THOUSAND)));
        command.execute();
        var afterBalance = department.getBalance();
        atmTwo.accept(new ArrayList<>(Arrays.asList(
                Bill.FIFTY,
                Bill.ONE_THOUSAND)));
        command.execute();
        afterBalance = department.getBalance();
        atmThree.accept(new ArrayList<>(Arrays.asList(
                Bill.FIFTY,
                Bill.ONE_HUNDRED,
                Bill.ONE_THOUSAND,
                Bill.ONE_THOUSAND,
                Bill.FIVE_HUNDRED)));
        command.execute();
        afterBalance = department.getBalance();
        department.snapShot();
        atmOne.setStatus(Status.Off);
        atmTwo.setStatus(Status.Off);
        atmThree.setStatus(Status.On);
        var status = atmOne.getStatus();
        department.reset();
        status = atmTwo.getStatus();
        status = atmThree.getStatus();
    }
}
