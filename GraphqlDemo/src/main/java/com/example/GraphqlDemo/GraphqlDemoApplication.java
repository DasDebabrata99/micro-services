package com.example.GraphqlDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.GraphqlDemo.api.BookService;
import com.example.GraphqlDemo.domain.Book;

@SpringBootApplication
public class GraphqlDemoApplication implements CommandLineRunner{

	@Autowired
	BookService bookService;
	
	public static void main(String[] args) {
		SpringApplication.run(GraphqlDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book b1 = new Book();
		b1.setAuthor( "ABC");
		b1.setTitle("CompleteReference");
		b1.setDescription("Starting to learn Java");
		b1.setPages( 3000);
		b1.setPrice(300);
		
		Book b2 = new Book();
		b2.setAuthor("XYZ");
		b2.setTitle("Think Java");
		b2.setDescription( "learn Java concepts");
		b2.setPages( 2000);
		b2.setPrice(200);
		
		Book b3 = new Book();
		b3.setAuthor("PQR");
		b3.setTitle( "Head First Java");
		b3.setDescription(  "New to Java");
		b3.setPages( 1000);
		b3.setPrice(400);
	
		bookService.savebook(b1);
		bookService.savebook(b2);
		bookService.savebook(b3);
	}

}
