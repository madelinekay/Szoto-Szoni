package com.Undis.Madeline.SzotoSzoves_CaseStudy.exceptions;

// Located in the appropriate package (e.g., com.example.exceptions)
public class NoRootsFoundException extends Exception {

    private static final long serialVersionUID = 1L; // Serialization version unique ID

    public NoRootsFoundException(String message) {
        super(message); // Constructor with message that can be logged
    }
}