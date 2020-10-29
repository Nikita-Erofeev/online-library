package model;

public class Book {
    private int id;
    private String author;
    private String name;
    private String genre;
    private String description;
    private int price;
    private boolean isAvailable;

    public Book(int id){
        this.id = id;
    }

    public Book(String author, String name, String genre, String description, int price){
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.price = price;
    }

    public Book(int id, String author, String name, String genre, String description, int price, boolean isAvailable){
        this.id = id;
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Book(int id, String author, String name, String genre, String description, int price){
        this.id = id;
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.price = price;
        this.isAvailable = false;
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

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isAvailable() {
        return isAvailable;
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
