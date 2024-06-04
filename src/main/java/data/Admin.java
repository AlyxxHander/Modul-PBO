package data;

import exception.custom.illegalAdminAccess;
import org.example.modul6tugas.LoginGUI;
import util.iMenu;
import com.main.LibrarySystem;
import books.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Admin extends User implements iMenu {
    private final String adminUsername = "a";
    private final String adminPassword = "a";
    private static ArrayList<Student> studentList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private LoginGUI login = new LoginGUI();

    @Override
    public void menu() { // FINISH
        try {
            LibrarySystem.startingGUI();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int isStudentNimDuplicate(String nim) { // FINISH
        /* ===============================
        0 = NIM duplicatedSuccess
        1 = NIM Invalid
        2 = Success
        =============================== */
        if(nim.length() != 15) {
            return 1;
        }

        LibrarySystem librarySystem = new LibrarySystem();
        for(int i = 0; i < librarySystem.getNimStudentList().size(); i++ ) {
            if(nim.equals(librarySystem.getNimStudentList().get(i))) {
                return 0;
            }
        }

        return 2;
    }

    public void addStudent() { // FINISH
        LibrarySystem.setLoginGUIOrder(5);
        try {
            LibrarySystem.startingGUI();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void addStudentList(String name, String faculty, String nim, String programStudi) { // FINISH
        studentList.add(new Student(name, faculty, nim, programStudi));
    }

    public boolean isAdmin(String username, String password) { // FINISH
        try {
            if( !(username.equals(adminUsername) && password.equals(adminPassword)) ) {

                login.alertWarning(3);
                throw new illegalAdminAccess("\nInvalid Credentials");
            }
        } catch (illegalAdminAccess e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public void displayStudents() {
        LibrarySystem.setLoginGUIOrder(1);
        try {
            LibrarySystem.tableViewStartingGUI();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void inputBook() {
        LibrarySystem.setLoginGUIOrder(6);
        try {
            LibrarySystem.startingGUI();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void displayBooks() {
        LibrarySystem.setLoginGUIOrder(2);

        super.displayBooks();
    }

    public String generateId() {
        StringBuilder idBuilder = new StringBuilder(); // object to construct String more efficiently and customizable
        Random random = new Random();

        for (int i = 0; i < 12; i++) {
            idBuilder.append(random.nextInt(10));
            if ((i + 1) % 4 == 0 && i != 11) {
                idBuilder.append("-");
            }
        }

        return idBuilder.toString();
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }
    public String getAdminUsername() {
        return adminUsername;
    }
    public String getAdminPassword() {
        return adminPassword;
    }
}
