/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfiloUtente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * 
 * @author Marco La Salvia
 */

public class DatiUtente {
    
    private DataDiNascita dataDiNascita;
    private String eMail, password, numeroDiTelefono;
    private Sesso sesso; 
    private boolean fumatore, cuoco, sportivo; 
    private String nome, cognome;
    private Nazionalita nazionalita;
    private Occupazione occupazione; 
    private boolean potenzialeCoinquilino;
    private Facolta facolta;
    private String cittaDiRicerca; // aggiunta da and (albe, niko, delbo) per eseguire la ricerca nelle citta dei coinquilini 
    /**
     *
     * @param nome
     * @param cognome
     * @param sesso
     * @param eMail
     * @param password
     * @param giorno
     * @param mese
     * @param anno
     * @param cellulare
     * @param nazionalita
     * @param occupazione
     * @param facolta
     * @param fumatore
     * @param cuoco
     * @param sportivo
     * @param cittaDiRicerca
     * @throws ParseException
     */
    public DatiUtente(String nome, String cognome, Sesso sesso,String eMail, String password,  int giorno, int mese, int anno, String cellulare, Nazionalita nazionalita, 
            
            Occupazione occupazione, Facolta facolta, boolean fumatore, boolean cuoco, boolean sportivo, String cittaDiRicerca) throws ParseException {
        
        this.nome=nome;
        this.cognome=cognome;
        this.sesso=sesso;
        this.eMail = eMail;
        this.password = password;
        this.dataDiNascita = new DataDiNascita(giorno, mese, anno);
        this.numeroDiTelefono=cellulare;
        this.nazionalita = nazionalita;
        this.occupazione=occupazione;
        this.facolta=facolta;
        this.fumatore=fumatore;
        this.cuoco=cuoco;
        this.sportivo=sportivo;
        this.cittaDiRicerca = cittaDiRicerca;
        this.potenzialeCoinquilino = false; 
        /**
         * Crea un'istanza di Dati Utente, ossia un contenitore dei dati di un utilizzatore della piattaforma di ricerca coinquilini;
         * PARAMETRI : NOME, COGNOME, SESSO, EMAIL, PASSWORD, GIORNO MESE E ANNO DI NASCITA,
         * CELLULARE, NAZIONALITA, OCCUPAZIONE, FACOLTA, FUMATORE, CUOCO, SPORTIVO E CITTA IN CUI CERCA COINQUILINO O VORREBBE TROVARE CASA;
         */
    }

    /**
     *
     * @return
     */
    public DataDiNascita getDataDiNascita() {
        return dataDiNascita;
        /**
         * RITORNA L'ISTANZA DI UNA DATA DI NASCITA OSSIA LA CLASSE CONTENENTE I DATI RIGUARDANTI L'ETA ED IL CALCOLO DELLA STESSA;
         * PARAMETRI: NESSUNO;
         * OUTPUT: DataDiNascita;
         */
    }

    /**
     *
     * @param giorno
     * @param mese
     * @param anno
     */
    public void setDataDiNascita(int giorno, int mese, int anno) {
        this.dataDiNascita = new DataDiNascita(giorno,mese,anno);
        /**
         * IMPOSTA LA DATA DI NASCITA DELL'UTENTE (O NE PERMETTE LA MODIFICA SE GIA' IMPOSTATA);
         * PARAMETRI: GIORNO, MESE, ANNO;
         * OUTPUT: NESSUNO;
         */
    }

    /**
     *
     * @return
     */
    public String geteMail() {
        return eMail;
        /**
         * METODO GET DELL'ATTRIBUTO EMAIL;
         * PARAMETRI: NESSUNO;
         * OUTPUT: EMAIL;
         */
    }

    /**
     *
     * @param eMail
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
        /**
         * METODO SET DELL'EMAIL DELL'UTENTE;
         * PARAMETRO: EMAIL;
         * OUTPUT: NESSUNO;
         */
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
        /**
          METODO GET DELL'ATTRIBUTO PASSWORD;
         * PARAMETRI: NESSUNO;
         * OUTPUT: PASSWORD;
         */
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
        /**
         * METODO SET DELL'ATTRIBUTO PASSWORD;
         * PARAMETRI: PASSWORD;
         * OUTPUT: NESSUNO;
         */
    }

    /**
     *
     * @return
     */
    public String getNumeroDiTelefono() {
        return numeroDiTelefono;
        /**
         * METODO GET DELL'ATTRIBUTO NUMERODITELEFONO;
         * PARAMETRI: NESSUNO;
         * OUTPUT: NUMERODITELEFONO
         */
    }

    /**
     *
     * @param numeroDiTelefono
     */
    public void setNumeroDiTelefono(String numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
        /**
         * METODO CHE PERMETTE LA MODIFICA DEL NUMERO DI TELEFONO (SETTER);
         * PARAMETRO: NUMERO DI TELEFONO (STRING)
         * OUTPUT: NESSUNO;
         */
    }

    /**
     *
     * @return
     */
    public Sesso getSesso() {
        return sesso;
        /**
         * METODO GET DELL'ATTRIBUTO SESSO DELL'UTENTE;
         * PARAMETRI: NESSUNO;
         * OUTPUT: SESSO(ENUM)
         */
    }

    /**
     *
     * @param sesso
     */
    public void setSesso(Sesso sesso) {
        this.sesso = sesso;
    }

    /**
     *
     * @return
     */
    public boolean isFumatore() {
        return fumatore;
    }

    /**
     *
     * @param fumatore
     */
    public void setFumatore(boolean fumatore) {
        this.fumatore = fumatore;
    }

    /**
     *
     * @return
     */
    public boolean isCuoco() {
        return cuoco;
    }

    /**
     *
     * @param cuoco
     */
    public void setCuoco(boolean cuoco) {
        this.cuoco = cuoco;
    }

    /**
     *
     * @return
     */
    public boolean isSportivo() {
        return sportivo;
    }

    /**
     *
     * @param sportivo
     */
    public void setSportivo(boolean sportivo) {
        this.sportivo = sportivo;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getCognome() {
        return cognome;
    }

    /**
     *
     * @param cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     *
     * @return
     */
    public Nazionalita getNazionalita() {
        return nazionalita;
    }

    /**
     *
     * @param nazionalita
     */
    public void setNazionalita(Nazionalita nazionalita) {
        this.nazionalita = nazionalita;
    }

    /**
     *
     * @return
     */
    public Occupazione getOccupazione() {
        return occupazione;
    }

    /**
     *
     * @param occupazione
     */
    public void setOccupazione(Occupazione occupazione) {
        this.occupazione = occupazione;
    }

    /**
     *
     * @return
     */
    public boolean isPotenzialeCoinquilino() {
        return potenzialeCoinquilino;
    }

    /**
     *
     * @param potenzialeCoinquilino
     */
    public void setPotenzialeCoinquilino(boolean potenzialeCoinquilino) {
        this.potenzialeCoinquilino = potenzialeCoinquilino;
    }

    /**
     *
     * @return
     */
    public Facolta getFacolta() {
        return facolta;
    }

    /**
     *
     * @param facolta
     */
    public void setFacolta(Facolta facolta) {
        this.facolta = facolta;
    }

    /**
     *
     * @return
     */
    public String getCittaDiRicerca() {
        return cittaDiRicerca;
    }

    /**
     *
     * @param cittaDiRicerca
     */
    public void setCittaDiRicerca(String cittaDiRicerca) {
        this.cittaDiRicerca = cittaDiRicerca;
    }
    
    
    
}