package ru.otus.hw06.Container;

import ru.otus.hw06.Bill.Bill;
import ru.otus.hw06.Cell.Cell;
import ru.otus.hw06.Cell.CellImpl;
import ru.otus.hw06.Operation.Operation;

import java.util.*;

public class ContainerImpl implements Container{
    private Map<Bill, Cell> container;

    {
        container = new TreeMap<>(Comparator.comparing(Bill::getNominal).reversed());
    }

    public ContainerImpl(Collection<Bill> c) {
        for (var e:c){
            container.putIfAbsent(e,new CellImpl(e));
        }
    }


    @Override
    public void add(Cell cell) {
        container.putIfAbsent(cell.getBill(),cell);
    }


    @Override
    public void update(Map<Bill,Integer> group, Operation op) {
        switch(op){
            case INCREASE: {
                group.entrySet()
                        .stream()
                        .forEach(e -> container.get(e.getKey()).addQuantity(e.getValue()));
                break;
            }
            case DECREASE: {
                group.entrySet()
                        .stream()
                        .forEach(e -> container.get(e.getKey()).decQuantity(e.getValue()));
                break;
            }
        }
    }

    @Override
    public Optional<Map<Bill,Integer>> give(int amount) {
        var remain = amount;
        Map<Bill,Integer> history = new HashMap<>();
        for (var cell : container.entrySet()){
            var quantity = cell.getValue().give(remain);
            if (quantity != 0) {
                history.put(cell.getKey(),quantity);
            }
            remain -= cell.getValue().getNominal() * quantity;
        }
        if (remain > 0) {
            return Optional.empty();
        }
        update(history,Operation.DECREASE);
        return Optional.of(history);
    }

    @Override
    public int getBalance() {
        return container.entrySet().stream()
                .reduce(0, (m,e)->m + (e.getValue().getBalance()),Integer::sum);
    }

    @Override
    public void clear() {
        container.clear();
    }

}
