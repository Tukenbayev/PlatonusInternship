package kz.platonus.task3.table;

public class ForeignKey {

    private String referenceTableName;
    private String columnName;

    public ForeignKey(String referenceTableName, String columnName) {
        this.referenceTableName = referenceTableName;
        this.columnName = columnName;
    }
}
