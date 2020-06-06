package net.nyrheim.bricksandmortar.exception;

public class UnregisteredServiceException extends RuntimeException {

    public UnregisteredServiceException(Class<?> serviceClass) {
        super("No service was registered for " + serviceClass.getTypeName());
    }
}
