package service;

import exception.*;
import model.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {
    private final Map<String, Book> bookMap = new HashMap<>();
    private final Map<String, Member> memberMap = new HashMap<>();
    private final List<LendingRecord> records = new ArrayList<>();

    public void addBook(Book book) { bookMap.put(book.getBookId(), book); }
    public void addMember(Member member) { memberMap.put(member.getMemberId(), member); }

    public void issueBook(String bookId, String memberId) throws Exception {
        Book book = bookMap.get(bookId);
        Member member = memberMap.get(memberId);

        if (book == null || member == null)
            throw new MemberNotFoundException("Book or Member not found.");
        if (book.getStatus() == Book.Status.ISSUED)
            throw new BookNotAvailableException("Book is already issued.");

        boolean hasOverdue = records.stream()
                .anyMatch(r -> r.getMember().equals(member) && r.isOverdue());
        if (hasOverdue)
            throw new OverdueBookException("Member has overdue books.");

        book.issue();
        records.add(new LendingRecord(UUID.randomUUID().toString(), book, member,
                LocalDate.now(), LocalDate.now().plusDays(14)));
    }

    public void returnBook(String bookId) {
        records.stream()
                .filter(r -> r.getBook().getBookId().equals(bookId) && r.getReturnDate() == null)
                .findFirst().ifPresent(r -> {
                    r.markReturned(LocalDate.now());
                    r.getBook().returnBook();
                });
    }

    public List<Book> getAvailableBooks() {
        return bookMap.values().stream()
                .filter(b -> b.getStatus() == Book.Status.AVAILABLE)
                .collect(Collectors.toList());
    }

    public List<Member> getActiveBorrowers() {
        return records.stream()
                .filter(r -> r.getReturnDate() == null)
                .map(LendingRecord::getMember)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<LendingRecord> getOverdueRecords() {
        return records.stream().filter(LendingRecord::isOverdue).collect(Collectors.toList());
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookMap.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Member> searchMembersByName(String name) {
        return memberMap.values().stream()
                .filter(m -> m.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksSortedByAuthor() {
        return bookMap.values().stream()
                .sorted(Comparator.comparing(Book::getAuthor))
                .collect(Collectors.toList());
    }
}
