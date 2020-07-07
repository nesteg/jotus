package ru.otus.services;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.UserDaoException;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;
import ru.otus.core.service.DbServiceException;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.List;

public class DBInitializationImpl implements DBInitialization {
    private static Logger logger = LoggerFactory.getLogger(UserDaoHibernate.class);

    private final DBServiceUser serviceUser;

    public DBInitializationImpl(DBServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @Override
    public void Init() {
        try {
            List.of(new User(1L, "Крис Гир", "user1", "11111"),
                    new User(2L, "Ая Кэш", "user2", "11111"),
                    new User(3L, "Десмин Боргес", "user3", "11111"),
                    new User(4L, "Кетер Донохью", "user4", "11111"),
                    new User(5L, "Стивен Шнайдер", "user5", "11111"),
                    new User(6L, "Джанет Вэрни", "user6", "11111"),
                    new User(7L, "Брэндон Смит", "user7", "11111"))
                    .forEach(user -> serviceUser.saveUser(user));
        } catch (DbServiceException ignored) {
        }
    }
}
