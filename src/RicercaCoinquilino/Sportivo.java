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
class Sportivo extends ParametroRicercaCoinquilino{

    private boolean sportivo;
    /**
     * Metodo che crea un'istanza di un parametro di ricerca sportivo.
     * @param stelle Importanza del parametro nella ricerca.
     * @param cuoco  true se si richiede un profilo che sia sportivo, altrimenti false.
     */
    Sportivo(int stelle,boolean sportivo) {
        super(stelle);
        this.sportivo=sportivo;
    }
    /**
     * CALCOLA L'AFFINITA DEL SINGOLO PARAMETRO SPORTIVO 
     * @param utente ANNUNCIO DELLA CASA IN QUESTIONE
     * @return RITORNA UN NUMERO FLOAT CHE INDICA L'AFFINITA
     */
    @Override
    float calcolaAffinità(Utente utente) {
        if(this.sportivo){
            if(utente.getDatiUtente().isSportivo())
                return super.getStelle();
            else
                return 0;
        } else {
            if(!utente.getDatiUtente().isSportivo())
                return super.getStelle();
            else
                return 0;
        }
    }
}
