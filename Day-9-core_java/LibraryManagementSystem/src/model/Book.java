package model;

public class Book implements Comparable<Book> {
    public enum Status { AVAILABLE, ISSUED }

    private String bookId, title, author;
    private Status status;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.status = Status.AVAILABLE;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Status getStatus() { return status; }

    public void issue() { this.status = Status.ISSUED; }
    public void returnBook() { this.status = Status.AVAILABLE; }

    @Override
    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return title + " by " + author + " [" + bookId + "] - " + status;
    }
}
