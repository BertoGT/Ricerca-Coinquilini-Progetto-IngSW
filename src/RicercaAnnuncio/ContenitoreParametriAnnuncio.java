
/*
 * Progetto Coinquilini
 * AA 2016/2017 * 
 */
package RicercaAnnuncio;

import Casa.Citta;
import Casa.HouseGenerality;
import java.util.ArrayList;

/**
 *
 * @author alberto
 */
public class ContenitoreParametriAnnuncio {
    private ArrayList<ParametroRicercaAnnuncio> parametriRicerca;

    public ContenitoreParametriAnnuncio() {
        parametriRicerca = new ArrayList<ParametroRicercaAnnuncio>();
    }
    
    public void setParametroCucina(int stelle, boolean cucinaSeparata) {
        parametriRicerca.add(new Cucina(stelle, cucinaSeparata));
    }
    
    public void setParametroDistCentro(int stelle, int distanzaMax) {
        parametriRicerca.add(new DistanzaCentro(stelle, distanzaMax));
    }
    
    public void setParametroNLocali(int stelle, int nLocali) {
        parametriRicerca.add(new NLocali(stelle, nLocali));
    }
    
    public void setParametroNBagni(int stelle, int nBagni) {
        parametriRicerca.add(new NumeroBagni(stelle, nBagni));
    }
    
    public void setParametroSessoCasa(int stelle, HouseGenerality sesso) {
        parametriRicerca.add(new SessoCasa(stelle, sesso));
    }
    
    public void setParametroTipoCamera(int stelle, int postiLetto) {
        parametriRicerca.add(new TipoCamera(stelle, postiLetto));
    }

    public ArrayList<ParametroRicercaAnnuncio> getParametriRicerca() {
        return parametriRicerca;
    }
    
    
}
