package ru.otus.hw07.Department;

import java.util.Optional;

public abstract class Handler {
    private Optional<Handler> successor;

    {
        successor=Optional.empty();
    }

    public void handler(Requestable request){
        if(!process(request)) {
            successor.ifPresent(successor -> successor.handler(request));
        }
    }

    public void add(Handler handler){
        this.successor.ifPresentOrElse(successor->successor.add(handler),
                ()->this.successor=Optional.ofNullable(handler));
    }

    public abstract  boolean process(Requestable request);
}
