/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import ProfiloUtente.DatiUtente;
import RicercaCoinquilino.ParametroRicercaCoinquilino;

/**
 *
 * @author Delbo
 */
public class Sportivo extends ParametroRicercaCoinquilino{

    private boolean sportivo;
    
    public Sportivo(int stelle,boolean sportivo) {
        super(stelle);
        this.sportivo=sportivo;
    }

    @Override
    public float calcolaAffinità(DatiUtente utente) {
        if(this.sportivo){
            if(utente.isSportivo())
                return super.getStelle();
            else
                return 0;
        } else {
            if(!utente.isSportivo())
                return super.getStelle();
            else
                return 0;
        }
    }
}
