package org.example.modul6tugas;

import books.Book;
import com.main.LibrarySystem;
import data.Admin;
import data.Student;
import data.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableViewGUI extends Application {
    LibrarySystem lS = new LibrarySystem();

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();

        //int switchs = 2;
        //lS.getGUILoginOrder()
        switch(lS.getGUILoginOrder()) {
            case 1:
                Label viewStudentMainLabel = new Label("Student List");
                viewStudentMainLabel.setFont(Font.font("Segoe ui", FontWeight.BOLD, 50));
                viewStudentMainLabel.setTextFill(Color.DARKGREEN);

                TableView<Student> viewStudent = new TableView<>();
                viewStudent.setEditable(false);
                viewStudent.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                viewStudent.setPrefHeight(300);
                viewStudent.setPrefWidth(360);

                TableColumn<Student, String> viewStudent_Collumn1 = new TableColumn<>("Nama");
                viewStudent_Collumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
                viewStudent_Collumn1.setMinWidth(285);

                TableColumn<Student, String> viewStudent_Collumn2 = new TableColumn<>("Faculty");
                viewStudent_Collumn2.setCellValueFactory(new PropertyValueFactory<>("faculty"));
                viewStudent_Collumn2.setMinWidth(285);

                TableColumn<Student, String> viewStudent_Collumn3 = new TableColumn<>("NIM");
                viewStudent_Collumn3.setCellValueFactory(new PropertyValueFactory<>("nim"));
                viewStudent_Collumn3.setMinWidth(285);

                TableColumn<Student, String> viewStudent_Collumn4 = new TableColumn<>("Prodi");
                viewStudent_Collumn4.setCellValueFactory(new PropertyValueFactory<>("ProgramStudi"));
                viewStudent_Collumn4.setMinWidth(285);

                viewStudent.getColumns().addAll(viewStudent_Collumn1, viewStudent_Collumn2, viewStudent_Collumn3, viewStudent_Collumn4);

                Admin admin = new Admin();
                List<Student> studentList = new ArrayList<>(admin.getStudentList());
                ObservableList<Student> studentDataList = FXCollections.observableArrayList(studentList);
                viewStudent.setItems(studentDataList);

                Button borrowedBookReturn = new Button("   Return   ");
                borrowedBookReturn.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                borrowedBookReturn.setTextFill(Color.WHITE);
                borrowedBookReturn.setStyle("-fx-padding: 7px 13px; " +
                        "-fx-background-color: #0077B6; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: #051094; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 5px;");
                borrowedBookReturn.setOnAction(e -> {
                    /*
                    System.out.print("Pilih buku yang akan dikembalikan (nomor): ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    // Sending the borrowedBook value of the returned book
                    System.out.print("Masukkan ID Buku yang ingin dikembalikan : ");
                    String bookId = scanner.next();
                    Book sB = idBookFinder(bookId); //String bookId, String title, String author, String category, int stock
                    User.updateStock(sB.getBookId(), sB.getStock()+1);

                    LibrarySystem lS = new LibrarySystem();
                    for(Book book : getBorrowedBooks()) {
                        if (choice > 0 && choice <= getBorrowedBooks().size() && lS.getNimStudentListHolder().equals(book.getNimStudent())) {
                            Book returnedBook = getBorrowedBooks().remove(choice - 1);
                            returnedBook.setStock(returnedBook.getStock() + 1);

                            System.out.println("Buku " + returnedBook.getBookId() + " berhasil dikembalikan ...");
                            break;
                        }
                    }

                     */
                });

                Button viewStudentBackBtn = new Button("Back");
                viewStudentBackBtn.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                viewStudentBackBtn.setMinSize(40, 100);
                viewStudentBackBtn.setTextFill(Color.DARKGREEN);
                viewStudentBackBtn.setMinSize(100, 40);
                viewStudentBackBtn.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                viewStudentBackBtn.setOnAction(e ->{
                    stage.close();
                    LibrarySystem.setLoginGUIOrder(4);

                    try {
                        LibrarySystem.startingGUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                GridPane viewStudentThirdRootPt2 = new GridPane();
                viewStudentThirdRootPt2.add(viewStudentBackBtn, 3, 12);

                VBox viewStudentThirdRoot = new VBox(40, viewStudentMainLabel, viewStudent);
                viewStudentThirdRoot.setAlignment(Pos.CENTER);

                VBox viewStudentSecRoot = new VBox(10, viewStudentThirdRoot, viewStudentThirdRootPt2);
                viewStudentSecRoot.setAlignment(Pos.CENTER);
                viewStudentSecRoot.setPadding(new Insets(0, 30, 60, 30));
                root.getChildren().add(viewStudentSecRoot);

                break;
            case 2:
                //template("No", "Id Buku", "Judul", "Author", "Category", "Stok");
                Label viewBooksMainLabel = new Label("Book List");
                viewBooksMainLabel.setFont(Font.font("Segoe ui", FontWeight.BOLD, 50));
                viewBooksMainLabel.setTextFill(Color.DARKGREEN);

                TableView<Book> viewBooks = new TableView<>();
                viewBooks.setEditable(false);
                viewBooks.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                viewBooks.setPrefHeight(300);
                viewBooks.setPrefWidth(360);

                TableColumn<Book, String> viewBook1_Collumn1 = new TableColumn<>("Book Id");
                viewBook1_Collumn1.setCellValueFactory(new PropertyValueFactory<>("BookId"));
                viewBook1_Collumn1.setMinWidth(228);

                TableColumn<Book, String> viewBook2_Collumn2 = new TableColumn<>("Title");
                viewBook2_Collumn2.setCellValueFactory(new PropertyValueFactory<>("title"));
                viewBook2_Collumn2.setMinWidth(228);

                TableColumn<Book, String> viewBook3_Collumn3 = new TableColumn<>("Author");
                viewBook3_Collumn3.setCellValueFactory(new PropertyValueFactory<>("author"));
                viewBook3_Collumn3.setMinWidth(228);

                TableColumn<Book, String> viewBook4_Collumn4 = new TableColumn<>("Category");
                viewBook4_Collumn4.setCellValueFactory(new PropertyValueFactory<>("category"));
                viewBook4_Collumn4.setMinWidth(228);

                TableColumn<Book, Integer> viewBook5_Collumn5 = new TableColumn<>("Stock");
                viewBook5_Collumn5.setCellValueFactory(new PropertyValueFactory<>("stock"));
                viewBook5_Collumn5.setMinWidth(228);

                viewBooks.getColumns().addAll(viewBook1_Collumn1, viewBook2_Collumn2, viewBook3_Collumn3, viewBook4_Collumn4, viewBook5_Collumn5);

                User user = new User();
                List<Book> bookList = new ArrayList<>(user.getBookList());
                ObservableList<Book> bookDataList = FXCollections.observableArrayList(bookList);
                viewBooks.setItems(bookDataList);

                Button viewBookBackBtn = new Button("Back");
                viewBookBackBtn.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                viewBookBackBtn.setTextFill(Color.DARKGREEN);
                viewBookBackBtn.setMinSize(100, 40);
                viewBookBackBtn.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                viewBookBackBtn.setOnAction(e ->{
                    stage.close();
                    LibrarySystem.setLoginGUIOrder(4);

                    try {
                        LibrarySystem.startingGUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                GridPane viewBookThirdRootPt2 = new GridPane();
                viewBookThirdRootPt2.add(viewBookBackBtn, 3, 12);

                VBox viewBookThirdRoot = new VBox(40, viewBooksMainLabel, viewBooks);
                viewBookThirdRoot.setAlignment(Pos.CENTER);

                VBox viewBookSecRoot = new VBox(10, viewBookThirdRoot, viewBookThirdRootPt2);
                viewBookSecRoot.setAlignment(Pos.CENTER);
                viewBookSecRoot.setPadding(new Insets(0, 30, 60, 30));
                root.getChildren().add(viewBookSecRoot);

                break;
            case 3:
                // template("No", "Id Buku", "Judul", "Author", "Category", "duration");
                Student student1 = new Student("","","","");

                Label viewBorrowedBooksMainLabel = new Label("Borrowed Book List");
                viewBorrowedBooksMainLabel.setFont(Font.font("Segoe ui", FontWeight.BOLD, 50));
                viewBorrowedBooksMainLabel.setTextFill(Color.DARKGREEN);

                TableView<Book> viewBorrowedBook0 = new TableView<>();
                viewBorrowedBook0.setEditable(false);
                viewBorrowedBook0.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                viewBorrowedBook0.setPrefHeight(200);
                viewBorrowedBook0.setPrefWidth(300);

                TableView<Book> viewBorrowedBook = new TableView<>();
                viewBorrowedBook.setEditable(false);
                viewBorrowedBook.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                viewBorrowedBook.setPrefHeight(300);
                viewBorrowedBook.setPrefWidth(360);

                for (Book book : Student.getBorrowedBooks()) {
                    if (lS.getNimStudentListHolder().equals(book.getNimStudent())) {
                        TableColumn<Book, String> viewBorrowedBook_Collumn1 = new TableColumn<>("Book Id");
                        viewBorrowedBook_Collumn1.setCellValueFactory(new PropertyValueFactory<>("BookId"));
                        viewBorrowedBook_Collumn1.setMinWidth(228);

                        TableColumn<Book, String> viewBorrowedBook_Collumn2 = new TableColumn<>("Title");
                        viewBorrowedBook_Collumn2.setCellValueFactory(new PropertyValueFactory<>("title"));
                        viewBorrowedBook_Collumn2.setMinWidth(228);

                        TableColumn<Book, String> viewBorrowedBook_Collumn3 = new TableColumn<>("Author");
                        viewBorrowedBook_Collumn3.setCellValueFactory(new PropertyValueFactory<>("author"));
                        viewBorrowedBook_Collumn3.setMinWidth(228);

                        TableColumn<Book, String> viewBorrowedBook_Collumn4 = new TableColumn<>("Category");
                        viewBorrowedBook_Collumn4.setCellValueFactory(new PropertyValueFactory<>("category"));
                        viewBorrowedBook_Collumn4.setMinWidth(228);

                        TableColumn<Book, Integer> viewBorrowedBook_Collumn5 = new TableColumn<>("Duration");
                        viewBorrowedBook_Collumn5.setCellValueFactory(new PropertyValueFactory<>("duration"));
                        viewBorrowedBook_Collumn5.setMinWidth(228);

                        viewBorrowedBook.getColumns().addAll(viewBorrowedBook_Collumn1, viewBorrowedBook_Collumn2, viewBorrowedBook_Collumn3, viewBorrowedBook_Collumn4, viewBorrowedBook_Collumn5);
                    }
                }

                List<Book> bookList0 = new ArrayList<>(Student.getBorrowedBooks());
                ObservableList<Book> bookDataList0 = FXCollections.observableArrayList(bookList0);
                viewBorrowedBook.setItems(bookDataList0);

                Button viewBorrowedBookBackBtn = new Button("Back");
                viewBorrowedBookBackBtn.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                viewBorrowedBookBackBtn.setTextFill(Color.DARKGREEN);
                viewBorrowedBookBackBtn.setMinSize(100, 40);
                viewBorrowedBookBackBtn.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                viewBorrowedBookBackBtn.setOnAction(e ->{
                    stage.close();
                    LibrarySystem.setLoginGUIOrder(7);

                    try {
                        LibrarySystem.startingGUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                HBox viewBorrowedBookFourtRootPt1 = new HBox(viewBorrowedBookBackBtn);
                viewBorrowedBookFourtRootPt1.setAlignment(Pos.CENTER_LEFT);

                VBox viewBorrowedBookThirdRoot = new VBox(40, viewBorrowedBooksMainLabel, viewBorrowedBook);
                viewBorrowedBookThirdRoot.setAlignment(Pos.CENTER);

                VBox viewBorrowedBookSecRoot = new VBox(10, viewBorrowedBookThirdRoot, viewBorrowedBookFourtRootPt1);
                viewBorrowedBookSecRoot.setAlignment(Pos.CENTER);
                viewBorrowedBookSecRoot.setPadding(new Insets(0, 30, 60, 30));
                root.getChildren().add(viewBorrowedBookSecRoot);

                break;
            case 4:
                // template("No", "Id Buku", "Judul", "Author", "Category", "duration");
                Student student = new Student("","","","");

                Label returnBorrowedBooksMainLabel = new Label("Return Book");
                returnBorrowedBooksMainLabel.setFont(Font.font("Segoe ui", FontWeight.BOLD, 50));
                returnBorrowedBooksMainLabel.setTextFill(Color.DARKGREEN);

                TableView<Book> returnBorrowedBook0 = new TableView<>();
                returnBorrowedBook0.setEditable(false);
                returnBorrowedBook0.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                returnBorrowedBook0.setPrefHeight(200);
                returnBorrowedBook0.setPrefWidth(300);

                TableView<Book> returnBorrowedBook = new TableView<>();
                returnBorrowedBook.setEditable(false);
                returnBorrowedBook.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                returnBorrowedBook.setPrefHeight(300);
                returnBorrowedBook.setPrefWidth(360);

                ArrayList<String> borrowedBookIndexLoc = new ArrayList<>();
                int iterator1 = 0, iterator2 = 0;
                for (Book book : Student.getBorrowedBooks()) {
                    if (lS.getNimStudentListHolder().equals(book.getNimStudent())) {
                        System.out.println("CHECK 2");
                        borrowedBookIndexLoc.add(iterator1, String.valueOf(iterator2+1));
                        iterator1++;
                    }
                    System.out.println(iterator2);
                    iterator2++;
                }

                Label returnBorroweBookLabel1 = new Label("Return Book Number");
                returnBorroweBookLabel1.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                returnBorroweBookLabel1.setTextFill(Color.DARKGREEN);

                ComboBox<String> returnBorrowedBookComboBox = new ComboBox<>();
                returnBorrowedBookComboBox.setMinSize(60,20);
                returnBorrowedBookComboBox.getItems().addAll(borrowedBookIndexLoc);

                Label returnBorroweBookLabel2 = new Label("Book Id");
                returnBorroweBookLabel2.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                returnBorroweBookLabel2.setTextFill(Color.DARKGREEN);

                TextField returnBorrowedBookIdTF = new TextField();
                returnBorrowedBookIdTF.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                returnBorrowedBookIdTF.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                returnBorrowedBookIdTF.setPromptText("Book Id");

                for (Book book : Student.getBorrowedBooks()) {
                    if (lS.getNimStudentListHolder().equals(book.getNimStudent())) {
                        TableColumn<Book, String> viewBorrowedBook_Collumn1 = new TableColumn<>("Book Id");
                        viewBorrowedBook_Collumn1.setCellValueFactory(new PropertyValueFactory<>("BookId"));
                        viewBorrowedBook_Collumn1.setMinWidth(228);

                        TableColumn<Book, String> viewBorrowedBook_Collumn2 = new TableColumn<>("Title");
                        viewBorrowedBook_Collumn2.setCellValueFactory(new PropertyValueFactory<>("title"));
                        viewBorrowedBook_Collumn2.setMinWidth(228);

                        TableColumn<Book, String> viewBorrowedBook_Collumn3 = new TableColumn<>("Author");
                        viewBorrowedBook_Collumn3.setCellValueFactory(new PropertyValueFactory<>("author"));
                        viewBorrowedBook_Collumn3.setMinWidth(228);

                        TableColumn<Book, String> viewBorrowedBook_Collumn4 = new TableColumn<>("Category");
                        viewBorrowedBook_Collumn4.setCellValueFactory(new PropertyValueFactory<>("category"));
                        viewBorrowedBook_Collumn4.setMinWidth(228);

                        TableColumn<Book, Integer> viewBorrowedBook_Collumn5 = new TableColumn<>("Duration");
                        viewBorrowedBook_Collumn5.setCellValueFactory(new PropertyValueFactory<>("duration"));
                        viewBorrowedBook_Collumn5.setMinWidth(228);

                        returnBorrowedBook.getColumns().addAll(viewBorrowedBook_Collumn1, viewBorrowedBook_Collumn2, viewBorrowedBook_Collumn3, viewBorrowedBook_Collumn4, viewBorrowedBook_Collumn5);
                    }
                }

                List<Book> bookList1 = new ArrayList<>(Student.getBorrowedBooks());
                ObservableList<Book> bookDataList1 = FXCollections.observableArrayList(bookList1);
                returnBorrowedBook.setItems(bookDataList1);

                Button borrowedBookReturn1 = new Button("   Return   ");
                borrowedBookReturn1.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                borrowedBookReturn1.setTextFill(Color.WHITE);
                borrowedBookReturn1.setStyle("-fx-padding: 7px 13px; " +
                        "-fx-background-color: #0077B6; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: #051094; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 5px;");
                borrowedBookReturn1.setOnAction(e -> {
                    LoginGUI lG = new LoginGUI();
                    if (Student.getBorrowedBooks().isEmpty()) {
                        lG.alertWarning(7);
                    }
                    else {
                        int choice = Integer.parseInt(returnBorrowedBookComboBox.getValue());
                        Book sB = student.idBookFinder(returnBorrowedBookIdTF.getText()); //String bookId, String title, String author, String category, int stock
                        User.updateStock(sB.getBookId(), sB.getStock()+1);

                        LibrarySystem lS = new LibrarySystem();
                        for(Book book : Student.getBorrowedBooks()) {
                            System.out.println("CHECK 3");
                            if (choice > 0 && choice <= Student.getBorrowedBooks().size() && lS.getNimStudentListHolder().equals(book.getNimStudent())) {
                                System.out.println("CHECK 4");
                                Book returnedBook = Student.getBorrowedBooks().remove(choice - 1);
                                returnedBook.setStock(returnedBook.getStock() + 1);

                                lG.alertWarning(8);
                                break;
                            }
                        }

                        stage.close();

                        LibrarySystem.setLoginGUIOrder(7);

                        try {
                            LibrarySystem.startingGUI();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

                Button returnBorrowedBookBack = new Button("Back");
                returnBorrowedBookBack.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                returnBorrowedBookBack.setTextFill(Color.DARKGREEN);
                returnBorrowedBookBack.setMinSize(100, 40);
                returnBorrowedBookBack.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                returnBorrowedBookBack.setOnAction(e ->{
                    stage.close();
                    LibrarySystem.setLoginGUIOrder(7);

                    try {
                        LibrarySystem.startingGUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                HBox returnBorrowedBookFourtRootPt3 = new HBox(10, returnBorroweBookLabel2, returnBorrowedBookIdTF);
                returnBorrowedBookFourtRootPt3.setAlignment(Pos.CENTER_LEFT);
                HBox returnBorrowedBookFourtRootPt2 = new HBox(10, returnBorroweBookLabel1, returnBorrowedBookComboBox);
                HBox returnBorrowedBookFourtRootPt1= new HBox(10, borrowedBookReturn1,returnBorrowedBookBack);

                HBox returnBorrowedBookThirdRootPt2 = new HBox(665, returnBorrowedBookFourtRootPt3, returnBorrowedBookFourtRootPt1);

                VBox returnBorrowedBookThirdRoot = new VBox(40, returnBorrowedBooksMainLabel, returnBorrowedBook);
                returnBorrowedBookThirdRoot.setAlignment(Pos.CENTER);

                VBox returnBorrowedBookSecRoot = new VBox(10, returnBorrowedBookThirdRoot, returnBorrowedBookThirdRootPt2, returnBorrowedBookFourtRootPt2);
                returnBorrowedBookSecRoot.setAlignment(Pos.CENTER);
                returnBorrowedBookSecRoot.setPadding(new Insets(0, 30, 60, 30));
                root.getChildren().add(returnBorrowedBookSecRoot);

                break;
        }

        Scene scene = new Scene(root, 1200,600);

        stage.setTitle("TableView");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
