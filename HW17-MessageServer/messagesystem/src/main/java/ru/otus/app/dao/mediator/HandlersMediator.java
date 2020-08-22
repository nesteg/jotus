package ru.otus.app.dao.mediator;

import ru.otus.dto.UserDto;
import ru.otus.dto.UserListDto;

import java.util.Map;
import java.util.concurrent.*;

public class HandlersMediator implements BaseMediator {

    private final BlockingQueue<SaveUser> saveUserQueue = new ArrayBlockingQueue<>(2);
    private final Map<String, CompletableFuture<UserDto>> saveUserMap = new ConcurrentHashMap<>();

    private final BlockingQueue<GetUserList> userListQueue = new ArrayBlockingQueue<>(2);
    private final Map<String, CompletableFuture<UserListDto>> userListMap = new ConcurrentHashMap<>();

    private final BlockingQueue<GetUser> getUserQueue = new ArrayBlockingQueue<>(2);
    private final Map<String, CompletableFuture<UserDto>> getUserMap = new ConcurrentHashMap<>();

    @Override
    public void addSaveUser(String key,SaveUser saveUser) {
           saveUserMap.put(key,saveUser.getFuture());
           saveUserQueue.add(saveUser);
    }

    @Override
    public SaveUser takeSaveUser() throws InterruptedException {
        return saveUserQueue.take();
    }

    @Override
    public void completeSaveUser(String key,UserDto userDto) {
        var saveUser = saveUserMap.get(key);
        saveUser.complete(userDto);
    }

    @Override
    public void addUserList(String key, GetUserList getUserList) {
        userListMap.put(key,getUserList.getFuture());
        userListQueue.add(getUserList);
    }

    @Override
    public GetUserList takeGetUserList() throws InterruptedException {
        return userListQueue.take();
    }

    @Override
    public void completeGetUserList(String key, UserListDto userListDto) {
        var getUserList = userListMap.get(key);
        getUserList.complete( userListDto);
    }

    @Override
    public void addGetUser(String key, GetUser getUser) {
        getUserMap.put(key,getUser.getFuture());
        getUserQueue.add(getUser);
    }

    @Override
    public GetUser takeGetUser() throws InterruptedException {
        return getUserQueue.take();
    }

    @Override
    public void completeGetUser(String key, UserDto userDto) {
        var getUser = getUserMap.get(key);
        getUser.complete(userDto);
    }


}
