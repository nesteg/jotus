package ru.otus.app.dao.mediator;

import ru.otus.dto.UserDto;
import ru.otus.dto.UserListDto;

public interface BaseMediator {

    void addSaveUser(String key, SaveUser saveUser);

    SaveUser takeSaveUser() throws InterruptedException;

    void completeSaveUser(String key, UserDto userDto);

    void addUserList(String key, GetUserList getUserList);

    GetUserList takeGetUserList() throws InterruptedException;

    void completeGetUserList(String key, UserListDto userListDto);

    void addGetUser(String key, GetUser getUser);

    GetUser takeGetUser() throws InterruptedException;

    void completeGetUser(String key, UserDto userDto);

}
