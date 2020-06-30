package ru.otus.hw09.jdbc.mapper;

import ru.otus.hw09.jdbc.mapper.Interfaces.EntityClassMetaData;
import ru.otus.hw09.jdbc.mapper.Interfaces.EntitySQLMetaData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {

    private final EntityClassMetaData entity;

    public EntitySQLMetaDataImpl(EntityClassMetaData entity) {
        this.entity = entity;
    }

    @Override
    public List<String> getInitSql() {
        var list = new ArrayList<String>();
        list.add("create table if not exists user(id long auto_increment, name varchar(50))");
        list.add("create table if not exists account(no long auto_increment, type varchar(255),rest int)");
        return list;
    }

    @Override
    public String getSelectAllSql() {
        List<Field> fields = entity.getAllFields();
        var names = fields.stream().map(Field::getName).collect(Collectors.joining(", "));
        return String.format("SELECT %s FROM %s ", names,entity.getName());
    }

    @Override
    public String getSelectByIdSql() {
        List<Field> fields = entity.getAllFields();
        var names = fields.stream().map(Field::getName).collect(Collectors.joining(", "));
        return String.format("SELECT %s FROM %s WHERE %s = ?", names,entity.getName(),entity.getIdField().getName());
    }

    @Override
    public String getInsertSql() {
        List<Field> fields = entity.getFieldsWithoutId();
        var names = fields.stream().map(Field::getName).collect(Collectors.joining(", "));
        var params = ":params"; //fields.stream().map(__ -> "?").collect(Collectors.joining(", "));
        return String.format("INSERT INTO %s(%s) VALUES(%s)", entity.getName(),names,params);
    }

    @Override
    public String getUpdateSql() {
        List<Field> fields = entity.getFieldsWithoutId();
        var names = fields.stream().map(field->field.getName() + " = ?").collect(Collectors.joining(", "));
        var id = entity.getIdField().getName();
        return String.format("UPDATE %s SET %s WHERE %s = ?", entity.getName(),names,id);
    }
}
