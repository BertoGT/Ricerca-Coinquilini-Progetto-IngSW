/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaAnnuncio;

import Casa.AnnuncioCasa;

/**
 *
 * @author alberto
 */
public class NLocali extends ParametroRicercaAnnuncio{
    private int nLocali;

    public NLocali(int stelle, int nLocali) {
        super(stelle);
        this.nLocali = nLocali;
    }
   
    @Override
    public float calcolaAffinità(AnnuncioCasa annuncio) {
        if(nLocali > annuncio.getnLocali())
            return 0;
        else
            return super.getStelle();
    }
    
    
    
}
