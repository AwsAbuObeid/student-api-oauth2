package com.erabia.studentapi.exceptions;

import com.erabia.studentapi.enums.StudentExceptionType;

public class StudentException extends Exception {

    private StudentExceptionType  type;

    public StudentExceptionType getType() {
        return type;
    }
    /**
     * Constructor for creating a StudentException object with a given StudentExceptionType and error message.
     *
     * @param message The error message for the exception.
     * @param type    The type of student exception.
     */
    public StudentException(String message, StudentExceptionType type) {
        super(message);
        this.type=type;
    }

    /**
     * Constructor for creating a StudentException object with a given StudentExceptionType, error message, and a throwable cause.
     *
     * @param message The error message for the exception.
     * @param cause   The throwable cause for the exception.
     * @param type    The type of student exception.
     */
    public StudentException(String message, Throwable cause, StudentExceptionType type) {
        super(message, cause);
        this.type=type;
    }

    /**
     * Constructor for creating a StudentException object with a given StudentExceptionType, error message, and a throwable cause.
     *
     * @param cause The throwable cause for the exception.
     * @param type  The type of student exception.
     */
    public StudentException(Throwable cause, StudentExceptionType type) {
        super(cause);
        this.type=type;
    }

    public StudentException() {super();}

}
