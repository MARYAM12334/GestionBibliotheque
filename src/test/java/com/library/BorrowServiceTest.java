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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BorrowServiceTest {
    private BorrowService borrowService;
    private BookDAO bookDAO;
    private StudentDAO studentDAO;
    private BorrowDAO borrowDAO;

    @BeforeEach
    void setUp() throws SQLException {

        DbConnection.getConnection().prepareStatement("DELETE FROM borrows").executeUpdate();
        DbConnection.getConnection().prepareStatement("DELETE FROM students").executeUpdate();
        DbConnection.getConnection().prepareStatement("DELETE FROM books").executeUpdate();

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
        Student student = studentDAO.getAllStudents().get(0);
        Book book = bookDAO.getAllBooks().get(0);

        Borrow borrow = new Borrow(student, book, new Date(), null);
        borrowService.borrowBook(borrow);

        List<Borrow> borrows = borrowDAO.getAllBorrows();
        assertFalse(borrows.isEmpty());
        assertEquals(student.getId(), borrows.get(0).getStudent().getId());
        assertEquals(book.getId(), borrows.get(0).getBook().getId());
    }

    /*@Test
    void testReturnBook() {
        // Créer un emprunt
        Student student = studentDAO.getAllStudents().get(0);
        Book book = bookDAO.getAllBooks().get(0);
        Borrow borrow = new Borrow(student, book, new Date(), null);

        // Enregistrer l'emprunt
        borrowService.borrowBook(borrow);

        // Récupérer l'emprunt depuis la base de données
        List<Borrow> borrows = borrowDAO.getAllBorrows();
        Borrow savedBorrow = borrows.get(0);

        // Retourner le livre
        borrowService.returnBook(savedBorrow);

        // Vérifier que la date de retour est définie
        Borrow returnedBorrow = borrowDAO.getAllBorrows().get(0);
        assertNotNull(returnedBorrow.getReturnDate());
    }

    @Test
    void testBorrowBookNotAvailable() {

        // Créer et enregistrer un premier emprunt
        Student student1 = studentDAO.getAllStudents().get(0);
        Book book = bookDAO.getAllBooks().get(0);
        Borrow firstBorrow = new Borrow(student1, book, new Date(), null);
        borrowService.borrowBook(firstBorrow);

        // Essayer d'emprunter le même livre avec un autre étudiant
        Student student2 = new Student("Bob");
        studentDAO.addStudent(student2);

        // Vérifier qu'on ne peut pas emprunter un livre déjà emprunté
        List<Borrow> activeBorrows = borrowDAO.getAllBorrows().stream()
                .filter(b -> b.getBook().getId() == book.getId() && b.getReturnDate() == null)
                .collect(Collectors.toList());

        assertFalse(activeBorrows.isEmpty());
    }

    @Test
    void testBorrowBookStudentNotFound() {

        // Créer un étudiant avec un ID qui n'existe pas
        Student nonExistentStudent = new Student("NonExistent");
        nonExistentStudent.setId(999); // ID qui n'existe pas

        // Obtenir un livre existant
        Book book = bookDAO.getAllBooks().get(0);

        // Créer un emprunt avec l'étudiant non existant
        Borrow borrow = new Borrow(nonExistentStudent, book, new Date(), null);

        // Vérifier que l'emprunt échoue
        assertThrows(RuntimeException.class, () -> {
            borrowService.borrowBook(borrow);
        });

    }*/
}
