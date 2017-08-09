package kz.platonus.task3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabaseImpl implements Database {

    private final String URL = "jdbc:mysql://localhost:3306/task3";
    private final String PASSWORD = "root";
    private final String USERNAME = "root";

    public Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
