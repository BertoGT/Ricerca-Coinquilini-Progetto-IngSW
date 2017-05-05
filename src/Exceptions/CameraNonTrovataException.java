/*
 * Progetto Coinquilini
 * AA 2016/2017 * 
 */
package Exceptions;

/**
 *
 * @author alberto
 */
public class CameraNonTrovataException extends Exception {

    /**
     * Creates a new instance of <code>CameraNonTrovataException</code> without
     * detail message.
     */
    public CameraNonTrovataException() {
    }

    /**
     * Constructs an instance of <code>CameraNonTrovataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CameraNonTrovataException(String msg) {
        super(msg);
    }
}
