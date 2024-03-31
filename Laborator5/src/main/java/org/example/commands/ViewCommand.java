package org.example.commands;

import org.example.service.DocumentService;
import org.example.exceptions.InvalidCommandException;

public class ViewCommand implements Command{

    private DocumentService documentService;

    public ViewCommand(DocumentService documentService){
        this.documentService = documentService;
    }

    @Override
    public void execute(String[] args) throws InvalidCommandException {
        if(args.length != 1){
            throw new InvalidCommandException("Document path is required!");
        }

        try {
            String documentPath = args[0];
            documentService.openDocument(documentPath);
        }catch(Exception e){
            throw new InvalidCommandException("Failed to open the document: " + e.getMessage());
        }
    }
}
