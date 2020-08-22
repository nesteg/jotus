package ru.otus.app.dao.mediator;

import io.grpc.stub.StreamObserver;
import ru.otus.dto.UserDto;
import ru.otus.dto.UserListDto;
import ru.otus.protobuf.generated.Empty;
import ru.otus.protobuf.generated.UserMessage;

import java.util.concurrent.CompletableFuture;

public class GetUserList extends Holder{
       private StreamObserver<Empty> response;
       private CompletableFuture<UserListDto> future;

    public GetUserList() {
       super();
    }

    public GetUserList(String key, StreamObserver<Empty> response, CompletableFuture<UserListDto> future) {
        super(key);
        this.response = response;
        this.future = future;
    }

    public CompletableFuture<UserListDto> getFuture() {
        return future;
    }

    public void setFuture(CompletableFuture<UserListDto> future) {
        this.future = future;
    }

    public StreamObserver<Empty> getResponse() {
        return response;
    }

}
