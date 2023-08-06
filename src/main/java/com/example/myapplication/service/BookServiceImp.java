package com.example.myapplication.service;

import com.example.myapplication.model.Book;
import com.example.myapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService{
    @Autowired private BookRepository bookRepository;
    @Override
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Book getBook(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()) {
            return book.get();
        }
        return null;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer id) {
        boolean exist = bookRepository.existsById(id);
        if (exist) {
            bookRepository.deleteById(id);
        }
    }

    @Override
    public void editBook(Book book) {
        Book book1 = bookRepository.findById(book.getId()).map(book2 -> {
            book2.setBookName(book.getBookName());
            book2.setAuthor(book.getAuthor());
            book2.setPrice(book.getPrice());
            return bookRepository.save(book2);
        }).orElseGet(() -> {
           return bookRepository.save(book);
        });
    }
}
