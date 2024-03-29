package model;

public class Book {
    private int id;
    private String author;
    private String name;
    private String genre;
    private String description;
    private int price;
    private String path;
    private boolean isAvailable = false;

    public Book(int id){
        this.id = id;
    }

    public Book(){
    }

    public Book(int id, String author, String name, String description, int price){
        this.id = id;
        this.author = author;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Book(int id, String author, String name, String genre, String description, int price, String path, boolean isAvailable){
        this.id = id;
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.price = price;
        this.path = path;
        this.isAvailable = isAvailable;
    }

    public Book(int id, String author, String name, String genre, String description, int price, String path){
        this.id = id;
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.price = price;
        this.isAvailable = false;
        this.path = path;
    }

    public Book(int id, String author, String name, String description, int price, String path, boolean isAvailable){
        this.id = id;
        this.author = author;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (price != book.price) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (genre != null ? !genre.equals(book.genre) : book.genre != null) return false;
        return description != null ? description.equals(book.description) : book.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}
