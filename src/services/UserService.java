package services;

import jdbc.DatabaseConnection;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public static User getUser() throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseConnection.getConnection();
        LOGGER.info("INDEX SERVLET:: Connection = {}", connection.toString());
        String select = "SELECT * FROM user";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        LOGGER.info("INDEX SERVLET:: preparedStatement = {}", preparedStatement.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        LOGGER.info("INDEX SERVLET:: resultSet = {}", resultSet.toString());
        if (resultSet.next()) {
            String pass = resultSet.getString("password");
            String log = resultSet.getString("login");
            return new User(log, pass);
        } else {
            return null;
        }
    }

    public static User getUserByLoginPass(String login, String password) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseConnection.getConnection();
        LOGGER.info("INDEX SERVLET:: Connection = {}", connection.toString());
        String select = "SELECT id FROM user WHERE login = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        LOGGER.info("INDEX SERVLET:: preparedStatement = {}", preparedStatement.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            return new User(id, login, password);
        }
        return null;
    }
}
