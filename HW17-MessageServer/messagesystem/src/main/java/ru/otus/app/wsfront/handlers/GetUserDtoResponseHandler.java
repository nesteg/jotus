package ru.otus.app.wsfront.handlers;

import ru.otus.dto.UserDto;
import ru.otus.core.RequestHandler;
import ru.otus.client.CallbackRegistry;
import ru.otus.message.Message;
import java.util.Optional;


public class GetUserDtoResponseHandler extends ResponseHandler implements RequestHandler<UserDto> {

    public GetUserDtoResponseHandler(CallbackRegistry callbackRegistry) {
        super(callbackRegistry);
    }

    @Override
    public Optional<Message> handle(Message msg) {
        return super.handle(msg);
    }
}
