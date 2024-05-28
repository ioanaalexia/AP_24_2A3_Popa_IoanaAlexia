package org.example.commands;

import java.io.IOException;

public interface Command {
    void execute(String ... params) throws IOException;
}