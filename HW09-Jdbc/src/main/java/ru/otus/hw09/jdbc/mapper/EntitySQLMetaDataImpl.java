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
    public String getSelectAllSql() {
       return String.format("SELECT * FROM %s ", entity.getName());
    }

    @Override
    public String getSelectByIdSql() {

        return String.format("SELECT * FROM %s WHERE %s = ?", entity.getName(),entity.getIdField().getName());
    }

    @Override
    public String getInsertSql() {
        List<Field> fields = entity.getFieldsWithoutId();
        var names = fields.stream().map(Field::getName).collect(Collectors.joining(", "));
        var values = fields.stream().map(__ -> "?").collect(Collectors.joining(", "));
        return String.format("INSERT INTO %s(%s) VALUES(%s)", entity.getName(),names,values);
    }

    @Override
    public String getUpdateSql() {
        List<Field> fields = entity.getFieldsWithoutId();
        var names = fields.stream().map(field->field.getName() + " = ?").collect(Collectors.joining(", "));
        var id = entity.getIdField().getName();
        return String.format("UPDATE %s SET %s WHERE %s = ?", entity.getName(),names,id);
    }
}
