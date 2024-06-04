package data;

import books.Book;
import com.main.LibrarySystem;

import java.io.IOException;
import java.util.ArrayList;

public class User {
    private static ArrayList<Book> bookList = new ArrayList<>();
    private static Book book = new Book("", "", "", "", 0);
    
    public void displayBooks() {
        try {
            LibrarySystem.tableViewStartingGUI();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void addBooks(String bookId, String title, String author, String category, int stock) {
        book.setStock(stock);
        bookList.add(new Book(bookId, title, author, category, book.getStock()));
    }

    // Update the book that has been returned
    public static void updateStock(String bookId, int stock) {
        int bookIndex = indexFinder(bookId);
        bookList.get(bookIndex).setStock(stock);
    }
    public static int indexFinder(String bookId) {
        int i = 0;
        User user = new User();
        for (Book book : user.getBookList()) {
            if (book != null && book.getBookId().equals(bookId)) {
                return i;
            }

            i++;
        }
        return 1;
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
