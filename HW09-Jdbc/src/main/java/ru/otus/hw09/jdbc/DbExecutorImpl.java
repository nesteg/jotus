package ru.otus.hw09.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class DbExecutorImpl<T> implements DbExecutor<T> {
    private static final Logger logger = LoggerFactory.getLogger(DbExecutorImpl.class);

    @Override
    public long executeInsert(Connection connection, String sql, List<Object> params) throws SQLException {
        if (params==null) throw new NullPointerException("params is null in insert");
        Savepoint savePoint = connection.setSavepoint("savePointName");
        try (PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int idx = 0; idx < params.size(); idx++) {
                pst.setObject(idx + 1, params.get(idx));
            }
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            connection.rollback(savePoint);
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public Optional<T> executeSelect(Connection connection, String sql, long id, Function<ResultSet, T> rsHandler) throws SQLException {
        if (rsHandler==null) throw new NullPointerException("handler is null in select");
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                return Optional.ofNullable(rsHandler.apply(rs));
            }
        }
    }

    @Override
    public void executeUpdate(Connection connection, String sql, List<Object> params) throws SQLException {
        if (params==null) throw new NullPointerException("params is null in update");
        Savepoint savePoint = connection.setSavepoint("savePointName");
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            for (int idx = 0; idx < params.size(); idx++) {
                pst.setObject(idx + 1, params.get(idx));
            }

            pst.executeUpdate();
        } catch (SQLException ex) {
            connection.rollback(savePoint);
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public void execute(Connection connection, String sql) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }
}
