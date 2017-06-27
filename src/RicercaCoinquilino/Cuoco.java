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
    
    Cuoco(int stelle,boolean cuoco) {
        super(stelle);
        this.cuoco=cuoco;
    }

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
