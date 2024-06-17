import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    // Methods for adding, removing, searching books and members
    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String ISBN) {
        books.removeIf(book -> book.getISBN().equals(ISBN));
    }

    public Book findBookByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public Book findBookByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .findFirst()
                .orElse(null);
    }

    public Book findBookByISBN(String ISBN) {
        return books.stream()
                .filter(book -> book.getISBN().equals(ISBN))
                .findFirst()
                .orElse(null);
    }

    public List<Book> listAllBooks() {
        return new ArrayList<>(books);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(String memberId) {
        members.removeIf(member -> member.getMemberId().equals(memberId));
    }

    public Member findMemberByName(String name) {
        return members.stream()
                .filter(member -> member.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public Member findMemberById(String memberId) {
        return members.stream()
                .filter(member -> member.getMemberId().equals(memberId))
                .findFirst()
                .orElse(null);
    }

    public List<Member> listAllMembers() {
        return new ArrayList<>(members);
    }

    // Methods for issuing and returning books
    public void issueBook(String ISBN, String memberId) throws Exception {
        Book book = findBookByISBN(ISBN);
        if (book == null || book.getAvailableCopies() == 0) {
            throw new Exception("Book not available.");
        }
        Member member = findMemberById(memberId);
        if (member == null) {
            throw new Exception("Member not found.");
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        member.getBorrowedBooks().add(book);
    }

    public void returnBook(String ISBN, String memberId) throws Exception {
        Member member = findMemberById(memberId);
        if (member == null) {
            throw new Exception("Member not found.");
        }
        Book book = findBookByISBN(ISBN);
        if (book == null || !member.getBorrowedBooks().contains(book)) {
            throw new Exception("Book not borrowed by this member.");
        }
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        member.getBorrowedBooks().remove(book);
    }

    // Methods for file I/O
    public void saveData(String bookFile, String memberFile) throws IOException {
        try (ObjectOutputStream bookOut = new ObjectOutputStream(new FileOutputStream(bookFile));
             ObjectOutputStream memberOut = new ObjectOutputStream(new FileOutputStream(memberFile))) {
            bookOut.writeObject(books);
            memberOut.writeObject(members);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadData(String bookFile, String memberFile) throws IOException, ClassNotFoundException {
        try (ObjectInputStream bookIn = new ObjectInputStream(new FileInputStream(bookFile));
             ObjectInputStream memberIn = new ObjectInputStream(new FileInputStream(memberFile))) {
            books = (List<Book>) bookIn.readObject();
            members = (List<Member>) memberIn.readObject();
        }
    }
}
