/*
 * Progetto Coinquilini
 * AA 2016/2017 * 
 */
package Exceptions;

/**
 *
 * @author alberto
 */
public class CameraNonInseritaException extends Exception {

    /**
     * Creates a new instance of <code>CameraNonInseritaException</code> without
     * detail message.
     */
    public CameraNonInseritaException() {
    }

    /**
     * Constructs an instance of <code>CameraNonInseritaException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CameraNonInseritaException(String msg) {
        super(msg);
    }
}
