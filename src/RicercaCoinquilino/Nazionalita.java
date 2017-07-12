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
class Nazionalita extends ParametroRicercaCoinquilino {

    private ProfiloUtente.Nazione nazione;
    /**
     * Metodo che crea un'istanza di un parametro di ricerca nazionalità.
     * @param stelle Importanza del parametro nella ricerca.
     * @param nazionalita  Nazionalita richiesta.
     */
    Nazionalita(int stelle,ProfiloUtente.Nazione nazionalita) {
        super(stelle);
        this.nazione=nazionalita;
    }
    /**
     * CALCOLA L'AFFINITA DEL SINGOLO PARAMETRO NAZIONALITA 
     * @param utente ANNUNCIO DELLA CASA IN QUESTIONE
     * @return RITORNA UN NUMERO FLOAT CHE INDICA L'AFFINITA
     */
    @Override
    float calcolaAffinità(Utente utente) {
        if(utente.getDatiUtente().getNazionalita()==this.nazione)
            return super.getStelle();
        else
            return 0;
    }
    
}
