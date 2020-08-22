package ru.otus.app.wsfront.handlers;

import ru.otus.dto.UserListDto;
import ru.otus.core.RequestHandler;
import ru.otus.client.CallbackRegistry;
import ru.otus.message.Message;

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
