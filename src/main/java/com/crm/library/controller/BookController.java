package com.crm.library.controller;

import com.crm.library.entity.Book;
import com.crm.library.entity.User;
import com.crm.library.service.BookService;
import com.crm.library.service.UserService;
import com.crm.library.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private UserService userService;

    @Autowired
    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/listofbooks")
    public String listOfAvailableBooks(Model model) {

        List<Book> availableBooks = bookService.findAllWithStatus(Status.DOSTEPNY);
        List<Book> rentedBooks = bookService.findAllWithStatus(Status.WYPOZYCZONY);
        List<Book> pendingBook = bookService.findAllWithStatus(Status.OCZEKUJACY);
        List<User> users = userService.findAllByActive();
        Book book = new Book();
        model.addAttribute("availableBooks", availableBooks);
        model.addAttribute("rentedBooks", rentedBooks);
        model.addAttribute("pendingBook", pendingBook);
        model.addAttribute("book", book);
        model.addAttribute("users", users);

        return "books/allBooks";
    }

    @GetMapping("/addBook")
    public String addBookForm(Model model) {
        Book book = new Book(Status.DOSTEPNY);

        model.addAttribute("book", book);

        return "books/bookForm";
    }

    @GetMapping("/editBook")
    public String editBookForm(@RequestParam("id") int id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);

        return "books/bookForm";
    }

    @PostMapping("/reserve")
    public String reservationCommit(@ModelAttribute("book") Book book, @RequestParam(name = "userId", required = false) String id) {


        switch (book.getStatus()){
            case OCZEKUJACY:{
                //po zastosowaniu systemu logowania tę wartość pobierać z zalogowanego użytkownika
                int loggedUserId = 1;
                User user = userService.findById(loggedUserId);

                book.setUser(user);

                bookService.save(book);
                break;
            }

            case DOSTEPNY:{
                bookService.save(book);
                break;
            }

            case WYPOZYCZONY:{
                book.setUser(userService.findById(Integer.parseInt(id)));
                bookService.save(book);
                break;
            }
        }


        return "redirect:/books/listofbooks";
    }

    @PostMapping("/saveBook")
    public String save(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/books/listofbooks";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        bookService.delete(id);
        return "redirect:/books/listofbooks";
    }
}
