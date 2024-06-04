package org.example;

import java.util.Scanner;

public class Student {
    String name = "Name: ";
    String faculty = "Faculty: ";
    static String programStudi = "";
    static int ke, j;
    static int[] borrowedBookIndex = new int[Main.bookList.length];
    public static void displayBooks() {
        Scanner scanner = new Scanner(System.in);
        String displayChoice = "";
        int[] stocks = new int[Main.bookList.length];

        do {
            if (programStudi.equals("1")) {
                System.out.println("Berikut List Buku yang dipinjam :");
                System.out.println("0======================O===================================================0======================0");
                System.out.println("|| No. ||        NIM        ||     Id Buku    || Nama buku || Author || Category ||");
                System.out.println("0======================O===================================================0======================0");
                //System.out.println("||  1. ||                                   || 388c-e681-9152 || title     || author || Sejarah  || ");

                for (int i = 0; i < ke; i++) {
                    System.out.println("||  " + (i + 1) + ". || " + Main.inputNIM + "  || " + Main.bookList[borrowedBookIndex[i]][0] + " || " + Main.bookList[borrowedBookIndex[i]][1] + "     || " + Main.bookList[borrowedBookIndex[i]][2] + " || " + Main.bookList[borrowedBookIndex[i]][3] + "  || ");
                }

                System.out.println("0======================O===================================================0======================0");
                Main.menuStudent();
                return;
            }
            else if (programStudi.equals("2")) {
                System.out.println("0======================O=======================0======================0");
                System.out.println("|| No. ||     Id Buku    || Nama buku || Author || Category || Stock ||");
                System.out.println("0======================O=======================0======================0");

                // Display available books
                for (int i = 0; i < Main.bookList.length; i++) {
                    System.out.println("||  " + (i + 1) + ". || " + Main.bookList[i][0] + " || " + Main.bookList[i][1] + "     || " + Main.bookList[i][2] + " || " + Main.bookList[i][3] + "  || " + Main.bookList[i][4] + "     ||");
                    stocks[i] = Integer.parseInt(Main.bookList[i][4]); // Parse the stocks to int
                }
                System.out.println("0======================O=======================0======================0");
                System.out.println("Input Id buku yang ingin dipinjam (input 99 untuk back) ");
                System.out.print("Input : ");
                displayChoice = scanner.nextLine();

                if (displayChoice.equals("99")) {
                    System.out.println("Kembali ke menu awal ...");
                    Main.menuStudent();
                }
                else {
                    boolean isFound = false;
                    for (int i = 0; i < Main.bookList.length; i++) {
                        if (displayChoice.equals(Main.bookList[i][0])) {
                            borrowedBookIndex[j] = i;
                            j++;
                            ke++;
                            isFound = true;
                            break;
                        }
                    }
                    if (isFound == false) {
                        System.out.println("Tidak tersedia buku dengan ID tersebut\n");
                        Main.menuStudent();
                        return;
                    }
                    for (int i = 0; i < Main.bookList.length; i++) {
                        if (Main.bookList[i][0].equals(displayChoice) && stocks[i] > 0) {
                            stocks[i] = stocks[i] - 1; // update the stocks (stocks - 1) [remotely]
                            Main.bookList[i][4] = String.valueOf(stocks[i]); // update the stocks in Main.bookList
                            System.out.println("Buku telah dipinjam ...");
                            break;
                        }
                        else if (Main.bookList[i][0].equals(displayChoice) && stocks[i] <= 0) {
                            System.out.println("Stock buku telah habis ! Kembali ke menu awal ...");
                            break;
                        }
                    }
                    Main.menuStudent();
                }

            }
        } while (!displayChoice.equals("99"));
    }
    public void logout() {
        System.out.println("Logging out from student account ...\n");

        Main.menu();
    }
}
