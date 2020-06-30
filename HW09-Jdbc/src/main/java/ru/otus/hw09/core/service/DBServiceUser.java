package ru.otus.hw09.core.service;

import ru.otus.hw09.core.model.User;

import java.util.Optional;

public interface DBServiceUser {

  long saveUser(User user);

  Optional<User> getUser(long id);

}
