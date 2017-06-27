/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaAnnuncio;

import Casa.AnnuncioCasa;
import Casa.HouseGenerality;

/**
 *
 * @author alberto
 */
class SessoCasa extends ParametroRicercaAnnuncio{
    private HouseGenerality sesso;

    SessoCasa(int stelle, HouseGenerality sesso) {
        super(stelle);
        this.sesso = sesso;
    }

    @Override
    float calcolaAffinità(AnnuncioCasa annuncio) {
        if(sesso == HouseGenerality.MISTA) {
            if(sesso == annuncio.getSessoCasa())
                return super.getStelle();
            else 
                return ((float)super.getStelle() / 2);
        } else {
            if(sesso == annuncio.getSessoCasa())
                return super.getStelle();
            else if(annuncio.getSessoCasa() == HouseGenerality.MISTA)
                return ((float)super.getStelle() / 2);
            else 
                return 0;
        } 
    }
    
}
