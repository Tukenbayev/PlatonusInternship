package kz.platonus.task3.service;


import kz.platonus.task3.enumeration.FieldType;
import kz.platonus.task3.table.TableField;

import java.util.List;

public interface TableService {

    void createTable(String tableName, String comment, List<TableField> fields);
    void addColumn(String tableName, TableField tableField);
    void dropColumn(String tableName,String columnName);
    void changeColumnType(String tableName, String columnName, FieldType type);
}
