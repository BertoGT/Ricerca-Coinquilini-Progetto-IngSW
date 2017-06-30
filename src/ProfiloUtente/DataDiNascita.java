/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfiloUtente;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author Marco La Salvia
 */
public class DataDiNascita {
    private SimpleDateFormat simpleDateFormat;
    private GregorianCalendar calendario;
    private String miaData;
    private int giorno, mese, anno, eta;
    
    /**
     *
     * @param giorno indica il giorno di nascita dell'utente
     * @param mese indica il mese di nascita dell'utente
     * @param anno indica l'anno di nascita dell'utente
     */
    public DataDiNascita(int giorno, int mese, int anno){
        this.calendario = new GregorianCalendar();
        this.simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.giorno = giorno;
        this.mese = mese;
        this.anno = anno;
        this.miaData= giorno + "/" + mese + "/" + anno;
        this.calcolaEta();
    }
    
    private void calcolaEta(){
        this.eta = this.calendario.get(GregorianCalendar.YEAR) - this.anno;
        if(this.calendario.get(GregorianCalendar.MONTH)<this.mese)
            this.eta = this.eta - 1;
        else if(this.mese == this.calendario.get(GregorianCalendar.MONTH) && this.giorno > this.calendario.get(GregorianCalendar.DAY_OF_MONTH))
            this.eta = this.eta - 1;
    }

    /**
     *
     * @return : RESTITUISCE IL GIORNO DI NASCITA DELL'UTENTE.
     */
    public int getGiorno() {
        return giorno;
    }

    /**
     *
     * @param giorno: INTERO INDICANTE IL GIORNO DI NASCITA DELL'UTENTE.
     */
    public void setGiorno(int giorno) {
        this.giorno = giorno;
        this.calcolaEta();
    }

    /**
     *
     * @return:RESTITUISCE IL MESE DI NASCITA DELL'UTENTE.
     */
    public int getMese() {
        return mese;
    }

    /**
     *
     * @param mese: INTERO INDICANTE IL MESE DI NASCITA DELL'UTENTE.
     */
    public void setMese(int mese) {
        this.mese = mese;
        this.calcolaEta();
    }

    /**
     *
     * @return: RESTITUISCE L'ANNO DI NASCITA DELL'UTENTE.
     */
    public int getAnno() {
        return anno;
    }

    /**
     *
     * @param anno: INTERO INDICANTE L'ANNO DI NASCITA DELL'UTENTE.
     */
    public void setAnno(int anno) {
        this.anno = anno;
        this.calcolaEta();
    }

    /**
     *
     * @return: STRINGA TESTUALE CONTENENTE LA DATA DI NASCITA.
     */
    @Override
    public String toString() {
        return "\nDATA DI NASCITA: " + this.miaData + "\n";
    }

    public int getEta() {
        return eta;
    }
    
    
}
