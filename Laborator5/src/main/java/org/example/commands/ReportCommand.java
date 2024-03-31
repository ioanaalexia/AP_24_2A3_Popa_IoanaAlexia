package org.example.commands;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.example.model.Document;
import org.example.service.RepositoryService;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ReportCommand implements Command{

    private RepositoryService repositoryService;

    public ReportCommand(RepositoryService repositoryService){
        this.repositoryService=repositoryService;
    }

    @Override
    public void execute(String[] args) {
        try {
            Properties props = new Properties();
            props.put("file.resource.loader.path", "src/main/resources");
            VelocityEngine ve = new VelocityEngine(props);
            ve.init();

            Template template = ve.getTemplate("repositoryReport.vm");

            VelocityContext context = new VelocityContext();
            context.put("repository", repositoryService.getDataForReport());

            StringWriter writer = new StringWriter();
            template.merge(context, writer);

            String reportFileName = "repositoryReport.html";
            Files.write(Paths.get(reportFileName), writer.toString().getBytes());

            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(Paths.get(reportFileName).toUri());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
