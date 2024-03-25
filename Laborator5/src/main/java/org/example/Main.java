package org.example;

import org.example.exceptions.InvalidDocumentFormatException;
import org.example.repository.Repository;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InvalidDocumentFormatException {
        Repository repo = new Repository("C://Users//popaa//OneDrive//Desktop//java2");

        repo.displayRepository();
    }
}