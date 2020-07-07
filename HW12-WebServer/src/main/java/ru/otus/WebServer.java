package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.SessionFactory;
import ru.otus.core.cachehw.MyCache;
import ru.otus.core.dao.UserDao;
import ru.otus.core.service.DBServiceUser;
import ru.otus.core.service.DbServiceUserCache;
import ru.otus.core.cachehw.HwCache;
import ru.otus.core.service.DbServiceUserImpl;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.core.model.AddressDataSet;
import ru.otus.core.model.PhoneDataSet;
import ru.otus.core.model.User;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;
import ru.otus.server.UsersWebServer;
import ru.otus.server.UsersWebServerWithFilterBasedSecurity;
import ru.otus.services.*;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница пользователей
    http://localhost:8080/users

    // REST сервис
    http://localhost:8080/api/user/3
*/
public class WebServer {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml",
                User.class,
                AddressDataSet.class,
                PhoneDataSet.class
        );
        HwCache<String,User> cache = new MyCache<>();
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser serviceUserImpl = new DbServiceUserImpl(userDao);
        DBServiceUser serviceUser  = new DbServiceUserCache(serviceUserImpl,cache);
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(serviceUser);
        DBInitialization dbInitialization = new DBInitializationImpl(serviceUser);

        UsersWebServer usersWebServer = new UsersWebServerWithFilterBasedSecurity(WEB_SERVER_PORT,
                authService, serviceUser, gson, templateProcessor,dbInitialization);

        usersWebServer.start();
        usersWebServer.join();
    }
}
