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
 * @author Margherita
 */
public class DatiUtente {
    
    
    private SimpleDateFormat sdf;
    private GregorianCalendar dataDiNascita= new GregorianCalendar();
    private String miaData;
    private int giorno, mese, anno;
    private int eta;
    private String sesso;  // volendo si può usare enum (F o M)
    private boolean fumatore, cuoco, sportivo; 
    private String nome, cognome, nazionalita;
    private Occupazione occupazione; 
    private boolean potenzialeCoinquilino;
    public Facolta facolta;
    
    
    public DatiUtente(String nome, String cognome, String sesso, int giorno, int mese, int anno) throws ParseException {

        this.nome=nome;
        this.cognome=cognome;
        this.sesso=sesso;
        
        calcoloEta(giorno, mese, anno);
        
    }

    public DatiUtente(String nome, String cognome, String sesso, int giorno, int mese, int anno, String nazionalita, 
            
            Occupazione occupazione, Facolta facolta, boolean fumatore, boolean cuoco, boolean sportivo) throws ParseException {
        
        this.nome=nome;
        this.cognome=cognome;
        this.sesso=sesso;
        calcoloEta(giorno, mese, anno);
        this.nazionalita=nazionalita;
        this.occupazione=occupazione;
        this.facolta=facolta;
        this.fumatore=fumatore;
        this.cuoco=cuoco;
        this.sportivo=sportivo;
        this.potenzialeCoinquilino=false; // diventa true in caso di candidatura come coinquilino

    }
    
    
    private void calcoloEta(int giorno, int mese, int anno) throws ParseException {
        
        this.anno=anno;
        this.mese=mese;
        this.giorno=giorno;
        this.sdf= new SimpleDateFormat("dd/MM/yyyy");
        this.miaData= giorno+"/"+mese+"/"+anno;
        dataDiNascita.setTime(sdf.parse(this.miaData));
        
        Calendar c= new GregorianCalendar();
        
        eta=c.get(Calendar.YEAR)-anno;    // calcolo età solo in base all'anno, non in base al mese e al giorno
    }

    public Calendar getDataDiNascita() {
        return dataDiNascita;
    }

    public String getSesso() {
        return sesso;
    }

    public int getEta() {
        return eta;
    }
    
    public String getNazionalita() {
        return nazionalita;
    }

    public boolean isFumatore() {
        return fumatore;
    }

    public boolean isCuoco() {
        return cuoco;
    }

    public boolean isSportivo() {
        return sportivo;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNazionalità() {
        return nazionalita;
    }

    public Occupazione getOccupazione() {
        return occupazione;
    }

    public boolean isPotenzialeCoinquilino() {
        return potenzialeCoinquilino;
    }

    public Facolta getFacolta() {
        return facolta;
    }

    public void setDataDiNascita(int giorno, int mese, int anno) throws ParseException {
        
        calcoloEta(giorno, mese, anno);
        
    }
    
    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public void setFumatore(boolean fumatore) {
        this.fumatore = fumatore;
    }

    public void setCuoco(boolean cuoco) {
        this.cuoco = cuoco;
    }

    public void setSportivo(boolean sportivo) {
        this.sportivo = sportivo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public void setOccupazione(Occupazione occupazione) {
        this.occupazione = occupazione;
    }
    
    public void setEta(int eta) {
        this.eta = eta;
    }


    public void candidaturaCoinquilino(boolean potenzialeCoinquilino) {
        this.potenzialeCoinquilino = potenzialeCoinquilino;
    }

    @Override
    public String toString() {
        return "DatiUtente: \n" + "- data di nascita = " + miaData + "\n- eta = "+ eta+ "\n- sesso = " + sesso + "\n- fumatore = " + fumatore 
                
                    + "\n- cuoco = " + cuoco + "\n- sportivo = " + sportivo + "\n- nome = " + nome + "\n- cognome = " + cognome + 
                
                    "\n- nazionalita = " + nazionalita + "\n- occupazione = " + occupazione + "\n- potenziale coinquilino = " 
                
                    + potenzialeCoinquilino + "\n- facolta = " + facolta ;
    }
    
    
    
    
    
    
}
