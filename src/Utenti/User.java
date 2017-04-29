/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Marco La Salvia
 */
public class User extends WebSurfer{
    private int power;
    private String eMail, password;
    private boolean loggedIn;
    
    public User(int numeroUtente, String eMail, String password){
        super(numeroUtente);
        this.eMail = eMail;
        this.password = password;
        this.loggedIn = false;
        this.power = 1;
    }

    public int getPower() {
        return power;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
}
