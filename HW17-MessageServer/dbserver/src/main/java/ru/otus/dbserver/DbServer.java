package ru.otus.dbserver;


import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;
import ru.otus.protobuf.generated.Empty;
import ru.otus.protobuf.generated.IdMessage;
import ru.otus.protobuf.generated.RemoteDBServiceGrpc.RemoteDBServiceStub;
import ru.otus.protobuf.generated.UserMessage;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;


@Component
public class DbServer {
    private static final Logger logger = LoggerFactory.getLogger(DbServer.class);
    private StreamObserver<UserMessage> streamSaveUser;
    private StreamObserver<UserMessage> streamFindAllUser;
    private StreamObserver<UserMessage> streamFindByIdUser;
    private final DBServiceUser dbServiceUser;
    private final RemoteDBServiceStub asyncStub;

    private class SaveUser implements StreamObserver<UserMessage> {
        @Override
        public void onNext(UserMessage value) {
            logger.info("got message:'SaveUser'{}", value);
            long id = dbServiceUser.saveUser(new User(value.getId(), value.getName()));
            UserMessage userMessage = UserMessage.newBuilder()
                    .setId(id)
                    .setName(value.getName())
                    .build();
            streamSaveUser.onNext(userMessage);
        }

        @Override
        public void onError(Throwable t) {
            logger.info("got message:'error SaveUser'{}", t);
        }

        @Override
        public void onCompleted() {
            logger.info("got message:'completed SaveUser'");
        }
    }

    private class FindAllUser implements StreamObserver<Empty> {

        @Override
        public void onNext(Empty value) {
            logger.info("got message:'FindAllUser'{}", value);
            List<User> users = dbServiceUser.findAll();
            for (var user : users) {
                streamFindAllUser.onNext(UserMessage.newBuilder()
                        .setId(user.getId())
                        .setName(user.getName())
                        .build());
            }
            streamFindAllUser.onNext(UserMessage.newBuilder().build());
        }

        @Override
        public void onError(Throwable t) {
            logger.info("got message:'error FindAllUser'{}", t);
        }

        @Override
        public void onCompleted() {
            logger.info("got message:'completed FindAllUser'{}");
        }
    }

    private class FindByIdUser implements StreamObserver<IdMessage> {
        @Override
        public void onNext(IdMessage value) {
            logger.info("got message:'FindByIdUser'{}", value);
            Optional<User> user = dbServiceUser.getUser(value.getId());
            user.ifPresentOrElse(user0 -> {
                        UserMessage userMessage = UserMessage.newBuilder()
                                .setId(user0.getId())
                                .setName(user0.getName())
                                .build();
                        streamFindByIdUser.onNext(userMessage);
                    },
                    () -> streamFindByIdUser.onNext(UserMessage.newBuilder().build()));
        }

        @Override
        public void onError(Throwable t) {
            logger.info("got message:'error FindByIdUser'{}", t);

        }

        @Override
        public void onCompleted() {
            logger.info("got message:'completed FindByIdUser'");
        }
    }


    public DbServer(DBServiceUser dbServiceUser, RemoteDBServiceStub asyncStub) {
        this.dbServiceUser = dbServiceUser;
        this.asyncStub = asyncStub;
    }

    @PostConstruct
    public void start() {
        UserMessage userMessage = UserMessage.newBuilder().build();
        streamSaveUser = asyncStub.saveUser(new SaveUser());
        streamSaveUser.onNext(userMessage);
        streamFindAllUser = asyncStub.findAllUsers(new FindAllUser());
        streamFindAllUser.onNext(userMessage);
        streamFindByIdUser = asyncStub.findByIdUser(new FindByIdUser());
        streamFindByIdUser.onNext(userMessage);
    }
}
