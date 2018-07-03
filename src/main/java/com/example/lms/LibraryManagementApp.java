package com.example.lms;

import com.example.lms.dao.*;
import com.example.lms.model.*;
import com.example.lms.service.BookService;
import com.example.lms.service.UserService;
import com.example.lms.utils.DateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LibraryManagementApp {

    public static void main(String[] args) throws IOException {




        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        User user;

        //BookDao bookDao = new InMemoryBookDaoImpl();

        BookDao bookDao = new MySQLBookDaoImpl();
        BookService bookService = new BookService(bookDao);

        //UserBookDao userBookDao = new InMemoryUserBookDaoImpl();

        UserBookDao userBookDao = new MySQLUserBookDaoImpl();

        //UserDao userDao = new InMemoryUserDaoImpl();

        UserDao userDao = new MySQLUserDaoImpl();
        UserService userService = new UserService(userDao, userBookDao, bookDao);


        String username, password;


        Integer response = 0;

        do {
            System.out.println("1. Login");
            System.out.println("0. Exit");

            response = Integer.parseInt(reader.readLine());

            switch (response) {
                case 0: {
                    System.out.println("Operation selected: Exit the App");
                    break;
                }
                case 1: {
                    System.out.println("Operation selected: Login to the App");
                    System.out.println("Username: ");
                    username = reader.readLine();
                    System.out.println("Password: ");
                    password = reader.readLine();
                    user = userService.authenticate(username, password);

                    Integer resp = 0;
                    if (user != null) {
                        System.out.println("Login Successful...");

                        do {
                            if (user instanceof Admin) {

                                System.out.println("Admin logged in \n");
                                System.out.println("0. Exit");
                                System.out.println("1. ViewUsers");
                                System.out.println("2. ViewBooks");
                                System.out.println("3. AddBook");
                                System.out.println("4. AddStudent");
                                System.out.println("5. AddTeacher");

                                resp = Integer.parseInt(reader.readLine());

                                switch (resp) {
                                    case 0: {
                                        System.out.println("Operation selected: Exit");
                                        break;
                                    }
                                    case 1: {
                                        System.out.println("Operation selected: View All Users");
                                        Collection<User> users = userService.findAll();
                                        users.forEach(System.out::println);
                                        break;
                                    }
                                    case 2: {
                                        System.out.println("Operation selected: View All Books");
                                        Collection<Book> books = bookService.findAll();
                                        books.forEach(System.out::println);
                                        break;
                                    }
                                    case 3: {
                                        System.out.println("Operation selected: Add Books");

                                        String name, publisher, edition, language, category;
                                        System.out.println("Name : ");
                                        name = reader.readLine();
                                        System.out.println("Publisher : ");
                                        publisher = reader.readLine();
                                        System.out.println("Edition : ");
                                        edition = reader.readLine();
                                        System.out.println("Language : ");
                                        language = reader.readLine();
                                        System.out.println("Category : ");
                                        category = reader.readLine();

                                        Book newBook = new Book(name, publisher, edition, language, category);
                                        newBook = bookService.save(newBook);
                                        System.out.println(newBook);
                                        break;
                                    }
                                    case 4: {
                                        System.out.println("Operation selected: Add Student");

                                        String name, usernme, passwd, email, gender, batch, branch, regNo, mobileNo;
                                        Integer rollNo;
                                        System.out.println("Name : ");
                                        name = reader.readLine();
                                        System.out.println("Username : ");
                                        usernme = reader.readLine();
                                        System.out.println("Email : ");
                                        email = reader.readLine();
                                        System.out.println("Registration No : ");
                                        regNo = reader.readLine();
                                        System.out.println("Mobile No : ");
                                        mobileNo = reader.readLine();
                                        System.out.println("Gender: ");
                                        gender = reader.readLine();
                                        System.out.println("Password: ");
                                        passwd = reader.readLine();
                                        System.out.println("Batch: ");
                                        batch = reader.readLine();
                                        System.out.println("Branch: ");
                                        branch = reader.readLine();
                                        System.out.println("Roll No : ");
                                        rollNo = Integer.parseInt(reader.readLine());
                                        User student = new Student(name, usernme, email, regNo, mobileNo, gender, passwd, "STUDENT", batch, branch, rollNo);
                                        student = userService.save(student);
                                        System.out.println(student);
                                        break;
                                    }
                                    case 5: {
                                        System.out.println("Operation selected: Add Librarian");

                                        String name, usernme, passwd, email, gender, department, date, regNo, mobileNo;
                                        System.out.println("Name : ");
                                        name = reader.readLine();
                                        System.out.println("Username : ");
                                        usernme = reader.readLine();
                                        System.out.println("Email : ");
                                        email = reader.readLine();
                                        System.out.println("Registration No : ");
                                        regNo = reader.readLine();
                                        System.out.println("Mobile No : ");
                                        mobileNo = reader.readLine();
                                        System.out.println("Gender: ");
                                        gender = reader.readLine();
                                        System.out.println("Password: ");
                                        passwd = reader.readLine();
                                        System.out.println("Department: ");
                                        department = reader.readLine();
                                        System.out.println("Date: ");
                                        date = reader.readLine();

                                        User librarian = new Teacher(name, usernme, email, regNo, mobileNo, gender, passwd, "TEACHER", department, date, Boolean.TRUE);
                                        librarian = userService.save(librarian);
                                        System.out.println(librarian);
                                        break;
                                    }
                                    case 6: {
                                        System.out.println("Operation selected: Add Teacher");

                                        String name, usernme, passwd, email, gender, department, date, regNo, mobileNo;
                                        boolean isLibrarian;
                                        System.out.println("Name : ");
                                        name = reader.readLine();
                                        System.out.println("Username : ");
                                        usernme = reader.readLine();
                                        System.out.println("Password: ");
                                        passwd = reader.readLine();
                                        System.out.println("Email : ");
                                        email = reader.readLine();
                                        System.out.println("Registration No : ");
                                        regNo = reader.readLine();
                                        System.out.println("Mobile No : ");
                                        mobileNo = reader.readLine();
                                        System.out.println("Gender: ");
                                        gender = reader.readLine();
                                        System.out.println("Password: ");
                                        passwd = reader.readLine();
                                        System.out.println("Department: ");
                                        department = reader.readLine();
                                        System.out.println("Date: ");
                                        date = reader.readLine();
                                        System.out.println("isLibrarian: ");

                                        User teacher = new Teacher(name, usernme, email, regNo, mobileNo, gender, passwd, "TEACHER", department, date, Boolean.FALSE);
                                        teacher = userService.save(teacher);
                                        System.out.println(teacher);
                                        break;
                                    }
                                    default:
                                        System.out.println("Incorrect operation selected");
                                }

                            } else if (user instanceof Teacher) {

                                if (userService.isLibrarian(user.getId())) {

                                    System.out.println("Librarian logged in\n");

                                    System.out.println("0. Exit");
                                    System.out.println("1. ViewBooks");
                                    System.out.println("2. AddBooks");
                                    System.out.println("3. IssueBook to Students/Teacher");
                                    System.out.println("4. Return book to Library");

                                    resp = Integer.parseInt(reader.readLine());

                                    switch (resp) {
                                        case 0: {
                                            System.out.println("Operation selected: Exit");
                                            break;
                                        }
                                        case 1: {
                                            System.out.println("Operation selected: View All Books");
                                            Collection<Book> books = bookService.findAll();
                                            books.forEach(System.out::println);
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Operation selected: Add Books");

                                            String name, publisher, edition, language, category;
                                            System.out.println("Name : ");
                                            name = reader.readLine();
                                            System.out.println("Publisher : ");
                                            publisher = reader.readLine();
                                            System.out.println("Edition : ");
                                            edition = reader.readLine();
                                            System.out.println("Language : ");
                                            language = reader.readLine();
                                            System.out.println("Category : ");
                                            category = reader.readLine();

                                            Book newBook = new Book(name, publisher, edition, language, category);
                                            newBook = bookService.save(newBook);
                                            System.out.println(newBook);
                                            break;

                                        }
                                        case 3: {
                                            System.out.println("Operation selected: IssueBook");
                                            System.out.println("Provide User name");
                                            String issuingUser = reader.readLine();

                                            User user1 = userService.findUser(issuingUser);

                                            System.out.println("All Books");
                                            Collection<Book> books1 = bookService.findAll();
                                            Map<Long, Book> map = books1.stream().collect(Collectors.toMap(Book::getId, Function.identity()));
                                            map.forEach((k,v)-> System.out.println( k + " : " + v.getName()));

                                            Collection<String> books = new ArrayList<>();
                                            System.out.println("Enter book id(s)");
                                            String book11 = reader.readLine();
                                            String[] book_ids_tobeIssued1 = book11.split(",");
                                            for (int k = 0; k < book_ids_tobeIssued1.length; k++) {
                                                System.out.println(book_ids_tobeIssued1[k]);
                                                books.add(map.get(Long.valueOf(book_ids_tobeIssued1[k])).getName());
                                            }
                                            userService.issueBook(user1, books);
                                            break;
                                        }
                                        case 4: {
                                            System.out.println("Operation selected: Return Book");
                                            System.out.println("Provide User name");
                                            String issuingUser = reader.readLine();
                                            User user1 = userService.findUser(issuingUser);
                                            List<Book> books = userService.findUserBooks(user1);
                                            Map<Long, Book> map = books.stream().collect(Collectors.toMap(Book::getId, Function.identity()));
                                            map.forEach((k,v)-> System.out.println( k + " : " + v.getName()));

                                            List<Book> books2 = new ArrayList<>();

                                            System.out.println("Enter book id(s)");
                                            String book11 = reader.readLine();
                                            String[] book_ids_tobeIssued1 = book11.split(",");
                                            for (int k = 0; k < book_ids_tobeIssued1.length; k++) {
                                                System.out.println(book_ids_tobeIssued1[k]);
                                                books2.add(map.get(Long.valueOf(book_ids_tobeIssued1[k])));
                                            }
                                            userService.returnBook(user1, books2);
                                            break;
                                        }
                                        default:
                                            System.out.println("Incorrect operation selected");
                                    }

                                } else {

                                    System.out.println("Teacher logged in");

                                    System.out.println("0. Exit");
                                    System.out.println("1. ViewBooks");
                                    System.out.println("2. IssueBook");

                                    resp = Integer.parseInt(reader.readLine());

                                    switch (resp) {
                                        case 0: {
                                            System.out.println("Operation selected: Exit");
                                            break;
                                        }
                                        case 1: {
                                            System.out.println("Operation selected: View All Books");
                                            Collection<Book> books = bookService.findAll();
                                            books.forEach(System.out::println);
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Operation selected: IssueBook");
                                            //userService.issueBook();
                                            break;

                                        }
                                        default:
                                            System.out.println("Incorrect operation selected");
                                    }
                                }
                            } else if (user instanceof Student) {

                                System.out.println("Student logged in \n");

                                System.out.println("0. Exit");
                                System.out.println("1. ViewBooks");
                                System.out.println("2. My Books");


                                resp = Integer.parseInt(reader.readLine());

                                switch (resp) {
                                    case 0: {
                                        System.out.println("Operation selected: Exit");
                                        break;
                                    }
                                    case 1: {
                                        System.out.println("Operation selected: View All Books");
                                        Collection<Book> books = bookService.findAll();
                                        books.forEach(System.out::println);
                                        break;
                                    }
                                    case 2: {
                                        System.out.println("Operation selected: View My Books");
                                        User user1 = userService.findUser(user.getUsername());
                                        List<Book> books = userService.findUserBooks(user1);
                                        Map<Long, Book> map = books.stream().collect(Collectors.toMap(Book::getId, Function.identity()));
                                        map.forEach((k,v)-> System.out.println( k + " : " + v.getName()));
                                        break;
                                    }
                                    default:
                                        System.out.println("Incorrect operation selected");
                                }
                            }

                        } while (resp != 0);
                    } else {
                        System.out.println("Login failed. Try Again...");
                    }

                    break;
                }

                default:
                    System.out.println("Invalid Operation selected");
            }
        } while (response != 0);

    }

}


