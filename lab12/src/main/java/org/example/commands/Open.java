package org.example.commands;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Open implements Command {
    @Override
    public void execute(String... params) throws IOException {
        for (String param : params) {
            Desktop.getDesktop().open(new File(param));
        }
    }
}
