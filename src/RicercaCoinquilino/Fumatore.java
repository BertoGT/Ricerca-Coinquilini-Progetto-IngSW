/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import ProfiloUtente.DatiUtente;

/**
 *
 * @author Niko
 */
public class Fumatore extends ParametroRicercaCoinquilino{
    
    private boolean fumatore;

    public Fumatore(int stelle, boolean fumatore) {
        super(stelle);
        this.fumatore = fumatore;
    }

    @Override
    public float calcolaAffinità(DatiUtente utente) {
        if(fumatore)
            return super.getStelle();
        else {
            if(utente.isFumatore())
                return 0;
            else 
                return super.getStelle();
        }
    }
}
