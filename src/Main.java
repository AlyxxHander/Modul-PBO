import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static ArrayList<Student> studentsList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String nimStudent = "";

    public static void main(Str.ing[] args) {
        User.bookList.add(0, new Book("1234-5678-asdf", "title1", "author1", "Sejarah", 5));
        User.bookList.add(1, new Book("uihe-2134-dq23", "title2", "author2", "Novel", 8));

        menu();
    }
    public static void menu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Library System Login");
            System.out.println("1. Login sebagai Mahasiswa");
            System.out.println("2. Login sebagai Admin");
            System.out.println("3. Keluar");
            System.out.print("Pilih antara (1-3): ");
            int menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1:
                    inputNim();
                    break;
                case 2:
                    Admin admin = new Admin();
                    admin.login();
                    break;
                case 3:
                    System.out.println("Keluar dari program ...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
    public static void inputNim() {
        do {
            System.out.print("Masukkan NIM Anda (masukkan 99 untuk kembali): ");
            nimStudent = scanner.next();
            if(nimStudent.equals("99")) {
                menu();
            }
            else if(checkNim(nimStudent)[0].equals(false)) {
                System.out.println("NIM tidak valid! Harus 15 karakter.");
            }
        } while(nimStudent.length() != 15);

        Student student = new Student("", "", "", "");
        student.setNimStudent(nimStudent);
        Student.displayInfo();
    }

    public static Object[] checkNim(String nim) {
        Object[] object = new Object[1];
        object[0] = false;

        if(nimStudent.length() == 15) {
            object[0] = true;
            return object;
        }
        
        return object;
    }

    public static void menuStudent() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("===== Menu Student =====\n1. Tampilkan Buku\n2. Pinjam Buku\n3. Kembalikan Buku\n4. Logout\nPilih antara (1-4): ");
            choice = scanner.nextInt();
            Student student = new Student("", "", "", "");

            switch (choice) {
                case 1:
                    student.showBorrowedBooks();
                    break;
                case 2:
                    student.displayBooks();
                    break;
                case 3:
                    student.returnBook();
                    break;
                case 4:
                    student.logout();
                default:
                    System.out.println("Pilihan invalid !");
            }
        } while(choice == 4);
    }
    public static void menuAdmin() {
        System.out.println("===== Menu Admin =====\n1. Tambah Mahasiswa\n2. Tampilkan Mahasiswa\n3. Input Buku\n4. Tampilkan Daftar Buku\n5. Logout\nPilih antara (1-5): ");
        int choice = scanner.nextInt();
        Admin admin = new Admin();

        switch (choice) {
            case 1:
                admin.addStudent();
                break;
            case 2:
                addTempStudent();
                admin.displayStudents();
                break;
            case 3:
                admin.inputBook();
                break;
            case 4:
                addTempBooks();
                admin.displayBooks();
                break;
            case 5:
                System.out.println("Logout berhasil.");
                Main.menu();
            default:
                System.out.println("Pilihan tidak valid!");
                break;
        }
    }
    public static void addTempStudent() {
        System.out.println("Daftar Mahasiswa yang terdaftar:");
    }
    public static void addTempBooks() {
        System.out.println("Daftar Buku Tersedia:");
    }

    public static class User {
        private static ArrayList<Book> bookList = new ArrayList<>();
        public void displayBooks() {
            int i = 0;

            System.out.println("No. === Id Buku === Judul === Author === Stok === ");
            for (Book book : User.bookList) {
                if (book != null) {
                    System.out.println(i++ + ". " + book.getbookId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + " || " + book.getStock());
                }
            }
        }
    }

    public static class Student extends User {
        private String name;
        private String faculty;
        private String nim;
        private String programStudi; // nim
        private static int[] arrDuration = new int[100];
        private static int duration;
        private static ArrayList<Book> borrowedBooks = new ArrayList<>();

        public Student(String name, String faculty, String nim, String programStudi) {
            this.name = name;
            this.faculty = faculty;
            this.nim = nim;
            this.programStudi = programStudi;
        }

        public static void displayInfo() {
            Student student = new Student("", "", "", "");
            if (checkNim(student.getNim())[0].equals(true) ) {
                System.out.println("Login sebagai Mahasiswa berhasil");
                Main.menuStudent();
            } else {
                System.out.println("NIM Mahasiswa tidak valid atau tidak ditemukan");
            }
            Main.menuStudent();
        }

        public void showBorrowedBooks() {
            int i = 0;
            System.out.println("Daftar Buku Tersedia:");
            for (Book book : User.bookList) {
                if (book != null) {
                    System.out.println(i++ + ". " + book.getbookId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + " || " + book.getStock() + " || " + arrDuration[i]);
                }
            }
            Main.menuStudent();
        }

        @Override
        public void displayBooks() {
            System.out.print("Masukkan ID Buku yang ingin dipinjam: ");
            String bookId = scanner.next();
            Book selectedBook = idBookFinder(bookId);

            System.out.print("Berapa lama buku ingin dipinjam?\nInput lama(hari) : ");
            duration = scanner.nextInt();
            for(int i = 0; i < (i+2); i++) {
                if(arrDuration[i] == 0) {
                    arrDuration[i] = duration;
                    break;
                }
            }

            if (selectedBook != null && selectedBook.getStock() > 0) {
                selectedBook.setStock(selectedBook.getStock() - 1);
                borrowedBooks.add(selectedBook);
                System.out.println("Berhasil meminjam buku: " + selectedBook.getTitle());
            } else {
                System.out.println("Buku tidak tersedia atau ID buku tidak ditemukan ...");
            }
            Main.menuStudent();
        }

        private void returnBook() {
            if (borrowedBooks.isEmpty()) {
                System.out.println("Anda belum meminjam buku ...");
                return;
            }

            System.out.println("Buku yang Anda pinjam:");
            for (int i = 0; i < borrowedBooks.size(); i++) {
                System.out.println((i + 1) + ". " + borrowedBooks.get(i).getbookId());
            }

            System.out.print("Pilih buku yang akan dikembalikan (nomor): ");
            int choice = scanner.nextInt();
            if (choice > 0 && choice <= borrowedBooks.size()) {
                Book returnedBook = borrowedBooks.remove(choice - 1);
                returnedBook.setStock(returnedBook.getStock() + 1);
                System.out.println("Buku " + returnedBook.getbookId() + " berhasil dikembalikan ...");
            } else {
                System.out.println("Pilihan anda tidak valid ...");
            }
            Main.menuStudent();
        }

        public Book idBookFinder(String id) {
            for (Book book : User.bookList) {
                if (book != null && book.getbookId().equals(id)) {
                    return book;
                }
            }
            return null;
        }

        public void logout() {
            System.out.println("Logout berhasil.");
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
        public void setNimStudent(String nimStudent) {
            this.nim = nimStudent;
        }

        public String getprogramStudi() {
            return programStudi;
        }

        public static ArrayList<Book> getBorrowedBooks() {
            return borrowedBooks;
        }
    }

    public static class Admin extends User {
        private final String adminUsername = "admin";
        private final String adminPassword = "admin";
        private static ArrayList<Student> studentList = new ArrayList<>();

        public void login() {
            System.out.print("Masukkan Username (admin): ");
            String username = scanner.next();
            System.out.print("Masukkan Password (admin): ");
            String password = scanner.next();
            if (isAdmin(username, password)) {
                System.out.println("Login berhasil sebagai Admin\n");
                menuAdmin();
            } else {
                System.out.println("User Admin tidak ditemukan\n");
            }
        }
        public boolean isAdmin(String username, String password) {
            return username.equals(adminUsername) && password.equals(adminPassword);
        }
        public void addStudent() {
            System.out.print("Masukkan Nama: ");
            String name = scanner.next();
            System.out.print("Masukkan NIM: ");
            String nim = scanner.next();
            if (nim.length() != 15) {
                System.out.println("NIM tidak valid! Harus 15 karakter.");
                menuAdmin();
            }
            System.out.print("Masukkan Fakultas: ");
            String faculty = scanner.next();
            System.out.print("Masukkan Program Studi: ");
            String programStudi = scanner.next();
            studentList.add(new Student(name, faculty, nim, programStudi));
            System.out.println("Mahasiswa dengan NIM " + nim + " berhasil ditambahkan ...\n");
            menuAdmin();
        }

        public void displayStudents() {
            int i = 0;
            for (Student student : studentList) {
                System.out.println("Nama            : " + student.getName());
                System.out.println("NIM             : " + student.getNim());
                System.out.println("Fakultas        : " + student.getFaculty());
                System.out.println("Program Studi   : " + student.getprogramStudi());
                ArrayList<Book> borrowedBooks = Student.getBorrowedBooks();
                System.out.println("List buku yang dipinjam:");
                if (!borrowedBooks.isEmpty()) {
                    for (Book book : borrowedBooks) {
                        System.out.println("- " + book.getTitle());
                    }
                } else {
                    System.out.println("- Belum ada buku yang dipinjam ...");
                }
                System.out.println();
            }
            menuAdmin();
        }

        public void inputBook() {
            System.out.println("Pilih jenis buku:");
            System.out.println("1. History Book");
            System.out.println("2. Story Book");
            System.out.println("3. Text Book");
            System.out.print("Pilih jenis buku (1-3): ");
            int bookType = scanner.nextInt();
            scanner.nextLine();

            String bookId, title, author;
            int stock;
            System.out.print("Masukkan title buku: ");
            title = scanner.nextLine();
            System.out.print("Masukkan author buku: ");
            author = scanner.nextLine();
            System.out.print("Masukkan stock buku: ");
            stock = scanner.nextInt();
            scanner.nextLine();

            String category;
            switch (bookType) {
                case 1:
                    HistoryBook historyBook = new HistoryBook("", "", "", "", 0);
                    category = historyBook.getCategory();
                    bookId = generateId();
                    User.bookList.add(new HistoryBook(bookId, title, author, category, stock));
                    break;
                case 2:
                    StoryBook storyBook = new StoryBook("", "", "", "", 0);
                    category = storyBook.getCategory();
                    bookId = generateId();
                    User.bookList.add(new StoryBook(bookId, title, author, category, stock));
                    break;
                case 3:
                    TextBook textBook = new TextBook("", "", "", "", 0);
                    category = textBook.getCategory();
                    bookId = generateId();
                    User.bookList.add(new TextBook(bookId, title, author, category, stock));
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    return;
            }
            System.out.println("Buku berhasil ditambahkan.");
            menuAdmin();
        }

        @Override
        public void displayBooks() {
            super.displayBooks();
            menuAdmin();
        }

        public String generateId() {
            StringBuilder idBuilder = new StringBuilder();
            Random random = new Random();

            for (int i = 0; i < 12; i++) {
                idBuilder.append(random.nextInt(10));
                if ((i + 1) % 4 == 0 && i != 11) {
                    idBuilder.append("-");
                }
            }

            return idBuilder.toString();
        }
    }

    public static class Book {
        private String bookId;
        private String title;
        private String author;
        private String category;
        private int stock;
        private int duration;

        public Book(String bookId, String title, String author, String category, int stock) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.category = category;
            this.stock = stock;
        }
        public String getbookId() {
            return bookId;
        }
        public String getTitle() {
            return title;
        }
        public String getAuthor() {
            return author;
        }
        public String getCategory() {
            return category;
        }
        public int getStock() {
            return stock;
        }
        public void setStock(int stock) {
            this.stock = stock;
        }
        public int getDuration() {
            return duration;
        }
    }
}
class HistoryBook extends Main.Book {
    private String category = "Sejarah";
    public HistoryBook(String bookId, String title, String author, String category, int stock) {
        super(bookId, title, author, category, stock);
    }
    public String getCategory() {
        return category;
    }
}

class StoryBook extends Main.Book {
    private String category = "Cerita";
    public StoryBook(String bookId, String title, String author, String category, int stock) {
        super(bookId, title, author, category, stock);
    }
    public String getCategory() {
        return category;
    }
}

class TextBook extends Main.Book {
    private String category = "Novel";
    public TextBook(String bookId, String title, String author, String category, int stock) {
        super(bookId, title, author, category, stock);
    }
    public String getCategory() {
        return category;
    }
}
