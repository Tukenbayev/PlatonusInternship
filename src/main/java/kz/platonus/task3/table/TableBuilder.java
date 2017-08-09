package kz.platonus.task3.table;


import java.util.*;

public class TableBuilder {

    private static final List<Table> sortedTables = new ArrayList<>();

    public static void createTables(List<Table> tables){
        for (Table table : tables)
            sortInCreationOrder(table,tables);

        for (Table table : sortedTables)
            table.createTable();
    }

    private static void sortInCreationOrder(Table table, List<Table> tables){
        if (!table.hasForeignKey() && !sortedTables.contains(table)){
            sortedTables.add(table);
        }else if (!sortedTables.contains(table)){
            for (TableField tableField : table.getTableFields()) {
                if (tableField.getForeignKey() != null) {
                    String referenceTableName = tableField.getForeignKey().getReferenceTableName();
                    Table referenceTable = getTableByName(referenceTableName, tables);
                    if (referenceTable != null)
                        sortInCreationOrder(referenceTable,tables);
                }
            }
        }

        if (!sortedTables.contains(table))
            sortedTables.add(table);
    }

    private static Table getTableByName(String tableName, List<Table> tables){
        for (Table table : tables){
            if (table.getTableName().equals(tableName))
                return table;
        }
        return null;
    }
}
