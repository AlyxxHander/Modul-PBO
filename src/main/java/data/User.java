package data;

import books.Book;

import java.util.ArrayList;

public class User {
    private static ArrayList<Book> bookList = new ArrayList<>();
    private static Book book = new Book("", "", "", "", 0);
    
    public void displayBooks() {
        int iterator = 1;
        if(bookList.isEmpty()) {
            System.out.println("Belum ada buku yang diinput ...");
            System.out.println("Kembali ke menu\n");

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
            if (book != null && book.getbookId().equals(bookId)) {
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
