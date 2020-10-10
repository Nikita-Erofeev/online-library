package services;

import dao.UserDao;
import daoImpl.UserDaoImpl;
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
    private UserDao userDao = new UserDaoImpl();
    private EmailValidator emailValidator = new EmailValidator();

    public User getUserByLoginPass(String login, String password) throws ClassNotFoundException, SQLException {
        if (emailValidator.validate(login) && password.length() >= 4) {
            return userDao.getUserByLoginPass(login, password);
        } else {
            return null;
        }
    }
}
