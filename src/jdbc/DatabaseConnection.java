package jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnection.class);
    private static Connection connection = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String user = "root";
        String password = "test";
        String url = "jdbc:mysql://localhost:3306/sitedb?useUnicode=yes&characterEncoding=UTF8&useSSL=false&serverTimezone=Asia/Omsk";
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);
        if (connection != null) {
            LOGGER.info("Connection установлено {}", DatabaseConnection.class.toString());
        } else {
            LOGGER.info("Connection is null {}", DatabaseConnection.class.toString());
        }
        return connection;
    }
}
