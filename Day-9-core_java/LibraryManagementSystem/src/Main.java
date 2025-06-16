import model.*;
import service.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        LibraryService library = new LibraryService();

        library.addBook(new Book("B1", "Java 101", "James Gosling"));
        library.addBook(new Book("B2", "Effective Java", "Joshua Bloch"));

        library.addMember(new Member("M1", "Alice", "alice@mail.com"));
        library.addMember(new Member("M2", "Bob", "bob@mail.com"));

        library.issueBook("B1", "M1");

        Thread monitor = new OverdueMonitor(library);
        monitor.start();

        List<Book> available = library.getAvailableBooks();
        available.forEach(System.out::println);
    }
}
