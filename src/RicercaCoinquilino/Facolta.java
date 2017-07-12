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
public class Facolta extends ParametroRicercaCoinquilino{
    
    private ProfiloUtente.Facolta facolta;
    /**
     * Metodo che crea un'istanza di un parametro di ricerca facoltà.
     * @param stelle Importanza del parametro nella ricerca.
     * @param facolta  Facolta richiesta.
     */
    public Facolta(int stelle,ProfiloUtente.Facolta facolta) {
        super(stelle);
        this.facolta=facolta;
    }
    /**
     * CALCOLA L'AFFINITA DEL SINGOLO PARAMETRO FACOLTA 
     * @param utente ANNUNCIO DELLA CASA IN QUESTIONE
     * @return RITORNA UN NUMERO FLOAT CHE INDICA L'AFFINITA
     */
    @Override
    public float calcolaAffinità(Utente utente) {
        if(utente.getDatiUtente().getFacolta()==this.facolta)
            return super.getStelle();
        else
            return 0;
         
        
    }
}
