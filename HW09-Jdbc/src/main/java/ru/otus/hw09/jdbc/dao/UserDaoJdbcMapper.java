package ru.otus.hw09.jdbc.dao;

import ru.otus.hw09.core.dao.UserDao;
import ru.otus.hw09.core.model.User;
import ru.otus.hw09.core.sessionmanager.SessionManager;
import ru.otus.hw09.jdbc.mapper.Interfaces.JdbcMapper;
import ru.otus.hw09.jdbc.sessionmanager.SessionManagerJdbc;

import java.sql.SQLException;
import java.util.Optional;

public class UserDaoJdbcMapper implements UserDao {

    private final SessionManagerJdbc sessionManager;
    private final JdbcMapper<User> jdbc;

    public UserDaoJdbcMapper(SessionManagerJdbc sessionManager, JdbcMapper<User> jdbc) {
        this.sessionManager = sessionManager;
        this.jdbc = jdbc;
    }

    @Override
    public Optional<User> findById(long id) {
        var user = jdbc.findById(id, User.class);
        if (user != null){
            return Optional.ofNullable(user);
        }else {
            return Optional.empty();
        }
    }

    @Override
    public long insertUser(User user) {
        jdbc.insert(user);
        return user.getId();
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
