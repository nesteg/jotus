package ru.otus.messagesystemapp.dao.handlers;

import ru.otus.core.service.DBServiceUser;
import ru.otus.dto.UserDto;
import ru.otus.dto.UserListDto;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;
import ru.otus.messagesystem.message.MessageHelper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GetUserListDtoRequestHandler implements RequestHandler<UserListDto> {

    private DBServiceUser dbserviceUser;

    public GetUserListDtoRequestHandler(DBServiceUser dbserviceUser) {
        this.dbserviceUser = dbserviceUser;
    }

    @Override
    public Optional<Message> handle(Message msg) {

        MessageHelper.getPayload(msg);
        List<UserDto> list = dbserviceUser.findAll()
                .stream()
                .map(e -> new UserDto(e.getId(), e.getName()))
                .collect(Collectors.toList());
        return Optional.of(MessageBuilder.buildReplyMessage(msg, new UserListDto(list)));
    }
}
