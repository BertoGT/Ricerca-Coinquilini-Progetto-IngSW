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
public class RegistrazioneException extends Exception {

    /**
     * Creates a new instance of <code>RegistrazioneException</code> without
     * detail message.
     */
    public RegistrazioneException() {
    }

    /**
     * Constructs an instance of <code>RegistrazioneException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public RegistrazioneException(String msg) {
        super(msg);
    }
}
