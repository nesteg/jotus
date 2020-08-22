package ru.otus.controllers;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import ru.otus.dto.UserDto;
import ru.otus.dto.UserListDto;
import ru.otus.protobuf.generated.Empty;
import ru.otus.protobuf.generated.IdMessage;
import ru.otus.protobuf.generated.RemoteMsServiceGrpc.RemoteMsServiceStub;
import ru.otus.protobuf.generated.UserMessage;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private RemoteMsServiceStub asyncStub;

    @MessageMapping("/message.getUsers")
    public void getUserList() {
        logger.info("got message:'getUsers'");
        List<UserDto> users = new ArrayList<>();
        StreamObserver<UserMessage> response = new StreamObserver<>() {
            @Override
            public void onNext(UserMessage value) {
                logger.info("got message:'getUser'{}", value);
                users.add(new UserDto(value.getId(), value.getName()));
            }

            @Override
            public void onError(Throwable t) {
                logger.info("got message:'error'", t);
            }

            @Override
            public void onCompleted() {
                logger.info("got message:'completed getUsers'");
                template.convertAndSend("/topic/response.users", new UserListDto(users));
            }
        };
        asyncStub.findAllUsers(Empty.newBuilder().build(), response);
    }

    @MessageMapping("/message.getUser.{id}")
    public void getUserById(@DestinationVariable Long id) {
        logger.info("got message:'getUserById' {}", id);
        StreamObserver<UserMessage> response = new StreamObserver<>() {
            @Override
            public void onNext(UserMessage value) {
                logger.info("got message:'getUserById'{}", value);
                if (value.hasField(value.getDescriptorForType().findFieldByName("id"))) {
                    template.convertAndSend("/topic/response.user", new UserDto(value.getId(), value.getName()));
                } else {
                    template.convertAndSend("/topic/response.user", new UserDto());
                }
            }

            @Override
            public void onError(Throwable t) {
                logger.info("got message:'error getUserById'", t);
            }

            @Override
            public void onCompleted() {
                logger.info("got message:'completed'");
            }
        };

        asyncStub.findByIdUser(IdMessage.newBuilder()
                .setId(id)
                .build(), response);
    }

    @MessageMapping("/message.saveUser")
    public void saveUser(UserDto userDto) {
        logger.info("got message:'saveUser'{}", userDto);
        StreamObserver<UserMessage> response = new StreamObserver<>() {
            @Override
            public void onNext(UserMessage value) {
                logger.info("got message:'savedUser'{}", value);
                template.convertAndSend("/topic/response.user", new UserDto(value.getId(), value.getName()));
            }

            @Override
            public void onError(Throwable t) {
                logger.info("got message:'error saveUser'", t);
            }

            @Override
            public void onCompleted() {
                logger.info("got message:'completed saveUser'");
            }
        };

        asyncStub.saveUser(UserMessage.newBuilder()
                .setId(userDto.getId())
                .setName(userDto.getName())
                .build(), response);
    }
}
