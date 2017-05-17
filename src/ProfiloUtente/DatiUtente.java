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
    }

    /**
     *
     * @return Crea un'istanza di Dati Utente, ossia un contenitore dei dati di un utilizzatore della piattaforma di ricerca coinquilini;
     */
    public DataDiNascita getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     *
     * @param giorno
     * @param mese
     * @param anno
     */
    public void setDataDiNascita(int giorno, int mese, int anno) {
        this.dataDiNascita = new DataDiNascita(giorno,mese,anno);
    }

    /**
     *
     * @return
     */
    public String geteMail() {
        return eMail;
    }

    /**
     *
     * @param eMail
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    /**
     *
     * @param numeroDiTelefono
     */
    public void setNumeroDiTelefono(String numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
    }

    /**
     *
     * @return
     * METODO GET DELL'ATTRIBUTO SESSO DELL'UTENTE;
         * PARAMETRI: NESSUNO;
         * OUTPUT: SESSO(ENUM)
     */
    public Sesso getSesso() {
        return sesso;
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

    @Override
    public String toString() {
        String s = "";
        s += "NOME: "+this.nome + "\nCOGNOME: "+this.cognome +"\nDATA DI NASCITA: " +this.dataDiNascita.toString();
        s += "\nSESSO: "+this.sesso + "\nEMAIL: "+this.eMail+"\nNAZIONALITA': "+this.nazionalita;
        s+= "\nOCCUPAZIONE: "+this.occupazione+"\nFACOLTA': "+this.facolta+"\nFUMATORE: "+this.fumatore;
        s+= "\nCUOCO: "+this.cuoco+"\nSPORTIVO: "+ this.sportivo+"\nCITTA' DI RICERCA: "+this.cittaDiRicerca;
        return s;
    }
    

}