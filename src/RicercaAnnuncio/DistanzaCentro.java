/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaAnnuncio;

import Casa.AnnuncioCasa;

/**
 *
 * @author Delbo
 */
public class DistanzaCentro extends ParametroRicercaAnnuncio {

    private int distanzaMax;
    
    public DistanzaCentro(int stelle,int distanzaMax) {
        super(stelle);
        this.distanzaMax=distanzaMax;
    }

    @Override
    public float calcolaAffinità(AnnuncioCasa annuncio) {
        if(this.distanzaMax<annuncio.getDistanzaCentro()) 
            return 0;
        
        else if(annuncio.getDistanzaCentro()==0)
            return super.getStelle();
        else
            return super.getStelle() - 
                    ( ( (float) super.getStelle()*annuncio.getDistanzaCentro()) / distanzaMax);
        
    }
    
}
