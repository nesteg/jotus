package ru.otus.hw07.Department;

import ru.otus.hw07.Atm.Atm;
import ru.otus.hw07.Bill.Bill;
import ru.otus.hw07.Container.Container;
import ru.otus.hw07.Department.*;
import ru.otus.hw07.Exceptions.AtmHasNotEnoughBillException;
import ru.otus.hw07.Operation.Operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AtmDefault extends Handler implements Atm, Listener {
    private final Receiver owner;
    private final Container cells;
    private Status status;
    private Memento memento;

    {
        status = Status.Off;
    }

    public class Memento {
        private Status saveStatus;

        public Memento() {
            saveStatus =status;
        }

        public Status getState() {
            return saveStatus;
        }
    }

    public AtmDefault(Receiver owner, Container cells){
        this.owner = owner;
        this.cells = cells;
    }

    public Memento snapShot(){
        memento =  new Memento();
        return memento;
    }

    public void reset(){
        status = memento.getState();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
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


    @Override
    public boolean process(Request request) {
        switch ( (Request)request) {
            case BALANCE: {
                owner.attachToSubject(this);
                return false;
            }
            case SNAPSHORT: {
                snapShot();
                return false;
            }
            case RESET:{
                reset();
                return false;
            }
            default:return false;
        }
    }

    @Override
    public void onEvent() {
        owner.addBalance(getBalance());
    }
}
