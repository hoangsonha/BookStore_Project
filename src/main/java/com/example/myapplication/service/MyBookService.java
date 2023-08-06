package com.example.myapplication.service;

import com.example.myapplication.model.Book;
import com.example.myapplication.model.MyBook;

import java.util.List;

public interface MyBookService {
    public void saveMyBook(MyBook myBook);

    public List<MyBook> getAllMyBooks();

    public void deleteMyBook(Integer id);


}
