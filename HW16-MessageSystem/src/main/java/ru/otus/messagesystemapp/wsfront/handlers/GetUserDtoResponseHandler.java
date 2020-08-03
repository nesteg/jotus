package ru.otus.messagesystemapp.wsfront.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.dto.UserDto;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.client.CallbackRegistry;
import ru.otus.messagesystem.client.MessageCallback;
import ru.otus.messagesystem.client.ResultDataType;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageHelper;

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
