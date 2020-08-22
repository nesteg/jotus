package ru.otus.client;

import ru.otus.client.ResultDataType;

import java.util.function.Consumer;

public interface MessageCallback<T extends ResultDataType> extends Consumer<T> {
}
