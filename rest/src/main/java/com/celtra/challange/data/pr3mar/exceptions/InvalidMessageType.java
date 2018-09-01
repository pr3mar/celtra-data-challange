package com.celtra.challange.data.pr3mar.exceptions;

public class InvalidMessageType extends Exception {

    private static final long serialVersionUID = -2695779985718787901L;

    public InvalidMessageType() {
        super();
    }

    /**
     * @param message the message for this exception
     */
    public InvalidMessageType(String message) {
        super(message);
    }

    /**
     * @param cause the root cause
     */
    public InvalidMessageType(Throwable cause) {
        super(cause);
    }

    /**
     * @param message the message for this exception
     * @param cause   the root cause
     */
    public InvalidMessageType(String message, Throwable cause) {
        super(message, cause);
    }
}
