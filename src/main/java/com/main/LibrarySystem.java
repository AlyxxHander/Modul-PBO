package com.main;

import data.Admin;
import data.Student;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.example.modul6tugas.LoginGUI;
import org.example.modul6tugas.TableViewGUI;

import java.io.IOException;
import java.util.ArrayList;

public class LibrarySystem {
    private static ArrayList<String> nimStudentList = new ArrayList<>();
    private static String nimStudentListHolder;
    private static LoginGUI login = new LoginGUI();
    private static int GUILoginOrder; // Uses to change state of the GUI
    private static int GUIOption = 0; // Uses to receive button input as a integer
    private static String GUIStringOption = ""; // GUIOption placeholder for checking nim as String
    // Used to call different Alert
    private static int GUIAlertState = 0;

    private static TableViewGUI tableView = new TableViewGUI();

    public static void main(String[] args) {
        menu();
    }
    public static void menu() {
        GUILoginOrder = 1;

        Platform.startup(() -> {
            try {
                login.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void inputNim() throws IOException {
        GUILoginOrder = 2;

        //startingGUI();
        if(GUIStringOption.equals("99")) {
            GUILoginOrder = 1;
        }

        Student student = new Student("", "", "", "");
        student.displayInfo();
    }

    public static Object[] checkNim() {
        /* ===========================
        0 = Nim is Registered
        1 = Nim isn't Registered
        2 = Nim lenght is invalid
        =========================== */
        boolean sensor = false;

        Object[] object = new Object[1];
        object[0] = -1;

        if( !(nimStudentList.isEmpty()) ) {
            //object[0] = 0; // 0 = Nim isn't registered yet (past)
            for (String string : nimStudentList) {
                if (string.equals(GUIStringOption)) {
                    object[0] = 0;
                    sensor = true;
                    break;
                }
                else if (string.length() != 15) {
                    object[0] = 2;
                    sensor = true;
                    break;
                }
            }
            if(sensor == false) {
                for(String string : nimStudentList) {
                    if(string.length() == 15) {
                        object[0] = 1;
                        break;
                    }
                }
            }
        }

        return object;
    }

    public static void startingGUI() throws IOException {
        Platform.runLater(() -> {
            try {
                login.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void tableViewStartingGUI() throws IOException {
        Platform.runLater(() -> {
            tableView.start(new Stage());
        });
    }

    public static void addTempStudent() {
        Admin admin = new Admin();
        admin.displayStudents();
    }

    public static void addTempBooks() {
        Admin admin = new Admin();
        admin.displayBooks();
    }

    public ArrayList<String> getNimStudentList() {
        return nimStudentList;
    }
    public void setNimStudentList(String nimStudent) {
        nimStudentList.add(nimStudent);
    }
    public void setNimStudentListHolder(String currentNim) {
        nimStudentListHolder = currentNim;
    }
    public String getNimStudentListHolder() {
        return nimStudentListHolder;
    }
    public static void setLoginGUIOrder(int GUIOrder) { // GUI
        LibrarySystem.GUILoginOrder = GUIOrder;
    }
    public int getGUILoginOrder() { // GUI
        return GUILoginOrder;
    }
    public static void setGUIOption(int GUIOption) { // GUI
        LibrarySystem.GUIOption = GUIOption;
    }
    public int getGUIOption() { // GUI
        return GUIOption;
    }
    public static void setGUIStringOption(String NIM) { // GUI
        GUIStringOption = NIM;
    }
    public String getGUIStringOption() { // GUI
        return GUIStringOption;
    }
    public static void setGUIAlertState(int GUIAlertState) { // Alert
        LibrarySystem.GUIAlertState = GUIAlertState;
    }
    public int getGUIAlertState() { // Alert
        return GUIAlertState;
    }
}
