/*
 * Progetto Coinquilini
 * AA 2016/2017 * 
 */
package RicercaAnnuncio;

import Casa.AnnuncioCasa;
import Casa.CameraDisponibile;
import java.util.ArrayList;

/**
 *
 * @author alberto
 */
public class RicercaAnnuncio {
    private ArrayList<AnnuncioCasa> annunciTotali;
    private ParametriRicercaAnnuncio par;

    public RicercaAnnuncio(ArrayList<AnnuncioCasa> annunciTotali, String citta, 
            int metriQuadriMin, int metriQuadriMax, int numeroLocaliMin, 
            int numeroLocaliMax, int numeroBagni, int postiLettoMax, int costoMax) {
        
        this.annunciTotali = annunciTotali;
        par = new ParametriRicercaAnnuncio(citta, metriQuadriMin, metriQuadriMax, 
                numeroLocaliMin, numeroLocaliMax, numeroBagni, postiLettoMax, costoMax);
    }
    
    public ArrayList<AnnuncioCasa> eseguiRicerca() {
        ArrayList<AnnuncioCasa> result = new ArrayList<>();
        for (AnnuncioCasa a : annunciTotali) {
            
            if(!par.getCitta().equals(a.getCitta()))
                break;
            
            if(a.getMetriQuadri() <= par.getMetriQuadriMin() && 
                    a.getMetriQuadri() >= par.getMetriQuadriMax())
                break;
            
            if(a.getnLocali() <= par.getNumeroLocaliMin() && 
                    a.getnLocali() >= par.getNumeroLocaliMax())
                break;
            
            if(a.getNumeroBagni() != par.getNumeroBagni())
                break;
            
            if(a.getCosto() >= par.getCostoMax())
                break;
            
            // Controllo che ci sia almeno una camera che soddisfa le richieste 
            ArrayList<CameraDisponibile> camera = a.getCamere();
            boolean flag = false;
            for (CameraDisponibile cameraDisponibile : camera) {
                if(cameraDisponibile.getPostiLettoDisponibili() > 0 &&
                        cameraDisponibile.getPostiLetto() <= par.getPostiLettoMax()) {
                    flag = true; // ho trovato almeno una camera che va bene
                    break;
                }
            }
            
            if(flag)
                result.add(a);
        }
        return result;
    }
    
    
    
    
}
