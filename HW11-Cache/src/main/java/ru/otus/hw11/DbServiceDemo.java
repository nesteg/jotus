package ru.otus.hw11;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw11.core.dao.UserDao;
import ru.otus.hw11.core.model.AddressDataSet;
import ru.otus.hw11.core.model.PhoneDataSet;
import ru.otus.hw11.hibernate.dao.UserDaoHibernate;
import ru.otus.hw11.core.service.DBServiceUser;
import ru.otus.hw11.core.service.DbServiceUserImpl;
import ru.otus.hw11.core.model.User;
import ru.otus.hw11.hibernate.HibernateUtils;
import ru.otus.hw11.hibernate.sessionmanager.SessionManagerHibernate;


import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class DbServiceDemo {


    public static void main(String[] args) {
        // Все главное см в тестах

        System.out.println("DbServiceDemo with Cache");

    }
}
