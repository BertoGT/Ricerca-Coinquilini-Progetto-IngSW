/*
 * Progetto Coinquilini
 * AA 2016/2017 * 
 */
package RicercaAnnuncio;

import BusinessModel.BusinessModelAnnuncio;
import Casa.AnnuncioCasa;
import Casa.Citta;
import Exceptions.NessunAnnuncioException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author alberto
 */
public class RicercaAnnuncio {

    private ArrayList<AnnuncioCasa> annunciTotali;
    private ContenitoreParametriAnnuncio parametriRicerca;
    private ArrayList<AnnuncioRisultante> annunciRisultanti;

    /**
     * Istanzia un oggetto che esegue la ricerca di un annuncio casa
     * @param parametriRicerca contenitore dei parametri di ricerca
     * @throws SQLException
     * @throws NessunAnnuncioException se nessun annuncio soddisfa i criteri di ricerca inseriti
     */
    public RicercaAnnuncio(ContenitoreParametriAnnuncio parametriRicerca) throws SQLException, NessunAnnuncioException {
        this.parametriRicerca = parametriRicerca;
        this.annunciRisultanti = new ArrayList<>();
        caricaAnnunciTotali();
    }
   
    private void calcolaAffinita() {
        for (AnnuncioCasa annuncioCasa : annunciTotali) {
            if(annuncioCasa==null)
                break;
            float affinitaTotale = 0;
            int totaleStelle = 0;
            boolean annuncioIncompatibile = false;
            for (ParametroRicercaAnnuncio parametroRicerca : parametriRicerca.getParametriRicerca()) {
                if(parametroRicerca==null)
                    break;
                totaleStelle += parametroRicerca.getStelle();
                float affinita = parametroRicerca.calcolaAffinità(annuncioCasa);
                if(affinita == -1) {                   // annuncio da escludere ai risultati
                    annuncioIncompatibile = true;
                    break;                      
                } else 
                    affinitaTotale += affinita;
            }
            if(!annuncioIncompatibile) {
                if(totaleStelle==0) {
                    this.annunciRisultanti.add(new AnnuncioRisultante(annuncioCasa, 100));
                } else {
                    float punteggio = affinitaTotale * 100 / totaleStelle;
                    this.annunciRisultanti.add(new AnnuncioRisultante(annuncioCasa, punteggio));
                }
            }       
        }
    }
    
    /**
     * Esegue la ricerca degli annunci compatibili e li ordina per punteggio (affinità) descrescente
     * @return Lista degli annunci compatibili ordinati per affinita descrescente
     */
    public ArrayList<AnnuncioRisultante> eseguiRicerca() {
        
        calcolaAffinita();
        Collections.sort(annunciRisultanti);
        return annunciRisultanti;
    } 
    
    private void caricaAnnunciTotali() throws SQLException, NessunAnnuncioException {
        BusinessModelAnnuncio bm = BusinessModelAnnuncio.getInstance();
        annunciTotali = bm.getAnnunciCase(parametriRicerca.getCitta(), parametriRicerca.getCostoMax());
    }
   
}
