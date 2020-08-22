package ru.otus.core;


import ru.otus.client.ResultDataType;
import ru.otus.message.Message;

import java.util.Optional;
import java.util.concurrent.ExecutionException;


public interface RequestHandler<T extends ResultDataType> {
    Optional<Message> handle(Message msg) throws ExecutionException, InterruptedException;
}
