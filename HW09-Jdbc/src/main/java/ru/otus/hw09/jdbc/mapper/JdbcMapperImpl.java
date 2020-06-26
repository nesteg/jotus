package ru.otus.hw09.jdbc.mapper;

import org.h2.command.dml.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw09.core.model.User;
import ru.otus.hw09.core.sessionmanager.SessionManagerException;
import ru.otus.hw09.jdbc.DbExecutorImpl;
import ru.otus.hw09.jdbc.dao.UserDaoJdbc;
import ru.otus.hw09.jdbc.mapper.Interfaces.EntityClassMetaData;
import ru.otus.hw09.jdbc.mapper.Interfaces.EntitySQLMetaData;
import ru.otus.hw09.jdbc.mapper.Interfaces.JdbcMapper;
import ru.otus.hw09.jdbc.sessionmanager.SessionManagerJdbc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JdbcMapperImpl<T> implements JdbcMapper<T> {
    private static final Logger logger = LoggerFactory.getLogger(JdbcMapperImpl.class);

    private final SessionManagerJdbc sessionManager;
    private final DbExecutorImpl<T> dbExecutor;
    private final EntityClassMetaData<T> meta;
    private final EntitySQLMetaData querys;

    public JdbcMapperImpl(SessionManagerJdbc sessionManager,
                          DbExecutorImpl<T> dbExecutor,
                          EntityClassMetaData<T> meta,
                          EntitySQLMetaData querys
                          ) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
        this.meta= meta;
        this.querys = querys;
    }

    @Override
    public void insert(T objectData)  {
        Object o = objectData;
        Connection connection;
        List<Field> fields = meta.getFieldsWithoutId();
        Boolean localBeginSession=false;
        try {
            var params = fields.stream().map(field -> {
                if (Modifier.isPrivate(field.getModifiers())){
                    field.setAccessible(true);
                }
                try {
                    return field.get(o).toString();
                } catch (IllegalAccessException e) {
                }
                return null;
            }).collect(Collectors.toList());
            try {
               connection = sessionManager.getCurrentSession().getConnection();
            }catch ( SessionManagerException e){
               sessionManager.beginSession();
               connection = sessionManager.getCurrentSession().getConnection();
               localBeginSession=true;
            }
            long id = dbExecutor.executeInsert(connection, querys.getInsertSql(), params);
            if(localBeginSession) {
                sessionManager.commitSession();
            }

            Field fldId = meta.getIdField();
            if (Modifier.isPrivate(fldId.getModifiers())){
                fldId.setAccessible(true);
            }
            try {
                fldId.set(o,id);
            } catch (IllegalAccessException e) {
            }
        } catch (SQLException e) {

        }catch (NullPointerException e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void update(T objectData) {
        Object o = objectData;
        Connection connection;
        Boolean localBeginSession=false;
        String paramId = null;
        List<Field> fields = meta.getFieldsWithoutId();
        var params = fields.stream().map(field -> {
            if (Modifier.isPrivate(field.getModifiers())){
                field.setAccessible(true);
            }
            try {
                return field.get(o).toString();
            } catch (IllegalAccessException e) {
                return null;
            }
        }).collect(Collectors.toList());
        var fieldId = meta.getIdField();
        if (Modifier.isPrivate(fieldId.getModifiers())){
            fieldId.setAccessible(true);
        }
        try {
            paramId =  fieldId.get(o).toString();
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
            paramId  = null;
        }
        params.add(paramId);
        try {
            try {
                connection = sessionManager.getCurrentSession().getConnection();
            }catch ( SessionManagerException e){
                sessionManager.beginSession();
                connection = sessionManager.getCurrentSession().getConnection();
                localBeginSession=true;
            }
            dbExecutor.executeUpdate(connection, querys. getUpdateSql(), params);
            if(localBeginSession) {
                sessionManager.commitSession();
            }
        } catch (SQLException e) {
             logger.error(e.getMessage());
        }
    }

    @Override
    public void insertOrUpdate(T objectData) {
        Class<T> clazz = (Class<T>) objectData.getClass();
        try {
            var fieldId = meta.getIdField();
            if (Modifier.isPrivate(fieldId.getModifiers())) {
                fieldId.setAccessible(true);
            }
            var id = (Long)fieldId.get(objectData);
            if (findById(id, clazz) != null) {
                update(objectData);
            } else {
                insert(objectData);
            }
        }catch (IllegalAccessException e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public T findById(long id, Class<T> clazz) {
        Optional<T> entity;
        Connection connection;
        Boolean localBeginSession=false;
        try {
            try {
                connection = sessionManager.getCurrentSession().getConnection();
            }catch ( SessionManagerException e){
                sessionManager.beginSession();
                connection = sessionManager.getCurrentSession().getConnection();
                localBeginSession=true;
            }
             entity = dbExecutor.executeSelect(connection, querys.getSelectByIdSql(), id, resultSet -> {
                 try (ResultSet rs = resultSet) {
                     rs.next();
                     var fields = meta.getAllFields();
                     var o = meta.getConstructor().newInstance();
                     for (var field:fields){
                         var value = rs.getObject(field.getName());
                         if (Modifier.isPrivate(field.getModifiers())){
                             field.setAccessible(true);
                         }
                         field.set(o,value);
                     }
                     return  o;

                 } catch ( IllegalAccessException | InvocationTargetException | InstantiationException ignored) {
                     logger.error("can't create object");
                 } catch (SQLException e){
                     logger.error(e.getMessage());
                 }
                 return null;
             });
            if(localBeginSession) {
                sessionManager.commitSession();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            entity=Optional.empty();
        }
        return entity.orElse(null);
     }
}
