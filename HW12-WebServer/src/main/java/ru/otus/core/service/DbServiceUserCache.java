package ru.otus.core.service;

import ru.otus.core.model.User;
import ru.otus.core.cachehw.HwCache;

import java.util.List;
import java.util.Optional;


public class DbServiceUserCache implements DBServiceUser {
    private final DBServiceUser dbServiceUser;
    private final HwCache<String, User> cache;

    public DbServiceUserCache(DBServiceUser decor, HwCache<String, User> cache) {
        this.dbServiceUser = decor;
        this.cache = cache;
    }

    @Override
    public long saveUser(User user) {
        long id = dbServiceUser.saveUser(user);
        String idCache = String.valueOf(id);
        cache.put(idCache, user);
        return id;
    }

    @Override
    public Optional<User> getUser(long id) {
        String idCache = String.valueOf(id);
        User user = cache.get(idCache);
        if (user != null) {
            return Optional.of(user);
        }
        var dbUser = dbServiceUser.getUser(id);
        dbUser.ifPresent(u -> cache.put(idCache, u));
        return dbUser;
    }

    @Override
    public List<User> findAll() {
        List<User> users = cache.getAll();
        if (!users.isEmpty()) {
            return users;
        } else {
            users = dbServiceUser.findAll();
            users.forEach(user -> cache.put(String.valueOf(user.getId()), user));
            return users;
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        Optional<User> userCache = cache.getAll().stream().filter(u -> u.getLogin() == login).findFirst();
        Optional<User> user = userCache.or(() -> {
            var dbUser = dbServiceUser.findByLogin(login);
            dbUser.ifPresent(u -> cache.put(String.valueOf(u.getId()), u));
            return dbUser;
        });
        return user;
    }
}
