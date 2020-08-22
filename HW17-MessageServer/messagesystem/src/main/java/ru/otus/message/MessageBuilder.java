package ru.otus.message;

import ru.otus.client.CallbackId;
import ru.otus.client.ResultDataType;

import java.util.Arrays;
import java.util.UUID;

public class MessageBuilder {
    private static final Message VOID_MESSAGE =
            new Message(new MessageId(UUID.randomUUID().toString()), null, null,
                    null, "voidTechnicalMessage", new byte[1],  null);

    private MessageBuilder() {
    }

    public static Message getVoidMessage() {
        return VOID_MESSAGE;
    }

    public static <T extends ResultDataType> Message buildMessage(String from, String to, MessageId sourceMessageId,
                                                                     T data, MessageType msgType) {
        return buildMessage(from, to, sourceMessageId, data, msgType, null);
    }

    public static <T extends ResultDataType> Message buildReplyMessage(Message message, T data) {
        MessageType msgType = Arrays.stream(MessageType.values())
                .filter(el->el.getName() == message.getType())
                .findFirst()
                .orElseThrow();
        return buildMessage(message.getTo(), message.getFrom(), message.getId(), data,
                msgType, message.getCallbackId());
    }

    private static <T extends ResultDataType> Message buildMessage(String from, String to, MessageId sourceMessageId,
                                                                     T data, MessageType msgType, CallbackId callbackId) {
        String id = UUID.randomUUID().toString();
        return new Message(new MessageId(id), from, to, sourceMessageId, msgType.getName(),
                Serializers.serialize(data), callbackId == null ? new CallbackId(id) : callbackId);
    }
}