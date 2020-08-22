package ru.otus.app.dao;


import io.grpc.stub.StreamObserver;
import ru.otus.app.dao.mediator.BaseMediator;
import ru.otus.app.dao.mediator.HandlersMediator;
import ru.otus.app.dao.streamobserves.GetUserListStreamObserver;
import ru.otus.app.dao.streamobserves.GetUserStreamObserver;
import ru.otus.app.dao.streamobserves.SaveUserStreamObserver;
import ru.otus.protobuf.generated.Empty;
import ru.otus.protobuf.generated.IdMessage;
import ru.otus.protobuf.generated.RemoteDBServiceGrpc.RemoteDBServiceImplBase;
import ru.otus.protobuf.generated.UserMessage;

public class DbServiceUserImpl extends RemoteDBServiceImplBase {
    private BaseMediator mediator;

    public DbServiceUserImpl(BaseMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public StreamObserver<UserMessage> saveUser(StreamObserver<UserMessage> responseObserver) {
        return new SaveUserStreamObserver(responseObserver, mediator);
    }

    @Override
    public StreamObserver<UserMessage> findAllUsers(StreamObserver<Empty> responseObserver) {
        return new GetUserListStreamObserver(responseObserver, mediator);
    }

    @Override
    public StreamObserver<UserMessage> findByIdUser(StreamObserver<IdMessage> responseObserver) {
        return new GetUserStreamObserver(responseObserver, mediator);
    }
}
