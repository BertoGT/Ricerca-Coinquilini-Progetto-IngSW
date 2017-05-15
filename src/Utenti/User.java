/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

import ProfiloUtente.DatiUtente;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Marco La Salvia
 */
public class User extends WebSurfer{
    private int power;
    private DatiUtente dati;
    private boolean loggedIn;
    
    public User(int numeroUtente, DatiUtente dati){
        super(numeroUtente);
        this.loggedIn = false;
        this.power = 1;
        this.dati = dati;
    }

    public int getPower() {
        return power;
    }

    public String geteMail() {
        return dati.geteMail();
    }

    public String getPassword() {
        return dati.getPassword();
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    
    
}
