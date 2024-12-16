package com.library;

import com.library.dao.StudentDAO;
import com.library.util.DbConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class StudentDAOTest {
    private StudentDAO  studentDAO;

    @BeforeEach
    void setUp() throws SQLException {
        studentDAO = new StudentDAO(DbConnection.getConnection());
    }

    @Test
    void FindStudentByID() {
        studentDAO.getStudentById(1);
    }
}
