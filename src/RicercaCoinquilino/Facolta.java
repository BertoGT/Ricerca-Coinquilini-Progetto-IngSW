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
class Facolta extends ParametroRicercaCoinquilino{
    
    private ProfiloUtente.Facolta facolta;

    Facolta(int stelle,ProfiloUtente.Facolta facolta) {
        super(stelle);
        this.facolta=facolta;
    }

    @Override
    float calcolaAffinità(Utente utente) {
        if(utente.getDatiUtente().getFacolta()==this.facolta)
            return super.getStelle();
        else
            return 0;
         
        
    }
}
