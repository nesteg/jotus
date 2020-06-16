import org.junit.jupiter.api.Test;
import ru.otus.hw06.Atm.AtmDefault;
import ru.otus.hw06.Bill.Bill;
import ru.otus.hw06.Container.ContainerImpl;
import ru.otus.hw06.Exceptions.AtmHasNotEnoughBillException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AtmTest {

    @Test
    public void AtmBalance(){
        var pack = new ArrayList<>(Arrays.asList(
                Bill.FIFTY,
                Bill.ONE_HUNDRED,
                Bill.ONE_HUNDRED,
                Bill.FIVE_THOUSAND,
                Bill.FIFTY,
                Bill.ONE_THOUSAND));
        var atm = new AtmDefault(new ContainerImpl(Arrays.asList(Bill.values())));
        atm.accept(pack);
        assertEquals(6300, atm.getBalance());

    }
    @Test
    public void AtmGiveBalance() throws Exception{
        var pack = new ArrayList<>(Arrays.asList(
                Bill.FIFTY,
                Bill.ONE_HUNDRED,
                Bill.ONE_HUNDRED,
                Bill.FIVE_THOUSAND,
                Bill.FIFTY,
                Bill.ONE_THOUSAND));
        var atm = new AtmDefault(new ContainerImpl(Arrays.asList(Bill.values())));
        atm.accept(pack);
        atm.give(250);
        assertEquals(6050, atm.getBalance());
    }

    @Test
    public void AtmGiveErrorBalance() {
        var pack = new ArrayList<>(Arrays.asList(
                Bill.FIFTY,
                Bill.ONE_HUNDRED,
                Bill.ONE_HUNDRED,
                Bill.FIVE_THOUSAND,
                Bill.FIFTY,
                Bill.ONE_THOUSAND));
        var atm = new AtmDefault(new ContainerImpl(Arrays.asList(Bill.values())));
        atm.accept(pack);
        assertThrows(AtmHasNotEnoughBillException.class, () -> atm.give(550));
        assertEquals(6300, atm.getBalance());
    }

}
