package data;

import com.main.LibrarySystem;
import javafx.application.Platform;
import org.example.modul6tugas.LoginGUI;
import util.iMenu;
import books.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements iMenu {
    private String name;
    private String faculty;
    private String nim;
    private String programStudi;
    private LibrarySystem lS = new LibrarySystem();
    private static ArrayList<Book> borrowedBooks = new ArrayList<>();
    private LoginGUI login = new LoginGUI();
    private Scanner scanner = new Scanner(System.in);
    private boolean isLogout = false;
    private int lock = 0;

    public Student(String name, String faculty, String nim, String programStudi) {
        this.name = name;
        this.faculty = faculty;
        this.nim = nim;
        this.programStudi = programStudi;
    }

    // ======= HOMEWORK : Continue after the nim is correct, then login to Student menu
    public void displayInfo() {
        if(lS.getGUIStringOption().equals("99")) {
        }
        else if(LibrarySystem.checkNim()[0].equals(2)) {
            Platform.runLater(() -> {
                login.alertWarning(2); // NIM tidak 15 karakter
            });
        }
        else if(LibrarySystem.checkNim()[0].equals(1)) {
            Platform.runLater(() -> {
                login.alertWarning(1); // NIM belum terdaftar
            });
        }
        else if(LibrarySystem.checkNim()[0].equals(0)) { // THIS
            login.alertWarning(4); // berhasil login
        }
    }

    @Override
    public void menu() {
        LibrarySystem.setLoginGUIOrder(7);
        try {
            LibrarySystem.startingGUI();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void showBorrowedBooks() {
        LibrarySystem.setLoginGUIOrder(3);

        try {
            LibrarySystem.tableViewStartingGUI();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void choiceBook() {
        //super.displayBooks();
        LibrarySystem.setLoginGUIOrder(8);

        try {
            LibrarySystem.startingGUI();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void returnBooks() {
        LibrarySystem.setLoginGUIOrder(4);

        try {
            LibrarySystem.tableViewStartingGUI();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Book idBookFinder(String id) {
        User user = new User();
        for (Book book : user.getBookList()) {
            if (book != null && book.getBookId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public void logout() {
        LibrarySystem.setLoginGUIOrder(1);

        try {
            LibrarySystem.startingGUI();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getNim() {
        return nim;
    }

    public String getProgramStudi() {
        return programStudi;
    }

    public static ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setIsLogout(boolean isLogoutState) {
        this.isLogout = isLogoutState;
    }

    public void setBorrowedBooks(String nim, String bookId, String title, String author, String category, int stock, int duration) {
        borrowedBooks.add(new Book(nim, bookId, title, author, category, stock, duration));
    }
}
