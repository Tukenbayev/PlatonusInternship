package kz.platonus.task3.service;

import kz.platonus.task3.database.Database;
import kz.platonus.task3.database.MySQLDatabaseImpl;
import kz.platonus.task3.enumeration.FieldType;
import kz.platonus.task3.table.TableField;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TableServiceImpl implements TableService {

    private Database database = new MySQLDatabaseImpl();
    private final String CREATE_TABLE_QUERY_PATTERN = "CREATE TABLE IF NOT EXISTS %s(%s) COMMENT=%s";
    private final String ADD_COLUMN_QUERY_PATTERN = "ALTER TABLE %s ADD %s";
    private final String DROP_COLUMN_QUERY_PATTERN = "ALTER TABLE %s DROP COLUMN %s";
    private final String MODIFY_COLUMN_TYPE_QUERY_PATTERN = "ALTER TABLE %s MODIFY COLUMN %s %s";

    @Override
    public void createTable(String tableName, String comment, List<TableField> fields){

        try(Connection conn = database.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.execute(buildCreateTableQuery(tableName,comment,fields));
            System.out.println(buildCreateTableQuery(tableName,comment,fields));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String buildCreateTableQuery(String tableName, String comment, List<TableField> fields){
        StringBuilder tableColumns = new StringBuilder();
        for (TableField field : fields){
            tableColumns.append(field.getFieldName() + " ").append(field.getFieldType());

            if (field.getFieldLength() != 0)
                tableColumns.append("("+field.getFieldLength()+") ");
            else
                tableColumns.append(" ");
            if (field.isPrimaryKey())
                tableColumns.append("PRIMARY KEY ");
            if (field.isNotNull())
                tableColumns.append("NOT NULL ");
            if (field.isAutoIncrement())
                tableColumns.append("AUTO_INCREMENT ");
            tableColumns.append(",\n");

            if (field.getForeignKey() != null){
                tableColumns.append("FOREIGN KEY ("+field.getFieldName()+") REFERENCES " +
                        field.getForeignKey().getReferenceTableName() + "(" + field.getForeignKey().getColumnName() + ")");
                tableColumns.append(",\n");
            }
        }
        tableColumns.deleteCharAt(tableColumns.length()-2);
        return String.format(CREATE_TABLE_QUERY_PATTERN,tableName,tableColumns.toString(),"\""+comment+"\"");
    }


    @Override
    public void addColumn(String tableName, TableField tableField) {
        try(Connection conn = database.getConnection();
            Statement addColumnStmt = conn.createStatement()) {

            addColumnStmt.execute(buildAddColumnQuery(tableName,tableField));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String buildAddColumnQuery(String tableName, TableField field){
        StringBuilder tableColumn = new StringBuilder();
        tableColumn.append(field.getFieldName() + " ").append(field.getFieldType());

        if (field.getFieldLength() != 0)
            tableColumn.append("("+field.getFieldLength()+") ");
        else
            tableColumn.append(" ");
        if (field.isPrimaryKey())
            tableColumn.append("PRIMARY KEY ");
        if (field.isNotNull())
            tableColumn.append("NOT NULL");
        if (field.isAutoIncrement())
            tableColumn.append("AUTO_INCREMENT");
        return String.format(ADD_COLUMN_QUERY_PATTERN,tableName,tableColumn.toString());
    }

    @Override
    public void dropColumn(String tableName,String columnName) {
        try(Connection conn = database.getConnection();
            Statement dropColumnStmt = conn.createStatement()) {

            dropColumnStmt.execute(String.format(DROP_COLUMN_QUERY_PATTERN,tableName,columnName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeColumnType(String tableName,String columnName, FieldType newType) {
        try(Connection conn = database.getConnection();
            Statement modifyColumnStmt = conn.createStatement()) {

            modifyColumnStmt.execute(String.format(MODIFY_COLUMN_TYPE_QUERY_PATTERN,tableName,columnName,newType));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
