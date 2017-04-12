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

            if (!par.getCitta().equals(a.getCitta())) {
                break;
            }
            
            // se i m2 max sono 0, non inserisco il parametro, quindi salta il filtro
            if (par.getMetriQuadriMax() != 0 ) {
                if (a.getMetriQuadri() <= par.getMetriQuadriMin()
                        && a.getMetriQuadri() >= par.getMetriQuadriMax()) {
                    break;
                }
            }
            if(par.getNumeroLocaliMax() != 0) {
                if (a.getnLocali() <= par.getNumeroLocaliMin()
                        && a.getnLocali() >= par.getNumeroLocaliMax()) {
                    break;
                }
            }

            if(par.getNumeroBagni() != 0) {
                if (a.getNumeroBagni() != par.getNumeroBagni()) {
                    break;
                }
            }

            if(par.getCostoMax() != 0) { 
                if (a.getCosto() >= par.getCostoMax()) {
                    break;
                }
            }

            if(par.getPostiLettoMax() != 0) {
                // Controllo che ci sia almeno una camera che soddisfa le richieste 
                ArrayList<CameraDisponibile> camera = a.getCamere();
                boolean flag = false;
                for (CameraDisponibile cameraDisponibile : camera) {
                    if (cameraDisponibile.getPostiLettoDisponibili() > 0
                           && cameraDisponibile.getPostiLetto() <= par.getPostiLettoMax()) {
                        flag = true; // ho trovato almeno una camera che va bene
                        break;
                    }
                }

                if (flag) {
                    result.add(a);
                }
            } else 
                result.add(a);
        }
        return result;
    }

}
