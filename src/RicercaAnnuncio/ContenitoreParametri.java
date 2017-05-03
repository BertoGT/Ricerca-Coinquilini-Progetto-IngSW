
/*
 * Progetto Coinquilini
 * AA 2016/2017 * 
 */
package RicercaAnnuncio;

import Casa.HouseGender;
import java.util.ArrayList;

/**
 *
 * @author alberto
 */
public class ContenitoreParametri {
    private ArrayList<ParametroRicercaAnnuncio> parametriRicerca;

    public ContenitoreParametri() {
        parametriRicerca = new ArrayList<ParametroRicercaAnnuncio>();
    }
    
    public void setParametroCitta(String nome) {
        parametriRicerca.add(new Città(nome));
    }
    
    public void setParametroCosto(int stelle, int costoMax) {
        parametriRicerca.add(new Costo(stelle, costoMax));
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
    
    public void setParametroSessoCasa(int stelle, HouseGender sesso) {
        parametriRicerca.add(new SessoCasa(stelle, sesso));
    }
    
    public void setParametroTipoCamera(int stelle, int postiLetto) {
        parametriRicerca.add(new TipoCamera(stelle, postiLetto));
    }

    public ArrayList<ParametroRicercaAnnuncio> getParametriRicerca() {
        return parametriRicerca;
    }
    
    
}
