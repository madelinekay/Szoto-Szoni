package com.Undis.Madeline.SzotoSzoves_CaseStudy.exceptions;

public class WordRepositoryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WordRepositoryException(String message) {
        super(message);
    }

    public WordRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}