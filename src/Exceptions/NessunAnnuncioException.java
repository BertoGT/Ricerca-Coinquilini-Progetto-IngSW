/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author cl428444
 */
public class NessunAnnuncioException extends Exception {

    /**
     * Creates a new instance of <code>NoAnnuncioPerUtenteException</code>
     * without detail message.
     */
    public NessunAnnuncioException() {
    }

    /**
     * Constructs an instance of <code>NoAnnuncioPerUtenteException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NessunAnnuncioException(String msg) {
        super(msg);
    }
}
