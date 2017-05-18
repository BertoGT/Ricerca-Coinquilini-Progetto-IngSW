/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

import java.util.Calendar;

public class WebSurfer{
    private Calendar dataOraAccesso;
    private boolean loggedIn;

    public WebSurfer() {
        this.loggedIn = false;
        this.dataOraAccesso = Calendar.getInstance();
    }
    public Calendar getDataOraAccesso() {
        return dataOraAccesso;
    }

    @Override
    public String toString() {
        return "\nACCESSO: " +this.dataOraAccesso.toString();
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
}
