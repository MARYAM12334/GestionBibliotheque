
package com.library.dao;

import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO {


    private final Connection connection;

    public BorrowDAO(Connection connection) {
        this.connection = connection;
    }


    //changer a méthode getAllBorrows pour utiliser la connection du constructeur

    public List<Borrow> getAllBorrows() {
        List<Borrow> borrows = new ArrayList<>();
        String query = "SELECT * FROM borrows";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int idStudent = rs.getInt("student_id");
                int bookId = rs.getInt("book_id");
                Student student = new StudentDAO(connection).getStudentById(idStudent);
                Book book = new BookDAO(connection).getBookById(bookId);

                Borrow borrow = new Borrow(
                        student,
                        book,
                        rs.getDate("borrow_date"),
                        rs.getDate("return_date")
                );
                borrow.setId(rs.getInt("id"));
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all borrows", e);
        }
        return borrows;
    }

    //completer la méthode save
    public void save(Borrow borrow) {
        String query = "INSERT INTO borrows (student_id, book_id, borrow_date, return_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, borrow.getStudent().getId());
            stmt.setInt(2, borrow.getBook().getId());
            stmt.setDate(3, new java.sql.Date(borrow.getBorrowDate().getTime()));
            stmt.setDate(4, borrow.getReturnDate() != null ? new java.sql.Date(borrow.getReturnDate().getTime()) : null);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving borrow", e);
        }
    }

    public void update(Borrow borrow) {
        String query = "UPDATE borrows SET return_date = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(borrow.getReturnDate().getTime()));
            stmt.setInt(2, borrow.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating borrow", e);
        }
    }
}
