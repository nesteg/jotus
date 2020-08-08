package ru.otus.messagesystemapp.wsfront;

import ru.otus.dto.UserDto;
import ru.otus.dto.UserListDto;
import ru.otus.messagesystem.client.MessageCallback;

public interface WsFrontService {

    void getUser(long userId, MessageCallback<UserDto> dataConsumer);
    void getAllUsers(UserListDto userListDto, MessageCallback<UserListDto> dataConsumer);
    void saveUser(UserDto userDto, MessageCallback<UserDto> dataConsumer);
}
