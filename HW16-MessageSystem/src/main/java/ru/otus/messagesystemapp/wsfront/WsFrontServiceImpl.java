package ru.otus.messagesystemapp.wsfront;

import ru.otus.dto.UserDto;
import ru.otus.dto.UserListDto;
import ru.otus.messagesystem.client.MessageCallback;
import ru.otus.messagesystem.client.MsClient;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageType;

public class WsFrontServiceImpl implements WsFrontService{

    private final MsClient msClient;
    private final String daoServiceClientName;

    public WsFrontServiceImpl(MsClient msClient , String daoServiceClientName) {
        this.msClient = msClient;
        this.daoServiceClientName = daoServiceClientName;
    }

    @Override
    public void getUser(long userId, MessageCallback<UserDto> dataConsumer) {
        Message outMsg = msClient.produceMessage(daoServiceClientName, new UserDto(userId,null),
                MessageType.USER_DATA, dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public void getAllUsers(UserListDto userListDto, MessageCallback<UserListDto> dataConsumer) {
        Message outMsg = msClient.produceMessage(daoServiceClientName, new UserListDto(),
                MessageType.USER_LIST_DATA, dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public void saveUser(UserDto userDto, MessageCallback<UserDto> dataConsumer) {
        Message outMsg = msClient.produceMessage(daoServiceClientName, userDto,
                MessageType.USER_SAVE_DATA, dataConsumer);
        msClient.sendMessage(outMsg);
    }
}
