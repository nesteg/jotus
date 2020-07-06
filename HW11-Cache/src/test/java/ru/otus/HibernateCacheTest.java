package ru.otus;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw11.core.cachehw.MyCache;
import ru.otus.hw11.core.cachehw.HwCache;
import ru.otus.hw11.core.model.AddressDataSet;
import ru.otus.hw11.core.model.PhoneDataSet;
import ru.otus.hw11.core.model.User;
import ru.otus.hw11.hibernate.HibernateUtils;
import ru.otus.hw11.core.dao.UserDao;
import ru.otus.hw11.core.service.DBServiceUser;
import ru.otus.hw11.hibernate.dao.UserDaoHibernate;
import ru.otus.hw11.core.service.DbServiceUserImpl;
import ru.otus.hw11.core.service.DbServiceUserCache;
import ru.otus.hw11.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public  class HibernateCacheTest {
    private static final String HIBERNATE_CFG_XML_FILE_RESOURCE = "hibernate-test.cfg.xml";

    protected static final String TEST_USER_NAME = "Вася";
    private static final Logger logger = LoggerFactory.getLogger(HibernateCacheTest.class);

    protected SessionFactory sessionFactory;
    private DBServiceUser dbServiceUser;
    private DbServiceUserCache dbServiceUserCache;
    HwCache<String,User> cache;

    @BeforeEach
    public void setUp() {
        sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_XML_FILE_RESOURCE, User.class,
                AddressDataSet.class,
                PhoneDataSet.class);
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        dbServiceUser = new DbServiceUserImpl(userDao);
        cache = new MyCache<>();
        dbServiceUserCache = new DbServiceUserCache(dbServiceUser,cache);

    }

    @AfterEach
    void tearDown() {
        sessionFactory.close();
    }


    @Test
    @DisplayName(" проверка, что  с cache работает быстрее, чем с БД")
    void meterWithCacheUser() {
        User savedUser = buildDefaultUser();
        long id = dbServiceUserCache.saveUser(savedUser);
        long start = System.nanoTime();
        Optional<User> mayBeUser = dbServiceUser.getUser(id);
        long end = System.nanoTime();
        var duration = end - start;
        long startCache = System.nanoTime();
        Optional<User> mayBeUserCache = dbServiceUserCache.getUser(id);
        long endCache= System.nanoTime();
        var durationCache = endCache - startCache;
        assertThat(duration > durationCache).isTrue();

    }

    @Test
    @DisplayName(" проверка, что происходит сброс cache ")
    void flushCache() throws InterruptedException {
        User savedUser = buildDefaultUser();
        long id = dbServiceUserCache.saveUser(savedUser);
        while (getUserStatistics().getLoadCount() == 0) {
            System.gc();
            dbServiceUserCache.getUser(id);
            long id0 = savedUser.getId();
            savedUser= buildDefaultUser();
            savedUser.setId(id0+1);
            id = dbServiceUserCache.saveUser(savedUser);
        }
        assertThat(getUserStatistics().getLoadCount() > 0).isTrue();
    }

     protected User buildDefaultUser() {
        var userDefault = new User(0, TEST_USER_NAME);
        var address = new AddressDataSet(0,"Капитан очевидность");
        var phones = Arrays.asList(new PhoneDataSet(0,"345-67-89"),new PhoneDataSet(0,"345-77-89"));
        phones.forEach(phone->phone.setUser(userDefault));
        userDefault.setAddress(address);
        userDefault.setPhones(phones);
        return  userDefault;
    }

    protected void saveUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            saveUser(session, user);
        }
    }

    protected void saveUser(Session session, User user) {
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    protected User loadUser(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(User.class, id);
        }
    }

    protected EntityStatistics getUserStatistics() {
        Statistics stats = sessionFactory.getStatistics();
        return stats.getEntityStatistics(User.class.getName());
    }
}