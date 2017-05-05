
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
public class Eta extends ParametroRicercaCoinquilino{
    
    private int etaMin, etaMax;

    public Eta(int stelle, int etaMin, int etaMax) {
        super(stelle);
        this.etaMin = etaMin;
        this.etaMax = etaMax;
    }

    @Override
    public float calcolaAffinità(DatiUtente utente) {
        if(utente.getEta() <= etaMax && utente.getEta() >= etaMin)
            return super.getStelle();
        else 
            return 0;
    }
    
}
