package com.example.GraphqlDemo.api;

import org.springframework.data.repository.CrudRepository;

import com.example.GraphqlDemo.domain.Book;

public interface BookRepository  extends CrudRepository<Book, Integer>{

}
