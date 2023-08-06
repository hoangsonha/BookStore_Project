package com.example.myapplication.service;

import com.example.myapplication.model.Book;
import com.example.myapplication.model.MyBook;
import com.example.myapplication.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookServiceImp implements MyBookService{
    @Autowired private MyBookRepository myBookRepository;
    @Override
    public void saveMyBook(MyBook myBook) {

        myBookRepository.save(myBook);
    }

    @Override
    public List<MyBook> getAllMyBooks() {
        return myBookRepository.findAll();
    }

    @Override
    public void deleteMyBook(Integer id) {
        boolean exist = myBookRepository.existsById(id);
        if(exist) {
            myBookRepository.deleteById(id);
        }
    }
}
