package com.example.GraphqlDemo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GraphqlDemo.domain.Book;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	public Iterable<Book> getBooks() {
		return bookRepository.findAll();
	}
	
	public Optional<Book> getBook(int bookId) {
		return bookRepository.findById(bookId);
	}
	
	public Book savebook(Book book) {
		return bookRepository.save(book);
	}
}
