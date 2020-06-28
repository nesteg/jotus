package ru.otus.hw09;

/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar ./HW09-Json/build/libs/Otus-0.1.jar
 *
 * To build:
 * ./gradlew build
 */



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw09.core.dao.UserDao;
import ru.otus.hw09.core.model.Account;
import ru.otus.hw09.core.service.DBServiceUser;
import ru.otus.hw09.core.service.DbServiceUserImpl;
import ru.otus.hw09.jdbc.DbExecutorImpl;
import ru.otus.hw09.h2.DataSourceH2;
import ru.otus.hw09.core.model.User;
import ru.otus.hw09.jdbc.dao.UserDaoJdbcMapper;
import ru.otus.hw09.jdbc.mapper.*;
import ru.otus.hw09.jdbc.mapper.Interfaces.EntityClassMetaData;
import ru.otus.hw09.jdbc.mapper.Interfaces.EntitySQLMetaData;
import ru.otus.hw09.jdbc.mapper.Interfaces.JdbcMapper;
import ru.otus.hw09.jdbc.sessionmanager.SessionManagerJdbc;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class DbServiceDemo {
    private static final Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) throws Exception {
        DataSource dataSource = new DataSourceH2();
        DbServiceDemo demo = new DbServiceDemo();
        demo.createTables(dataSource);
        demo.testUser(dataSource);
        demo.testAccount(dataSource);
    }

    private void testUser(DataSource dataSource) {
        try {
            SessionManagerJdbc sessionManager = new SessionManagerJdbc(dataSource);
            DbExecutorImpl<User> dbExecutor = new DbExecutorImpl<>();
            EntityClassMetaData<User> meta = new EntityClassMetaDataImpl<>(User.class);
            EntitySQLMetaData querys = new EntitySQLMetaDataImpl(meta);
            JdbcMapper<User> userMapper = new JdbcMapperImpl<>(sessionManager, dbExecutor, meta, querys);
            UserDao userDao = new UserDaoJdbcMapper(sessionManager, userMapper);
            DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);
            long id = dbServiceUser.saveUser(new User(0, "dbServiceUser"));
            Optional<User> user = dbServiceUser.getUser(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private void testAccount(DataSource dataSource) {
        try {
            SessionManagerJdbc sessionManager = new SessionManagerJdbc(dataSource);
            DbExecutorImpl<Account> dbExecutor = new DbExecutorImpl<>();
            EntityClassMetaData<Account> meta = new EntityClassMetaDataImpl<>(Account.class);
            EntitySQLMetaData querys = new EntitySQLMetaDataImpl(meta);
            JdbcMapper<Account> accountMapper = new JdbcMapperImpl<>(sessionManager, dbExecutor, meta, querys);
            var account = new Account(0, "basic", 54);
            accountMapper.insert(account);
            logger.info("add account: {}", account);
            account.setNo(2);
            account.setRest(32);
            accountMapper.insertOrUpdate(account);
            logger.info("add account: {}", account);
            account.setNo(1);
            account.setType("leading");
            account.setRest(5);
            accountMapper.insertOrUpdate(account);
            accountMapper.findById(1, Account.class);
            logger.info("update account: {}", account);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    private void createTables(DataSource dataSource) throws SQLException {
        DbExecutorImpl<User> dbExecutor = new DbExecutorImpl<>();
        EntitySQLMetaData querys = new EntitySQLMetaDataImpl(null);
        try (Connection connection = dataSource.getConnection()){
            for(var query :querys.getInitSql()) {
                try (PreparedStatement pst = connection.prepareStatement(query)) {
                    pst.executeUpdate();
                }
            }
            logger.info("tables created");
        } catch (SQLException e) {
            logger.error("can't tables created", e);
        }

    }
}
