package data;

import java.util.ArrayList;
import books.Book;

public class User {
    private static ArrayList<Book> bookList = new ArrayList<>();
    
    public void displayBooks() {
        int iterator = 1;
        if(bookList.isEmpty()) {
            System.out.println("Belum ada buku yang diinput ...");
            System.out.println("Kembali ke menu");

            Student student = new Student("", "", "", "");
            student.menu();
        }
        else {
            template("No", "Id Buku", "Judul", "Author", "Category", "Stok");

            for (Book book : bookList) {
                if (book != null) {
                    System.out.println("<" + iterator + "> " + book.getbookId() + " - " + book.getTitle() + " - " + book.getAuthor() + " - " + book.getCategory() + " - " + book.getStock());
                }
                iterator++;
            }
        }
    }

    public static void addBooks(String bookId, String title, String author, String category, int stock) {
        bookList.add(new Book(bookId, title, author, category, stock));
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    // Method Overload
    public void template(String no, String bookId, String judul, String author, String category) {
        System.out.println(no + " - " + bookId + " - " + judul + " - " + author + " - " + category + " - duration");
    }
    // Method Overload
    public void template(String no, String bookId, String judul, String author, String category, String stock) {
        System.out.println(no + " - " + bookId + " - " + judul + " - " + author + " - " + category + " - "  + stock);
    }
}
