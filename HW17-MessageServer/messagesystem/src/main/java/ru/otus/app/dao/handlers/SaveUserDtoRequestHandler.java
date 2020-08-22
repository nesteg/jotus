package ru.otus.app.dao.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.app.dao.mediator.BaseMediator;
import ru.otus.app.dao.mediator.SaveUser;
import ru.otus.dto.UserDto;
import ru.otus.core.RequestHandler;
import ru.otus.message.Message;
import ru.otus.message.MessageBuilder;
import ru.otus.message.MessageHelper;
import ru.otus.protobuf.generated.UserMessage;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class SaveUserDtoRequestHandler implements RequestHandler<UserDto> {
    private static final Logger logger = LoggerFactory.getLogger(SaveUserDtoRequestHandler.class);
    private BaseMediator mediator;

    public SaveUserDtoRequestHandler( BaseMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public Optional<Message> handle(Message msg)  {
        UserDto userDtoOut = new UserDto();
        SaveUser saveUser = new SaveUser();
        UserDto userDto = MessageHelper.getPayload(msg);
        UserMessage userMessage = UserMessage.newBuilder().setId(userDto.getId()).setName(userDto.getName()).build();
        try {
             saveUser =  mediator.takeSaveUser();
             saveUser.getResponse().onNext(userMessage);
             userDtoOut = saveUser.getFuture().get();
        } catch (InterruptedException | ExecutionException e) {

        }
        saveUser.setFuture(new CompletableFuture<>());
        mediator.addSaveUser(saveUser.getKey(),saveUser);
        return Optional.ofNullable(MessageBuilder.buildReplyMessage(msg, userDtoOut));
    }

}
