package LockedMeApp;

import java.io.File;
import java.io.IOException;
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
            case 2:
                addUserSpecifiedFile(scanner);
                break;
            case 3:
                deleteUserSpecifiedFile(scanner);
                break;
            case 4:
                searchUserSpecifiedFile(scanner);
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

    private static void addUserSpecifiedFile(Scanner scanner) {
        System.out.print("Enter the file name to add: ");
        String fileName = scanner.nextLine();

        String directoryPath = "src/files_dummy";

        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] existingFiles = directory.listFiles();

            // Check for case-insensitive file name match
            boolean fileExists = Arrays.stream(existingFiles)
                    .anyMatch(file -> file.getName().equalsIgnoreCase(fileName));

            if (!fileExists) {
                File newFile = new File(directory, fileName);

                try {
                    if (newFile.createNewFile()) {
                        System.out.println("File added successfully.");
                    } else {
                        System.out.println("File already exists with that name.");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred while adding the file.");
                }
            } else {
                System.out.println("File already exists with a similar name.");
            }
        } else {
            System.out.println("Invalid directory path.");
        }
    }


    private static void deleteUserSpecifiedFile(Scanner scanner) {
        System.out.print("Enter the file name to delete: ");
        String fileNameToDelete = scanner.nextLine();

        String directoryPath = "src/files_dummy";

        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] existingFiles = directory.listFiles();

            // Check for case-insensitive file name match
            File fileToDelete = Arrays.stream(existingFiles)
                    .filter(file -> file.getName().equalsIgnoreCase(fileNameToDelete))
                    .findFirst()
                    .orElse(null);

            if (fileToDelete != null) {
                if (fileToDelete.delete()) {
                    System.out.println("File deleted successfully.");
                } else {
                    System.out.println("Unable to delete the file.");
                }
            } else {
                System.out.println("File not found.");
            }
        } else {
            System.out.println("Invalid directory path.");
        }
    }

    private static void searchUserSpecifiedFile(Scanner scanner) {
        System.out.print("Enter the file name to search: ");
        String fileNameToSearch = scanner.nextLine();

        String directoryPath = "src/files_dummy";

        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] existingFiles = directory.listFiles();

            // Check for case-insensitive file name match
            File foundFile = Arrays.stream(existingFiles)
                    .filter(file -> file.getName().equalsIgnoreCase(fileNameToSearch))
                    .findFirst()
                    .orElse(null);

            if (foundFile != null) {
                System.out.println("File found: " + foundFile.getName());
            } else {
                System.out.println("File not found: " + fileNameToSearch);
            }
        } else {
            System.out.println("Invalid directory path.");
        }
    }
}


