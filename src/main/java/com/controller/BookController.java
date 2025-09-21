package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.BookEntity;
import com.repository.BookRepository;

@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@PostMapping("/books")
	public ResponseEntity<BookEntity> addBook(@RequestBody BookEntity bookEntity) {
		bookRepository.save(bookEntity);// insert
		System.out.println(bookEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(bookEntity);
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
	public ResponseEntity<BookEntity> getBookById(@PathVariable Integer bookId) {
		Optional<BookEntity> op = bookRepository.findById(bookId);

		if (op.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(op.get());
		}
	}

	// delete book -> id
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<?> removeBookById(@PathVariable Integer bookId) {
		Optional<BookEntity> op = bookRepository.findById(bookId);
		HashMap<String, Object> map = new HashMap<>();
		
		if (op.isEmpty()) {
			map.put("msg", "Invalid Id");
			map.put("bookId", bookId);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		} else {
			map.put("data", op.get());
			map.put("msg", "success");
			map.put("code", 1);
			bookRepository.deleteById(bookId);
			return ResponseEntity.status(HttpStatus.OK).body(map);
		}
	}

	// update
	@PutMapping("/books/{bookId}")
	public BookEntity updateBook(@PathVariable Integer bookId, @RequestBody BookEntity bookEntity) {
		Optional<BookEntity> op = bookRepository.findById(bookId);
		if (op.isEmpty()) {
			return null;
		} else {
			// update
			BookEntity dbBook = op.get();
			dbBook.setPrice(bookEntity.getPrice());
			dbBook.setAuthorName(bookEntity.getAuthorName());

			bookRepository.save(dbBook);// save -> update -> id ?
			return dbBook;

		}

	}

}
