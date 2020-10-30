package services;

import dao.UserDao;
import daoImpl.UserDaoImpl;
import model.Book;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

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

    public List<Book> getBooksByUserID(int userID) throws ClassNotFoundException, SQLException {
        if (userID <= 0) {
            return null;
        } else {
            return userDao.getBooksByUserID(userID);
        }
    }

    public List<Book> getBookBySearch(int userId, String search) throws ClassNotFoundException, SQLException {
        return userDao.getBookBySearch(userId, search);
    }

    public String readBook(int userId, int bookId) throws ClassNotFoundException, SQLException {
        return userDao.readBook(userId, bookId);
    }

    public boolean checkBookIsAvailable(int userId, int bookId) throws ClassNotFoundException, SQLException {
        return userDao.checkBookIsAvailable(userId, bookId);
    }

    public Book getBookById(int bookId) throws ClassNotFoundException, SQLException {
        return userDao.getBookById(bookId);
    }

    public boolean buyBook(int userId, int bookId, String login) throws ClassNotFoundException, SQLException {
        return userDao.buyBook(userId, bookId, login);
    }
}
