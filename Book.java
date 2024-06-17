import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private String ISBN;
    private int availableCopies;

    public Book(String title, String author, String ISBN, int availableCopies) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.availableCopies = availableCopies;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", availableCopies=" + availableCopies +
                '}';
    }
}
