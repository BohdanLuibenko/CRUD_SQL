package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorService {
    private static ConnectorService instance = new ConnectorService();
    public static final String URL = "jdbc:mysql://localhost:3306/dt";
    public static final String USER = "root";
    public static final String PASSWORD = "Hardi2013@";
    public static final String DRIVER = "com.mysql.jdbc.Driver";

    private Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to Database.");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR: Unable to Connect to Database1.");
        }
        return connection;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }
}