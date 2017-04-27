
/*
 * Progetto Coinquilini
 * AA 2016/2017 * 
 */
package RicercaAnnuncio;

/**
 *
 * @author alberto
 */
public class ParametriRicercaAnnuncio {
    private String citta;
    private int metriQuadriMin, metriQuadriMax;
    private int numeroLocaliMin, numeroLocaliMax;
    private int numeroBagni;
    private int postiLettoMax;
    private int costoMax;

    public ParametriRicercaAnnuncio(String citta, int metriQuadriMin, int metriQuadriMax, 
            int numeroLocaliMin, int numeroLocaliMax, int numeroBagni, int postiLettoMax,
            int costoMax) {
        
        this.citta = citta;
        this.metriQuadriMin = metriQuadriMin;
        this.metriQuadriMax = metriQuadriMax;
        this.numeroLocaliMin = numeroLocaliMin;
        this.numeroLocaliMax = numeroLocaliMax;
        this.numeroBagni = numeroBagni;
        this.postiLettoMax = postiLettoMax;
        this.costoMax = costoMax;
    }

    public String getCitta() {
        return citta;
    }

    public int getMetriQuadriMin() {
        return metriQuadriMin;
    }

    public int getMetriQuadriMax() {
        return metriQuadriMax;
    }

    public int getNumeroLocaliMin() {
        return numeroLocaliMin;
    }

    public int getNumeroLocaliMax() {
        return numeroLocaliMax;
    }

    public int getNumeroBagni() {
        return numeroBagni;
    }

    public int getPostiLettoMax() {
        return postiLettoMax;
    }

    public int getCostoMax() {
        return costoMax;
    }
    
    
    
    
}
