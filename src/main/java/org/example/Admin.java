package org.example;

import java.util.Scanner;

public class Admin {
    String adminUsername = "admin";
    String adminPassword = "admin";
    static String studentList = "\nList of Registered Students: ";
    static int parse1 = 0;

    static void addStudents() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nMasukkan data mahasiswa:");
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        String nim;
        do {
            System.out.print("NIM (panjang harus 15 angka): ");
            nim = scanner.nextLine();
        } while (nim.length() != 15);
        System.out.print("Fakultas : ");
        String fakultas = scanner.nextLine();
        System.out.print("Jurusan: ");
        String jurusan = scanner.nextLine();

        Main.userStudent[parse1][0] = nama;
        Main.userStudent[parse1][1] = nim;
        Main.userStudent[parse1][2] = fakultas;
        Main.userStudent[parse1][3] = jurusan;

        parse1++;
        Main.menuAdmin();
    }
    static void inputBook() {

    }
    static void displayBook() {

    }
    static void displayStudents() {
        Student student = new Student();
        System.out.println(studentList);

        for (int i = 0; i <= parse1; i++) {
            if (Main.userStudent[i][0] == null) {
                break;
            }

            System.out.println(student.name + Main.userStudent[i][0]);
            System.out.println("NIM: " + Main.userStudent[i][1]);
            System.out.println(student.faculty + Main.userStudent[i][2]);
            System.out.println("Program Studi: " + Main.userStudent[i][3]);
            System.out.println();

        }
        Main.menuAdmin();
    }

}
