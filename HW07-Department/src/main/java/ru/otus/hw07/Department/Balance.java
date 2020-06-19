package ru.otus.hw07.Department;

import java.util.ArrayList;
import java.util.List;

public class Balance implements Requestable{
    private List<Listener> listeners;
    {
        listeners = new ArrayList<>();
    }

    public void attach(Listener listener) {
        listeners.add(listener);
    }

    public void deattach(Listener listener) {
        listeners.remove(listener);
    }

    public void collectBalance() {
        listeners.forEach(listener -> listener.onEvent());
    }
}

