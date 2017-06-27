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
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Marco La Salvia
 */
public class User implements Power{
    
    private int power;
    private ProfileManager profileManager;
    private Calendar dataOraAccesso;

    public User(){
        this.power = 1;
        this.dataOraAccesso = null;
    }

    @Override
    public int getPower() {
        return power;
    }  

    @Override
    public String toString() {
        return "\nACCESSO: " +this.dataOraAccesso.toString() + "\nUSER \nPOWER: " + this.power;
    }

    public Calendar getDataOraAccesso() {
        return dataOraAccesso;
    }

    public void setDataOraAccesso(Calendar dataOraAccesso) {
        this.dataOraAccesso = dataOraAccesso;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }  

    public void setProfileManager(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }
    
}
