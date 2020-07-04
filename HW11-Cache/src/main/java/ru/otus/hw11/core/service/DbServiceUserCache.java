package ru.otus.hw11.core.service;

import ru.otus.hw11.core.model.User;
import ru.otus.hw11.core.cachehw.HwCache;

import java.util.Optional;

public class DbServiceUserCache implements DBServiceUser {
    DBServiceUser dbServiceUser;
    HwCache<Long,User> cache;

    public DbServiceUserCache(DBServiceUser decor, HwCache<Long,User> cache){
        this.dbServiceUser = decor;
        this.cache = cache;
    }

    @Override
    public long saveUser(User user) {
        long id = dbServiceUser.saveUser(user);
        Long idCache = (long)id;
        cache.put(idCache,user);
        return id;
    }

    @Override
    public Optional<User> getUser(long id) {
        User user = cache.get(id);
        if (user == null){
           return dbServiceUser.getUser(id);
        }
        return Optional.of(user);
    }
}
