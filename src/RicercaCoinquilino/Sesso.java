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
public class Sesso extends ParametroRicercaCoinquilino{

    private ProfiloUtente.Sesso sesso;
    
    public Sesso(ProfiloUtente.Sesso sesso) {
        super(0);
        this.sesso=sesso;
    }

    @Override
    public float calcolaAffinità(DatiUtente utente) {
        if(this.sesso==utente.getSesso())
            return super.getStelle();
        else
            return -1;
    }
    
}
