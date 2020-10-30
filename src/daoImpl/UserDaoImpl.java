package daoImpl;

import dao.UserDao;
import jdbc.DatabaseConnection;
import model.Book;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.UserService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Override
    public User getUserByLoginPass(String login, String password) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String select = "SELECT id FROM user WHERE login = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            return new User(id, login, password);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return null;
    }

    @Override
    public List<Book> getBooksByUserID(int userID) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String select = " SELECT firstname, lastname, patronymic, book.id, genre.name AS genreName, " +
                "book.name, description, price, path_book FROM book INNER JOIN author ON book.author_id = author.id  INNER JOIN" +
                " genre ON book.genre_id = genre.id WHERE book.id = (SELECT book_id FROM access_book WHERE user_id" +
                " = ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setInt(1, userID);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> books = new ArrayList<>();
        int id, price;
        String name, genre, description, path;
        StringBuilder stringBuilder = new StringBuilder();
        while (resultSet.next()) {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            LOGGER.debug("userDaoImpl book id = {} name = {}", id, name);
            genre = resultSet.getString("genreName");
            description = resultSet.getString("description");
            price = resultSet.getInt("price");
            path = resultSet.getString("path_book");
            stringBuilder.append(resultSet.getString("lastname"));
            stringBuilder.append(" ");
            stringBuilder.append(resultSet.getString("firstname").charAt(0) + ". ");
            stringBuilder.append(resultSet.getString("patronymic").charAt(0) + ".");
            books.add(new Book(id, stringBuilder.toString(), name, genre, description, price, path));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return books;
    }

    @Override
    public List<Book> getBookBySearch(int userId, String search) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String select = "SELECT book.id as id, name, description, price, path_book, firstname, lastname, patronymic, " +
                "IF(book.id IN (SELECT book_id FROM access_book WHERE user_id = ?),true,false) as isAvailable\n" +
                " FROM book INNER JOIN author ON book.author_id = author.id WHERE name like ? OR lastname like ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        search = search + "%";
        preparedStatement.setInt(1, userId);
        preparedStatement.setString(2, search);
        preparedStatement.setString(3, search);
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        int id, price;
        String name, genre, description, path;
        boolean isAvailable;
        StringBuilder stringBuilder = new StringBuilder();
        while (resultSet.next()) {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            LOGGER.debug("userDaoImpl book id = {} name = {}", id, name);
            description = resultSet.getString("description");
            price = resultSet.getInt("price");
            path = resultSet.getString("path_book");
            stringBuilder.append(resultSet.getString("lastname"));
            stringBuilder.append(" ");
            stringBuilder.append(resultSet.getString("firstname").charAt(0) + ". ");
            stringBuilder.append(resultSet.getString("patronymic").charAt(0) + ".");
            isAvailable = resultSet.getBoolean("isAvailable");
            books.add(new Book(id, stringBuilder.toString(), name, description, price, path, isAvailable));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return books;
    }

    @Override
    public String readBook(int userId, int bookId) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String select = "SELECT path_book FROM book INNER JOIN access_book ON book.id = access_book.book_id WHERE user_id = ? AND book.id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, bookId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            String path = resultSet.getString("path_book");
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return path;
        } else {
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return null;
        }
    }


}
