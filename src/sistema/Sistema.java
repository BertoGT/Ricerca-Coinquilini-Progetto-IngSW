/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import BusinessModel.BusinessModelAnnuncio;
import BusinessModel.BusinessModelUtente;
import Casa.Citta;
import Exceptions.LoginException;
import Exceptions.NessunAnnuncioException;
import Exceptions.RegistrazioneException;
import ProfiloUtente.*;
import RicercaAnnuncio.ContenitoreParametriAnnuncio;
import RicercaAnnuncio.RicercaAnnuncio;
import RicercaCoinquilino.ContenitoreParametriCoinquilino;
import RicercaCoinquilino.RicercaCoinquilino;
import Utenti.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco La Salvia
 */
public class Sistema {
    private Guest guest;
    private User user;
    private ContenitoreParametriAnnuncio parametriAnnuncio;
    private RicercaAnnuncio ricercaAnnuncio;
    private ContenitoreParametriCoinquilino parametriCoinquilino;
    private RicercaCoinquilino ricercaCoinquilino;
    private BusinessModelUtente bmUtente;
    private BusinessModelAnnuncio bmAnnuncio;
     private Calendar dataOraAccesso;

    public Sistema() throws SQLException {
        this.dataOraAccesso = Calendar.getInstance();
        this.bmUtente = new BusinessModelUtente();
        this.bmAnnuncio = new BusinessModelAnnuncio();
        this.switchToGuest();
        this.parametriAnnuncio = null;
        this.parametriCoinquilino = null;
        this.ricercaAnnuncio = null;
        this.ricercaCoinquilino = null;
    }
    public String logIn(String eMail, String password) throws SQLException, LoginException{
        int idUtente = this.bmUtente.login(eMail, password);
        this.switchToUser();
        Utente temp = new Utente(idUtente, this.bmUtente.getDatiUtente(idUtente));
        try {
           this.user.setProfileManager(new ProfileManager(temp, this.bmUtente.getAnnuncioUtente(idUtente)));
        } catch (NessunAnnuncioException ex) {
           this.user.setProfileManager(new ProfileManager(temp, null));
        }       
        return "LOGIN EFFETTUATO CON SUCCESSO\n";
    }
    public void registrazioneUtente(String nome, String cognome, Sesso sesso,String eMail, String password,  int giorno, int mese, int anno, String cellulare, Nazione nazionalita, 
            
            Occupazione occupazione, Facolta facolta, boolean fumatore, boolean cuoco, boolean sportivo, Citta cittaDiRicerca, boolean potenzialeCoinquilino) throws RegistrazioneException, SQLException, ParseException{
        
        int idUtente = this.bmUtente.registrazione(eMail, password, potenzialeCoinquilino);
        this.bmUtente.inserisciAnagraficaUtente(idUtente, this.bmUtente.getDatiUtente(idUtente));
        this.bmUtente.inserisciInfoUtente(idUtente, this.bmUtente.getDatiUtente(idUtente));
        
    }
    private void switchToUser(){
        this.setNullAll();
        this.user = new User();
        this.user.setDataOraAccesso(dataOraAccesso);
    }
    private void setNullAll(){
        this.guest = null;
        this.user = null;
    }
    private void switchToGuest(){
        this.setNullAll();
        this.guest = new Guest();
        this.guest.setDataOraAccesso(dataOraAccesso);
    }
    

    
}
