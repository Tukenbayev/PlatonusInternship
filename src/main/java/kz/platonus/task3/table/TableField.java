package kz.platonus.task3.table;

import kz.platonus.task3.enumeration.FieldType;

public class TableField {

    private String fieldName;
    private FieldType fieldType;
    private int fieldLength;
    private boolean primaryKey;
    private ForeignKey foreignKey;
    private boolean notNull;
    private boolean autoIncrement;

    public TableField fieldName(String fieldName){
        this.fieldName = fieldName;
        return this;
    }

    public TableField fieldType(FieldType fieldType){
        this.fieldType = fieldType;
        return this;
    }

    public TableField fieldLength(int fieldLength){
        this.fieldLength = fieldLength;
        return this;
    }

    public TableField primaryKey(boolean primaryKey){
        this.primaryKey = primaryKey;
        return this;
    }

    public TableField foreignKey(ForeignKey foreignKey){
        this.foreignKey = foreignKey;
        return this;
    }

    public TableField notNull(boolean notNull){
        this.notNull = notNull;
        return this;
    }

    public TableField autoIncrement(boolean autoIncrement){
        this.autoIncrement = autoIncrement;
        return this;
    }
}
