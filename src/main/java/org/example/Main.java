package org.example;

import org.example.controller.StudentController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentController studentController = new StudentController();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Get Student by ID");
            System.out.println("3. Get All Students");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    studentController.addStudents();
                    break;
                case 2:
                    studentController.getStudentById();
                    break;
                case 3:
                    studentController.getAllStudents();
                    break;
                case 4:
                    studentController.updateStudent();
                    break;
                case 5:
                    studentController.deleteStudent();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}