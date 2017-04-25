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
public class WebSurfer {
    private String id;
    private Calendar dataOraAccesso;

    public WebSurfer(int numeroUtente) {
        this.id = "ID_" + numeroUtente;
        this.dataOraAccesso = Calendar.getInstance();
        
    }

    public String getId() {
        return id;
    }

    public Calendar getDataOraAccesso() {
        return dataOraAccesso;
    }

    @Override
    public String toString() {
        return "WebSurfer{" + "id=" + id + ", dataOraAccesso=" + dataOraAccesso + '}';
    }
      
    
    
    
}
