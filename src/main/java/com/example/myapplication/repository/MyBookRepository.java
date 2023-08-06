package com.example.myapplication.repository;

import com.example.myapplication.model.MyBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBookRepository extends JpaRepository<MyBook, Integer> {
}
