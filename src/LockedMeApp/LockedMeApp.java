package LockedMeApp;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LockedMeApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayWelcomeScreen();
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
                case 5:
                    System.out.println("Returning to the main context.");
                    break;
                case 6:
                    System.out.println("Closing the application.");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option (1-6).");
            }
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

    private static void searchUserSpecifiedFile(Scanner scanner) {
        System.out.print("Enter the file name to search: ");
        String fileNameToSearch = scanner.nextLine();

        String directoryPath = "src/files_dummy";

        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] existingFilesArray = directory.listFiles();

            if (existingFilesArray != null) {
                List<File> existingFiles = new ArrayList<>();
                Collections.addAll(existingFiles, existingFilesArray);

                // Realiza una búsqueda sin distinguir mayúsculas y minúsculas en la lista de archivos
                File foundFile = null;
                for (File file : existingFiles) {
                    if (file.getName().equalsIgnoreCase(fileNameToSearch)) {
                        foundFile = file;
                        break;
                    }
                }

                if (foundFile != null) {
                    System.out.println("File found: " + foundFile.getName());
                } else {
                    System.out.println("File not found: " + fileNameToSearch);
                }
            } else {
                System.out.println("No files found in the directory.");
            }
        } else {
            System.out.println("Invalid directory path.");
        }
    }

    // Method to retrieve and display file names in ascending order
    private static void retrieveFileNamesInAscendingOrder() {
        System.out.println();

        String directoryPath = "src/files_dummy";

        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] existingFilesArray = directory.listFiles();

            if (existingFilesArray != null && existingFilesArray.length > 0) {
                // Use a PriorityQueue with a custom comparator to sort file names
                PriorityQueue<String> fileNames = new PriorityQueue<>(new IgnoreCaseComparator());
                for (File file : existingFilesArray) {
                    fileNames.add(file.getName());
                }

                System.out.println("File Names in Ascending Order:");
                while (!fileNames.isEmpty()) {
                    System.out.println(fileNames.poll());
                }
            } else {
                System.out.println("The directory is empty.");
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
            List<File> existingFiles = new ArrayList<>();
            File[] existingFilesArray = directory.listFiles();

            if (existingFilesArray != null) {
                Collections.addAll(existingFiles, existingFilesArray);

                // Verificar si el archivo ya existe (ignorando mayúsculas/minúsculas)
                boolean fileExists = false;
                for (File file : existingFiles) {
                    if (file.getName().equalsIgnoreCase(fileName)) {
                        fileExists = true;
                        break;
                    }
                }

                if (!fileExists) {
                    File newFile = new File(directory, fileName);

                    try {
                        if (newFile.createNewFile()) {
                            System.out.println("File added successfully.");
                        } else {
                            System.out.println("File already exists with that name.");
                        }
                    } catch (IOException e) {
                        System.out.println("An error occurred while adding the file.");
                    }
                } else {
                    System.out.println("File already exists with a similar name.");
                }
            } else {
                System.out.println("No files found in the directory.");
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

            ArrayList<File> matchingFiles = new ArrayList<>();

            for (File file : existingFiles) {
                if (file.getName().equalsIgnoreCase(fileNameToDelete)) {
                    matchingFiles.add(file);
                }
            }

            if (!matchingFiles.isEmpty()) {
                for (File fileToDelete : matchingFiles) {
                    if (fileToDelete.delete()) {
                        System.out.println("File deleted successfully: " + fileToDelete.getName());
                    } else {
                        System.out.println("Unable to delete the file: " + fileToDelete.getName());
                    }
                }
            } else {
                System.out.println("File not found.");
            }
        } else {
            System.out.println("Invalid directory path.");
        }
    }

}


