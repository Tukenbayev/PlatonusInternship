package kz.platonus.task3.main;

import kz.platonus.task3.enumeration.FieldType;
import kz.platonus.task3.table.ForeignKey;
import kz.platonus.task3.table.Table;
import kz.platonus.task3.table.TableBuilder;
import kz.platonus.task3.table.TableField;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        List<Table> tables = new ArrayList<>();
        Table students = new Table("students","initial comment");
        students.addField(new TableField()
                .fieldName("student_id")
                .fieldType(FieldType.INT)
                .primaryKey(true)
                .fieldLength(11)
                .autoIncrement(true));
        students.addField(new TableField()
                .fieldName("student_name")
                .fieldType(FieldType.CHAR)
                .notNull(true));
        students.addField(new TableField()
                .fieldName("email")
                .fieldType(FieldType.CHAR)
                .fieldLength(100)
                .notNull(true));


        Table faculties = new Table("faculty","initial comment");
        faculties.addField(new TableField()
                .fieldName("faculty_id")
                .fieldType(FieldType.INT)
                .primaryKey(true)
                .fieldLength(11)
                .autoIncrement(true));
        faculties.addField(new TableField()
                .fieldName("faculty_name")
                .fieldType(FieldType.CHAR)
                .notNull(true));

        Table faculty_student = new Table("faculty_student","comment");
        faculty_student.addField(new TableField()
                .fieldName("faculty_id")
                .fieldType(FieldType.INT)
                .notNull(true)
                .foreignKey(new ForeignKey("faculty","faculty_id")));
        faculty_student.addField(new TableField()
                .fieldName("student_id")
                .fieldType(FieldType.INT)
                .notNull(true)
                .foreignKey(new ForeignKey("students","student_id")));

        tables.add(faculty_student);
        tables.add(students);
        tables.add(faculties);

        TableBuilder.createTables(tables);





    }


}
