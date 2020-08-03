package ru.otus.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.otus.core.cachehw.HwCache;
import ru.otus.core.cachehw.MyCache;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.AddressDataSet;
import ru.otus.core.model.PhoneDataSet;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;
import ru.otus.core.service.DbServiceUserCache;
import ru.otus.core.service.DbServiceUserImpl;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;




@Configuration
public class AppConfig {

    @Bean
    public <K, V> HwCache<K, V> cache() {
        return new MyCache<>();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return HibernateUtils.buildSessionFactory("hibernate.cfg.xml",
                User.class,
                AddressDataSet.class,
                PhoneDataSet.class
        );
    }

    @Bean
    public UserDao userDao(SessionFactory session) {
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(session);
        return new UserDaoHibernate(sessionManager);
    }


    @Bean
    public DBServiceUser serviceUserWithCache(UserDao userDao,
                                              HwCache<String, User> cache) {
        var serviceUser = new DbServiceUserImpl(userDao);
        return new DbServiceUserCache(serviceUser, cache);
    }



}
