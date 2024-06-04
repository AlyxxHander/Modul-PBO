package data;

import java.util.ArrayList;
import java.util.Scanner;

import com.main.LibrarySystem;
import util.iMenu;
import books.*;

public class Student extends User implements iMenu {
    private String name;
    private String faculty;
    private String nim;
    private String programStudi;
    private static ArrayList<Book> borrowedBooks = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int lock = 0;
    private boolean isLogout = false;

    public Student(String name, String faculty, String nim, String programStudi) {
        this.name = name;
        this.faculty = faculty;
        this.nim = nim;
        this.programStudi = programStudi;
    }

    public void displayInfo() {
        if (LibrarySystem.checkNim()[0].equals(1) ) {
            System.out.println("Login sebagai Mahasiswa berhasil\n");
            menu();
        } else {
            System.out.println("NIM Mahasiswa tidak valid atau tidak ditemukan\n");
        }
        menu();
    }

    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.print("===== Menu Student =====\n1. Tampilkan Buku yang dipinjam\n2. Pinjam Buku\n3. Kembalikan Buku\n4. Logout\nPilih antara (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showBorrowedBooks();
                    break;
                case 2:
                    isLogout = false;
                    choiceBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    logout();
                    break;
                default:
                    System.out.println("Pilihan invalid !\n");
                    break;
            }
        } while(choice != 4);
    }

    public void showBorrowedBooks() {
        LibrarySystem librarySystem = new LibrarySystem();
        for (Book book : borrowedBooks) {
            if (lock == 0) {
                Admin admin = new Admin();
                for (Student student : admin.getStudentList()) {
                    System.out.println("\nProfil Mahasiswa\n=============================\nNama : " + student.getName() + "\nNIM : " + student.getNim() + "\n=============================");
                    System.out.println("Daftar Buku yang dipinjam :");
                    template("No", "Id Buku", "Judul", "Author", "Category");
                    break;
                }
                lock = 1;
            }
            if(librarySystem.getNimStudentListHolder().equals(book.getNimStudent())) {
                System.out.println("<> " + book.getbookId() + " - " + book.getTitle() + " - " + book.getAuthor() + " - " + book.getCategory() + " - " + book.getDuration());
            }
        }

        System.out.println();
        lock = 0;
        menu();
    }

    public void choiceBook() {
        super.displayBooks();

        System.out.print("Masukkan ID Buku yang ingin dipinjam : ");
        String bookId = scanner.next();

        if(isLogout == true) {
            System.out.print("Apakah anda yakin ingin meminjam buku tersebut (y/n) ? ");
            String choice = scanner.next();
            if (choice.equals("n")) {
                logout();
                return;
            }
            else {
                System.out.println("Masukkan pilihan yang tepat");
                menu();

            }
        }

        int duration;
        do {
            System.out.print("Berapa lama buku ingin dipinjam?\nInput lama (maksimal 14 hari) : ");
            duration = scanner.nextInt();
            if (duration <= 0) {
                System.out.println("\ndurasi buku harus lebih dari 0 !\n");
            }
            else if (duration > 14) {
                System.out.println("\nBuku tidak boleh dipinjam lebih dari 14 hari !\n");
            }
        } while(duration <= 0 || duration > 14);
        scanner.nextLine();

        User user = new User();
        for (int i = 0; i < (user.getBookList().size()); i++) {
            Book book = getBookList().get(i);
            LibrarySystem librarySystem = new LibrarySystem();

            if (book.getbookId().equals(bookId)) {
                book.setDuration(duration);
                borrowedBooks.add(new Book(book.setNimStudent(librarySystem.getNimStudentListHolder()), book.getbookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock(), book.getDuration()));
                break;
            }
        }

        Book selectedBook = idBookFinder(bookId); // get all the selected book information
        if (selectedBook != null && selectedBook.getStock() > 0) {
            selectedBook.setStock(selectedBook.getStock() - 1);
            //borrowedBooks.add(selectedBook);
            System.out.println("Berhasil meminjam buku: " + selectedBook.getTitle());
        } else {
            System.out.println("Buku tidak tersedia atau ID buku tidak ditemukan ...");
        }

        System.out.println();
        menu();
    }

    private void returnBook() { // NEED FIXING
        if (getBorrowedBooks().isEmpty()) {
            System.out.println("Anda belum meminjam buku ...");
            return;
        }

        int i = 0;
        int iterator = 1;
        System.out.println("Buku yang Anda pinjam:");
        for (Book book : getBorrowedBooks()) {
            LibrarySystem librarySystem = new LibrarySystem();

            if(i == getBorrowedBooks().size()) {
                break;
            }
            else if (book.getNimStudent() != null) {
                if(librarySystem.getNimStudentListHolder().equals(book.getNimStudent())) {
                    System.out.println("<" + iterator + "> " + getBorrowedBooks().get(i).getbookId() + " - " + getBorrowedBooks().get(i).getTitle());
                }
            }
            iterator++;
            i++;
        }

        System.out.print("Pilih buku yang akan dikembalikan (nomor): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        LibrarySystem lS = new LibrarySystem();
        for(Book book : getBorrowedBooks()) {
            if (choice > 0 && choice <= getBorrowedBooks().size() && lS.getNimStudentListHolder().equals(book.getNimStudent())) {
                Book returnedBook = getBorrowedBooks().remove(choice - 1);
                returnedBook.setStock(returnedBook.getStock() + 1);

                System.out.println("Buku " + returnedBook.getbookId() + " berhasil dikembalikan ...");
                break;
            }
        }

        System.out.println();
        menu();
    }

    public Book idBookFinder(String id) {
        User user = new User();
        for (Book book : user.getBookList()) {
            if (book != null && book.getbookId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public void logout() {
        int choice;
        do {
            System.out.print("Apakah anda ingin logout atau pinjam buku (1 / 2) ? ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Logout berhasil.\n");
                    break;
                case 2:
                    isLogout = true;
                    choiceBook();
                    break;
                default:
                    System.out.println("Pilihlah pilihan yg tepat !");
                    break;
            }
        } while(choice > 2 || choice < 1);
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

    public String getprogramStudi() {
        return programStudi;
    }

    public static ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
