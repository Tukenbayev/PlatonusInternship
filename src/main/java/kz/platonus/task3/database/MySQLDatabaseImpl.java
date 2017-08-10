package kz.platonus.task3.database;

import java.sql.Connection;

public class MySQLDatabaseImpl implements Database {

    private ConnectionPool pool;
    private static final String PASSWORD = "root";
    private static final String USERNAME = "root";
    private static final String MYSQL_DB_URI = "jdbc:mysql://localhost:3306/task3";
    private static final String MYSQL_DRIVER = "org.gjt.mm.mysql.Driver";


    public MySQLDatabaseImpl(){
        pool = ConnectionPool.getInstance();
    }

    public Connection getConnection() {

        if (!pool.isInitialized()){
            pool.setConnectionNumber(5);
            pool.setUrl(MYSQL_DB_URI);
            pool.setUsername(USERNAME);
            pool.setPassword(PASSWORD);
            pool.setDriver(MYSQL_DRIVER);
            pool.initConnections();

            return pool.getConnection();
        }


        return pool.getConnection();
    }
}
