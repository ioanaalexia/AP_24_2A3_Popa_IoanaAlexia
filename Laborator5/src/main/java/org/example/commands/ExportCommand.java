package org.example.commands;

import org.example.service.RepositoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.example.exceptions.InvalidCommandException;

public class ExportCommand implements Command{

    private RepositoryService repositoryService;

    public ExportCommand(RepositoryService repositoryService){

        this.repositoryService=repositoryService;
    }

    @Override
    public void execute(String[] args) throws InvalidCommandException {

        String filePath = "repositoryExport.json";
        if (args.length > 0) {
            filePath = args[0];
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(repositoryService.getDataForReport());
            Files.write(Paths.get(filePath), json.getBytes());
            System.out.println("Data successfully exported to " + filePath);
        } catch (IOException e) {
            throw new InvalidCommandException("Failed to export data: " + e.getMessage());
        }
    }
}
