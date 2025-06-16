package model;

import java.time.LocalDate;

public class LendingRecord {
    private String recordId;
    private Book book;
    private Member member;
    private LocalDate issueDate, dueDate, returnDate;

    public LendingRecord(String recordId, Book book, Member member, LocalDate issueDate, LocalDate dueDate) {
        this.recordId = recordId;
        this.book = book;
        this.member = member;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public String getRecordId() { return recordId; }
    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void markReturned(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isOverdue() {
        return returnDate == null && dueDate.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return book.getTitle() + " issued to " + member.getName() + " (Due: " + dueDate + ")";
    }
}
