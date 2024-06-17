import java.io.IOException;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);
    private static final String BOOK_FILE = "books.txt";
    private static final String MEMBER_FILE = "members.txt";

    public static void main(String[] args) {
        try {
            library.loadData(BOOK_FILE, MEMBER_FILE);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing data found. Starting with an empty library.");
        }

        int choice;
        do {
            System.out.println("Library Management System");
            System.out.println("1. Add a new book");
            System.out.println("2. Remove a book");
            System.out.println("3. Search for a book");
            System.out.println("4. List all books");
            System.out.println("5. Add a new member");
            System.out.println("6. Remove a member");
            System.out.println("7. Search for a member");
            System.out.println("8. List all members");
            System.out.println("9. Issue a book");
            System.out.println("10. Return a book");
            System.out.println("11. List all borrowed books");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    listBooks();
                    break;
                case 5:
                    addMember();
                    break;
                case 6:
                    removeMember();
                    break;
                case 7:
                    searchMember();
                    break;
                case 8:
                    listMembers();
                    break;
                case 9:
                    issueBook();
                    break;
                case 10:
                    returnBook();
                    break;
                case 11:
                    listBorrowedBooks();
                    break;
                case 0:
                    saveAndExit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private static void addBook() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Enter available copies: ");
        int availableCopies = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Book book = new Book(title, author, ISBN, availableCopies);
        library.addBook(book);
        System.out.println("Book added successfully.");
    }

    private static void removeBook() {
        System.out.print("Enter ISBN: ");
        String ISBN = scanner.nextLine();
        library.removeBook(ISBN);
        System.out.println("Book removed successfully.");
    }

    private static void searchBook() {
        System.out.println("Search by: 1. Title 2. Author 3. ISBN");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                Book bookByTitle = library.findBookByTitle(title);
                System.out.println(bookByTitle != null ? bookByTitle : "Book not found.");
                break;
            case 2:
                System.out.print("Enter author: ");
                String author = scanner.nextLine();
                Book bookByAuthor = library.findBookByAuthor(author);
                System.out.println(bookByAuthor != null ? bookByAuthor : "Book not found.");
                break;
            case 3:
                System.out.print("Enter ISBN: ");
                String ISBN = scanner.nextLine();
                Book bookByISBN = library.findBookByISBN(ISBN);
                System.out.println(bookByISBN != null ? bookByISBN : "Book not found.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void listBooks() {
        System.out.println("Listing all books:");
        for (Book book : library.listAllBooks()) {
            System.out.println(book);
        }
    }

    private static void addMember() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Member member = new Member(name, memberId, email);
        library.addMember(member);
        System.out.println("Member added successfully.");
    }

    private static void removeMember() {
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();
        library.removeMember(memberId);
        System.out.println("Member removed successfully.");
    }

    private static void searchMember() {
        System.out.println("Search by: 1. Name 2. Member ID");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                Member memberByName = library.findMemberByName(name);
                System.out.println(memberByName != null ? memberByName : "Member not found.");
                break;
            case 2:
                System.out.print("Enter member ID: ");
                String memberId = scanner.nextLine();
                Member memberById = library.findMemberById(memberId);
                System.out.println(memberById != null ? memberById : "Member not found.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void listMembers() {
        System.out.println("Listing all members:");
        for (Member member : library.listAllMembers()) {
            System.out.println(member);
        }
    }

    private static void issueBook() {
        try {
            System.out.print("Enter ISBN: ");
            String ISBN = scanner.nextLine();
            System.out.print("Enter member ID: ");
            String memberId = scanner.nextLine();
            library.issueBook(ISBN, memberId);
            System.out.println("Book issued successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void returnBook() {
        try {
            System.out.print("Enter ISBN: ");
            String ISBN = scanner.nextLine();
            System.out.print("Enter member ID: ");
            String memberId = scanner.nextLine();
            library.returnBook(ISBN, memberId);
            System.out.println("Book returned successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listBorrowedBooks() {
        System.out.println("Listing all borrowed books:");
        for (Member member : library.listAllMembers()) {
            if (!member.getBorrowedBooks().isEmpty()) {
                System.out.println("Member: " + member.getName());
                for (Book book : member.getBorrowedBooks()) {
                    System.out.println(" - " + book);
                }
            }
        }
    }

    private static void saveAndExit() {
        try {
            library.saveData(BOOK_FILE, MEMBER_FILE);
            System.out.println("Data saved successfully. Exiting...");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
        System.exit(0);
    }
}
