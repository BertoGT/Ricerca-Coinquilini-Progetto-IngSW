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
class Cuoco extends ParametroRicercaCoinquilino {

    private boolean cuoco;
    /**
     * Metodo che crea un'istanza di un parametro di ricerca "cuoco".
     * @param stelle Importanza del parametro nella ricerca.
     * @param cuoco  true se si richiede un profilo che sappia cucinare, altrimenti false.
     */
    Cuoco(int stelle,boolean cuoco) {
        super(stelle);
        this.cuoco=cuoco;
    }
    /**
     * CALCOLA L'AFFINITA DEL SINGOLO PARAMETRO CUOCO 
     * @param utente ANNUNCIO DELLA CASA IN QUESTIONE
     * @return RITORNA UN NUMERO FLOAT CHE INDICA L'AFFINITA
     */
    @Override
    float calcolaAffinità(Utente utente) {
        if(this.cuoco){
            if(utente.getDatiUtente().isCuoco())
                return super.getStelle();
            else
                return 0;
        } else
            return super.getStelle();
    }          
    
}
