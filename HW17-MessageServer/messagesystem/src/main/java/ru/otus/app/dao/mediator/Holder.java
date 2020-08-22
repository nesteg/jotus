package ru.otus.app.dao.mediator;

import io.grpc.stub.StreamObserver;
import ru.otus.protobuf.generated.UserMessage;

public class Holder {
    private final String key;


    public Holder() {
        key = null;
    }

    public Holder(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }



}
