package com.library;

import com.library.dao.BookDAO;
import com.library.dao.BorrowDAO;
import com.library.dao.StudentDAO;
import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.service.BorrowService;
import com.library.util.DbConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BorrowServiceTest {
    private BorrowService borrowService;
    private BookDAO bookDAO;
    private StudentDAO studentDAO;

    @BeforeEach
    void setUp() throws SQLException {
        bookDAO = new BookDAO(DbConnection.getConnection());
        studentDAO = new StudentDAO(DbConnection.getConnection());

        borrowService = new BorrowService(new BorrowDAO(DbConnection.getConnection()));

        // Ajouter un étudiant
        studentDAO.addStudent(new Student("Alice", "alice@example.com"));
        studentDAO.addStudent(new Student("Bob", "bob@example.com"));

        // Ajouter des livres
        bookDAO.add(new Book( "Java Programming", "John Doe", "12335",2023));
        bookDAO.add(new Book("Advanced Java", "Jane Doe","12334", 2023));
    }

    @Test
    void testBorrowBook() {
        //TODO
        //assertEquals("Livre emprunté avec succès!", borrowService.borrowBook(new Borrow(1,1)));
        //assertFalse(bookDAO.getBookById(1).isAvailable());
    }

    @Test
    void testReturnBook() {
        //TODO
//        borrowService.borrowBook(1, 1);
//        assertEquals("Livre retourné avec succès!", borrowService.returnBook(1, 1));
//        assertTrue(bookDAO.getBookById(1).get().isAvailable());
    }

    @Test
    void testBorrowBookNotAvailable() {
        //TODO
//        borrowService.borrowBook(1, 1);
//        assertEquals("Le livre n'est pas disponible.", borrowService.borrowBook(2, 1));
    }

    @Test
    void testBorrowBookStudentNotFound() {
        //TODO
//        assertEquals("Étudiant ou livre non trouvé.", borrowService.borrowBook(3, 1));
    }
}
