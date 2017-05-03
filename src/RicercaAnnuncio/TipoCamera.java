/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaAnnuncio;

import Casa.AnnuncioCasa;
import Casa.CameraDisponibile;

/**
 *
 * @author alberto
 */
public class TipoCamera extends ParametroRicercaAnnuncio{
    private int postiLetto;

    public TipoCamera(int stelle, int postiLetto) {
        super(stelle);
        this.postiLetto = postiLetto;
    }

    @Override
    public float calcolaAffinità(AnnuncioCasa annuncio) {
        float stelleTemporanee = 0;
        // TODO le case senza posti disponibili le eliminiamo dalla ricerca 
        // prima di mandarla??
        for (CameraDisponibile camera : annuncio.getCamere()) {
            if(camera.getPostiLettoDisponibili() != 0) {
                if(postiLetto == camera.getPostiLetto()) {
                    stelleTemporanee = super.getStelle();
                    break;
                } else if(postiLetto > camera.getPostiLetto()) {
                    stelleTemporanee = ((float) super.getStelle() / 2);
                }
            }
        }
        return stelleTemporanee;                 
    }
    
}
