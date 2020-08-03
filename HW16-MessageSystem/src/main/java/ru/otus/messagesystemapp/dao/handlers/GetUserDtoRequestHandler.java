package ru.otus.messagesystemapp.dao.handlers;

import ru.otus.core.service.DBServiceUser;
import ru.otus.dto.UserDto;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;
import ru.otus.messagesystem.message.MessageHelper;

import java.util.Optional;

public class GetUserDtoRequestHandler implements RequestHandler<UserDto> {
    private DBServiceUser dbserviceUser;

    public GetUserDtoRequestHandler(DBServiceUser dbService) {
        this.dbserviceUser = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {

        UserDto userDto = MessageHelper.getPayload(msg);
        UserDto user = dbserviceUser
                .getUser(userDto.getId())
                .map(u->new UserDto(u.getId(),u.getName()))
                .orElse(new UserDto());
        return Optional.of(MessageBuilder.buildReplyMessage(msg, user));
    }
}
