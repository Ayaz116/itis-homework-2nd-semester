package ru.itis.library;

import ru.itis.library.exceptions.DbException;
import ru.itis.library.model.Book;
import ru.itis.library.model.GivenBook;
import ru.itis.library.model.User;
import ru.itis.library.reposiroty.impl.BookRepository;
import ru.itis.library.reposiroty.impl.GivenBookRepository;
import ru.itis.library.reposiroty.impl.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static BookRepository bookRepository;
    private static GivenBookRepository givenBookRepository;
    private static UserRepository userRepository;

    public static void main(String[] args) throws DbException {
        bookRepository =  new BookRepository();
        givenBookRepository =  new GivenBookRepository();
        userRepository =  new UserRepository();

        while (true) {
            String command = scanner.nextLine();
            List<GivenBook> givenBooks = givenBookRepository.getAll();
            List<Book> books = bookRepository.getAll();
            List<User> users = userRepository.getAll();
            switch (command) {
                case "":
                    break;
                case "help":
                    ru.itis.library.BooksHelp.printHelp();
                    break;
                case "readAllBooks":
                    printBooks(books);
                    break;
                case "readAllUsers":
                    printUsers(users);
                    break;
                case "readGivenBooks":
                    printGivenBooks(givenBooks);
                    break;
                case "addBook":
                    addBook();
                    break;
                case "addUser":
                    addUser();
                    break;
                case "giveBook":
                    giveBook();
                    break;
                case "returnBook":
                    returnBook();
                    break;
                case "freeBooks":
                    freeBooks(givenBooks, books);
                    break;
                case "debtors":
                    debtors(givenBooks, users);
                    break;
                case "quit":
                    System.out.println("Good bye");
                    return;
                default:
                    System.out.println("Unknown command");
            }
        }
    }
    private static void debtors(List<GivenBook> Books, List<User> Users) {
        for(GivenBook Book: Books){
            long monthsBetween = Book.getCreatedDate() .until(LocalDate.now(), ChronoUnit.MONTHS);
            if (monthsBetween >= 1){
                System.out.println(Users.get(Book.getUserId()));
            }
        }
    }

    private static void freeBooks(List<GivenBook> GivenBooks, List<Book> Books) {
        int n;
        boolean f;
        for (Book book : Books) {
            f = true;
            n = book.getId();
            for (GivenBook books : GivenBooks) {
                if (n == books.getBookId()) {
                    f = false;
                    break;
                }
            }
            if (f) {
                System.out.println(book);;
            }
        }
    }

    private static void returnBook() throws DbException{
        System.out.println("Введите id читателя: ");
        int userId = scanner.nextInt();
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            System.out.println("Такого пользователя нет.");
            return;
        }
        User user = optionalUser.get();

        System.out.println("Введите id книги: ");
        int bookId = scanner.nextInt();
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()) {
            System.out.println("Такой книги нет.");
            return;
        }
        Book book = optionalBook.get();
        returnBook(book);
    }
    public static void returnBook(Book book) throws DbException {
        givenBookRepository.deleteById(book.getId());
        System.out.println("Книга возвращена.");
    }
    private static void addBook() throws DbException {
        System.out.println("Введите название книги: ");
        String name = scanner.nextLine();
        System.out.println("Введите автора книги: ");
        String author = scanner.nextLine();
        Book book = new Book(null, author, name);
        Book savedBook = bookRepository.save(book);
        System.out.println("Книга сохранена с id " + savedBook.getId());
    }

    private static void addUser() throws DbException {
        System.out.println("Введите имя читателя: ");
        String name = scanner.nextLine();
        System.out.println("Введите возраст читателя: ");
        int age = scanner.nextInt();
        User user = new User(null, name, age);
        User savedUser = userRepository.save(user);
        System.out.println("Сохранен читатель с id " + savedUser.getId());
    }

    private static void giveBook() throws DbException {
        System.out.println("Введите id читателя: ");
        int userId = scanner.nextInt();
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            System.out.println("Такого пользователя нет.");
            return;
        }
        User user = optionalUser.get();

        System.out.println("Введите id книги: ");
        int bookId = scanner.nextInt();
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()) {
            System.out.println("Такой книги нет.");
            return;
        }
        Book book = optionalBook.get();
        giveBook(user, book, LocalDate.now());
    }

    public static void giveBook(User user, Book book, LocalDate date) throws DbException {
        GivenBook givenBook = new GivenBook(null, user.getId(), book.getId(), date);
        GivenBook savedGivenBook = givenBookRepository.save(givenBook);
        System.out.println("Книга выдана. Id записи: " + savedGivenBook.getId());
    }

    private static void printBooks(List<Book> books) {
        for(Book book: books) {
            System.out.println(book);
        }
    }

    private static void printUsers(List<User> users) {
        for(User user: users) {
            System.out.println(user);
        }
    }

    private static void printGivenBooks(List<GivenBook> givenBooks) throws DbException {
        for(GivenBook givenBook: givenBooks) {
            Optional<Book> optionalBook = bookRepository.findById(givenBook.getBookId());
            Optional<User> optionalUser = userRepository.findById(givenBook.getUserId());
            if(optionalBook.isEmpty() || optionalUser.isEmpty()) {
                throw new DbException("Data corrupted");
            }
            Book book = optionalBook.get();
            User user = optionalUser.get();
            System.out.println("id: " + givenBook.getId() + ". Книга: "
                    + book.getName() + ". Читатель: " + user + ". Дата выдачи: "
                    + givenBook.getCreatedDate());
        }
    }
}
