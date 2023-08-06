package com.example.myapplication.service;

import com.example.myapplication.model.Book;
import com.example.myapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService {
    public List<Book> getAllBooks();
    public Book getBook(Integer id);
    public Book saveBook(Book book);

    public void deleteBook(Integer id);

    public void editBook(Book book);

}
