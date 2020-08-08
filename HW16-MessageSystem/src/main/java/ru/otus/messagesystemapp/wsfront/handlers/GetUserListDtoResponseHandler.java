package ru.otus.messagesystemapp.wsfront.handlers;

import ru.otus.dto.UserListDto;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.client.CallbackRegistry;
import ru.otus.messagesystem.message.Message;

import java.util.Optional;

public class GetUserListDtoResponseHandler extends ResponseHandler implements RequestHandler<UserListDto> {

    public GetUserListDtoResponseHandler(CallbackRegistry callbackRegistry) {
        super(callbackRegistry);
    }

    @Override
    public Optional<Message> handle(Message msg) {
        return super.handle(msg);
    }
}
