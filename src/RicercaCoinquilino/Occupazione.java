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
public class Occupazione extends ParametroRicercaCoinquilino{

    private ProfiloUtente.Occupazione occupazione;
    
    public Occupazione(int stelle,ProfiloUtente.Occupazione occupazione) {
        super(stelle);
        this.occupazione=occupazione;
    }

    @Override
    public float calcolaAffinità(Utente utente) {
        if(utente.getDatiUtente().getOccupazione()==this.occupazione)
            return super.getStelle();
        else
            return 0;
    }
    
}
