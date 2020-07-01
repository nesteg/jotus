package ru.otus.hw10;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw10.core.dao.UserDao;
import ru.otus.hw10.core.model.AddressDataSet;
import ru.otus.hw10.core.model.PhoneDataSet;
import ru.otus.hw10.hibernate.dao.UserDaoHibernate;
import ru.otus.hw10.core.service.DBServiceUser;
import ru.otus.hw10.core.service.DbServiceUserImpl;
import ru.otus.hw10.core.model.User;
import ru.otus.hw10.hibernate.HibernateUtils;
import ru.otus.hw10.hibernate.sessionmanager.SessionManagerHibernate;


import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class DbServiceDemo {
    private static Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) {
        // Все главное см в тестах

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml",
                                                                            User.class,
                                                                            AddressDataSet.class,
                                                                            PhoneDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

        var address = new AddressDataSet(0,"Капитан очевидность");
        var phones = Arrays.asList(new PhoneDataSet(0,"345-67-89"),new PhoneDataSet(0,"345-77-89"));
        var user = new User(0, "Вася");
        phones.forEach(phone->phone.setUser(user));
        user.setAddress(address);
        user.setPhones(phones);
        long id = dbServiceUser.saveUser(user);
        Optional<User> mayBeCreatedUser = dbServiceUser.getUser(id);
        var  u = mayBeCreatedUser.get();
        System.out.println(u.getAddress());
        var  updateUser = new User(1L, "А! Нет. Это же совсем не Вася");
        updateUser.setAddress(u.getAddress());

        id = dbServiceUser.saveUser(updateUser);
        Optional<User> mayBeUpdatedUser = dbServiceUser.getUser(id);
        var  cu = mayBeUpdatedUser.get();

        outputUserOptional("Created user", mayBeCreatedUser);
        outputUserOptional("Updated user", mayBeUpdatedUser);
        sessionFactory.close();
    }

    private static void outputUserOptional(String header, Optional<User> mayBeUser) {
        System.out.println("-----------------------------------------------------------");
        System.out.println(header);
        mayBeUser.ifPresentOrElse(System.out::println, () -> logger.info("User not found"));
    }
}
