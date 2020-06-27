package dev.customExceptions;

public class ItemDisplayAlreadyExistsException extends Exception{
    public ItemDisplayAlreadyExistsException() {
    }

    public ItemDisplayAlreadyExistsException(String message) {
        super(message);
    }
}
