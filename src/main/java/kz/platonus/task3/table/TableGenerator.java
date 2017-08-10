package kz.platonus.task3.table;


import java.util.*;

public class TableGenerator {

    private final List<Table> sortedTables = new ArrayList<>();
    List<Table> tables;

    public TableGenerator(List<Table> tables){
        this.tables = tables;
    }

    public void generateTables(){
        for (Table table : tables)
            sortInCreationOrder(table);

        for (Table table : sortedTables)
            table.generateTable();
    }

    private void sortInCreationOrder(Table table){
        if (!table.hasForeignKey() && !sortedTables.contains(table)){
            sortedTables.add(table);
        }else if (!sortedTables.contains(table)){
            for (TableField tableField : table.getTableFields()) {
                if (tableField.getForeignKey() != null) {
                    String referenceTableName = tableField.getForeignKey().getReferenceTableName();
                    Table referenceTable = getTableByName(referenceTableName);
                    if (referenceTable != null)
                        sortInCreationOrder(referenceTable);
                }
            }
        }

        if (!sortedTables.contains(table))
            sortedTables.add(table);
    }

    private Table getTableByName(String tableName){
        for (Table table : tables){
            if (table.getTableName().equals(tableName))
                return table;
        }
        return null;
    }
}
