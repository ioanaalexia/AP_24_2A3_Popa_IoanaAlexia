package org.example.service;
import org.example.repository.Repository;
import org.example.model.Person;
import org.example.model.Document;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DocumentService {
    public void openDocument(String documentPath) throws IOException {
        if (!Desktop.isDesktopSupported()) {
            throw new UnsupportedOperationException("Desktop is not supported");
        }

        File file = new File(documentPath);
        if (file.exists()) {
            Desktop.getDesktop().open(file);
        } else {
            throw new IOException("File does not exist: " + documentPath);
        }
    }
}
