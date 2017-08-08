package kz.platonus.task3.service;

import kz.platonus.task3.database.Database;
import kz.platonus.task3.database.MySQLDatabaseImpl;
import kz.platonus.task3.enumeration.FieldType;
import kz.platonus.task3.table.TableField;

import java.util.List;

public class TableServiceImpl implements TableService {

    private Database database = new MySQLDatabaseImpl();


    @Override
    public void createTable(String tableName, String comment, List<TableField> fields){

    }

    @Override
    public void alterTable() {

    }

    @Override
    public void dropTable() {

    }

    @Override
    public void addColumn(TableField tableField) {

    }

    @Override
    public void dropColumn(String columnName) {

    }

    @Override
    public void changeColumnType(String columnName, FieldType type) {

    }
}
