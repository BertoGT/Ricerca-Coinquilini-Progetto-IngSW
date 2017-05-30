/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import BusinessModel.BusinessModelAnnuncio;
import BusinessModel.BusinessModelUtente;
import Casa.Citta;
import Casa.HouseGenerality;
import Exceptions.LoginException;
import Exceptions.NessunAnnuncioException;
import Exceptions.RegistrazioneException;
import ProfiloUtente.*;
import RicercaAnnuncio.AnnuncioRisultante;
import RicercaAnnuncio.ContenitoreParametriAnnuncio;
import RicercaAnnuncio.Cucina;
import RicercaAnnuncio.DistanzaCentro;
import RicercaAnnuncio.NLocali;
import RicercaAnnuncio.NumeroBagni;
import RicercaAnnuncio.ParametroRicercaAnnuncio;
import RicercaAnnuncio.RicercaAnnuncio;
import RicercaAnnuncio.SessoCasa;
import RicercaAnnuncio.TipoCamera;
import RicercaCoinquilino.CoinquilinoRisultante;
import RicercaCoinquilino.ContenitoreParametriCoinquilino;
import RicercaCoinquilino.Cuoco;
import RicercaCoinquilino.Eta;
import RicercaCoinquilino.Fumatore;
import RicercaCoinquilino.Nazionalita;
import RicercaCoinquilino.RicercaCoinquilino;
import RicercaCoinquilino.Sportivo;
import Utenti.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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
    private ArrayList<AnnuncioRisultante> annunciRisultanti;
    private ArrayList<CoinquilinoRisultante> coinquiliniRisultanti;

    public Sistema() throws SQLException {
        this.dataOraAccesso = Calendar.getInstance();
        this.bmUtente = new BusinessModelUtente();
        this.bmAnnuncio = new BusinessModelAnnuncio();
        this.switchToGuest();
        this.parametriAnnuncio = null;
        this.parametriCoinquilino = null;
        this.ricercaAnnuncio = null;
        this.ricercaCoinquilino = null;
        this.annunciRisultanti = null;
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
    
    public void setCostoMax(int costo) {
        this.parametriAnnuncio.setCostoMax(costo);
    }
    public void iniziaRicercaAnnunci(Citta citta){
        this.parametriAnnuncio = new ContenitoreParametriAnnuncio(citta);
    }
    public void iniziaRicercaCoinquilini(Citta citta){
        this.parametriCoinquilino = new ContenitoreParametriCoinquilino(citta);
    }
    public void setParametroCucina(int stelle, boolean cucinaSeparata) {
        this.parametriAnnuncio.setParametroCucina(stelle, cucinaSeparata);
    }
    
    public void setParametroDistCentro(int stelle, int distanzaMax) {
        this.parametriAnnuncio.setParametroDistCentro(stelle, distanzaMax);
    }
    
    public void setParametroNLocali(int stelle, int nLocali) {
        this.parametriAnnuncio.setParametroNLocali(stelle, nLocali);
    }
    
    public void setParametroNBagni(int stelle, int nBagni) {
        this.parametriAnnuncio.setParametroNBagni(stelle, nBagni);
    }
    
    public void setParametroSessoCasa(int stelle, HouseGenerality sesso) {
        this.parametriAnnuncio.setParametroSessoCasa(stelle, sesso);
    }
    
    public void setParametroTipoCamera(int stelle, int postiLetto) {
        this.parametriAnnuncio.setParametroTipoCamera(stelle, postiLetto);
    }
    
    public Guest getGuest() {
        return guest;
    }

    public User getUser() {
        return user;
    }

    public ContenitoreParametriAnnuncio getParametriAnnuncio() {
        return parametriAnnuncio;
    }

    public RicercaAnnuncio getRicercaAnnuncio() {
        return ricercaAnnuncio;
    }

    public ContenitoreParametriCoinquilino getParametriCoinquilino() {
        return parametriCoinquilino;
    }

    public RicercaCoinquilino getRicercaCoinquilino() {
        return ricercaCoinquilino;
    }

    public Calendar getDataOraAccesso() {
        return dataOraAccesso;
    }
    
    public void ricercaAnnuncio() throws SQLException, NessunAnnuncioException{
        this.ricercaAnnuncio = new RicercaAnnuncio(this.parametriAnnuncio);
        this.annunciRisultanti = this.ricercaAnnuncio.eseguiRicerca();
    }
    public void setParametroCuoco(int stelle, Boolean isCuoco){
        this.parametriCoinquilino.setParametroCuoco(stelle, isCuoco);
    }
    
    public void setParametroEta(int stelle,int etaMin,int etaMax){
        this.parametriCoinquilino.setParametroEta(stelle, etaMin, etaMax);
    }
    
    public void setParametroFacolta(int stelle,ProfiloUtente.Facolta facolta){
        this.parametriCoinquilino.setParametroFacolta(stelle, facolta);
    }
    
    public void setParametroFumatore(int stelle,boolean isFumatore){
        this.parametriCoinquilino.setParametroFumatore(stelle, isFumatore);
    }
    
    public void setParametroNazionalita(int stelle,ProfiloUtente.Nazione nazionalita){
        this.parametriCoinquilino.setParametroNazionalita(stelle, nazionalita);
    }
    
    public void setParametroOccupazione(int stelle,ProfiloUtente.Occupazione occupazione){
        this.parametriCoinquilino.setParametroOccupazione(stelle, occupazione);
    } 
    
    public void setParametroSportivo(int stelle,boolean sportivo){
        this.parametriCoinquilino.setParametroSportivo(stelle, sportivo);
    }
    public void setSesso(Sesso sesso) {
        this.parametriCoinquilino.setSesso(sesso);
    }
    public void ricercaCoinquilino() throws SQLException, NessunAnnuncioException {
        this.ricercaCoinquilino = new RicercaCoinquilino(this.parametriCoinquilino);
        this.coinquiliniRisultanti = this.ricercaCoinquilino.eseguiRicerca();
    }
}
