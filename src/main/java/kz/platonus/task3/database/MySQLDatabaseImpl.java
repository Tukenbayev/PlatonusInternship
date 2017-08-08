package kz.platonus.task3.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDatabaseImpl implements Database {

    private final Properties DB_PROP = new Properties();
    private final String DB_PROP_FILE_NAME = "database.properties";

    public Connection getConnection(){
        Connection connection = null;
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(DB_PROP_FILE_NAME);
            Class.forName(DB_PROP.getProperty("driver"));
            connection = DriverManager.getConnection(DB_PROP.getProperty("url"), DB_PROP.getProperty("username"),
                    DB_PROP.getProperty("password"));

        } catch (ClassNotFoundException |FileNotFoundException| SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
