package ru.otus.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;
import ru.otus.core.service.DbServiceException;
import ru.otus.hibernate.dao.UserDaoHibernate;

import java.util.List;

public class DBInitializationImpl implements DBInitialization {
    private static Logger logger = LoggerFactory.getLogger(UserDaoHibernate.class);

    private final DBServiceUser serviceUser;

    public DBInitializationImpl(DBServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @Override
    public void init() {
        try {
            List.of(new User(1L, "Крис Гир"),
                    new User(2L, "Ая Кэш"),
                    new User(3L, "Десмин Боргес"),
                    new User(4L, "Кетер Донохью"),
                    new User(5L, "Стивен Шнайдер"),
                    new User(6L, "Джанет Вэрни"),
                    new User(7L, "Брэндон Смит"))
                    .forEach(user -> serviceUser.saveUser(user));
        } catch (DbServiceException ignored) {
        }
    }
}
