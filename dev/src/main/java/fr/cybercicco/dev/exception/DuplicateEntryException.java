package fr.cybercicco.dev.exception;

public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException(String s) {
        super(s);
    }
}
