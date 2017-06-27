
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import ProfiloUtente.DatiUtente;
import ProfiloUtente.Utente;

/**
 *
 * @author Niko
 */
class Eta extends ParametroRicercaCoinquilino{
    
    private int etaMin, etaMax;

    Eta(int stelle, int etaMin, int etaMax) {
        super(stelle);
        this.etaMin = etaMin;
        this.etaMax = etaMax;
    }

    @Override
    float calcolaAffinità(Utente utente) {
        if(utente.getDatiUtente().getDataDiNascita().getEta() <= etaMax && utente.getDatiUtente().getDataDiNascita().getEta() >= etaMin)
            return super.getStelle();
        else 
            return 0;
    }
    
}
