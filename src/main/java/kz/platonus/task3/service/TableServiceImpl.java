package kz.platonus.task3.service;

import kz.platonus.task3.database.Database;
import kz.platonus.task3.database.MySQLDatabaseImpl;
import kz.platonus.task3.enumeration.FieldType;
import kz.platonus.task3.table.Table;
import kz.platonus.task3.table.TableField;

import java.sql.*;
import java.util.List;

public class TableServiceImpl implements TableService {

    private Database database = new MySQLDatabaseImpl();
    private DatabaseMetaData metaData;
    private final String CREATE_TABLE_QUERY_PATTERN = "CREATE TABLE IF NOT EXISTS %s(%s) COMMENT=%s";
    private final String ADD_COLUMN_QUERY_PATTERN = "ALTER TABLE %s ADD %s";
    private final String MODIFY_COLUMN_TYPE_QUERY_PATTERN = "ALTER TABLE %s MODIFY COLUMN %s %s";

    @Override
    public void generateTable(Table table){

        if (isExists(table)){
            alterTable(table);
        }else {
            try (Connection conn = database.getConnection()) {
                Statement stmt = conn.createStatement();
                stmt.execute(buildCreateTableQuery(table.getTableName(), table.getComment(), table.getTableFields()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String buildCreateTableQuery(String tableName, String comment, List<TableField> fields){
        StringBuilder tableColumns = new StringBuilder();
        for (TableField field : fields){
            tableColumns.append(field.getFieldName() + " ").append(field.getFieldType());

            if (field.getFieldLength() != 0){
                tableColumns.append("("+field.getFieldLength()+")");
            }
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

    private boolean isExists(Table table){
        try(Connection connection = database.getConnection()){
            metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null,null,table.getTableName(),null);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void alterTable(Table table){
        try{
            for (TableField field : table.getTableFields()){
                ResultSet resultSet = metaData.getColumns(null,null,table.getTableName(),field.getFieldName());
                if (resultSet.next()){
                    String currentFieldType = resultSet.getString("TYPE_NAME");
                    if (!currentFieldType.equals(field.getFieldType().toString())){
                        changeColumnType(table,field);
                    }
                }else{
                    addColumn(table.getTableName(),field);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void addColumn(String tableName, TableField tableField) {
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

    private void changeColumnType(Table table, TableField field){
        try(Connection conn = database.getConnection();
            Statement modifyColumnStmt = conn.createStatement()){
            modifyColumnStmt.execute(String.format(MODIFY_COLUMN_TYPE_QUERY_PATTERN,table.getTableName(),
                    field.getFieldName(),field.getFieldType().toString()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
