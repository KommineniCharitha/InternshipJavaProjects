package pkg1;

import java.io.*;
import java.util.Scanner;

public class FileHandler {

    // File to use for reading, writing, and modifying
    private static final String FILE_NAME = "internship_task_file.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== JAVA FILE HANDLING UTILITY ===");

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Write to file");
            System.out.println("2. Read from file");
            System.out.println("3. Modify file content");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice;

            // Check if user input is a valid integer
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // clear newline
            } else {
                System.out.println("Invalid input! Please enter a number between 1 and 4.");
                scanner.nextLine(); // clear invalid input
                continue; // go back to menu
            }

            // Perform operation based on choice
            switch (choice) {
                case 1:
                    writeToFile(scanner);
                    break;
                case 2:
                    readFromFile();
                    break;
                case 3:
                    modifyFileContent(scanner);
                    break;
                case 4:
                    System.out.println("Exiting... All operations completed.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            }
        }
    }

    // Function to write user input to the file
    public static void writeToFile(Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            System.out.print("Enter text to write to the file: ");
            String content = scanner.nextLine();
            writer.write(content);
            writer.newLine(); // Adds a new line after writing
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Function to read and display file content
    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            System.out.println("\n--- File Content ---");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("---------------------");
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Please write to the file first.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    // Function to modify file content based on user input
    public static void modifyFileContent(Scanner scanner) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("File does not exist. Please write something first.");
                return;
            }

            // Read all content from the file
            String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
            System.out.println("\n--- Current File Content ---");
            System.out.println(content);

            // Ask user what to replace
            System.out.print("\nEnter text to replace: ");
            String oldText = scanner.nextLine();

            System.out.print("Enter replacement text: ");
            String newText = scanner.nextLine();

            // Replace content
            content = content.replace(oldText, newText);

            // Write updated content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                writer.write(content);
            }

            System.out.println("Content modified successfully!");

        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }
}
