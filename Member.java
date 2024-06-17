import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable {
    private String name;
    private String memberId;
    private String email;
    private List<Book> borrowedBooks;

    public Member(String name, String memberId, String email) {
        this.name = name;
        this.memberId = memberId;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", memberId='" + memberId + '\'' +
                ", email='" + email + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
