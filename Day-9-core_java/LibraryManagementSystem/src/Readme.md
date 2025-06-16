
# Library Management System

##  Overview

A simple and robust Java-based Library Management System that supports adding/removing books and members, issuing and returning books, tracking lending records, and monitoring overdue books with multithreading.

Built using core Java concepts like OOP, Exception Handling, Generics, Collections, Multithreading, Streams, and more.

---

## Features Implemented

### Java Fundamentals

* `Book`, `Member`, and `LendingRecord` classes with fields and methods.
* Enums used for `Book.Status` (AVAILABLE, ISSUED).

### Exception Handling and Generics

* Generic `Repository<T>` to handle lists of objects.
* Custom exceptions:

    * `BookNotAvailableException`
    * `MemberNotFoundException`
    * `OverdueBookException`

### Java Collections

* `HashMap<String, Book>` and `HashMap<String, Member>` to manage books and members.
* `ArrayList<LendingRecord>` for lending history.
* Search books by title/author and members by name/email.
* Sort books by title (Comparable) and author (Comparator).

### Java Multithreading

* `OverdueMonitor` thread runs every minute.
* Scans for overdue books and prints alerts to the console.

### Java Advanced Concepts

* `Comparable<Book>` for title sorting.
* Streams and Lambdas used for filtering, sorting, reporting.
* Inner classes (optional) inside `Repository<T>` if needed.

---

## Project Structure

```
src/
├── model/
│   ├── Book.java
│   ├── Member.java
│   └── LendingRecord.java
├── exception/
│   ├── BookNotAvailableException.java
│   ├── MemberNotFoundException.java
│   └── OverdueBookException.java
├── repository/
│   └── Repository.java
├── service/
│   ├── LibraryService.java
│   └── OverdueMonitor.java
└── Main.java
```

---

## Setup Instructions

1. Clone or download the project files.
2. Use any Java IDE (IntelliJ, Eclipse, VS Code) or compile via terminal:

   ```sh
   javac Main.java
   java Main
   ```


---

##  Functional Usage Examples

###  Add a Book

```java
library.addBook(new Book("B1", "Clean Code", "Robert C. Martin"));
```

###  Add a Member

```java
library.addMember(new Member("M1", "Alice", "alice@mail.com"));
library.addMember(new Member("M2", "Bob", "bob@mail.com"));
```

###  Issue a Book

```java
try {
    library.issueBook("B1", "M1");
} catch (Exception e) {
    System.out.println("Error: " + e.getMessage());
}
```

###  Return a Book

```java
library.returnBook("B1");
```

###  Search for Books by Title

```java
List<Book> foundBooks = library.searchBooksByTitle("Clean");
foundBooks.forEach(System.out::println);
```

###  Search Members by Name

```java
List<Member> foundMembers = library.searchMembersByName("Alice");
foundMembers.forEach(System.out::println);
```

###  List Available Books

```java
List<Book> available = library.getAvailableBooks();
available.forEach(System.out::println);
```

###  List Overdue Books

```java
List<LendingRecord> overdue = library.getOverdueRecords();
overdue.forEach(System.out::println);
```

###  Active Borrowers

```java
List<Member> active = library.getActiveBorrowers();
active.forEach(System.out::println);
```
---
