import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.hw07.Bill.Bill;
import ru.otus.hw07.Container.ContainerImpl;
import ru.otus.hw07.Department.Balance;
import ru.otus.hw07.Department.CommandBalance;
import ru.otus.hw07.Department.Department;
import ru.otus.hw07.Department.AtmDefault;
import ru.otus.hw07.Department.Status;
import ru.otus.hw07.Exceptions.AtmHasNotEnoughBillException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtmTest {
    private static Balance balance;
    private static Department department;
    private static AtmDefault atmOne;
    private static AtmDefault atmTwo;
    private static AtmDefault atmThree;

    @BeforeAll
    public static void initialize(){
        balance = new Balance();
        department = new Department(balance);
        atmOne = new AtmDefault(department, new ContainerImpl(List.of(Bill.values())));
        atmTwo = new AtmDefault(department, new ContainerImpl(List.of(Bill.values())));
        atmThree = new AtmDefault(department, new ContainerImpl(List.of(Bill.values())));
        department.addAtm(atmOne);
        department.addAtm(atmTwo);
        department.addAtm(atmThree);
    }

    @Test
    public void DepartmentChainBalance(){

        department.attachToObservableBalance();
        var command = new CommandBalance(balance);
        department.setBalanceZero();
        command.execute();
        var balance0 = department.getBalance();
        atmOne.accept(new ArrayList<>(Arrays.asList(
                Bill.FIFTY,
                Bill.ONE_HUNDRED,
                Bill.ONE_HUNDRED,
                Bill.FIVE_THOUSAND,
                Bill.FIFTY,
                Bill.ONE_THOUSAND)));
        department.setBalanceZero();
        command.execute();
        var balance1 = department.getBalance();
        atmTwo.accept(new ArrayList<>(Arrays.asList(
                Bill.FIFTY,
                Bill.ONE_THOUSAND)));
        department.setBalanceZero();
        command.execute();
        var balance2 = department.getBalance();
        atmThree.accept(new ArrayList<>(Arrays.asList(
                Bill.FIFTY,
                Bill.ONE_HUNDRED,
                Bill.ONE_THOUSAND,
                Bill.ONE_THOUSAND,
                Bill.FIVE_HUNDRED)));
        department.setBalanceZero();
        command.execute();
        var balance3 = department.getBalance();
        assertEquals(0, balance0);
        assertEquals(6300, balance1);
        assertEquals(7350, balance2);
        assertEquals(10000, balance3);

    }
    @Test
    public void DepartmentChainMemento() {
        atmOne.setStatus(Status.On);
        atmTwo.setStatus(Status.On);
        department.snapShot();
        atmOne.setStatus(Status.Off);
        atmTwo.setStatus(Status.Off);
        atmThree.setStatus(Status.On);
        assertEquals(Status.Off, atmOne.getStatus());
        assertEquals(Status.Off, atmTwo.getStatus());
        assertEquals(Status.On, atmThree.getStatus());
        department.reset();
        assertEquals(Status.On, atmOne.getStatus());
        assertEquals(Status.On, atmTwo.getStatus());
        assertEquals(Status.Off, atmThree.getStatus());
    }
}
