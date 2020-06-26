package ru.otus.hw09.jdbc.mapper.Interfaces;

public interface EntitySQLMetaData {
    String getSelectAllSql();
    String getSelectByIdSql();
    String getInsertSql();
    String getUpdateSql();
}
