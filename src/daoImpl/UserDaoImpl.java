package daoImpl;

import dao.UserDao;
import jdbc.DatabaseConnection;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Override
    public User getUserByLoginPass(String login, String password) throws ClassNotFoundException, SQLException {
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
