package ru.otus.app.dao.streamobserves;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.app.dao.mediator.BaseMediator;
import ru.otus.app.dao.mediator.SaveUser;
import ru.otus.dto.UserDto;
import ru.otus.protobuf.generated.UserMessage;

import java.util.concurrent.CompletableFuture;


public class SaveUserStreamObserver extends BaseStreamObserver implements StreamObserver<UserMessage> {

    private static final Logger logger = LoggerFactory.getLogger(SaveUserStreamObserver.class);
    private final StreamObserver<UserMessage> responseObserver;
    private final BaseMediator mediator;

    public SaveUserStreamObserver(StreamObserver<UserMessage> responseObserver, BaseMediator mediator) {
        this.responseObserver = responseObserver;
        this.mediator = mediator;
    }

    @Override
    public void onNext(UserMessage value) {
        if (!UserMessageNotEmpty(value)) {
            DefineKey(responseObserver);
            if (key != null) {
                mediator.addSaveUser(key, new SaveUser(key, responseObserver, new CompletableFuture<>()));
            }
        } else {
            if (key != null) {
                mediator.completeSaveUser(key, new UserDto(value));
            }
        }
        logger.info("got message from {}:'savedUser'{}", key, value);
    }

    @Override
    public void onError(Throwable t) {
        logger.error("got message:'savedUser error'", t);
    }

    @Override
    public void onCompleted() {
        logger.info("got message:'savedUser completed'");
    }

}
