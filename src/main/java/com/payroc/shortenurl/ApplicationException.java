package com.payroc.shortenurl;

public class ApplicationException extends Exception {

    String message;

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApplicationException{" +
                "message='" + message + '\'' +
                '}';
    }

}
