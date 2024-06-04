package com.main;

import data.Admin;
import data.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {
    private static Admin admin = new Admin();
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> nimStudentList = new ArrayList<>();
    private static String nimStudentListHolder;

    public static void main(String[] args) {
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
            nimStudentListHolder = scanner.next();
            if(nimStudentListHolder.equals("99")) {
                menu();
            }

            if(checkNim()[0].equals(0)) {
                System.out.println("NIM anda belum terdaftar!\n");
            }
            else if(checkNim()[0].equals(1)) {
                System.out.println("Anda telah login ...\n");
            }
            else if(checkNim()[0].equals(2)) {
                System.out.println("NIM anda tidak valid! Harus 15 karakter.\n");
            }
        } while( !(checkNim()[0].equals(1)) );

        Student student = new Student("", "", "", "");
        student.displayInfo();
    }

    public static Object[] checkNim() {
        Object[] object = new Object[1];
        object[0] = 0; // 0 = Nim isn't registered yet

        for (String string : nimStudentList) { // 1 = NIM is registered
            if (string.equals(nimStudentListHolder)) {
                object[0] = 1;
                return object;
            }
            else if (string.length() != 15) { // 2 = length is invalid
                object[0] = 2;
                return object;
            }
        }

        return object;
    }

    public static void addTempStudent() {
        System.out.println("Daftar Data siswa :");
    }

    public static void addTempBooks() {
        System.out.println("Daftar Buku :");
    }

    public ArrayList<String> getNimStudentList() {
        return nimStudentList;
    }
    public void setNimStudentList(String nimStudent) {
        nimStudentList.add(nimStudent);
    }
    public String getNimStudentListHolder() {
        return nimStudentListHolder;
    }
}
