package org.example.repository;

import org.example.exceptions.InvalidDocumentFormatException;

import java.io.File;

public class Repository {
    private final String repositoryDirectory;

    public Repository(String repositoryDirectory) throws InvalidDocumentFormatException {
        if (repositoryDirectory == null || repositoryDirectory.isEmpty()) {
            throw new InvalidDocumentFormatException(new IllegalArgumentException("Repository directory is null or empty."));
        }
        this.repositoryDirectory = repositoryDirectory;
    }


    public void displayRepository() {
        try {
            File directory = new File(repositoryDirectory);
            if (!directory.exists() || !directory.isDirectory()) {
                throw new InvalidDocumentFormatException(new IllegalArgumentException("Repository directory not found or is not a directory."));
            }

            File[] personDirs = directory.listFiles(File::isDirectory);
            if (personDirs != null) {
                for (File personDir : personDirs) {
                    System.out.println("Person: " + personDir.getName());
                    File[] documents = personDir.listFiles();
                    if (documents != null) {
                        for (File document : documents) {
                            System.out.println("\t- " + document.getName());
                        }
                    }
                }
            }
        } catch (InvalidDocumentFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}