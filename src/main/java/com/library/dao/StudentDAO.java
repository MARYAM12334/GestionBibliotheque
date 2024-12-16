package com.library.dao;

import com.library.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {
    private final Connection connection;
    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public void addStudent(Student student) {
        String query = "INSERT INTO students (name,email) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'ajout de l'étudiant", e);
        }
    }

    public Student getStudentById(int id) {
        String query = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Student student = new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    );
                    //System.out.println("Student trouver est "+"ID: " + student.getId() + " | Nom: " + student.getName());
                    return student;
                } else {
                    LOGGER.log(Level.WARNING, "Aucun étudiant trouvé avec l'ID : " + id);
                    // Retourner un étudiant vide plutôt que null
                    return new Student(-1, "Étudiant non trouvé");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération de l'étudiant", e);
            // En cas d'erreur, retourner un étudiant avec un message d'erreur
            return new Student(-1, "Erreur de base de données");
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                students.add(new Student(resultSet.getString("name"), resultSet.getString("email")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération des étudiants", e);
        }
        return students;
    }

    public void updateStudent(Student student) {
        String query = "UPDATE students SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la mise à jour de l'étudiant", e);
        }
    }

    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la suppression de l'étudiant", e);
        }
    }
}
