package ru.otus.app.wsfront.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.client.CallbackRegistry;
import ru.otus.client.MessageCallback;
import ru.otus.client.ResultDataType;
import ru.otus.message.Message;
import ru.otus.message.MessageHelper;

import java.util.Optional;

public class ResponseHandler {
    private static final Logger logger = LoggerFactory.getLogger(ResponseHandler.class);
    private final CallbackRegistry callbackRegistry;

    public ResponseHandler(CallbackRegistry callbackRegistry) {
        this.callbackRegistry = callbackRegistry;
    }


    public Optional<Message> handle(Message msg) {
        try {
            MessageCallback<? extends ResultDataType> callback = callbackRegistry.getAndRemove(msg.getCallbackId());
            if (callback != null) {
                callback.accept(MessageHelper.getPayload(msg));
            } else {
                logger.error("callback for Id:{} not found", msg.getCallbackId());
            }
        } catch (Exception ex) {
            logger.error("msg:{}", msg, ex);
        }
        return Optional.empty();
    }

}
