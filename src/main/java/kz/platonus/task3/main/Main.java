package kz.platonus.task3.main;

import kz.platonus.task3.enumeration.FieldType;
import kz.platonus.task3.table.ForeignKey;
import kz.platonus.task3.table.Table;
import kz.platonus.task3.table.TableGenerator;
import kz.platonus.task3.table.TableField;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        List<Table> tables = new ArrayList<>();
        Table students = new Table("students");
        students.addField(new TableField()
                    .fieldName("StudentID")
                    .fieldType(FieldType.INT)
                    .autoIncrement()
                    .notNull()
                    .primaryKey());
        students.addField(new TableField()
                .fieldName("FirstName")
                .fieldType(FieldType.CHAR)
                .fieldLength(30)
                .notNull());
        students.addField(new TableField()
                .fieldName("LastName")
                .fieldType(FieldType.CHAR)
                .notNull()
                .fieldLength(30));
        students.addField(new TableField()
                .fieldName("email")
                .fieldType(FieldType.CHAR)
                .fieldLength(50));
        students.addField(new TableField()
                .fieldName("address")
                .fieldType(FieldType.CHAR)
                .fieldLength(50));
        tables.add(students);

        Table studentTransactions = new Table("student_transactions");
        studentTransactions.addField(new TableField()
                    .fieldName("TransactionID")
                    .fieldType(FieldType.INT)
                    .autoIncrement()
                    .notNull()
                    .primaryKey());
        studentTransactions.addField(new TableField()
                .fieldName("StudentID")
                .fieldType(FieldType.INT)
                .notNull()
                .foreignKey(new ForeignKey("students","StudentID")));
        studentTransactions.addField(new TableField()
                .fieldName("PostDate")
                .fieldType(FieldType.YEAR));
        studentTransactions.addField(new TableField()
                .fieldName("Description")
                .fieldType(FieldType.CHAR)
                .fieldLength(100));
        studentTransactions.addField(new TableField()
                .fieldName("subscription")
                .fieldType(FieldType.CHAR)
                .fieldLength(100));
        tables.add(studentTransactions);

        Table grades = new Table("grades");
        grades.addField(new TableField()
                .fieldName("StudentID")
                .fieldType(FieldType.INT)
                .notNull()
                .foreignKey(new ForeignKey("students","StudentID")));
        grades.addField(new TableField()
                .fieldName("CourseID")
                .fieldType(FieldType.INT)
                .notNull()
                .foreignKey(new ForeignKey("courses","CourseID")));
        grades.addField(new TableField()
                .fieldName("Year")
                .fieldType(FieldType.YEAR)
                .notNull());
        grades.addField(new TableField()
                .fieldName("Semester")
                .fieldType(FieldType.INT)
                .notNull()
                .fieldLength(2));
        grades.addField(new TableField()
                .fieldName("Grade")
                .fieldType(FieldType.CHAR)
                .notNull()
                .fieldLength(10));
        tables.add(grades);

        Table courses = new Table("courses");
        courses.addField(new TableField()
                .fieldName("CourseID")
                .fieldType(FieldType.INT)
                .autoIncrement()
                .notNull()
                .primaryKey());
        courses.addField(new TableField()
                .fieldName("DepartmentID")
                .fieldType(FieldType.INT)
                .notNull()
                .foreignKey(new ForeignKey("departments","DepartmentID")));
        courses.addField(new TableField()
                .fieldName("Name")
                .fieldType(FieldType.CHAR)
                .fieldLength(30)
                .notNull());
        tables.add(courses);

        Table enrollments = new Table("enrollments");
        enrollments.addField(new TableField()
                .fieldName("CourseID")
                .fieldType(FieldType.INT)
                .notNull()
                .foreignKey(new ForeignKey("courses","CourseID")));
        enrollments.addField(new TableField()
                .fieldName("Section")
                .fieldType(FieldType.INT)
                .notNull()
                .foreignKey(new ForeignKey("sections","Section")));
        enrollments.addField(new TableField()
                .fieldName("StudentID")
                .fieldType(FieldType.INT)
                .notNull()
                .foreignKey(new ForeignKey("students","StudentID")));
        tables.add(enrollments);

        Table departments = new Table("departments");
        departments.addField(new TableField()
                .fieldName("DepartmentID")
                .fieldType(FieldType.INT)
                .autoIncrement()
                .notNull()
                .primaryKey());
        departments.addField(new TableField()
                .fieldName("Description")
                .fieldType(FieldType.CHAR)
                .fieldLength(100)
                .notNull());
        tables.add(departments);

        Table sections = new Table("sections","Description of section table");
        sections.addField(new TableField()
                .fieldName("Section")
                .fieldType(FieldType.INT)
                .autoIncrement()
                .primaryKey());
        sections.addField(new TableField()
                .fieldName("CourseID")
                .fieldType(FieldType.INT)
                .notNull()
                .foreignKey(new ForeignKey("courses","CourseID")));
        sections.addField(new TableField()
                .fieldName("ProfessorID")
                .fieldType(FieldType.INT)
                .notNull()
                .foreignKey(new ForeignKey("employees","EmployeeID")));
        sections.addField(new TableField()
                .fieldName("StartTime")
                .fieldType(FieldType.DATE));
        tables.add(sections);

        Table employees = new Table("employees");
        employees.addField(new TableField()
                .fieldName("EmployeeID")
                .fieldType(FieldType.INT)
                .notNull()
                .primaryKey()
                .autoIncrement());
        employees.addField(new TableField()
                .fieldName("FirstName")
                .fieldType(FieldType.CHAR)
                .notNull()
                .fieldLength(30));
        employees.addField(new TableField()
                .fieldName("LastName")
                .fieldType(FieldType.CHAR)
                .notNull()
                .fieldLength(30));
        employees.addField(new TableField()
                .fieldName("DepartmentID")
                .fieldType(FieldType.INT)
                .notNull()
                .foreignKey(new ForeignKey("departments", "DepartmentID")));
        tables.add(employees);

        TableGenerator tableGenerator = new TableGenerator(tables);
        tableGenerator.generateTables();




    }


}
