/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfiloUtente;

import Casa.Citta;
import java.text.ParseException;
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
    private Nazione nazionalita;
    private Occupazione occupazione; 
    private boolean potenzialeCoinquilino;
    private Facolta facolta;
    private Citta cittaDiRicerca; // aggiunta da and (albe, niko, delbo) per eseguire la ricerca nelle citta dei coinquilini 
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
    public DatiUtente(String nome, String cognome, Sesso sesso,String eMail, String password,  int giorno, int mese, int anno, String cellulare, Nazione nazionalita, 
            
            Occupazione occupazione, Facolta facolta, boolean fumatore, boolean cuoco, boolean sportivo, Citta cittaDiRicerca) throws ParseException {
        
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
     * @return RESTITUISCE LA DATA DI NASCITA DELL'UTENTE.
     */
    public DataDiNascita getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     *
     * @param giorno: GIORNO DI NASCITA.
     * @param mese: MESE DI NASCITA.
     * @param anno: ANNO DI NASCITA.
     */
    public void setDataDiNascita(int giorno, int mese, int anno) {
        this.dataDiNascita = new DataDiNascita(giorno,mese,anno);
    }

    /**
     *
     * @return RESTITUISCE L'EMAIL DELL'UTENTE.
     */
    public String geteMail() {
        return eMail;
    }

    /**
     *
     * @param eMail: EMAIL INSERITA DURANTE LA REGISTRAZIONE.
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     *
     * @return RESTITUISCE LA PASSWORD DELL'UTENTE.
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password: PASSWORD INSERITA DURANTE LA REGISTRAZIONE.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return RESTITUISCE IL NUMERO DI TELEFONO DELL'UTENTE.
     */
    public String getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    /**
     *
     * @param numeroDiTelefono: NUMERO TELEFONICO INSERITO DALL'UTENTE PER ESSERE CONTATTATO.
     */
    public void setNumeroDiTelefono(String numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
    }

    /**
     *
     * @return RESTITUISCE IL SESSO, M O F, DELL'UTENTE.
     */
    public Sesso getSesso() {
        return sesso;
    }

    /**
     *
     * @param sesso: MASCHIO O FEMMINA.
     */
    public void setSesso(Sesso sesso) {
        this.sesso = sesso;
    }

    /**
     *
     * @return RESTITUISCE TRUE SE L'UTENTE E' UN FUMATORE, VICEVERSA RESTITUISCE FALSE.
     */
    public boolean isFumatore() {
        return fumatore;
    }

    /**
     *
     * @param fumatore: TRUE SE L'UTENTE E' FUMATORE, FALSE SE NON LO E'.
     */
    public void setFumatore(boolean fumatore) {
        this.fumatore = fumatore;
    }

    /**
     *
     * @return RESTITUISCE TRUE SE L'UTENTE SI RITIENE UNA PERSONA CAPACE DI CUCINARE, VICEVERSA RESTITUISCE FALSE.
     */
    public boolean isCuoco() {
        return cuoco;
    }

    /**
     *
     * @param cuoco: TRUE SE L'UTENTE SI RITIENE IN GRADO DI CUCINARE DISCRETAMENTE, FALSE SE NON LO E'.
     */
    public void setCuoco(boolean cuoco) {
        this.cuoco = cuoco;
    }

    /**
     *
     * @return RESTITUISCE TRUE SE L'UTENTE SI RITIENE UNA PERSONA CHE PRATICA SPORT, VICEVERSA RESTITUISCE FALSE.
     */
    public boolean isSportivo() {
        return sportivo;
    }

    /**
     *
     * @param sportivo: TRUE SE L'UTENTE PRATICA SPORT, VICEVERSA FALSE.
     */
    public void setSportivo(boolean sportivo) {
        this.sportivo = sportivo;
    }

    /**
     *
     * @return RESTITUISCE UNA STRINGA DI TESTO CONTENENTE IL NOME DELL'UTENTE.
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome: STRINGA DI TESTO CON IL NOME DELL'UTENTE.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return RESTITUISCE UNA STRINGA DI TESTO CONTENENTE IL COGNOME DELL'UTENTE.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     *
     * @param cognome: STRINGA DI TESTO CON IL COGNOME DELL'UTENTE.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     *
     * @return RESTITUISCE LA NAZIONALITA' DELL'UTENTE.
     */
    public Nazione getNazionalita() {
        return nazionalita;
    }

    /**
     *
     * @param nazionalita: ENUM CONTENENTE LA NAZIONE DI PROVENIENZA DELL'UTENTE.
     */
    public void setNazionalita(Nazione nazionalita) {
        this.nazionalita = nazionalita;
    }

    /**
     *
     * @return RESTITUISCE L'OCCUPAZIONE DELL'UTENTE: STUDENTE, LAVORATORE O ALTRO.
     */
    public Occupazione getOccupazione() {
        return occupazione;
    }

    /**
     *
     * @param occupazione: ENUM CONTENENTE L'OCCUPAZIONE DELL'UTENTE.
     */
    public void setOccupazione(Occupazione occupazione) {
        this.occupazione = occupazione;
    }

    /**
     *
     * @return RESTITUISCE TRUE SE L'UTENTE VUOLE ESSERE INSERITO IN UNA EVENTUALE RICERCA DI COINQUILINI DA PARTE DI UN SECONDO UTENTE, VICEVERSA RESTITUISCE FALSE.
     */
    public boolean isPotenzialeCoinquilino() {
        return potenzialeCoinquilino;
    }

    /**
     *
     * @param potenzialeCoinquilino: TRUE SE L'UTENTE VUOLE CANDIDARSI COME POTENZIALE COINQUILINO.
     */
    public void setPotenzialeCoinquilino(boolean potenzialeCoinquilino) {
        this.potenzialeCoinquilino = potenzialeCoinquilino;
    }

    /**
     *
     * @return RESTITUISCE LA FACOLTA FREQUENTATA DALL'UTENTE SE QUESTO E' UNO STUDENTE.
     */
    public Facolta getFacolta() {
        return facolta;
    }

    /**
     *
     * @param facolta: ENUM DELLA FACOLTA FREQUENTATA DALL'UTENTE, SE STUDENTE.
     */
    public void setFacolta(Facolta facolta) {
        this.facolta = facolta;
    }

    /**
     *
     * @return RESTITUISCE LA CITTA' IN CUI L'UTENTE CERCA CASA O COINQUILINI.
     */
    public Citta getCittaDiRicerca() {
        return cittaDiRicerca;
    }

    /**
     *
     * @param cittaDiRicerca: ENUM CONTENTENTE LA CITTA' IN CUI LO STUDENTE CERCA CASA O COINQUILINI.
     */
    public void setCittaDiRicerca(Citta cittaDiRicerca) {
        this.cittaDiRicerca = cittaDiRicerca;
    }

    /**
     *
     * @return RESTITUISCE UNA STRINGA DI TESTO CONTENTENTE IL RIASSUNTO DEI DATI DELL'UTENTE.
     */
    @Override
    public String toString() {
        String s = "";
        s += "NOME: "+this.nome + "\nCOGNOME: "+this.cognome +"\nDATA DI NASCITA: " +this.dataDiNascita.toString();
        s += "\nSESSO: "+this.sesso + "\nEMAIL: "+this.eMail+"\nNAZIONALITA': "+this.nazionalita;
        s+= "\nOCCUPAZIONE: "+this.occupazione+"\nFACOLTA': "+this.facolta;
        if(this.fumatore)
            s+="\nFUMATORE: SI";
        else
            s+="\nFUMATORE: NO";
        if(this.cuoco)
            s+="\nCUOCO: SI";
        else
            s+="\nCUOCO: NO";
        if(this.sportivo)
            s+="\nSPORTIVO: SI";
        else
            s+="\nSPORTIVO: NO";
        s+= "\nCITTA' DI RICERCA: "+this.cittaDiRicerca;
        return s;
    }
    

}