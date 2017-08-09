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

    @Override
    public void createTable(String tableName, String comment, List<TableField> fields){

        try(Connection conn = database.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.execute(buildCreateTableQuery(tableName,comment,fields));
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
                tableColumns.append("NOT NULL");
            if (field.isAutoIncrement())
                tableColumns.append("AUTO_INCREMENT");
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
    public void addColumn(TableField tableField) {

    }

    @Override
    public void dropColumn(String columnName) {

    }

    @Override
    public void changeColumnType(String columnName, FieldType type) {

    }
}
