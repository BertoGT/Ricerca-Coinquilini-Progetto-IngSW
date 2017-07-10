/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

import ProfiloUtente.DatiUtente;
import Sistema.ProfileManager;
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

    /**
     * 
     * @return RITORNA L'ORA DI ACCESSO DELL'USER 
     */
    public Calendar getDataOraAccesso() {
        return dataOraAccesso;
    }

    /**
     * 
     * @param dataOraAccesso SETTA L'ORA DI ACCESSO DELL'USER
     */
    public void setDataOraAccesso(Calendar dataOraAccesso) {
        this.dataOraAccesso = dataOraAccesso;
    }

    /**
     * 
     * @return RITORNA IL PROFILE MANAGER DELL'UTENTE
     */
    public ProfileManager getProfileManager() {
        return profileManager;
    }  

    /**
     * 
     * @param profileManager SETTA IL PROFILE MANAGER DELL'UTENTE 
     */
    public void setProfileManager(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }
    
}
