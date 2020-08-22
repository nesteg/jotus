package ru.otus.dto;

import java.util.ArrayList;
import java.util.List;

public class UserListDto  {

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
