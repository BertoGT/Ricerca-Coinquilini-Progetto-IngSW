/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

import java.util.Calendar;

public class WebSurfer implements Power{
    private String id;
    private int power;
    private Calendar dataOraAccesso;

    public WebSurfer(int numeroUtente) {
        this.id = "ID_" + numeroUtente;
        this.power = -1;
        this.dataOraAccesso = Calendar.getInstance();
    }

    public String getId() {
        return id;
    }

    public Calendar getDataOraAccesso() {
        return dataOraAccesso;
    }
    public int getNumeroUtente(){
        return Integer.parseInt(id);
    }
    @Override
    public String toString() {
        return "ACCESSO: " +this.dataOraAccesso.toString() + "\n"+this.id+"\n";
    }
    
    @Override
    public int getPower() {
        return this.power;
    }

    
    
    
}
