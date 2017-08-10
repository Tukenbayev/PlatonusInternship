package kz.platonus.task3.table;

import kz.platonus.task3.service.TableService;
import kz.platonus.task3.service.TableServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private String tableName;
    private String comment;
    private List<TableField> tableFields;
    private TableService tableService;

    public Table(String tableName, String comment) {
        this(tableName);
        this.comment = comment;
    }

    public Table(String tableName){
        this.tableName = tableName;
        tableFields = new ArrayList<>();
        tableService = new TableServiceImpl();
    }

    public void addField(TableField field){
        tableFields.add(field);
    }

    public void generateTable(){
        tableService.generateTable(this);
    }

    public String getTableName() {
        return tableName;
    }

    public String getComment() {
        return comment;
    }

    public TableField getFieldByName(String fieldName){

        for (TableField field : tableFields){
            if (field.getFieldName().equals(fieldName))
                return field;
        }
        return null;
    }

    public boolean hasForeignKey(){
        for (TableField field : tableFields){
            if (field.getForeignKey() != null)
                return true;
        }
        return false;
    }


    public List<TableField> getTableFields() {
        return tableFields;
    }
}
