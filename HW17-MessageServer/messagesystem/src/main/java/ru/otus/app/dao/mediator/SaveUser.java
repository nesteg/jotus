package ru.otus.app.dao.mediator;

import io.grpc.stub.StreamObserver;
import ru.otus.dto.UserDto;
import ru.otus.protobuf.generated.UserMessage;

import java.util.concurrent.CompletableFuture;

public class SaveUser extends Holder {
    private StreamObserver<UserMessage> response;
    private CompletableFuture<UserDto> future;

    public SaveUser(){
        super();
    }

    public SaveUser(String key,StreamObserver<UserMessage> response, CompletableFuture<UserDto> future) {
        super(key);
        this.response = response;
        this.future = future;
    }



    public CompletableFuture<UserDto> getFuture() {
        return future;
    }

    public void setFuture(CompletableFuture<UserDto> future) {
        this.future = future;
    }

    public StreamObserver<UserMessage> getResponse() {
        return response;
    }

}
