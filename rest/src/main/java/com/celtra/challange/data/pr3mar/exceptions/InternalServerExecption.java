package com.celtra.challange.data.pr3mar.exceptions;

public class InternalServerExecption extends Exception {

    private static final long serialVersionUID = 8800423362209593156L;

    public InternalServerExecption() {
        super();
    }

    /**
     * @param message the message for this exception
     */
    public InternalServerExecption(String message) {
        super(message);
    }

    /**
     * @param cause the root cause
     */
    public InternalServerExecption(Throwable cause) {
        super(cause);
    }

    /**
     * @param message the message for this exception
     * @param cause   the root cause
     */
    public InternalServerExecption(String message, Throwable cause) {
        super(message, cause);
    }
}
