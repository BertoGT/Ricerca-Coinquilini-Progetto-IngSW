
/*
 * Progetto Coinquilini
 * AA 2016/2017 * 
 */
package RicercaAnnuncio;

import Casa.Citta;
import Casa.ElettroDomestico;
import Casa.HouseGenerality;
import java.util.ArrayList;

/**
 *
 * @author alberto
 */
public class ContenitoreParametriAnnuncio {
    private ArrayList<ParametroRicercaAnnuncio> parametriRicerca;
    private Citta citta;
    private int costoMax;

    /**
     * Istanzia un oggetto che contiene i parametri di ricerca di annunci casa.
     * @param citta Citta in cui cercare (parametro obbligatorio)
     */
    public ContenitoreParametriAnnuncio(Citta citta) {
        parametriRicerca = new ArrayList<ParametroRicercaAnnuncio>();
        this.citta = citta;
    }

    public Citta getCitta() {
        return citta;
    }

    public int getCostoMax() {
        return costoMax;
    }

    /**
     * Imposta il costo massimo degli annunci da applicare alla ricerca
     * @param costo costo massimo
     */
    public void setCostoMax(int costo) {
        this.costoMax = costo;
    }
    
    /**
     * Imposta il parametro relativo al tipo di cucina da ricercare
     * @param stelle peso che viene assegnato al parametro 
     * @param cucinaSeparata true se la cucina è separata, false altrimenti
     */
    public void setParametroCucina(int stelle, boolean cucinaSeparata) {
        parametriRicerca.add(new Cucina(stelle, cucinaSeparata));
    }
    
    /**
     * Imposta la distanza dal centro massima entro cui riocercare la casa
     * @param stelle peso che viene assegnato al parametro
     * @param distanzaMax distanza massima dal centro citta in metri
     */
    public void setParametroDistCentro(int stelle, int distanzaMax) {
        parametriRicerca.add(new DistanzaCentro(stelle, distanzaMax));
    }
    
    /**
     * Imposta il numero locali della ricerca
     * @param stelle peso che viene assegnato al parametro
     * @param nLocali numero locali da applicare alla ricerca
     */
    public void setParametroNLocali(int stelle, int nLocali) {
        parametriRicerca.add(new NLocali(stelle, nLocali));
    }
    
    /**
     * Imposta il numero bagni della ricerca
     * @param stelle peso che viene assegnato al parametro
     * @param nBagni numero di bagni 
     */
    public void setParametroNBagni(int stelle, int nBagni) {
        parametriRicerca.add(new NumeroBagni(stelle, nBagni));
    }
    
    /**
     * Imposta la preferenza sul sesso della casa
     * @param stelle peso che viene assegnato al parametro
     * @param sesso Sesso relativo ai membri della casa
     */
    public void setParametroSessoCasa(int stelle, HouseGenerality sesso) {
        parametriRicerca.add(new SessoCasa(stelle, sesso));
    }
    
    /**
     * Imposta il numero di posti letto della camera
     * @param stelle peso che viene assegnato al parametro
     * @param postiLetto numero posti letto totali contenuti nella stanza
     */
    public void setParametroTipoCamera(int stelle, int postiLetto) {
        parametriRicerca.add(new TipoCamera(stelle, postiLetto));
    }
    
    /**
     * Imposta la preferenza sulla presenza in casa di elettrodomestici
     * @param stelle
     * @param elettro 
     */
    public void setElettrodomestico(int stelle, ElettroDomestico elettro){
        parametriRicerca.add(new Elettrodomestico(stelle, elettro));
    }
    
    /**
     * Restituisce la lista dei parametri della ricerca inseriti
     * @return lista parametri ricerca
     */
    public ArrayList<ParametroRicercaAnnuncio> getParametriRicerca() {
        return parametriRicerca;
    }
    
    
    
    
}
