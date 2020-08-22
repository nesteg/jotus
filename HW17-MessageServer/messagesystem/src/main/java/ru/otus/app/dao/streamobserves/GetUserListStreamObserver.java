package ru.otus.app.dao.streamobserves;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.app.dao.DbServiceUserImpl;
import ru.otus.app.dao.mediator.BaseMediator;
import ru.otus.app.dao.mediator.GetUserList;
import ru.otus.dto.UserDto;
import ru.otus.dto.UserListDto;
import ru.otus.protobuf.generated.Empty;
import ru.otus.protobuf.generated.UserMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetUserListStreamObserver extends  BaseStreamObserver implements StreamObserver<UserMessage> {

    private static final Logger logger = LoggerFactory.getLogger(GetUserListStreamObserver.class);
    private final StreamObserver<Empty>  responseObserver;
    private final BaseMediator mediator;
    private int stateListUser = 0;
    private List<UserDto> users = new ArrayList<>();
    private boolean clearListUser = false;




    public GetUserListStreamObserver(StreamObserver<Empty> responseObserver, BaseMediator mediator) {
        this.responseObserver = responseObserver;
        this.mediator = mediator;
    }

    @Override
    public void onNext(UserMessage value) {
        if (!UserMessageNotEmpty(value)) {
            DefineKey(responseObserver);
            if (stateListUser == 0) {
                if (key != null) {
                    mediator.addUserList(key, new GetUserList(key, responseObserver, new CompletableFuture<>()));
                }
                stateListUser = 1;
            } else if (stateListUser == 1) {
                if (key != null) {
                    mediator.completeGetUserList(key, new UserListDto(users));
                }
                clearListUser = true;
            }
        } else {
            if (clearListUser){
                users.clear();
                clearListUser=false;
            }
            users.add(new UserDto(value));
        }
        logger.info("got message from {}:'findAllUsers'{}", key,value);
    }


    @Override
    public void onError(Throwable t) {
        logger.error("got message:'findAllUsers error'", t);
    }

    @Override
    public void onCompleted() {
        logger.info("got message:'savedUser completed'");
    }
}
