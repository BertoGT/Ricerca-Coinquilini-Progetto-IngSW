/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfiloUtente;

/**
 *
 * @author Marco La Salvia
 */
public class Utente {
    private String idUtente;
    private DatiUtente datiUtente;

    /**
     *
     * @param idUtente
     * @param datiUtente
     * @param dataDiNascita
     */
    public Utente(String idUtente, DatiUtente datiUtente) {
        this.idUtente = idUtente;
        this.datiUtente = datiUtente;
    }

    /**
     *
     * @return : RESTITUISCE L'ID DELL'UTENTE ALL'INTERNO DEL DATABASE.
     */
    public String getIdUtente() {
        return idUtente;
    }

    /**
     *
     * @return RESTITUISCE UN OGGETTO DATIUTENTE CONTENENTE I DATI INSERITI DALL'UTENTE DURANTE LA REGISTRAZIONE.
     */
    public DatiUtente getDatiUtente() {
        return datiUtente;
    }

    /**
     *
     * @param datiUtente: OGGETTO DATI UTENTE CONTENTENTE DATI NUOVI O PARZIALMENTE NUOVI CON I QUALI SI VUOLE MODIFICARE QUELLI GIA' ESISTENTI.
     */
    public void setDatiUtente(DatiUtente datiUtente) {
        this.datiUtente = datiUtente;
    }
    /**
     *
     * @return RESTITUISCE UNA STRINGA CONTENENTE UN RIASSUNTO DEI DATI DELL'UTENTE.
     */
    @Override
    public String toString() {
        return "UTENTE " + this.idUtente +" :" + this.datiUtente.toString();
    }
    
    
    
    
    
}
