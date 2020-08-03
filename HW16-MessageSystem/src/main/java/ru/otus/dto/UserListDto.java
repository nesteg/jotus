package ru.otus.dto;


import ru.otus.messagesystem.client.ResultDataType;

import java.util.ArrayList;
import java.util.List;

public class UserListDto extends ResultDataType {

     private List<UserDto> users;

     public UserListDto(){
         this.users = new ArrayList<>();
     };

     public UserListDto(List<UserDto> users){
         this.users = users;
     }

    public List<UserDto> getUsers(){
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserListDto{" +
                "users=" + users +
                '}';
    }
}
