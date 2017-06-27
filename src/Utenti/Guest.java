/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

import java.util.Calendar;

/**
 *
 * @author Marco La Salvia
 */
public class Guest implements Power{
    private int power;
    private Calendar dataOraAccesso;
    
    public Guest() {
        this.power = 0;
        this.dataOraAccesso = null;
    }

    public Calendar getDataOraAccesso() {
        return dataOraAccesso;
    }

    public void setDataOraAccesso(Calendar dataOraAccesso) {
        this.dataOraAccesso = dataOraAccesso;
    }

    @Override
    public String toString() {
        return "\nACCESSO: " +this.dataOraAccesso.toString() + "\nGUEST \nPOWER: " + this.power;
    }

    @Override
    public int getPower() {
        return this.power;
    }
    
}
