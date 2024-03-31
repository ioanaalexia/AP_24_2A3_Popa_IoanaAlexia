package org.example;

import org.example.commands.Command;
import org.example.commands.CommandFactory;
import org.example.exceptions.InvalidCommandException;
import org.example.repository.Repository;
import org.example.service.DocumentService;
import org.example.service.RepositoryService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            String documentDirectory = "C://Users//popaa//OneDrive//Desktop//java_2";
            Repository repository = new Repository(documentDirectory);
            RepositoryService repositoryService = new RepositoryService(repository);
            DocumentService documentService = new DocumentService();

            CommandFactory commandFactory = new CommandFactory(documentService, repositoryService);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Document Management System");
            System.out.println("Type 'exit' to quit.");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }

                String[] parts = input.split("\\s+");
                if (parts.length == 0) {
                    continue; // No command entered
                }

                String commandName = parts[0];
                String[] commandArgs = new String[parts.length - 1];
                System.arraycopy(parts, 1, commandArgs, 0, parts.length - 1);
                try {
                    Command command = commandFactory.getCommand(commandName);
                    command.execute(commandArgs);
                } catch (InvalidCommandException e) {
                    System.out.println("Error: " + e.getMessage());
                    // Additional handling, if necessary, like showing usage help
                } catch (Exception e) {
                    System.out.println("Unexpected error executing command: " + e.getMessage());
                    // Handle other exceptions
                }
            }

        } catch (IOException e) {
            System.out.println("Failed to initialize the document repository: " + e.getMessage());
        }
    }
}
