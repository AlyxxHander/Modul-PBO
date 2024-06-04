package org.example.modul6tugas;

import books.Book;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import com.main.LibrarySystem;
import data.Admin;

import data.Student;
import data.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class LoginGUI extends Application {
    private LibrarySystem lS = new LibrarySystem();
    @Override
    public void start(Stage stage) throws IOException {
        /* ====================
        GUILoginOrder:
        0 = reset order/blank
        1 = LibrarySystem menu
        2 = Input NIm
        3 = Check Admin
        4 = Admin menu
        5 = Admin adding student
        6 = Admin Input Book
        7 = Student Menu
        8 = Student Borrow Book
        9 =
        GUI OPTION
        99 = Back
        ==================== */

        // Layout to Stack Component on top of each other
        StackPane root = new StackPane();

        // Load, resize, and put Background Image to root
        try {
            Image backgroundImage = new Image("file:/D:/Coding Stuff/Library/Image/LeavesBg.jpg");
            ImageView backgroundImageView = new ImageView(backgroundImage);

            backgroundImageView.fitWidthProperty().bind(stage.widthProperty());
            backgroundImageView.fitHeightProperty().bind(stage.heightProperty());
            root.getChildren().add(backgroundImageView);
        } catch(Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            e.printStackTrace();
        }

        //int GUILoginOrder = 8;
        //lS.getGUILoginOrder() ; GUILoginOrder
        switch(lS.getGUILoginOrder()) {
            case 1:
                // COMPONENTS
                Label lSLogin = new Label("Library System Login");
                lSLogin.setFont(Font.font("Segoe ui", FontWeight.BOLD, 30));
                lSLogin.setTextFill(Color.DARKGREEN);

                Button lSBtn1 = new Button("Login sebagai Mahasiswa");
                lSBtn1.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 17));
                lSBtn1.setTextFill(Color.DARKGREEN);
                lSBtn1.setStyle("-fx-padding: 10px 15px; " +
                        "-fx-background-radius: 10px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 10px;");
                lSBtn1.setOnAction(e -> {
                    //LibrarySystem.setGUIOption(1);
                    if(lS.getNimStudentList().isEmpty()) {
                        alertWarning(1);
                    }
                    else {
                        stage.close();

                        try {
                            LibrarySystem.setLoginGUIOrder(2);
                            LibrarySystem.startingGUI();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

                Button lSBtn2 = new Button("     Login sebagai Admin     ");
                lSBtn2.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                lSBtn2.setTextFill(Color.DARKGREEN);
                lSBtn2.setStyle("-fx-padding: 10px 15px; " +
                        "-fx-background-radius: 10px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 10px;");
                lSBtn2.setOnAction(e -> {
                    stage.close();
                    LibrarySystem.setLoginGUIOrder(3);
                    try {
                        LibrarySystem.startingGUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                Button lSBtn3 = new Button("                Keluar                 ");
                lSBtn3.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                lSBtn3.setTextFill(Color.DARKGREEN);
                lSBtn3.setStyle("-fx-padding: 10px 15px; " +
                        "-fx-background-radius: 10px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 10px;");
                lSBtn3.setOnAction(e -> {
                    //LibrarySystem.setGUIOption(3);
                    stage.close();
                });

                // THIRD (vOrder Sub-Panel)
                VBox lSThirdRoot = new VBox(15, lSBtn1, lSBtn2, lSBtn3);
                lSThirdRoot.setAlignment(Pos.CENTER);

                // SECOND (root Sub-Panel)
                VBox lSSecRoot = new VBox(40, lSLogin, lSThirdRoot);
                lSSecRoot.setAlignment(Pos.CENTER);
                lSSecRoot.setPadding(new Insets(0, 0, 30, 0));
                root.getChildren().add(lSSecRoot);

                break;
            // Input NIM [SIMPLE (NEED DESIGNING) ]
            case 2:
                Student student1 = new Student("","","","");

                Label inputNimLabel = new Label("Masukkan NIM Anda (99 back)");
                inputNimLabel.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 18));
                inputNimLabel.setTextFill(Color.DARKGREEN);

                TextField inputNimTF = new TextField();
                inputNimTF.setPromptText("Masukkan 99 untuk kembali)");
                inputNimTF.setMaxWidth(260);

                Button inputNimBtn1 = new Button("Sign In");
                inputNimBtn1.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                inputNimBtn1.setTextFill(Color.DARKGREEN);
                inputNimBtn1.setStyle("-fx-padding: 10px 15px; " +
                        "-fx-background-radius: 10px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 10px;");
                inputNimBtn1.setOnAction(e -> {
                    LibrarySystem.setGUIStringOption(inputNimTF.getText());

                    boolean isCorrect = false;
                    for(String s : lS.getNimStudentList()) {
                        if(s != null && s.equals(inputNimTF.getText())) {
                            stage.close();
                            isCorrect = true;
                            lS.setNimStudentListHolder(inputNimTF.getText());

                            student1.menu();
                        }
                    }

                    // NEED FIXING
                    /*
                    User user = new User();
                    //user.getBookList()
                    for (Book book : user.getBookList()) {
                        if (book != null && book.getNimStudent().equals(inputNimTF.getText())) {
                            stage.close();
                            isCorrect = true;
                            System.out.println("CHECK 2");

                            student1.menu();
                        }
                    }
                     */
                    if(isCorrect == false) {
                        try {
                            LibrarySystem.inputNim();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                
                VBox lSSecRoot1 = new VBox(15, inputNimLabel, inputNimTF, inputNimBtn1);
                lSSecRoot1.setPadding(new Insets(0, 0, 20, 0));
                lSSecRoot1.setAlignment(Pos.CENTER);
                root.getChildren().add(lSSecRoot1);

                break;
            // Checking is Admin
            case 3:
                Label adminLoginLabel = new Label("Admin Login");
                adminLoginLabel.setFont(Font.font("Segoe ui", FontWeight.BOLD, 30));
                adminLoginLabel.setTextFill(Color.DARKGREEN);

                TextField adminUsernameInput = new TextField();
                adminUsernameInput.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                adminUsernameInput.setMaxWidth(250);
                adminUsernameInput.setStyle("-fx-padding: 5px 10px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 5px;");
                adminUsernameInput.setPromptText("Username");

                PasswordField adminPasswordInput = new PasswordField();
                adminPasswordInput.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                adminPasswordInput.setMaxWidth(250);
                adminPasswordInput.setStyle("-fx-padding: 5px 10px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 5px;");
                adminPasswordInput.setPromptText("Password");

                Button adminLoginSignIn = new Button("                  Sign In                  ");
                adminLoginSignIn.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                adminLoginSignIn.setTextFill(Color.WHITE);
                //adminLoginSignIn.setAlignment(Pos.BASELINE_RIGHT);
                adminLoginSignIn.setStyle("-fx-padding: 7px 13px; " +
                        "-fx-background-color: #0077B6; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: #051094; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 5px;");
                adminLoginSignIn.setOnAction(e -> {
                    Admin admin = new Admin();

                    boolean isCorrect = admin.isAdmin(adminUsernameInput.getText(), adminPasswordInput.getText());

                    if (isCorrect == true) {
                        stage.close();
                        LibrarySystem.setLoginGUIOrder(4);

                        try {
                            LibrarySystem.startingGUI();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else {
                        stage.close();
                        LibrarySystem.setLoginGUIOrder(1);

                        try {
                            LibrarySystem.startingGUI();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

                VBox adminLoginThirdRoot = new VBox(10, adminUsernameInput, adminPasswordInput, adminLoginSignIn);
                adminLoginThirdRoot.setAlignment(Pos.CENTER);

                VBox adminLoginSecRoot = new VBox(30, adminLoginLabel, adminLoginThirdRoot);
                adminLoginSecRoot.setPadding(new Insets(0, 0, 40, 0));
                adminLoginSecRoot.setAlignment(Pos.CENTER);
                root.getChildren().add(adminLoginSecRoot);

                break;
            // Admin [Simple (Need Designing) ]
            case 4:
                /* ==============================================
                System.out.print("===== Menu Admin =====");
                System.out.println("1. Tambah Mahasiswa");
                System.out.println("2. Tampilkan Mahasiswa");
                System.out.println("3. Input Buku");
                System.out.println("4. Tampilkan Daftar Buku");
                System.out.println("5. Logout");
                System.out.println("Pilih antara (1-5):");
                ============================================== */
                Label menuAdminLabel = new Label("Menu Admin");
                menuAdminLabel.setFont(Font.font("Segoe ui", FontWeight.BOLD, 30));
                menuAdminLabel.setTextFill(Color.DARKGREEN);

                Button menuAdminBtn1 = new Button("      Tambah Mahasiswa      ");
                menuAdminBtn1.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                menuAdminBtn1.setStyle("-fx-padding: 5px 10px; " +
                        "-fx-background-radius: 8px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 8px;");
                menuAdminBtn1.setOnAction(e -> {
                    stage.close();

                    Admin admin = new Admin();
                    admin.addStudent();
                });

                Button menuAdminBtn2 = new Button("    Tampilkan Mahasiswa    ");
                menuAdminBtn2.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                menuAdminBtn2.setStyle("-fx-padding: 5px 10px; " +
                        "-fx-background-radius: 8px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 8px;");
                menuAdminBtn2.setOnAction(e -> {
                    stage.close();

                    LibrarySystem.addTempStudent();
                });

                Button menuAdminBtn3 = new Button("             Input Buku             ");
                menuAdminBtn3.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                menuAdminBtn3.setStyle("-fx-padding: 5px 10px; " +
                        "-fx-background-radius: 8px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 8px;");
                menuAdminBtn3.setOnAction(e -> {
                    stage.close();

                    Admin admin = new Admin();
                    admin.inputBook();
                });

                Button menuAdminBtn4 = new Button("   Tampilkan Daftar Buku   ");
                menuAdminBtn4.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                menuAdminBtn4.setStyle("-fx-padding: 5px 10px; " +
                        "-fx-background-radius: 8px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 8px;");
                menuAdminBtn4.setOnAction(e -> {
                    stage.close();

                    LibrarySystem.addTempBooks();
                });

                Button menuAdminBtn5 = new Button("               Logout                ");
                menuAdminBtn5.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                menuAdminBtn5.setStyle("-fx-padding: 5px 10px; " +
                        "-fx-background-radius: 8px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 8px;");
                menuAdminBtn5.setOnAction(e -> {
                    stage.close();
                    LibrarySystem.setLoginGUIOrder(1);

                    Admin admin = new Admin();
                    admin.menu();

                });

                VBox adminThirdRoot = new VBox(10, menuAdminBtn1, menuAdminBtn2, menuAdminBtn3, menuAdminBtn4, menuAdminBtn5);
                adminThirdRoot.setPadding(new Insets(0, 0, 30, 0));
                adminThirdRoot.setAlignment(Pos.CENTER);

                VBox adminSecRoot = new VBox(40, menuAdminLabel,adminThirdRoot);
                adminSecRoot.setAlignment(Pos.CENTER);
                root.getChildren().add(adminSecRoot);

                break;
            case 5:
                Label adminMainLabelChoice1 = new Label("Adding Student");
                adminMainLabelChoice1.setFont(Font.font("Segoe ui", FontWeight.BOLD, 30));
                adminMainLabelChoice1.setTextFill(Color.DARKGREEN);

                Label adminUsernameChoice1 = new Label("Name");
                adminUsernameChoice1.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                adminUsernameChoice1.setTextFill(Color.DARKGREEN);

                TextField adminAddingUsername = new TextField();
                adminAddingUsername.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                adminAddingUsername.setMaxWidth(200);
                adminAddingUsername.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                adminAddingUsername.setPromptText("Username");

                Label adminNimChoice1 = new Label("NIM");
                adminNimChoice1.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                adminNimChoice1.setTextFill(Color.DARKGREEN);

                TextField adminAddingNim = new TextField();
                adminAddingNim.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                adminAddingNim.setMaxWidth(200);
                adminAddingNim.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                adminAddingNim.setPromptText("NIM");

                Label adminFakultasChoice1 = new Label("Fakultas");
                adminFakultasChoice1.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                adminFakultasChoice1.setTextFill(Color.DARKGREEN);

                TextField adminAddingFakultas = new TextField();
                adminAddingFakultas.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                adminAddingFakultas.setMaxWidth(200);
                adminAddingFakultas.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                adminAddingFakultas.setPromptText("Fakultas");

                Label adminProdiChoice1 = new Label("Prodi");
                adminProdiChoice1.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                adminProdiChoice1.setTextFill(Color.DARKGREEN);

                TextField adminAddingProdi = new TextField();
                adminAddingProdi.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                adminAddingProdi.setMaxWidth(200);
                adminAddingProdi.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                adminAddingProdi.setPromptText("Prodi");

                Button adminAddingBack = new Button("   Back   ");
                adminAddingBack.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                adminAddingBack.setTextFill(Color.DARKGREEN);
                adminAddingBack.setStyle("-fx-padding: 7px 13px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 5px;");
                adminAddingBack.setOnAction(e -> {
                    stage.close();
                    LibrarySystem.setLoginGUIOrder(4);

                    try {
                        LibrarySystem.startingGUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                Button adminAddingAdd = new Button("   Add   ");
                adminAddingAdd.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                adminAddingAdd.setTextFill(Color.WHITE);
                adminAddingAdd.setStyle("-fx-padding: 7px 13px; " +
                        "-fx-background-color: #0077B6; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: #051094; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 5px;");
                adminAddingAdd.setOnAction(e -> {

                    LibrarySystem.setLoginGUIOrder(4);
                    Admin admin = new Admin();
                    int isNimDupe = admin.isStudentNimDuplicate(adminAddingNim.getText());
                    if(isNimDupe == 0) {
                        alertWarning(0);
                    }
                    else if(isNimDupe == 1) {
                        alertWarning(2);
                    }
                    else if(isNimDupe == 2) {
                        lS.setNimStudentList(adminAddingNim.getText());

                        // Adding new Student Data that's not a duplicated
                        Admin.addStudentList(adminAddingUsername.getText(),
                                adminAddingFakultas.getText(),
                                adminAddingNim.getText(),
                                adminAddingProdi.getText());

                        alertWarning(5);

                        stage.close();

                        LibrarySystem.setLoginGUIOrder(4);

                        try {
                            LibrarySystem.startingGUI();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

                GridPane adminThirdRootChoice1 = new GridPane();
                adminThirdRootChoice1.setVgap(10);
                adminThirdRootChoice1.setHgap(20);
                adminThirdRootChoice1.setAlignment(Pos.BASELINE_LEFT);
                adminThirdRootChoice1.setPadding(new Insets(0, 0, 0, 35));
                adminThirdRootChoice1.add(adminUsernameChoice1, 1, 1);
                adminThirdRootChoice1.add(adminAddingUsername, 2, 1, 4, 1);
                adminThirdRootChoice1.add(adminNimChoice1, 1, 2);
                adminThirdRootChoice1.add(adminAddingNim, 2, 2, 4, 1);
                adminThirdRootChoice1.add(adminFakultasChoice1, 1, 3);
                adminThirdRootChoice1.add(adminAddingFakultas, 2, 3, 4, 1);
                adminThirdRootChoice1.add(adminProdiChoice1, 1, 4);
                adminThirdRootChoice1.add(adminAddingProdi, 2, 4, 4, 1);
                adminThirdRootChoice1.add(adminAddingBack, 3, 5);
                adminThirdRootChoice1.add(adminAddingAdd,4, 5);

                VBox adminSecRootChoice1 = new VBox(40, adminMainLabelChoice1,adminThirdRootChoice1);
                adminSecRootChoice1.setAlignment(Pos.CENTER);
                adminSecRootChoice1.setPadding(new Insets(0, 0, 40, 0));
                root.getChildren().add(adminSecRootChoice1);

                break;
            case 6:
                Label inputBookMainLabel = new Label("Input Book");
                inputBookMainLabel.setFont(Font.font("Segoe ui", FontWeight.BOLD, 30));
                inputBookMainLabel.setTextFill(Color.DARKGREEN);

                Label inputBookLabel1 = new Label("Book Type");
                inputBookLabel1.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                inputBookLabel1.setTextFill(Color.DARKGREEN);

                ComboBox<String> inputBookComboBox = new ComboBox<>();
                inputBookComboBox.getItems().addAll("History", "Story", "Text");

                AtomicReference<String> category = new AtomicReference<>("");
                inputBookComboBox.setOnAction(event -> {
                    int selectedIndex = inputBookComboBox.getSelectionModel().getSelectedIndex() + 1;

                    switch (selectedIndex) {
                        case 1:
                            HistoryBook historyBook = new HistoryBook("", "", "", "", 0);
                            category.set(historyBook.getCategory());
                            break;
                        case 2:
                            StoryBook storyBook = new StoryBook("", "", "", "", 0);
                            category.set(storyBook.getCategory());
                            break;
                        case 3:
                            TextBook textBook = new TextBook("", "", "", "", 0);
                            category.set(textBook.getCategory());
                            break;
                        default:
                            System.out.println("Pilihan tidak valid!");
                    }
                });

                Label inputBookLabel2 = new Label("Title");
                inputBookLabel2.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                inputBookLabel2.setTextFill(Color.DARKGREEN);

                TextField inputBookTF1 = new TextField();
                inputBookTF1.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                inputBookTF1.setMaxWidth(200);
                inputBookTF1.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                inputBookTF1.setPromptText("Title");

                Label inputBookLabel3 = new Label("Author");
                inputBookLabel3.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                inputBookLabel3.setTextFill(Color.DARKGREEN);

                TextField inputBookTF2 = new TextField();
                inputBookTF2.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                inputBookTF2.setMaxWidth(200);
                inputBookTF2.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                inputBookTF2.setPromptText("Author");

                Label inputBookLabel4 = new Label("Stock");
                inputBookLabel4.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                inputBookLabel4.setTextFill(Color.DARKGREEN);

                TextField inputBookTF3 = new TextField();
                inputBookTF3.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                inputBookTF3.setMaxWidth(200);
                inputBookTF3.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                inputBookTF3.setPromptText("Stock");

                Admin admin = new Admin();

                Button inputBookBack = new Button("   Back   ");
                inputBookBack.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                inputBookBack.setTextFill(Color.DARKGREEN);
                inputBookBack.setStyle("-fx-padding: 7px 13px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 5px;");
                inputBookBack.setOnAction(e -> {
                    stage.close();
                    LibrarySystem.setLoginGUIOrder(4);

                    try {
                        LibrarySystem.startingGUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                Button inputBookAdd = new Button("   Add   ");
                inputBookAdd.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                inputBookAdd.setTextFill(Color.WHITE);
                inputBookAdd.setStyle("-fx-padding: 7px 13px; " +
                        "-fx-background-color: #0077B6; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: #051094; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 5px;");
                inputBookAdd.setOnAction(e -> {
                    // Final Data to Input Book
                    //String bookId = admin.generateId();
                    //String title = inputBookTF1.getText();
                    //String author = inputBookTF2.getText();
                    //String covertedCategory = category.get();
                    //int stock = Integer.parseInt(inputBookTF3.getText());

                    User.addBooks(admin.generateId(),
                            inputBookTF1.getText(),
                            inputBookTF2.getText(),
                            category.get(),
                            Integer.parseInt(inputBookTF3.getText()));

                    stage.close();
                    LibrarySystem.setLoginGUIOrder(4);
                    alertWarning(5);

                    try {
                        LibrarySystem.startingGUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                GridPane inputBookThirdRoot = new GridPane();
                inputBookThirdRoot.setVgap(10);
                inputBookThirdRoot.setHgap(20);
                inputBookThirdRoot.setAlignment(Pos.BASELINE_LEFT);
                //==========================================================================
                //inputBookThirdRoot.setPadding(new Insets(0, 0, 0, 15));
                inputBookThirdRoot.add(inputBookLabel1, 5, 1);
                inputBookThirdRoot.add(inputBookComboBox, 5, 2);
                inputBookThirdRoot.add(inputBookLabel2, 2, 1);
                inputBookThirdRoot.add(inputBookTF1, 2, 2, 4, 1);
                inputBookThirdRoot.add(inputBookLabel3, 2, 3);
                inputBookThirdRoot.add(inputBookTF2, 2, 4, 4, 1);
                inputBookThirdRoot.add(inputBookLabel4, 2, 5);
                inputBookThirdRoot.add(inputBookTF3, 2, 6, 4, 1);
                inputBookThirdRoot.add(inputBookBack, 2, 7);
                inputBookThirdRoot.add(inputBookAdd, 3, 7);

                VBox inputBookSecRoot = new VBox(20, inputBookMainLabel, inputBookThirdRoot);
                inputBookSecRoot.setAlignment(Pos.CENTER);
                inputBookSecRoot.setPadding(new Insets(0, 0, 80, 0));
                root.getChildren().add(inputBookSecRoot);

                break;
            case 7:
                /* ==============================================
                System.out.print("===== Menu Student =====\n
                1. Tampilkan Buku yang dipinjam\n
                2. Pinjam Buku\n
                3. Kembalikan Buku\n
                4. Logout\n
                Pilih antara (1-4): ");
                ============================================== */
                Label menuStudentMainLabel = new Label("Menu Student");
                menuStudentMainLabel.setFont(Font.font("Segoe ui", FontWeight.BOLD, 30));
                menuStudentMainLabel.setTextFill(Color.DARKGREEN);

                Button menuStudentBtn1 = new Button("      View borrowed Books      ");
                menuStudentBtn1.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                menuStudentBtn1.setStyle("-fx-padding: 5px 10px; " +
                        "-fx-background-radius: 8px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 8px;");
                menuStudentBtn1.setOnAction(e -> { // tableview
                    stage.close();

                    Student student = new Student("", "","","");
                    student.showBorrowedBooks();
                });

                Button menuStudentBtn2 = new Button("      Borrow Book      ");
                menuStudentBtn2.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                menuStudentBtn2.setStyle("-fx-padding: 5px 10px; " +
                        "-fx-background-radius: 8px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 8px;");
                menuStudentBtn2.setOnAction(e -> {
                    stage.close();

                    Student student = new Student("","","","");
                    student.choiceBook();
                });

                Button menuStudentBtn3 = new Button("      Return Book      ");
                menuStudentBtn3.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                menuStudentBtn3.setStyle("-fx-padding: 5px 10px; " +
                        "-fx-background-radius: 8px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 8px;");
                menuStudentBtn3.setOnAction(e -> {
                    stage.close();

                    Student student = new Student("","","","");
                    student.returnBooks();
                });

                Button menuStudentBtn4 = new Button("      Logout      ");
                menuStudentBtn4.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                menuStudentBtn4.setStyle("-fx-padding: 5px 10px; " +
                        "-fx-background-radius: 8px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 8px;");
                menuStudentBtn4.setOnAction(e -> { // NEED CONTINUING
                    stage.close();

                    //Confirmation
                    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmationAlert.setTitle("Confirmation");
                    confirmationAlert.setHeaderText("Please Confirm your choice");
                    confirmationAlert.setContentText("Do you want to proceed (no = borrow book, yes = logout)?");
                    confirmationAlert.setResizable(false);

                    ButtonType yesBtn = new ButtonType("Yes");
                    ButtonType noBtn = new ButtonType("No");
                    confirmationAlert.getButtonTypes().setAll(yesBtn, noBtn);

                    Student student = new Student("", "", "", "");
                    Optional<ButtonType> result = confirmationAlert.showAndWait();
                    if(result.isPresent()) {
                        ButtonType chosenBtn = result.get();
                        if(chosenBtn == yesBtn) {
                            stage.close();

                            student.logout();
                        }
                        else if(chosenBtn == noBtn) {
                            stage.close();

                            student.returnBooks();
                        }
                    }
                });

                VBox menuStudentThirdRoot = new VBox(10, menuStudentBtn1, menuStudentBtn2, menuStudentBtn3, menuStudentBtn4);
                menuStudentThirdRoot.setPadding(new Insets(0, 0, 30, 0));
                menuStudentThirdRoot.setAlignment(Pos.CENTER);

                VBox menuStudentSecRoot = new VBox(40, menuStudentMainLabel,menuStudentThirdRoot);
                menuStudentSecRoot.setAlignment(Pos.CENTER);
                root.getChildren().add(menuStudentSecRoot);

                break;
            case 8:
                Label borrowBookMainLabel = new Label("Borrow Book");
                borrowBookMainLabel.setFont(Font.font("Segoe ui", FontWeight.BOLD, 30));
                borrowBookMainLabel.setTextFill(Color.DARKGREEN);

                Label borrowBookLabel1 = new Label("Book Id");
                borrowBookLabel1.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                borrowBookLabel1.setTextFill(Color.DARKGREEN);

                TextField borrowBookTF1 = new TextField();
                borrowBookTF1.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                borrowBookTF1.setMaxWidth(360);
                borrowBookTF1.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                borrowBookTF1.setPromptText("Book Id");

                Label borrowBookLabel2 = new Label("Date Due");
                borrowBookLabel2.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 16));
                borrowBookLabel2.setTextFill(Color.DARKGREEN);

                TextField borrowBookTF2 = new TextField();
                borrowBookTF2.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                borrowBookTF2.setMaxWidth(420);
                borrowBookTF2.setStyle("-fx-padding: 4px 7px; " +
                        "-fx-background-radius: 4px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 4px;");
                borrowBookTF2.setPromptText("Date Due");

                Button borrowBookBack = new Button("   Back   ");
                borrowBookBack.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                borrowBookBack.setTextFill(Color.DARKGREEN);
                borrowBookBack.setStyle("-fx-padding: 5px 10px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: #006400; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 5px;");
                borrowBookBack.setOnAction(e -> {
                    stage.close();
                    LibrarySystem.setLoginGUIOrder(7);

                    try {
                        LibrarySystem.startingGUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                Button borrowBookBorrow = new Button("   Borrow   ");
                borrowBookBorrow.setFont(Font.font("Segoe ui", FontWeight.NORMAL, 15));
                borrowBookBorrow.setTextFill(Color.WHITE);
                borrowBookBorrow.setStyle("-fx-padding: 5px 10px; " +
                        "-fx-background-color: #0077B6; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: #051094; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 5px;");
                borrowBookBorrow.setOnAction(e -> {
                    boolean isCorrect = false;
                    boolean isCorrect1 = false;
                    final String[] unrestrictedDateDue = {"1", "2", "3", "4", "5", "6", "7", "8", "9",
                            "10", "11", "12", "13", "14"};
                    User user = new User();
                    for(Book book : user.getBookList()) {
                        if(borrowBookTF1.getText().equals(book.getBookId()) ) {
                            isCorrect1 = true;
                        }
                    }
                    for (String s : unrestrictedDateDue) {
                        if (borrowBookTF2.getText().equals(s) && isCorrect1 == true) {
                            isCorrect = true;
                            LibrarySystem.setLoginGUIOrder(7);
                            alertWarning(5);

                            Student student = new Student("", "", "", "");
                            for (int i = 0; i < (user.getBookList().size()); i++) {
                                Book book = user.getBookList().get(i);
                                LibrarySystem librarySystem = new LibrarySystem();

                                if (book.getBookId().equals(borrowBookTF1.getText())) {
                                    book.setDuration(Integer.parseInt(borrowBookTF2.getText()));

                                    student.setBorrowedBooks(book.setNimStudent(librarySystem.getNimStudentListHolder()), book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock(), book.getDuration() );
                                    //borrowedBooks.add(new Book(book.setNimStudent(librarySystem.getNimStudentListHolder()), book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock(), book.getDuration()));
                                    break;
                                }
                            }

                            Book selectedBook = student.idBookFinder(borrowBookTF1.getText()); // get all the selected book information
                            if (selectedBook != null && selectedBook.getStock() > 0) {
                                selectedBook.setStock(selectedBook.getStock() - 1);
                                //borrowedBooks.add(selectedBook);
                            }

                            stage.close();
                            try {
                                LibrarySystem.startingGUI();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }

                    if(isCorrect == false && isCorrect1 == false) {
                        alertWarning(6);
                    }
                });

                GridPane borrowBookThirdRoot = new GridPane();
                borrowBookThirdRoot.setVgap(10);
                borrowBookThirdRoot.setHgap(20);
                borrowBookThirdRoot.setAlignment(Pos.BASELINE_LEFT);
                //==========================================================================
                borrowBookThirdRoot.add(borrowBookLabel1, 3, 2);
                borrowBookThirdRoot.add(borrowBookTF1, 3, 3, 6, 1);
                borrowBookThirdRoot.add(borrowBookLabel2, 3, 4);
                borrowBookThirdRoot.add(borrowBookTF2, 3, 5, 6, 1);
                borrowBookThirdRoot.add(borrowBookBack, 3, 6);
                borrowBookThirdRoot.add(borrowBookBorrow, 7, 6);

                VBox borrowBookSecRoot = new VBox(20, borrowBookMainLabel, borrowBookThirdRoot);
                borrowBookSecRoot.setAlignment(Pos.CENTER);
                borrowBookSecRoot.setPadding(new Insets(0, 0, 80, 0));
                root.getChildren().add(borrowBookSecRoot);

                break;
        }

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));Scene scene = new Scene(root, 320, 240);
        //stage.setScene(new Stage());
        Scene scene = new Scene(root, 400,450);

        stage.setTitle("Halaman Login");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void alertWarning(int alertState) {
        /* ====================
        AlertWarning State:
        0 = reset order/blank
        1 = NIM anda belum terdaftar !
        2 = NIM anda tidak valid! Harus 15 karakter !
        3 = Illegal Credentials (username dan password admin tidak benar)
        ==================== */

        switch(alertState) {
            case 0:
                Alert alertWarning0 = new Alert(Alert.AlertType.INFORMATION);
                alertWarning0.setTitle("NOTICE");
                alertWarning0.setHeaderText("NIM anda Sudah terdaftar");
                alertWarning0.setResizable(false);
                alertWarning0.showAndWait();

                break;
            case 1:
                Alert alertWarning1 = new Alert(Alert.AlertType.INFORMATION);
                alertWarning1.setTitle("NOTICE");
                alertWarning1.setHeaderText("NIM anda belum terdaftar !");
                alertWarning1.setContentText("Masukkan NIM yang sudah terdaftar ...");
                alertWarning1.setResizable(false);
                alertWarning1.showAndWait();

                break;
            case 2:
                Alert alertWarning2 = new Alert(Alert.AlertType.INFORMATION);
                alertWarning2.setTitle("NOTICE");
                alertWarning2.setHeaderText("NIM anda tidak valid! Harus 15 karakter !");
                alertWarning2.setContentText("Masukkan NIM dengan 15 karakter ...");
                alertWarning2.setResizable(false);
                alertWarning2.showAndWait();

                break;
            case 3:
                Alert alertWarning3 = new Alert(Alert.AlertType.INFORMATION);
                alertWarning3.setTitle("NOTICE");
                alertWarning3.setHeaderText("Invalid Credentials");
                alertWarning3.setContentText("Username atau Password yang anda masukkan salah");
                alertWarning3.setResizable(false);
                alertWarning3.showAndWait();

                break;
            case 4:
                Alert alertWarning4 = new Alert(Alert.AlertType.INFORMATION);
                alertWarning4.setTitle("Notice");
                alertWarning4.setHeaderText("Anda Berhasil Login");
                alertWarning4.setResizable(false);
                alertWarning4.showAndWait();

                break;
            case 5:
                Alert alertWarning5 = new Alert(Alert.AlertType.INFORMATION);
                alertWarning5.setTitle("Notice");
                alertWarning5.setHeaderText("Berhasil Menambahkan");
                alertWarning5.setResizable(false);
                alertWarning5.showAndWait();

                break;
            case 6:
                Alert alertWarning6 = new Alert(Alert.AlertType.INFORMATION);
                alertWarning6.setTitle("Notice");
                alertWarning6.setHeaderText("Invalid Due Date or Book Id");
                alertWarning6.setResizable(false);
                alertWarning6.showAndWait();

                break;
            case 7:
                Alert alertWarning7 = new Alert(Alert.AlertType.INFORMATION);
                alertWarning7.setTitle("Notice");
                alertWarning7.setHeaderText("Anda Belum Meminjam Buku");
                alertWarning7.setResizable(false);
                alertWarning7.showAndWait();

                break;
            case 8:
                Alert alertWarning8 = new Alert(Alert.AlertType.INFORMATION);
                alertWarning8.setTitle("Notice");
                alertWarning8.setHeaderText("Anda Berhasil Mengembalikan Buku");
                alertWarning8.setResizable(false);
                alertWarning8.showAndWait();

                break;
        }
    }
}

