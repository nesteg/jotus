package ru.otus.app.wsfront;

import io.grpc.stub.StreamObserver;
import ru.otus.client.MsClient;
import ru.otus.dto.UserDto;
import ru.otus.dto.UserListDto;
import ru.otus.message.Message;
import ru.otus.message.MessageType;
import ru.otus.protobuf.generated.Empty;
import ru.otus.protobuf.generated.IdMessage;
import ru.otus.protobuf.generated.RemoteMsServiceGrpc.RemoteMsServiceImplBase;
import ru.otus.protobuf.generated.UserMessage;

public class WsFrontServiceImpl extends RemoteMsServiceImplBase {

    private final MsClient msClient;
    private final String daoServiceClientName;

    public WsFrontServiceImpl(MsClient msClient, String daoServiceClientName) {
        this.msClient = msClient;
        this.daoServiceClientName = daoServiceClientName;
    }

    @Override
    public void findAllUsers(Empty request, StreamObserver<UserMessage> responseObserver) {
        Message outMsg = msClient.produceMessage(daoServiceClientName, new UserListDto(),
                MessageType.USER_LIST_DATA, userListDto ->
                {
                    for (var userDto : userListDto.getUsers()) {
                        responseObserver.onNext(UserMessage.newBuilder()
                                .setId(userDto.getId())
                                .setName(userDto.getName())
                                .build());
                    }
                    responseObserver.onCompleted();

                });
        msClient.sendMessage(outMsg);
    }

    @Override
    public void saveUser(UserMessage request, StreamObserver<UserMessage> responseObserver) {

        Message outMsg = msClient.produceMessage(daoServiceClientName, new UserDto(request),
                MessageType.USER_SAVE_DATA, userDto ->
                {
                    responseObserver.onNext(UserMessage.newBuilder()
                            .setId(userDto.getId())
                            .setName(userDto.getName())
                            .build());
                    responseObserver.onCompleted();

                });
        msClient.sendMessage(outMsg);
    }

    @Override
    public void findByIdUser(IdMessage request, StreamObserver<UserMessage> responseObserver) {
        //long userId, MessageCallback<UserDto> dataConsumer
        Message outMsg = msClient.produceMessage(daoServiceClientName, new UserDto(request.getId()),
                MessageType.USER_DATA, userDto ->
                {
                    if (userDto == null) {
                        responseObserver.onNext(UserMessage
                                .newBuilder()
                                .build());
                    } else {
                        responseObserver.onNext(UserMessage.newBuilder()
                                .setId(userDto.getId())
                                .setName(userDto.getName())
                                .build());
                    }
                    responseObserver.onCompleted();
                });
        msClient.sendMessage(outMsg);
    }
}
