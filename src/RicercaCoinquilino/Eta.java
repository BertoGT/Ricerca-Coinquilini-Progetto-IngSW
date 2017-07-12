
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import ProfiloUtente.Utente;

/**
 *
 * @author Niko
 */
class Eta extends ParametroRicercaCoinquilino{
    
    private int etaMin, etaMax;
    /**
     * Metodo che crea un'istanza di un parametro di ricerca età.
     * @param stelle Importanza del parametro nella ricerca.
     * @param etaMin  valore minimo di età tollerata.
     * @param etaMax  valore massimo di età tollerata.
     */
    Eta(int stelle, int etaMin, int etaMax) {
        super(stelle);
        this.etaMin = etaMin;
        this.etaMax = etaMax;
    }
    /**
     * CALCOLA L'AFFINITA DEL SINGOLO PARAMETRO ETA' 
     * @param utente ANNUNCIO DELLA CASA IN QUESTIONE
     * @return RITORNA UN NUMERO FLOAT CHE INDICA L'AFFINITA
     */
    @Override
    float calcolaAffinità(Utente utente) {
        if(utente.getDatiUtente().getDataDiNascita().getEta() <= etaMax && utente.getDatiUtente().getDataDiNascita().getEta() >= etaMin)
            return super.getStelle();
        else 
            return 0;
    }
    
}
