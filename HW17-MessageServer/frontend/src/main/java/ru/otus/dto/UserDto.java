package ru.otus.dto;



public class UserDto  {

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