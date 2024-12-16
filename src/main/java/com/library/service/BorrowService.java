
package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.dao.BorrowDAO;
import com.library.model.Borrow;

import java.util.Date;
import java.util.List;

public class BorrowService {

    private BorrowDAO borrowDAO;

    // Constructeur avec BorrowDAO
    public BorrowService(BorrowDAO borrowDAO) {
        this.borrowDAO = borrowDAO;
    }

    // Méthode pour emprunter un livre
    public void borrowBook(Borrow borrow) {
        // Sauvegarde de l'emprunt dans la base de données
        borrowDAO.save(borrow);
    }

    public void returnBook(Borrow borrow) {
        borrow.setReturnDate(new Date()); // Définir la date de retour
        borrowDAO.update(borrow);
    }

    //Afficher les emprunts (méthode fictive, à adapter)
    public void displayBorrows() {
        List<Borrow> borrows = borrowDAO.getAllBorrows();
        for (Borrow borrow : borrows) {
            System.out.println(
                    "ID: " + borrow.getId() +
                            "  Étudiant: " + borrow.getStudent().getName() +
                            "  Livre: " + borrow.getBook().getTitle() +
                            "  Date d'emprunt: " + borrow.getBorrowDate() +
                            "  Date de retour: " + borrow.getReturnDate()
            );
        }
    }
}
