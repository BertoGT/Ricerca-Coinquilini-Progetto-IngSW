/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

/**
 *
 * @author Marco La Salvia
 */
public class Guest extends WebSurfer {
    private int power;

    public Guest(int numeroUtente) {
        super(numeroUtente);
        this.power = 0;
    }

    @Override
    public String toString() {
        return super.toString() + " Guest{" + "power=" + power + '}';
    }
    
}
