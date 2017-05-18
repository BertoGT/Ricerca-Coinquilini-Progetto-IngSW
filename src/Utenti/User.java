/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

import ProfiloUtente.DatiUtente;
import ProfiloUtente.ProfileManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Marco La Salvia
 */
public class User extends WebSurfer implements Power{
    
    private int power;
    private ProfileManager profileManager;

    public User(){
        super();
        this.power = 1;
    }

    @Override
    public int getPower() {
        return power;
    }  

    @Override
    public String toString() {
        return super.toString() + "\nUSER \nPOWER: " + this.power;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }    
}
