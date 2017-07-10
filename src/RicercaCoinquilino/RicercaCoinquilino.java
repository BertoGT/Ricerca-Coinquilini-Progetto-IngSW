/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import BusinessModel.BusinessModelAnnuncio;
import Exceptions.NessunAnnuncioException;
import ProfiloUtente.Utente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Delbo
 */
public class RicercaCoinquilino {
    
    private ArrayList<Utente> utentiTotali;
    private ContenitoreParametriCoinquilino parametriRicerca;
    private ArrayList<CoinquilinoRisultante> coinquiliniRisultanti;

    /**
     * Istanzia un oggetto che esegue la ricerca di un potenziale coinquilino
     * @param parametriRicerca contenitore dei parametri di ricerca
     * @throws SQLException
     * @throws NessunAnnuncioException se nessun coinquilino soddisfa i criteri di ricerca inseriti
     */
    public RicercaCoinquilino(ContenitoreParametriCoinquilino parametriRicerca) throws SQLException, NessunAnnuncioException {
        this.parametriRicerca = parametriRicerca;
        this.coinquiliniRisultanti= new ArrayList<>();
        caricaDati();
    }
    
    private void calcolaAffinita() {
        for (Utente dati : utentiTotali) {
            if(dati==null)
                break;
            float affinitaTotale = 0;
            int totaleStelle = 0;
            boolean coinquilinoIncompatibile = false;
            
            for (ParametroRicercaCoinquilino parametroRicerca : parametriRicerca.getParametri()) {
                if(parametroRicerca==null)
                    break;
                totaleStelle += parametroRicerca.getStelle();
                float affinita = parametroRicerca.calcolaAffinità(dati);
                if(affinita == -1) {
                    // coinquilino da escludere ai risultati
                    coinquilinoIncompatibile = true;
                    break;                      
                } else 
                    affinitaTotale += affinita;
            }
            if(!coinquilinoIncompatibile) {
                if(totaleStelle == 0) {
                    this.coinquiliniRisultanti.add(new CoinquilinoRisultante(dati, 100));
                } else {
                    float punteggio = affinitaTotale * 100 / totaleStelle;
                    this.coinquiliniRisultanti.add(new CoinquilinoRisultante(dati, punteggio));
                }
            }      
        }
    }
    
    /**
     * Esegue la ricerca degi coinquilini e li ordina per punteggio (affinità) descrescente
     * @return Lista di potenziali conquilino compatibili ordinati per affinita descrescente
     */
    public ArrayList<CoinquilinoRisultante> eseguiRicerca() {
        calcolaAffinita();
        Collections.sort(coinquiliniRisultanti);
        return coinquiliniRisultanti;
    } 
    
    private void caricaDati() throws SQLException, NessunAnnuncioException{
        BusinessModelAnnuncio bm = BusinessModelAnnuncio.getInstance();
        utentiTotali = bm.getAnnunciCoinquilini(parametriRicerca.getCittaDiRicerca(), 
                parametriRicerca.getSesso());
    }
    
    
    
}
