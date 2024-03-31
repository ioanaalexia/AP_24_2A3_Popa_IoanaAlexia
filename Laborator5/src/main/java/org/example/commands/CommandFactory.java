package org.example.commands;

import org.example.exceptions.InvalidCommandException;
import org.example.service.DocumentService;
import org.example.service.RepositoryService;

public class CommandFactory {
    private DocumentService documentService;
    private RepositoryService repositoryService;

    public CommandFactory(DocumentService documentService, RepositoryService repositoryService) {
        this.documentService = documentService;
        this.repositoryService = repositoryService;
    }

    public Command getCommand(String commandName) throws InvalidCommandException {
        switch (commandName.toLowerCase()) {
            case "view":
                return new ViewCommand(documentService);
            case "report":
                return new ReportCommand(repositoryService);
            case "export":
                return new ExportCommand(repositoryService);
            default:
                throw new IllegalArgumentException("Invalid command: " + commandName);
        }
    }
}
