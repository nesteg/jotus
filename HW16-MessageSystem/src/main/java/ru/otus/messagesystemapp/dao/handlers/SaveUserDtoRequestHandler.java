package ru.otus.messagesystemapp.dao.handlers;

import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;
import ru.otus.dto.UserDto;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;
import ru.otus.messagesystem.message.MessageHelper;

import java.util.Optional;

public class SaveUserDtoRequestHandler implements RequestHandler<UserDto> {
    private DBServiceUser dbserviceUser;

    public SaveUserDtoRequestHandler(DBServiceUser dbService) {
        this.dbserviceUser = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {

        UserDto userDto = MessageHelper.getPayload(msg);
        long userId = dbserviceUser.saveUser(new User(userDto.getId(),userDto.getName()));
        return Optional.of(MessageBuilder.buildReplyMessage(msg, new UserDto(userId,userDto.getName())));
    }
}
