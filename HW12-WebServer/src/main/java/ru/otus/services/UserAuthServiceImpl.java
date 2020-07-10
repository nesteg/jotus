package ru.otus.services;

import ru.otus.core.service.DBServiceUser;

public class UserAuthServiceImpl implements UserAuthService {

    private final DBServiceUser serviceUser;

    public UserAuthServiceImpl(DBServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return serviceUser.findByLogin(login)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

}
