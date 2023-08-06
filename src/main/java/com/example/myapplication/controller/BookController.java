package com.example.myapplication.controller;

import com.example.myapplication.model.Book;
import com.example.myapplication.model.MyBook;
import com.example.myapplication.service.BookService;
import com.example.myapplication.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {
    @Autowired private BookService bookService;

    @Autowired private MyBookService myBookService;

    @GetMapping("")
    public String showHomePage() {
        return "home_page";
    }

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("bookList", bookList);
        return "list_book";
    }

    @GetMapping("/books/new")
    public String showAddBookPage(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("title", "New Book Register");
        return "add_book";
    }
    @PostMapping("/books/save")
    public String saveBook(Book book, Model model) {

        model.addAttribute("book",  bookService.saveBook(book));
        return "redirect:/books";
    }
    @GetMapping("/books/mybooks")
    public String myBookPage(Model model) {
        List<MyBook> myBookList = myBookService.getAllMyBooks();
        model.addAttribute("myBookList", myBookList);
        return "my_book";
    }

    @GetMapping("/mybooks/save/{id}")
    public String showMyBook(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {

        Book book = bookService.getBook(id);

        MyBook myBook = new MyBook(book.getId(), book.getBookName(), book.getAuthor(), book.getPrice());

        myBookService.saveMyBook(myBook);
        redirectAttributes.addFlashAttribute("message", "Add to my Book successfully with id " + id);
        return "redirect:/books";
    }
    @GetMapping("mybooks/delete/{id}")
    public String deleteMyBook(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        myBookService.deleteMyBook(id);
        redirectAttributes.addFlashAttribute("message1", "Delete successfully");
        return "redirect:/books/mybooks";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteBook(id);
            redirectAttributes.addFlashAttribute("message", "Delete successfully id " + id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/books";
    }
    @GetMapping("/book/edit/{id}")
    public String editBook(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Book book = bookService.getBook(id);
            model.addAttribute("book", book);
            model.addAttribute("title", "Edit Book with id " + id);
            return "add_book";
        }catch (Exception e) {
            return "redirect:/books";
        }
    }
}
