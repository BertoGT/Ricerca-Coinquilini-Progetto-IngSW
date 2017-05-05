/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaAnnuncio;

import Casa.AnnuncioCasa;
import Casa.HouseGender;

/**
 *
 * @author alberto
 */
public class SessoCasa extends ParametroRicercaAnnuncio{
    private HouseGender sesso;

    public SessoCasa(int stelle, HouseGender sesso) {
        super(stelle);
        this.sesso = sesso;
    }

    @Override
    public float calcolaAffinità(AnnuncioCasa annuncio) {
        if(sesso == HouseGender.MISTA) {
            if(sesso == annuncio.getSessoCasa())
                return super.getStelle();
            else 
                return ((float)super.getStelle() / 2);
        } else {
            if(sesso == annuncio.getSessoCasa())
                return super.getStelle();
            else if(annuncio.getSessoCasa() == HouseGender.MISTA)
                return ((float)super.getStelle() / 2);
            else 
                return 0;
        } 
    }
    
}
