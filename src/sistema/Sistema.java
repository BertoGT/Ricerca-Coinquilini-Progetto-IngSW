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
import Casa.ElettroDomestico;
import RicercaAnnuncio.RicercaAnnuncio;
import RicercaCoinquilino.CoinquilinoRisultante;
import RicercaCoinquilino.ContenitoreParametriCoinquilino;
import RicercaCoinquilino.RicercaCoinquilino;
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
        this.bmUtente = BusinessModelUtente.getInstance();
        this.bmAnnuncio = BusinessModelAnnuncio.getInstance();
        this.switchToGuest();
        this.parametriAnnuncio = null;
        this.parametriCoinquilino = null;
        this.ricercaAnnuncio = null;
        this.ricercaCoinquilino = null;
        this.annunciRisultanti = null;
    }
    
    /**
     * 
     * @param eMail indica eMail dell'utente
     * @param password indica password dell'utente
     * @return
     * @throws SQLException
     * @throws LoginException 
     */
    
    public int logIn(String eMail, String password) throws SQLException, LoginException{
        int idUtente = this.bmUtente.login(eMail, password);
        this.switchToUser();
        Utente temp = new Utente(idUtente, this.bmUtente.getDatiUtente(idUtente));
        try {
           this.user.setProfileManager(new ProfileManager(temp, this.bmUtente.getAnnuncioUtente(idUtente)));
        } catch (NessunAnnuncioException ex) {
           this.user.setProfileManager(new ProfileManager(temp, null));
        }   
        return idUtente;
    }
    
    public void settaLoggato(int idUtente) throws SQLException {
        this.switchToUser();
        Utente temp = new Utente(idUtente, this.bmUtente.getDatiUtente(idUtente));
        try {
           this.user.setProfileManager(new ProfileManager(temp, this.bmUtente.getAnnuncioUtente(idUtente)));
        } catch (NessunAnnuncioException ex) {
           this.user.setProfileManager(new ProfileManager(temp, null));
        }   
    }
    
    /**
     * 
     * @param nome NOME DELL'UTENTE
     * @param cognome COGNOME DELL'UTENTE
     * @param sesso SESSO DELL'UTENTE
     * @param eMail eMail DELL'UTENTE
     * @param password PASSWORD DELL'UTENTE
     * @param giorno GIORNO DI NASCITA DELL'UTENTE
     * @param mese MESE DI NASCITA DELL'UTENTE
     * @param anno ANNO DI NASCITA DELL'UTENTE
     * @param cellulare CELLULARE DELL'UTENETE
     * @param nazionalita NAZIONALITA DELL'UTENTE
     * @param occupazione OCCUPAZIONE DELL'UTENTE
     * @param facolta FACOLTA DELL'UTENTE
     * @param fumatore BOOLEANO CHE INDICA SE L'UTENTE FUMA
     * @param cuoco BOOLEANO CHE INDICA SE L'UTENTE SA CUCINARE
     * @param sportivo BOOLEAN CHE INDICA SE L'UTENTE FA SPORT
     * @param cittaDiRicerca CITTA IN CUI L'UTENTE CERCA CASA
     * @param potenzialeCoinquilino BOOLEANO CHE INDICA SE L'UTENTE È CANDIDATO COME COINQUILINO
     * @throws RegistrazioneException
     * @throws SQLException
     * @throws ParseException 
     */
    public void registrazioneUtente(String nome, String cognome, Sesso sesso,String eMail, String password,  int giorno, int mese, int anno, String cellulare, Nazione nazionalita, 
            
            Occupazione occupazione, Facolta facolta, boolean fumatore, boolean cuoco, boolean sportivo, Citta cittaDiRicerca, boolean potenzialeCoinquilino) throws RegistrazioneException, SQLException, ParseException{
        
        int idUtente = this.bmUtente.registrazione(eMail, password, potenzialeCoinquilino);
        DatiUtente dati = new DatiUtente(nome, cognome, sesso, eMail, password, giorno, mese, anno, cellulare, nazionalita, occupazione, facolta, fumatore, cuoco, sportivo, cittaDiRicerca, potenzialeCoinquilino);
        this.bmUtente.inserisciAnagraficaUtente(idUtente, dati);
        this.bmUtente.inserisciInfoUtente(idUtente, dati);
        
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
    
    /**
     * 
     * @param costo PREZZO MAX CHE L'UTENTE VUOLE SPENDERE PER LA CASA
     */
    public void setCostoMax(int costo) {
        this.parametriAnnuncio.setCostoMax(costo);
    }
    
    /**
     * 
     * @param citta CITTA IN CUI L'UTENTE CERCA CASA
     */
    public void iniziaRicercaAnnunci(Citta citta){
        this.parametriAnnuncio = new ContenitoreParametriAnnuncio(citta);
    }
    
    /**
     * 
     * @param citta CITTA IN CUI L'UTENTE CERCA COINQUILINO 
     */
    public void iniziaRicercaCoinquilini(Citta citta){
        this.parametriCoinquilino = new ContenitoreParametriCoinquilino(citta);
    }
    
    /**
     * 
     * @param stelle STELLE CHE INDICANO L'IMPORTANZA CHE L'UTENTE DA ALLA PRESENZA O MENO DELLA CUCINA SEPARATA
     * @param cucinaSeparata BOOLEANO CHE INDICA SE L'UTENTE CERCA UNA CASA CON CUCINA SEPARATA 
     */
    public void setParametroCucina(int stelle, boolean cucinaSeparata) {
        this.parametriAnnuncio.setParametroCucina(stelle, cucinaSeparata);
    }
    
    /**
     * 
     * @param stelle STELLE CHE INDICANO L'IMPORTANZA CHE L'UTENTE DA ALLA DISTANZA DELLA CASA DAL CENTRO
     * @param distanzaMax DISTANZA MAX CHE L'UTENTE VUOLE DELLA CASA DAL CENTRO
     */
    public void setParametroDistCentro(int stelle, int distanzaMax) {
        this.parametriAnnuncio.setParametroDistCentro(stelle, distanzaMax);
    }
    
    /**
     * 
     * @param stelle STELLE CHE INDICANO L'IMPORTANZA CHE L'UTENTE DA AL NUMERO DEI LOCALI PRESENTI IN CASA
     * @param nLocali NUMERO DEI LOCALI CHE L'UTENTE RICERCA IN UNA CASA  
     */
    public void setParametroNLocali(int stelle, int nLocali) {
        this.parametriAnnuncio.setParametroNLocali(stelle, nLocali);
    }
    
    /**
     * 
     * @param stelle STELLE CHE INDICANO L'IMPORTANZA CHE L'UTENTE DA AL NUMERO DEI BAGNI PRESENTI IN CASA
     * @param nBagni NUMERO DEI BAGNI CHE L'UTENTE RICERCA IN UNA CASA
     */
    public void setParametroNBagni(int stelle, int nBagni) {
        this.parametriAnnuncio.setParametroNBagni(stelle, nBagni);
    }
    
    /**
     * 
     * @param stelle STELLE CHE INDICANO L'IMPORTANZA CHE L'UTENTE DA AL SESSO PRESENTE IN CASA
     * @param sesso SESSO DEGLI INQUILINI CHE L'UTENTE RICERCA IN UNA CASA
     */
    public void setParametroSessoCasa(int stelle, HouseGenerality sesso) {
        this.parametriAnnuncio.setParametroSessoCasa(stelle, sesso);
    }
    
    /**
     * 
     * @param stelle STELLE CHE INDICANO L'IMPORTANZA CHE L'UTENTE DA AL TIPO DI CAMERA CHE RICERCA IN UNA CASA
     * @param postiLetto NUMERO DEI POSTI LETTO CHE L'UTENTE RICERCA IN UNA CAMERA DI UNA CASA
     */
    public void setParametroTipoCamera(int stelle, int postiLetto) {
        this.parametriAnnuncio.setParametroTipoCamera(stelle, postiLetto);
    }
    
    public void setParametroElettrodomestico(int stelle, ElettroDomestico elettrodomestico) {
        this.parametriAnnuncio.setElettrodomestico(stelle, elettrodomestico);
    }
    
    /**
     * 
     * @return RITORNA UN GUEST 
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * 
     * @return RITORNA UN USER  
     */
    public User getUser() {
        return user;
    }

    /**
     * 
     * @return RITORNA I PARAMETRI MESSI DALL'UTENTE PER LA RICERCA ANNUNCIO
     */
    public ContenitoreParametriAnnuncio getParametriAnnuncio() {
        return parametriAnnuncio;
    }

    /**
     * 
     * @return RITORNA LA RICERCA ANNUNCIO 
     */
    public RicercaAnnuncio getRicercaAnnuncio() {
        return ricercaAnnuncio;
    }

    /**
     * 
     * @return RITORNA I PARAMETRI MESSI DALL'UTENTE PER LA RICERCA COINQUILINO 
     */
    public ContenitoreParametriCoinquilino getParametriCoinquilino() {
        return parametriCoinquilino;
    }
    
    /**
     * 
     * @return RITORNA LA RICERCA COINQUILINO
     */
    public RicercaCoinquilino getRicercaCoinquilino() {
        return ricercaCoinquilino;
    }

    /**
     * 
     * @return RITORNA L'ORA DI ACCESSO DI UN UTENTE 
     */
    public Calendar getDataOraAccesso() {
        return dataOraAccesso;
    }
    
    /**
     * 
     * @throws SQLException
     * @throws NessunAnnuncioException 
     */
    public void ricercaAnnuncio() throws SQLException, NessunAnnuncioException{
        this.ricercaAnnuncio = new RicercaAnnuncio(this.parametriAnnuncio);
        this.annunciRisultanti = this.ricercaAnnuncio.eseguiRicerca();
    }
    
    /**
     * 
     * @param stelle STELLE CHE INDICANO L'IMPORTANZA CHE UN UTENTE DA ALLA CAPACITA O MENO DI UN COINQUILINO DI CUCINARE
     * @param isCuoco BOOLEANO CHE INDICANO LA PREFERENZA SULLA CAPACITA O MENO DI UN COINQUILINO DI CUCINARE
     */
    public void setParametroCuoco(int stelle, Boolean isCuoco){
        this.parametriCoinquilino.setParametroCuoco(stelle, isCuoco);
    }
    
    /**
     * 
     * @param stelle STELLE CHE INDICANO L'IMPORTANZA CHE UN UTENTE DA ALLA FASCIA DI ETA DEL COINQUILINO RICERCATO
     * @param etaMin ETA MINIMA DEL COINQUILINO RICERCATO
     * @param etaMax ETA MAX DEL COINQUILINO RICERCATO
     */
    public void setParametroEta(int stelle,int etaMin,int etaMax){
        this.parametriCoinquilino.setParametroEta(stelle, etaMin, etaMax);
    }
    
    /**
     * 
     * @param stelle STELLE CHE INDICANO L'IMPORTANZA CHE L'UTENTE DA ALLA FACOLTA FREQUENTATA DAL COINQUILINO RICERCATO
     * @param facolta FACOLTA DEL COINQUILINO RICERCATO 
     */
    public void setParametroFacolta(int stelle,ProfiloUtente.Facolta facolta){
        this.parametriCoinquilino.setParametroFacolta(stelle, facolta);
    }
    
    /**
     * 
     * @param stelle STELLE CHE INDICANO L'IMPORTANZA CHE UN UTENTE DA AL FATTO CHE IL COINQUILINO RICERCATO FUMI O MENO
     * @param isFumatore BOOLEANO CHE INDICA SE IL COINQUILINO RICERCATO FUMA O NO 
     */
    public void setParametroFumatore(int stelle,boolean isFumatore){
        this.parametriCoinquilino.setParametroFumatore(stelle, isFumatore);
    }
    
    /**
     * 
     * @param stelle STELLE CHE INDICANO L'IMPORTANZA  CHE UN UTENTE DA ALLA NAZIONALITA DEL COINQUILINO
     * @param nazionalita NAZIONALITA DEL COINQUILINO RICERCATO
     */
    public void setParametroNazionalita(int stelle,ProfiloUtente.Nazione nazionalita){
        this.parametriCoinquilino.setParametroNazionalita(stelle, nazionalita);
    }
    
    /**
     * 
     * @param stelle STELLE CHE INDICANO L'IMPORTANZA CHE UN UTENTE DA ALL'OCCUPAZIONE DEL COINQUILINO RICERCATO
     * @param occupazione OCCUPAZIONE DEL COINQUILINO RICERCATO
     */
    public void setParametroOccupazione(int stelle,ProfiloUtente.Occupazione occupazione){
        this.parametriCoinquilino.setParametroOccupazione(stelle, occupazione);
    } 
    
    /**
     * 
     * @param stelle STELLE CHE INDICANO L'IMPORTANZA CHE L'UTENTE DA AL FATTO CHE IL COINQUILINO RICERCATO SIA SPORTIVO
     * @param sportivo BOOLEANO CHE INDICA SE IL COINQUILINO RICERCATO DEVE ESSERE SPORTIVO O MENO
     */
    public void setParametroSportivo(int stelle,boolean sportivo){
        this.parametriCoinquilino.setParametroSportivo(stelle, sportivo);
    }
    
    /**
     * 
     * @param sesso SESSO DEL COINQUILINO RICERCATO 
     */
    public void setSesso(Sesso sesso) {
        this.parametriCoinquilino.setSesso(sesso);
    }
    
    /**
     * 
     * @throws SQLException
     * @throws NessunAnnuncioException 
     */
    public void ricercaCoinquilino() throws SQLException, NessunAnnuncioException {
        this.ricercaCoinquilino = new RicercaCoinquilino(this.parametriCoinquilino);
        this.coinquiliniRisultanti = this.ricercaCoinquilino.eseguiRicerca();
    }

    /**
     * 
     * @return RITORNA GLI ANNUNCI RISULTANTI DALLA RICERCA 
     */
    public ArrayList<AnnuncioRisultante> getAnnunciRisultanti() {
        return annunciRisultanti;
    }
    
    /**
     * 
     * @return RITORNA I COINQUILINI RISULTANTI DALLA RICERCA
     */
    public ArrayList<CoinquilinoRisultante> getCoinquiliniRisultanti() {
        return coinquiliniRisultanti;
    }
    
    /**
     * VERIFICA LA PRESENZA DI ALMENO UN ANNUNCIO DI UN UTENTE
     * @param idUtenteProprietario
     * @return TRUE SE L'AQNNUNCIO È PRESENTE, ALTRIMENTI FALSE
     * @throws SQLException 
     */
    public boolean verificaPresenzaAnnunci(int idUtenteProprietario) throws SQLException {
        return bmUtente.verificaPresenzaAnnuncio(idUtenteProprietario);
    }
    
    
}
