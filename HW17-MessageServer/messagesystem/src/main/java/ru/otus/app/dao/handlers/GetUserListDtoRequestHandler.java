package ru.otus.app.dao.handlers;


import ru.otus.app.dao.mediator.BaseMediator;
import ru.otus.app.dao.mediator.GetUserList;
import ru.otus.dto.UserListDto;
import ru.otus.core.RequestHandler;
import ru.otus.message.Message;
import ru.otus.message.MessageBuilder;
import ru.otus.message.MessageHelper;
import ru.otus.protobuf.generated.Empty;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class GetUserListDtoRequestHandler implements RequestHandler<UserListDto> {

    BaseMediator mediator;

    public GetUserListDtoRequestHandler(BaseMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        UserListDto userListDto = new UserListDto();
        GetUserList getUserList = new GetUserList();
        MessageHelper.getPayload(msg);

        try {
            getUserList = mediator.takeGetUserList();
            getUserList.getResponse().onNext(Empty.newBuilder().build());
            userListDto =  getUserList.getFuture().get();
        } catch (InterruptedException | ExecutionException e) {

        }
        getUserList.setFuture(new CompletableFuture<>());
        mediator.addUserList(getUserList.getKey(),getUserList);
        return Optional.of(MessageBuilder.buildReplyMessage(msg, userListDto));
    }
}
