package LockedMeApp;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class for user input

public class LockedMeApp {

    public static void main(String[] args) {
        displayWelcomeScreen();

        Scanner scanner = new Scanner(System.in);

        int userChoice = getUserChoice(scanner);

        switch (userChoice) {
            case 1:
                retrieveFileNamesInAscendingOrder();
                break;
            // Other cases will be added here
            default:
                System.out.println("Invalid choice. Please select a valid option (1-6).");
        }

        scanner.close();
    }

    // Method to display the welcome screen and menu options
    private static void displayWelcomeScreen() {
        System.out.println("**********************************************");
        System.out.println("*          Welcome to LockedMe.com           *");
        System.out.println("*                Developed by                *");
        System.out.println("*           Jaime González Casero            *");
        System.out.println("**********************************************");
        System.out.println();
        System.out.println("User Options:");
        System.out.println("1. Retrieve file names in ascending order");
        System.out.println("2. Add a user-specified file");
        System.out.println("3. Delete a user-specified file");
        System.out.println("4. Search for a user-specified file");
        System.out.println("5. Navigate back to the main context");
        System.out.println("6. Close the application");
        System.out.println();
        System.out.print("Please enter your choice (1-6): ");
    }

    // Method to retrieve and display file names in ascending order
    private static void retrieveFileNamesInAscendingOrder() {
        System.out.println();

        String directoryPath = "src/files_dummy";

        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {

            String[] fileNames = directory.list();
            Arrays.sort(fileNames);
            System.out.println("File Names in Ascending Order:");

            for (String fileName : fileNames) {
                System.out.println(fileName);
            }

        } else {
            System.out.println("Invalid directory path.");
        }
    }

    // Method to get the user's choice with error handling
    private static int getUserChoice(Scanner scanner) {
        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                choice = Integer.parseInt(scanner.nextLine());

                if (choice >= 1 && choice <= 6) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please select a valid option (1-6): ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number (1-6): ");
            }
        }

        return choice;
    }
}


