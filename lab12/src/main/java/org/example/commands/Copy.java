package org.example.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Copy implements Command {
    @Override
    public void execute(String... params) throws IOException {
        String source = params[0];
        String target = params[1];
        Files.copy(Paths.get(source),Paths.get(target),REPLACE_EXISTING);
    }
}
