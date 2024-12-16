package com.library;

import com.library.dao.BookDAO;
import com.library.model.Book;
import com.library.service.BookService;
import com.library.util.DbConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

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
        // Ajouter d'abord un livre
        Book book = new Book("Java Programming", "John Doe", "12345", 2023);
        bookService.addBook(book);

        // Récupérer l'ID du livre ajouté
        List<Book> books = bookDAO.getAllBooks();
        int bookId = books.get(0).getId();

        // Créer un livre mis à jour
        Book bookUpdate = new Book("Advanced Java", "Jane Doe", "12345", 2025);
        bookUpdate.setId(bookId); // Important: définir l'ID pour la mise à jour

        bookService.updateBook(bookUpdate);

        Book updatedBook = bookDAO.getBookById(bookId);
        assertEquals("Advanced Java", updatedBook.getTitle());
        assertEquals("Jane Doe", updatedBook.getAuthor());
    }

    @Test
    void testDeleteBook() {
        // Ajouter d'abord un livre
        Book book = new Book("Java Programming", "John Doe", "12345", 2023);
        bookService.addBook(book);

        // Récupérer l'ID du livre ajouté
        List<Book> books = bookDAO.getAllBooks();
        int bookId = books.get(0).getId();

        // Supprimer le livre
        bookService.deleteBook(bookId);

        // Vérifier que le livre est supprimé
        assertNull(bookDAO.getBookById(bookId));
    }
}
