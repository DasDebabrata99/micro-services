package com.example.GraphqlDemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.GraphqlDemo.domain.Book;

import lombok.Getter;
import lombok.Setter;

/*
 * In the request body:   have type as Graphql  
 * all requests to be POST
 * To get all books:
 *  * query{
    getAllBooks{
        id
        title
        price
    }
    
    To get a single book:
    query{
     getBook(bookId: 3){
          id
          title
      }
    }
    
    To insert a book:
    mutation{
     createBook(book: {
         title:"my title",
         description: "my description",
         author:"I am",
         price:100,
         pages:1
     }){
         id
         title
     }
}
}
 */
@Controller
public class BookController {

	@Autowired
	BookService bookService;
	
	@QueryMapping("getAllBooks")
	public Iterable<Book> getBooks() {
		
		return bookService.getBooks();
		
	}
	
	@QueryMapping("getBook")
	public Book getBookById(@Argument int bookId) {
		return bookService.getBook(bookId).orElseThrow( ()-> new RuntimeException("Invalid book id"));
	}
	
	@MutationMapping("createBook")
	public Book createBook(@Argument BookInput book) {
		Book b = new Book();
		b.setPages(book.getPages());
	    b.setTitle(book.getTitle());
	    System.out.println(b.getTitle());
	    b.setDescription(book.getDescription());
	    b.setAuthor(book.getAuthor());
	    b.setPrice(book.getPrice());

	    return bookService.savebook(b);
	}
}

@Getter
@Setter
class BookInput{
	private int pages;
	private String title;
	private String description;
	private String author;	
	private int price;
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}