package com.springinaction.spitter.exceptions;

/**
 * The only checked exception to be thrown from DAO.
 */
public class DAOException extends Exception {
    public DAOException(Throwable cause) {
        super(cause);
    }
}
