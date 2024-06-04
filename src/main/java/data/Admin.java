package data;

import exception.custom.InvalidChoice;
import exception.custom.illegalAdminAccess;
import util.iMenu;
import com.main.LibrarySystem;
import books.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Admin extends User implements iMenu {
    private final String adminUsername = "admin";
    private final String adminPassword = "admin";
    private static ArrayList<Student> studentList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void menu() {
        try {
            System.out.print("===== Menu Admin =====\n1. Tambah Mahasiswa\n2. Tampilkan Mahasiswa\n3. Input Buku\n4. Tampilkan Daftar Buku\n5. Logout\nPilih antara (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice > 5 || choice < 1) {
                throw new InvalidChoice("Pilihan tidak valid!\n");
            }

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    LibrarySystem.addTempStudent();
                    displayStudents();
                    break;
                case 3:
                    inputBook();
                    break;
                case 4:
                    LibrarySystem.addTempBooks();
                    displayBooks();
                    break;
                case 5:
                    System.out.println("Logout berhasil.\n");
                    break;
            }
        } catch (InvalidChoice e) {
            System.out.println(e.getMessage());
        }
    }

    public void login() {
        System.out.print("Masukkan Username (admin): ");
        String username = scanner.next();
        System.out.print("Masukkan Password (admin): ");
        String password = scanner.next();
        if (isAdmin(username, password)) {
            System.out.println("Login berhasil sebagai Admin\n");
            menu();
        } else {
            System.out.println("User Admin tidak ditemukan\n");
        }
    }

    public boolean isAdmin(String username, String password) {
        try {
            if( !(username.equals(adminUsername) && password.equals(adminPassword)) ) {
                throw new illegalAdminAccess("\nInvalid Credentials");
            }
        } catch (illegalAdminAccess e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public void addStudent() {
        System.out.print("Masukkan Nama: ");
        String name = scanner.next();
        System.out.print("Masukkan NIM: ");
        String nim = scanner.next();
        if (nim.length() != 15) {
            System.out.println("NIM tidak valid! Harus 15 karakter.\n");
            menu();
        }

        LibrarySystem librarySystem = new LibrarySystem();
        for(int i = 0; i < librarySystem.getNimStudentList().size(); i++ ) {
            if(nim.equals(librarySystem.getNimStudentList().get(i))) {
                System.out.println("Nim anda sudah terdaftar !");
                menu();
            }
        }

        librarySystem.setNimStudentList(nim);

        System.out.print("Masukkan Fakultas: ");
        String faculty = scanner.next();
        System.out.print("Masukkan Program Studi: ");
        String programStudi = scanner.next();

        studentList.add(new Student(name, faculty, nim, programStudi));

        System.out.println("Mahasiswa dengan NIM " + nim + " berhasil ditambahkan ...\n");
        menu();
    }

    public void displayStudents() {
        for (Student student : studentList) {
            System.out.println("===================================");
            System.out.println("Nama            : " + student.getName());
            System.out.println("NIM             : " + student.getNim());
            System.out.println("Fakultas        : " + student.getFaculty());
            System.out.println("Program Studi   : " + student.getprogramStudi());
            System.out.println("===================================");
        }

        System.out.println();
        menu();
    }

    public void inputBook() {
        int bookType;
        do {
            System.out.println("\nPilih jenis buku:");
            System.out.println("1. History Book");
            System.out.println("2. Story Book");
            System.out.println("3. Text Book");
            System.out.print("Pilih jenis buku (1-3): ");
            bookType = scanner.nextInt();
            scanner.nextLine();
        } while(bookType > 3 || bookType < 1);

        String bookId;
        System.out.print("Masukkan title buku: ");
        String title = scanner.nextLine();
        System.out.print("Masukkan author buku: ");
        String author = scanner.nextLine();
        System.out.print("Masukkan stock buku: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        String category;
        switch (bookType) {
            case 1:
                HistoryBook historyBook = new HistoryBook("", "", "", "", 0);
                category = historyBook.getCategory();
                break;
            case 2:
                StoryBook storyBook = new StoryBook("", "", "", "", 0);
                category = storyBook.getCategory();
                break;
            case 3:
                TextBook textBook = new TextBook("", "", "", "", 0);
                category = textBook.getCategory();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                return;
        }
        bookId = generateId();
        User.addBooks(bookId, title, author, category, stock);

        System.out.println("Buku berhasil ditambahkan.\n");
        menu();
    }

    @Override
    public void displayBooks() {
        super.displayBooks();
        menu();
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
}
