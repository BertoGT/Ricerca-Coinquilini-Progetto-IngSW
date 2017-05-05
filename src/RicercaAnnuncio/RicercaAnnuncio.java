/*
 * Progetto Coinquilini
 * AA 2016/2017 * 
 */
package RicercaAnnuncio;

import Casa.AnnuncioCasa;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author alberto
 */
public class RicercaAnnuncio {

    private ArrayList<AnnuncioCasa> annunciTotali;
    private ArrayList<ParametroRicercaAnnuncio> parametriRicerca;
    private ArrayList<AnnuncioRisultante> annunciRisultanti;

    public RicercaAnnuncio(ArrayList<AnnuncioCasa> annunciTotali, ArrayList<ParametroRicercaAnnuncio> parametriRicerca) {
        this.annunciTotali = annunciTotali;
        this.parametriRicerca = parametriRicerca;
        this.annunciRisultanti = new ArrayList<AnnuncioRisultante>();
    }
   
    private void calcolaAffinita() {
        for (AnnuncioCasa annuncioCasa : annunciTotali) {
            float affinitaTotale = 0;
            int totaleStelle = 0;
            boolean annuncioIncompatibile = false;
            
            for (ParametroRicercaAnnuncio parametroRicerca : parametriRicerca) {
                totaleStelle += parametroRicerca.getStelle();
                float affinita = parametroRicerca.calcolaAffinità(annuncioCasa);
                if(affinita == -1) {
                    // annuncio da escludere ai risultati
                    annuncioIncompatibile = true;
                    break;                      
                } else 
                    affinitaTotale += affinita;
            }
            if(!annuncioIncompatibile) {
                float punteggio = affinitaTotale * 100 / totaleStelle;
                this.annunciRisultanti.add(new AnnuncioRisultante(annuncioCasa, punteggio));
            }      
        }
    }
    
    public ArrayList<AnnuncioRisultante> eseguiRicerca() {
        calcolaAffinita();
        Collections.sort(annunciRisultanti);
        return annunciRisultanti;
    } 
   
}
