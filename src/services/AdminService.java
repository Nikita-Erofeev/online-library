package services;

import dao.AdminDao;
import daoImpl.AdminDaoImpl;
import model.Author;
import model.Book;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class AdminService {
    private EmailValidator emailValidator = new EmailValidator();
    AdminDao adminDao = new AdminDaoImpl();

    public User getAdminByLoginPass(String login, String password) throws SQLException, ClassNotFoundException {
        if (emailValidator.validate(login) && password.length() >= 4) {
            return adminDao.getAdminByLoginPass(login, password);
        } else {
            return null;
        }
    }

    public boolean addAuthor(String firstname, String lastname, String patronymic) throws SQLException, ClassNotFoundException {
        if(firstname.equals("") || lastname.equals("") || patronymic.equals("")){
            return false;
        } else {
            adminDao.addAuthor(firstname, lastname, patronymic);
            return true;
        }
    }

    public List<Author> getAllAuthors() throws SQLException, ClassNotFoundException{
        return adminDao.getAllAuthors();
    }

    public boolean addBook(Book book) throws SQLException, ClassNotFoundException{
        try {
            Integer.parseInt(book.getAuthor());
            Integer.parseInt(book.getGenre());
        } catch (ClassCastException ex){
            return false;
        }
        adminDao.addBook(book);
        return true;
    }
}
