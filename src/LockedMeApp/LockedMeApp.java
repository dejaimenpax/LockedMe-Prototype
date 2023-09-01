package LockedMeApp;

import java.io.File;
import java.util.Arrays;

public class LockedMeApp {

    public static void main(String[] args) {

        displayWelcomeScreen();

        int userChoice = 1;

        switch (userChoice) {
            case 1:
                retrieveFileNamesInAscendingOrder();
                break;
            // Other cases will be added here
            default:
                System.out.println("Invalid choice. Please select a valid option (1-6).");
        }
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
        // Specify the directory path (change this to your desired directory)
        String directoryPath = "src/files_dummy";

        // Create a File object for the directory
        File directory = new File(directoryPath);

        // Check if the directory exists and is a directory
        if (directory.exists() && directory.isDirectory()) {
            // List the files in the directory
            String[] fileNames = directory.list();

            // Sort the file names in ascending order
            Arrays.sort(fileNames);

            // Display the sorted file name
            System.out.println("File Names in Ascending Order:");
            for (String fileName : fileNames) {
                System.out.println(fileName);
            }
        } else {
            System.out.println("Invalid directory path.");
        }
    }
}

