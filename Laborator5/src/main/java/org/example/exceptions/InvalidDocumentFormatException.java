package org.example.exceptions;

public class InvalidDocumentFormatException extends Exception{
    public InvalidDocumentFormatException(Exception ex) {
        super("Invalid repository.", ex);

    }
}
