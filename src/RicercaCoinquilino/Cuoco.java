/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import ProfiloUtente.DatiUtente;

/**
 *
 * @author Delbo
 */
public class Cuoco extends ParametroRicercaCoinquilino {

    private boolean cuoco;
    
    public Cuoco(int stelle,boolean cuoco) {
        super(stelle);
        this.cuoco=cuoco;
    }

    @Override
    public float calcolaAffinità(DatiUtente utente) {
        if(this.cuoco){
            if(utente.isCuoco())
                return super.getStelle();
            else
                return 0;
        } else
            return super.getStelle();
    }          
    
}
