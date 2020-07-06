package ru.otus.dao;

import ru.otus.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> findById(long id);
    Optional<User> findRandomUser();
    Optional<User> findByLogin(String login);
    List<User>     findAll();
    long           saveUser(User user);
}