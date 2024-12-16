package com.library;

import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.service.StudentService;
import com.library.util.DbConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private StudentService studentService;
    private StudentDAO studentDAO;

    @BeforeEach
    void setUp() throws SQLException {

        DbConnection.getConnection().prepareStatement("DELETE FROM borrows").executeUpdate();
        DbConnection.getConnection().prepareStatement("DELETE FROM students").executeUpdate();
        DbConnection.getConnection().prepareStatement("DELETE FROM books").executeUpdate();


        studentDAO = new StudentDAO(DbConnection.getConnection());
        studentService = new StudentService(studentDAO);
    }

    @Test
    void testAddStudent() {
        Student student = new Student("Alice", "alice@example.com");
        studentService.addStudent(student);
        assertEquals(1, studentDAO.getAllStudents().size());
        assertEquals("Alice", studentDAO.getStudentById(1).getName());
    }

    /*@Test
    void testUpdateStudent() {
        Student student = new Student("Alice", "alice@example.com");
        Student studentupdate = new Student("Alice Smith", "alice.smith@example.com");

        studentService.addStudent(student);
        studentService.updateStudent(studentupdate);
        assertEquals("Alice Smith", studentDAO.getStudentById(1).getName());
    }*/

    /*@Test
    void testDeleteStudent() {

        Student student = new Student("Alice", "alice@example.com");
        studentService.addStudent(student);
        List<Student> students = studentDAO.getAllStudents();
        int studentId = students.get(0).getId();
        studentService.deleteStudent(studentId);
        assertNull(studentDAO.getStudentById(studentId));
    }*/

    @Test
    void testGetAllStudents() {
        Student student1 = new Student("Alice", "alice@example.com");
        Student student2 = new Student("Bob", "bob@example.com");
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        assertEquals(2, studentDAO.getAllStudents().size());
    }
    @Test
    void testFindStudentById() {
        Student student1 = studentService.findStudentById(1);
        System.out.println(student1);

    }
}
