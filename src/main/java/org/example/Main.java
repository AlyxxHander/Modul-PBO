package org.example;

import java.util.Scanner;

public class Main {
    static String[][] bookList = { // 1.id, 2.title, 3.author, 4.category, 5.stock
            {"388c-e681-9152", "title1", "author1", "Sejarah", "3"},
            {"ed90-be30-5cdb", "title2", "author2", "Sejarah", "3"},
            {"d95e-0c4a-9523", "title3", "author3", "Sejarah", "2"},
    };
    static String[][] userStudent = new String[100][4];
    static String inputNIM;
    static void menu() {
        Scanner scanner;
        int menuChoice;
        do {
            scanner = new Scanner(System.in);
            System.out.println("===== Library System =====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Choose option (1-3) : ");
            menuChoice = scanner.nextInt();
            scanner.nextLine();
            switch (menuChoice) {
                case 1:
                    inputNim();
                    menuStudent();
                    break;
                case 2:
                    boolean checkIn;
                    Admin admin = new Admin();
                    do {
                        checkIn = true;
                        System.out.print("Enter the username : ");
                        String aUsername = scanner.nextLine();
                        System.out.print("Enter the password : ");
                        String aPassword = scanner.nextLine();
                        if(!aUsername.equals(admin.adminUsername) || !aPassword.equals(admin.adminPassword)) {
                            System.out.println("Invalid credentials for admin ...");
                            checkIn = false;
                        }
                    } while(checkIn == false);
                    menuAdmin();
                    break;
                case 3:
                    System.out.println("Thank you, Exiting program ...");
                    return;
                default:
                    System.out.println("Invalid choice ! Please try again ");
                    break;
            }
        } while(menuChoice < 1 || menuChoice > 3);
        scanner.close();
    }
    static void inputNim() {
        Scanner scanner = new Scanner(System.in);
        boolean isLooping;
        do {
            isLooping = false;
            do {
                System.out.print("Enter your NIM (input 99 untuk back) : ");
                inputNIM = scanner.nextLine();
                if (inputNIM.equals("99")) {
                    System.out.println("Kembali ke menu awal ...\n");
                    menu();
                }
            } while (inputNIM.length() != 15);
            checkNim(inputNIM);
            if (!checkNim(inputNIM).equals("Valid")) {
                System.out.println("NIM anda tidak valid !");
                isLooping = true;
            }
        } while(isLooping == true);
    }
    static Object checkNim(String inputNIM) {
        String isValid = "Not Valid";
        for(int i = 0; i <= (userStudent.length/5); i++) {
            if (inputNIM.equals(userStudent[i][1])) {
                isValid = "Valid";
                break;
            }
        }
        return isValid;
    }
    static void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ===== Admin Menu =====");
        System.out.println("1. Add Student");
        System.out.println("2. Display Registered Students");
        System.out.println("3. Logout");
        System.out.print("Choose option (1-3) : ");
        int choice = scanner.nextInt();
        Admin admin = new Admin();

        switch(choice) {
            case 1:
                Admin.addStudents();
                break;
            case 2:
                Admin.displayStudents();
                break;
            case 3:
                System.out.println("Logging out from admin account ...\n");
                menu();
                break;
        }
    }
    static void menuStudent() {
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n====== Student Menu =====");
        System.out.println("1. Buku terpinjam"); // display
        System.out.println("2. Pinjam buku"); // function pinjam
        System.out.println("3. logout");
        System.out.print("Choose Option (1-3) : ");
        int menuChoice = scanner.nextInt();
        switch(menuChoice) {
            case 1:
                Student.programStudi = "1";
                Student.displayBooks();
                break;
            case 2:
                Student.programStudi = "2";
                Student.displayBooks();
                break;
            case 3:
                student.logout();
                break;
        }
    }

    public static void main(String[] args) {
        menu();
    }

}