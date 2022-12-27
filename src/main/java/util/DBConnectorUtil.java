package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectorUtil {
    private static DBConnectorUtil instance = new DBConnectorUtil();
    public static final String URL = "jdbc:mysql://localhost:3306/dt";
    public static final String USER = "root";
    public static final String PASSWORD = "Hardi2013@";
    public static final String DRIVER = "com.mysql.jdbc.Driver";

    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Connection connection = null;
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to Database.");
            
        return connection;
    }
}