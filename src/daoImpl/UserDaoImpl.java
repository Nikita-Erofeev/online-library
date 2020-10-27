package daoImpl;

import dao.UserDao;
import jdbc.DatabaseConnection;
import model.Book;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.UserService;

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
        return null;
    }

    @Override
    public List<Book> getBooksByUserID(int userID) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String select = " SELECT firstname, lastname, patronymic, book.id, genre.name AS genreName, " +
                "book.name, description, price FROM book INNER JOIN author ON book.author_id = author.id  INNER JOIN" +
                " genre ON book.genre_id = genre.id WHERE book.id = (SELECT book_id FROM access_book WHERE user_id" +
                " = ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setInt(1, userID);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> books = new ArrayList<>();
        int id, price;
        String name, genre, description;
        StringBuilder stringBuilder = new StringBuilder();
        while (resultSet.next()) {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            LOGGER.debug("userDaoImpl book id = {} name = {}", id, name);
            genre = resultSet.getString("genreName");
            description = resultSet.getString("description");
            price = resultSet.getInt("price");
            stringBuilder.append(resultSet.getString("lastname"));
            stringBuilder.append(" ");
            stringBuilder.append(resultSet.getString("firstname").charAt(0) + ". ");
            stringBuilder.append(resultSet.getString("patronymic").charAt(0) + ".");
            books.add(new Book(id, stringBuilder.toString(), name, genre, description, price));
        }
        return books;
    }
}
