/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import ProfiloUtente.Utente;

/**
 *
 * @author Delbo
 */
class Occupazione extends ParametroRicercaCoinquilino{

    private ProfiloUtente.Occupazione occupazione;
    /**
     * Metodo che crea un'istanza di un parametro di ricerca occupazione.
     * @param stelle Importanza del parametro nella ricerca.
     * @param occupazione Occupazione richiesta dalla ricerca.
     */
    Occupazione(int stelle,ProfiloUtente.Occupazione occupazione) {
        super(stelle);
        this.occupazione=occupazione;
    }
    /**
     * CALCOLA L'AFFINITA DEL SINGOLO PARAMETRO OCCUPAZIONE 
     * @param utente ANNUNCIO DELLA CASA IN QUESTIONE
     * @return RITORNA UN NUMERO FLOAT CHE INDICA L'AFFINITA
     */
    @Override
    float calcolaAffinità(Utente utente) {
        if(utente.getDatiUtente().getOccupazione()==this.occupazione)
            return super.getStelle();
        else
            return 0;
    }
    
}
