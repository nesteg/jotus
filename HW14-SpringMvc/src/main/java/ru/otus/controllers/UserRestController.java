package ru.otus.controllers;

import org.springframework.web.bind.annotation.*;
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
    public User saveUser(@RequestBody User user) {
         usersService.saveUser(user);
         return user;
    }
}
