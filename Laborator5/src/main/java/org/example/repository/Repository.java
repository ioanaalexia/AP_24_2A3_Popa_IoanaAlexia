package org.example.repository;

import org.example.model.Document;
import org.example.model.Person;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Repository {

    private final String repositoryDirectory;
    private Map<Person, List<Document>> documents = new HashMap<>();

    public Map<Person, List<Document>> getDocuments() {
        return new HashMap<>(documents);
    }

    public Repository(String directory) throws IOException {
        this.repositoryDirectory = directory;
        loadDocuments();
    }

    private void loadDocuments() throws IOException {
        try (Stream<Path> walk = Files.walk(Paths.get(repositoryDirectory))) {
            walk.filter(Files::isDirectory)
                    .filter(path -> !path.getFileName().toString().equals(repositoryDirectory))
                    .forEach(path -> {
                        String fileName = path.getFileName().toString();
                        String[] parts = fileName.split("_");

                        if (parts.length >= 2) {
                            String name = parts[0];
                            String id = parts[1];
                            Person person = new Person(name, id);
                            List<Document> personDocuments = new ArrayList<>();
                            try (Stream<Path> files = Files.list(path)) {
                                files.forEach(filePath -> {
                                    String documentName = filePath.getFileName().toString();
                                    String documentPath = filePath.toString();
                                    personDocuments.add(new Document(documentName, documentPath));
                                });
                            } catch (IOException e) {
                                System.out.println("exception when reading filepath");
                                e.printStackTrace();
                            }
                            documents.put(person, personDocuments);
                        } else {

                            System.err.println("Invalid filename format: " + fileName);
                        }
                    });
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}