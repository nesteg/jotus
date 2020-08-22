package ru.otus.app.dao.handlers;

import ru.otus.app.dao.mediator.BaseMediator;
import ru.otus.app.dao.mediator.GetUser;
import ru.otus.dto.UserDto;
import ru.otus.core.RequestHandler;
import ru.otus.message.Message;
import ru.otus.message.MessageBuilder;
import ru.otus.message.MessageHelper;
import ru.otus.protobuf.generated.IdMessage;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class GetUserDtoRequestHandler implements RequestHandler<UserDto> {
    private BaseMediator mediator;

    public GetUserDtoRequestHandler(BaseMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        UserDto userDtoOut = new UserDto();
        GetUser getUser = new GetUser();
        UserDto userDto = MessageHelper.getPayload(msg);
        IdMessage userMessage = IdMessage.newBuilder().setId(userDto.getId()).build();

        try {
            getUser = mediator.takeGetUser();
            getUser.getResponse().onNext(userMessage);
            userDtoOut =getUser.getFuture().get();
        } catch (InterruptedException | ExecutionException e) {

        }
        getUser.setFuture(new CompletableFuture<>());
        mediator.addGetUser(getUser.getKey(),getUser);
        return Optional.of(MessageBuilder.buildReplyMessage(msg, userDtoOut));
    }
}
