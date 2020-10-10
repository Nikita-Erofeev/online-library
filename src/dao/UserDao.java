package dao;

import model.User;

import java.sql.SQLException;

public interface UserDao {
    User getUserByLoginPass(String login, String password) throws ClassNotFoundException, SQLException;
}
