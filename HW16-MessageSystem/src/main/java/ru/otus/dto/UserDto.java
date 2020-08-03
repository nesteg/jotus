package ru.otus.dto;

import ru.otus.messagesystem.client.ResultDataType;

public class UserDto extends ResultDataType {

    private Long id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public UserDto() {
    }

    public UserDto(Long id,String name) {
        this.id = id;
        this.name = name;
    }
}