package org.example.service;

import org.example.repository.Repository;
import org.example.model.Person;
import org.example.model.Document;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.module.Configuration;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositoryService {

    private Repository repository;

    public RepositoryService(Repository repository) {

        this.repository = repository;
    }

    public List<Map<String, Object>> getDataForReport() {
        List<Map<String, Object>> reportData = new ArrayList<>();
        Map<Person, List<Document>> documents = repository.getDocuments();

        documents.forEach((person, docs) -> {
            Map<String, Object> personData = new HashMap<>();
            personData.put("personName", person.name());
            personData.put("personId", person.id());
            personData.put("documents", docs);
            reportData.add(personData);
        });

        return reportData;
    }

}
