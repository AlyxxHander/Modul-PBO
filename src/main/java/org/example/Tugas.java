package org.example;

import java.util.Scanner;

// Making library login system that compromise of user as admin or student

public class Tugas {
    public static void main(String[] args) {
        boolean isLooping = true;
        long[] studentNIM = new long[] {
            202310370311179L, 202310370311180L, 202310370311181L, 202310370311182L, 202310370311183L  
        };

        String username = "alyxx";
        String password = "alyxx";

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("=== Library System ===");
            System.out.print("1. Login as Student\n2. Login as Admin\n3. Exit\nChoose an option(1-3) : ");
            int choice = scanner.nextInt();
            if(!(choice <= 3 && choice >= 0)) {
                System.out.println("Wrong choice !\n");
            }
            scanner.nextLine(); // delete newline character
            switch(choice) {
                case 1:
                    System.out.print("Enter your NIM : ");
                    Long inputedNIM = scanner.nextLong();
                    if(!(inputedNIM.longValue() < 15 || inputedNIM.longValue() > 16)) {
                        System.out.println("User not found !\n");
                        break;
                    }
                    for(int i = 0; i < studentNIM.length; i++) {
                        if(inputedNIM == studentNIM[i]) {
                            System.out.println("Successfully Login as Student\n");
                            break;
                        }
                        else {
                            System.out.println("User Not Found\n");
                            break;
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter your username : ");
                    String inputedUsername = scanner.nextLine();
                    System.out.print("Enter your password : ");
                    String inputedPassword = scanner.nextLine();
                    if(!(inputedUsername.equals(username) || (inputedPassword.equals(password)) )) {
                        System.out.println("Admin User Not Found\n");
                        break;
                    }
                    System.out.println("Successfully Login as Admin\n");
                    break;
                case 3:
                    System.out.println("Closing program ...");
                    isLooping = false;
                    break;
            }
        } while(isLooping == true);
        scanner.close();
    }
}
