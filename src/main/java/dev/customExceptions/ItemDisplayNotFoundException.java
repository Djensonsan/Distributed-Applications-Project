package dev.customExceptions;

public class ItemDisplayNotFoundException extends Exception {
    public ItemDisplayNotFoundException() {
    }

    public ItemDisplayNotFoundException(String message) {
        super(message);
    }
}
