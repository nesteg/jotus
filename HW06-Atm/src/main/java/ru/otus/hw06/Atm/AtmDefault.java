package ru.otus.hw06.Atm;

import ru.otus.hw06.Bill.Bill;
import ru.otus.hw06.Container.Container;
import ru.otus.hw06.Exceptions.AtmHasNotEnoughBillException;
import ru.otus.hw06.Operation.Operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AtmDefault implements Atm {
    private final Container cells;

    public AtmDefault(Container cells){
        this.cells = cells;
    }

    public void accept(List<Bill> pack){
        Map<Bill,Integer> group = pack
                .stream()
                .collect(Collectors.groupingBy(Bill::self,Collectors.collectingAndThen(Collectors.counting(), e -> e.intValue())));
        cells.update(group, Operation.OPERATION_INCREASE);
    }

    @Override
    public List<Bill> give(int amount) throws AtmHasNotEnoughBillException {

        return cells.give(amount)
                .map(x->x.entrySet()
                        .stream()
                        .flatMap(this::convert)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new AtmHasNotEnoughBillException("can't give out the requested amount"));
    }

    @Override
    public int getBalance() {
        return cells.getBalance();
    }

    Stream<Bill> convert(Map.Entry<Bill,Integer> entry){
        Bill[] bills = new Bill[entry.getValue()];
        Arrays.fill(bills,entry.getKey());
        return new ArrayList<>(Arrays.asList(bills)).stream();
    }


}
