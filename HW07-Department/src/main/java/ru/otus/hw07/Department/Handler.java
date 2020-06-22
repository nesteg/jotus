package ru.otus.hw07.Department;

import java.util.Optional;

public abstract class Handler {
    private Optional<Handler> successor = Optional.empty();

    public void handler(Request request){
        if(!process(request)) {
            successor.ifPresent(h -> h.handler(request));
        }
    }

    public void add(Handler handler){
        successor.ifPresentOrElse(h->h.add(handler),
                ()->successor=Optional.ofNullable(handler));
    }

    public abstract  boolean process(Request request);
}
