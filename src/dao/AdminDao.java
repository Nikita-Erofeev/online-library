package dao;

import model.Author;
import model.Book;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface AdminDao {
    User getAdminByLoginPass(String login,String password) throws SQLException, ClassNotFoundException;
    void addAuthor(String firstname, String lastname, String patronymic) throws SQLException, ClassNotFoundException;
    List<Author> getAllAuthors() throws SQLException, ClassNotFoundException;
    void addBook(Book book) throws SQLException, ClassNotFoundException;
    void deleteAuthorById(int authorId) throws SQLException, ClassNotFoundException;
    void changeAuthor(Author author) throws SQLException, ClassNotFoundException;
    List<Book> getAllBooks() throws SQLException, ClassNotFoundException;
    void deleteBookById(int bookId) throws SQLException, ClassNotFoundException;
    void changeBook(Book book) throws SQLException, ClassNotFoundException;
    String reportAllPurchase() throws SQLException, ClassNotFoundException;
    String reportSumOfPurchase() throws SQLException, ClassNotFoundException;
    String priceForBooks() throws SQLException, ClassNotFoundException;
}