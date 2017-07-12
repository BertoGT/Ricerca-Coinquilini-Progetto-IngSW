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
class Fumatore extends ParametroRicercaCoinquilino{
    
    private boolean fumatore;
    /**
     * Metodo che crea un'istanza di un parametro di ricerca fumatore.
     * @param stelle Importanza del parametro nella ricerca.
     * @param fumatore  true se si richiede un profilo che abbia il vizio del fumo, altrimenti false.
     */
    Fumatore(int stelle, boolean fumatore) {
        super(stelle);
        this.fumatore = fumatore;
    }
    /**
     * CALCOLA L'AFFINITA DEL SINGOLO PARAMETRO CUOCO 
     * @param utente ANNUNCIO DELLA CASA IN QUESTIONE
     * @return RITORNA UN NUMERO FLOAT CHE INDICA L'AFFINITA
     */
    @Override
    float calcolaAffinità(Utente utente) {
        if(fumatore)
            return super.getStelle();
        else {
            if(utente.getDatiUtente().isFumatore())
                return 0;
            else 
                return super.getStelle();
        }
    }
}
