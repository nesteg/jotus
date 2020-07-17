package ru.otus.controllers;

import org.springframework.web.bind.annotation.*;
import ru.otus.core.dto.UserDto;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;

@RestController
public class UserRestController {

    private final DBServiceUser usersService;

    public UserRestController(DBServiceUser usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/api/user/{id}")
    public User getUserById(@PathVariable(name = "id") long id) {
        return usersService.getUser(id).orElse(new User());
    }


    @PostMapping("/api/user")
    public User saveUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
         usersService.saveUser(user);
         return user;
    }
}
