package ru.otus.hw09.jdbc;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw09.core.model.User;
import ru.otus.hw09.core.model.Account;
import ru.otus.hw09.jdbc.mapper.Interfaces.EntityClassMetaData;
import ru.otus.hw09.jdbc.mapper.EntityClassMetaDataImpl;
import ru.otus.hw09.jdbc.mapper.Interfaces.EntitySQLMetaData;
import ru.otus.hw09.jdbc.mapper.EntitySQLMetaDataImpl;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class ExecutorDemo {
  private static final String URL = "jdbc:h2:mem:";
  private static final Logger logger = LoggerFactory.getLogger(ExecutorDemo.class);

  public static void main(String[] args)  {
    ExecutorDemo demo = new ExecutorDemo();

    try (Connection connection = DriverManager.getConnection(URL)) {
      connection.setAutoCommit(false);
      demo.createTable(connection);
      demo.testUser(connection);
      demo.testAccount(connection);
    }catch (SQLException e){
      logger.error(e.getMessage(), e);
    }
  }


  private void testUser(Connection connection)throws SQLException {
    try{
      EntityClassMetaData<User> meta = new EntityClassMetaDataImpl<>(User.class);
      EntitySQLMetaData querys = new EntitySQLMetaDataImpl(meta);
      DbExecutorImpl<User> executorUser = new DbExecutorImpl<>();
      long userId = executorUser.executeInsert(connection,  querys.getInsertSql(), Collections.singletonList("testUserName"));
      logger.info("created user:{}", userId);
      connection.commit();

      for(var i=0;true;i++) {
        Optional<User> user = executorUser.executeSelect(connection, querys.getSelectByIdSql(), userId, resultSet -> {
          try (ResultSet rs = resultSet) {
            rs.next();
            return new User(resultSet.getLong("id"), resultSet.getString("name"));
          } catch (SQLException e) {
            logger.error(e.getMessage(), e);
          }
          return null;
        });
        user.ifPresentOrElse(us -> logger.info("user: {}", us),
                () -> logger.info("user with Id={} not found", userId));
        if (i > 0) break;
        executorUser.executeUpdate(connection, querys.getUpdateSql(), List.of( "developerUserName","1"));
        connection.commit();
      }
    } catch (NoSuchMethodException e ) {
      logger.error(e.getMessage(), e);
    }catch (NullPointerException e){
      logger.error(e.getMessage(), e);
    }
  }


  private void testAccount(Connection connection) throws SQLException {
    try{
      EntityClassMetaData<Account> meta = new EntityClassMetaDataImpl<>(Account.class);
      EntitySQLMetaData querys = new EntitySQLMetaDataImpl(meta);
      DbExecutorImpl<Account> executor = new DbExecutorImpl<>();
      new Account(0,"",5);
      var names = meta.getFieldsWithoutId().stream().map(Field::getName).collect(Collectors.toCollection(ArrayList::new));
      ArrayList<String> params = new ArrayList<>(2);
      var index = names.indexOf("type");
      params.add(index,"leading");
      index = names.indexOf("rest");
      params.add(index,"5");
      long accountNo = executor.executeInsert(connection,  querys.getInsertSql(), params);
      logger.info("created account:{}", accountNo);
      connection.commit();

      for(var i=0;true;i++) {
        Optional<Account> account = executor.executeSelect(connection, querys.getSelectByIdSql(), accountNo, resultSet -> {
          try (ResultSet rs = resultSet) {
            rs.next();
            return new Account(resultSet.getLong("no"), resultSet.getString("type"),resultSet.getInt("rest"));
          } catch (SQLException e) {
            logger.error(e.getMessage(), e);
          }
          return null;
        });
        account.ifPresentOrElse(a -> logger.info("account: {}", a),
                () -> logger.info("account with no={} not found",accountNo));
        if (i > 0) break;
        executor.executeUpdate(connection, querys.getUpdateSql(), List.of( "basic","15","1"));
        connection.commit();
      }
    } catch (NoSuchMethodException e ) {
      logger.error(e.getMessage(), e);
    }catch (NullPointerException e){
      logger.error(e.getMessage(), e);
    }
  }


  private void createTable(Connection connection)  {
    try (PreparedStatement pst = connection.prepareStatement("create table user(id long auto_increment, name varchar(50))");
         PreparedStatement pstMore = connection.prepareStatement("create table account(no long auto_increment, type varchar(255),rest int)")
    ){
      pst.executeUpdate();
      pstMore.executeUpdate();
      logger.info("tables created");
    }catch (SQLException e){
      logger.error("can't tables created",e);
    }
  }


}
