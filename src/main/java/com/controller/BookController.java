package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.BookEntity;
import com.repository.BookRepository;

@RestController
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@PostMapping("/books")
	public BookEntity addBook(@RequestBody BookEntity bookEntity) {
		bookRepository.save(bookEntity);// insert
		System.out.println(bookEntity);
		return bookEntity;
	}

	// read all books
	@GetMapping("/books")
	public List<BookEntity> getAllBooks() {
		List<BookEntity> books = bookRepository.findAll();
		return books;
	}

	// http://localhost:9999/books/3
	// read single book -> id
	@GetMapping("/books/{bookId}")
	public BookEntity getBookById(@PathVariable Integer bookId) {
		Optional<BookEntity> op = bookRepository.findById(bookId);

		if (op.isEmpty()) {
			return null;
		} else {
			return op.get();
		}
	}

	// delete book -> id
	@DeleteMapping("/books/{bookId}")
	public BookEntity removeBookById(@PathVariable Integer bookId) {
		Optional<BookEntity> op = bookRepository.findById(bookId);

		if (op.isEmpty()) {
			return null;
		} else {
			bookRepository.deleteById(bookId);
			return op.get();
		}

	}

}
