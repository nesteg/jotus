package ru.otus.app.dao.mediator;

import io.grpc.stub.StreamObserver;
import ru.otus.dto.UserDto;
import ru.otus.protobuf.generated.IdMessage;
import ru.otus.protobuf.generated.UserMessage;

import java.util.concurrent.CompletableFuture;

public class GetUser extends Holder {
    private StreamObserver<IdMessage> response;
    private CompletableFuture<UserDto> future;

    public GetUser() {
        super();
    }

    public GetUser(String key, StreamObserver<IdMessage> response, CompletableFuture<UserDto> futureUser) {
        super(key);
        this.response = response;
        this.future = futureUser;
    }

    public CompletableFuture<UserDto> getFuture() {
        return future;
    }

    public void setFuture(CompletableFuture<UserDto> future) {
        this.future = future;
    }

    public StreamObserver<IdMessage> getResponse() {
        return response;
    }


}
