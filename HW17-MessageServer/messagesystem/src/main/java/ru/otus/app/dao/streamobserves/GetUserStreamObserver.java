package ru.otus.app.dao.streamobserves;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.app.dao.DbServiceUserImpl;
import ru.otus.app.dao.mediator.BaseMediator;
import ru.otus.app.dao.mediator.GetUser;
import ru.otus.dto.UserDto;
import ru.otus.protobuf.generated.IdMessage;
import ru.otus.protobuf.generated.UserMessage;

import java.util.concurrent.CompletableFuture;

public class GetUserStreamObserver extends  BaseStreamObserver implements StreamObserver<UserMessage> {

    private static final Logger logger = LoggerFactory.getLogger(GetUserStreamObserver.class);
    private final StreamObserver<IdMessage>  responseObserver;
    private final BaseMediator mediator;
    private int stateGetUser = 0;

    public GetUserStreamObserver(StreamObserver<IdMessage> responseObserver, BaseMediator mediator) {
        this.responseObserver = responseObserver;
        this.mediator = mediator;
    }

    @Override
    public void onNext(UserMessage value) {
        if (!UserMessageNotEmpty(value)) {
            DefineKey(responseObserver);
            if (stateGetUser == 0) {
                if( key != null) {
                    mediator.addGetUser(key, new GetUser(key, responseObserver, new CompletableFuture<>()));
                }
                stateGetUser = 1;
            } else if (stateGetUser == 1) {
                if (key != null) {
                    mediator.completeGetUser(key, null);
                }
            }
        } else {
            if (key != null) {
                mediator.completeGetUser(key, new UserDto(value));
            }
        }
        logger.info("got message from {}:'findByIdUser'{}",key, value);
    }

    @Override
    public void onError(Throwable t) {
        logger.error("got message:'findByIdUser error'", t);
    }

    @Override
    public void onCompleted() {
        logger.info("got message:'findByIdUser completed'");
    }
}
