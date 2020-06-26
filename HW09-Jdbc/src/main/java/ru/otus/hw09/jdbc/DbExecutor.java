package ru.otus.hw09.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface DbExecutor<T> {

    long executeInsert(Connection connection, String sql, List<String> params) throws SQLException;

    Optional<T> executeSelect(Connection connection, String sql, long id, Function<ResultSet, T> rsHandler) throws SQLException;

    void executeUpdate(Connection connection, String sql, List<String> params) throws SQLException;
}
