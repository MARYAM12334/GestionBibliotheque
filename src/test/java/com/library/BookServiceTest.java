package com.library;

import com.library.dao.BookDAO;
import com.library.model.Book;
import com.library.service.BookService;
import com.library.util.DbConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookService bookService;
    private BookDAO bookDAO;

    @BeforeEach
    void setUp() throws SQLException {
        bookDAO = new BookDAO(DbConnection.getConnection());
        bookService = new BookService(bookDAO);
    }

    @Test
    void testAddBook() {
        Book book = new Book("Java Programming", "John Doe","12345", 2023);
        bookService.addBook(book);
        assertEquals(1, bookDAO.getAllBooks().size());
        assertEquals("Java Programming", bookDAO.getBookById(1).getTitle());
    }

    @Test
    void testUpdateBook() {
        Book book = new Book("Java Programming", "John Doe","12345", 2023);
        bookService.addBook(book);
        Book bookUpdate = new Book("Advanced Java", "Jane Doe","12345", 2025);
        bookService.updateBook(bookUpdate);
        assertEquals("Advanced Java", bookDAO.getBookById(1).getTitle());
        //assertFalse(bookDAO.getBookById(1).isAvailable());
        assertEquals(1, bookDAO.getAllBooks().size());
        assertEquals("Java Programming", bookDAO.getBookById(1).getTitle());
    }

    @Test
    void testDeleteBook() {
        Book book = new Book("Java Programming", "John Doe","12345", 2023);
        bookService.addBook(book);
        bookService.deleteBook(1);
        //assertTrue(bookDAO.getBookById(1).isEmpty());
        assertNull(bookDAO.getBookById(1));
    }
}
