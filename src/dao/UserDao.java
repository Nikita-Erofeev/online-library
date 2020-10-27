package dao;

import model.Book;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User getUserByLoginPass(String login, String password) throws ClassNotFoundException, SQLException;
    List<Book> getBooksByUserID(int userID) throws ClassNotFoundException, SQLException;
}
