package daoImpl;

import dao.AdminDao;
import jdbc.DatabaseConnection;
import model.Author;
import model.Book;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    @Override
    public User getAdminByLoginPass(String login, String password) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String select = "SELECT id FROM admin WHERE login = ? AND password = ?";
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
    public void addAuthor(String firstname, String lastname, String patronymic) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String add = "INSERT INTO author VALUES(null, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1, firstname);
        preparedStatement.setString(2, lastname);
        preparedStatement.setString(3, patronymic);
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public List<Author> getAllAuthors() throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String select = "SELECT * FROM author";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Author> authors = new ArrayList<>();
        while (resultSet.next()) {
            authors.add(new Author(resultSet.getInt("id"), resultSet.getString("firstname"),
                    resultSet.getString("lastname"), resultSet.getString("patronymic")));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return authors;
    }

    @Override
    public void addBook(Book book) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String add = " INSERT INTO book VALUES (?, null, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setInt(1, Integer.parseInt(book.getAuthor()));
        preparedStatement.setString(2, book.getName());
        preparedStatement.setInt(3, Integer.parseInt(book.getGenre()));
        preparedStatement.setString(4, book.getDescription());
        preparedStatement.setInt(5, book.getPrice());
        preparedStatement.setString(6, book.getPath());
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void deleteAuthorById(int authorId) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String add = "DELETE FROM author WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setInt(1, authorId);
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void changeAuthor(Author author) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String add = "UPDATE author SET firstname = ?, lastname = ?, patronymic = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1, author.getFirstname());
        preparedStatement.setString(2, author.getLastname());
        preparedStatement.setString(3, author.getPatronymic());
        preparedStatement.setInt(4, author.getId());
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }
}
