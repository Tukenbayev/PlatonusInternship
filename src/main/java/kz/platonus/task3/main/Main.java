package kz.platonus.task3.main;

import kz.platonus.task3.enumeration.FieldType;
import kz.platonus.task3.table.Table;
import kz.platonus.task3.table.TableField;


public class Main {

    public static void main(String[] args) {
        Table students = new Table("students","initial comment");
        students.addField(new TableField()
                .fieldName("student_id")
                .fieldType(FieldType.INT)
                .fieldLength(11)
                .primaryKey(true)
                .autoIncrement(true));
        students.addField(new TableField()
                .fieldName("student_name")
                .fieldType(FieldType.CHAR)
                .fieldLength(100)
                .notNull(true));
        students.addField(new TableField()
                .fieldName("email")
                .fieldType(FieldType.CHAR)
                .fieldLength(100)
                .notNull(true));

        students.createTable();

    }
}
