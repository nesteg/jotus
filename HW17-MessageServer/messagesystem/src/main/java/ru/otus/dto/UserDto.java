package ru.otus.dto;

import  ru.otus.client.ResultDataType;
import ru.otus.protobuf.generated.UserMessage;

public class UserDto extends ResultDataType {

    private final UserMessage userMessage;
    private final long id;

    public UserDto() {
       userMessage = UserMessage.newBuilder().build();
       id = 0;
    }

    public UserDto(long id) {
        this.id  = id;
        this.userMessage = null;
    }

    public UserDto(UserMessage userMessage, long id) {
        this.userMessage = userMessage;
        this.id = id;
    }

    public UserDto(UserMessage userMessage) {
        this.userMessage = userMessage;
        this.id = 0;
    }

    public Long getId (){
        return userMessage == null ? id:userMessage.getId();
    }

    public String getName (){
        return userMessage.getName();
   }


}
