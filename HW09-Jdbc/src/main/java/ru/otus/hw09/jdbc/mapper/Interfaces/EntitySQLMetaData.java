package ru.otus.hw09.jdbc.mapper.Interfaces;

import java.util.List;

public interface EntitySQLMetaData {
    List<String> getInitSql();
    String getSelectAllSql();
    String getSelectByIdSql();
    String getInsertSql();
    String getUpdateSql();
}
