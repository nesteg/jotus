package ru.otus.hw11.core.service;

import ru.otus.hw11.core.model.User;
import ru.otus.hw11.core.cachehw.HwCache;

import java.util.Optional;

public class DbServiceUserCache implements DBServiceUser {
    private final DBServiceUser dbServiceUser;
    private final HwCache<String,User> cache;

    public DbServiceUserCache(DBServiceUser decor, HwCache<String,User> cache){
        this.dbServiceUser = decor;
        this.cache = cache;
    }

    @Override
    public long saveUser(User user) {
        long id = dbServiceUser.saveUser(user);
        String idCache = String.valueOf(id);
        cache.put(idCache,user);
        return id;
    }

    @Override
    public Optional<User> getUser(long id) {
        String idCache = String.valueOf(id);
        User user = cache.get(idCache);
        if (user != null){
            return Optional.of(user);
        }
        var dbUser = dbServiceUser.getUser(id);
        dbUser.ifPresent(u -> cache.put(idCache, u));
        return dbUser;

    }
}
