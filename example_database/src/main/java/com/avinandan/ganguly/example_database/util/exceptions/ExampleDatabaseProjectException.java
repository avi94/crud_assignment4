package com.avinandan.ganguly.example_database.util.exceptions;

public class ExampleDatabaseProjectException extends RuntimeException {

    public ExampleDatabaseProjectException(String message) {
        super(message);
    }

    public ExampleDatabaseProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExampleDatabaseProjectException(Throwable cause) {
        super(cause);
    }
}
