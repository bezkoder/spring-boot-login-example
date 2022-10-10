package br.com.douglas.aterrosystem.exception;

public class DomainException extends Exception {
    public DomainException(String s) {
        super(s);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
