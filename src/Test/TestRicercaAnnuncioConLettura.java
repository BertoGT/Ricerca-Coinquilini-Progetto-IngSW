/*
 * Progetto Coinquilini
 * AA 2016/2017 * 
 */
package Test;

import Casa.AnnuncioCasa;
import Casa.HouseGender;
import Lettori.LeggiAnnunci;
import RicercaAnnuncio.AnnuncioRisultante;
import RicercaAnnuncio.ContenitoreParametriAnnuncio;
import RicercaAnnuncio.RicercaAnnuncio;
import java.util.ArrayList;

/**
 *
 * @author alberto
 */
public class TestRicercaAnnuncioConLettura {
    public static void main(String[] args) {
        LeggiAnnunci lettore = new LeggiAnnunci();
        lettore.leggiFile();
        ArrayList<AnnuncioCasa> annunciTotali = lettore.getAnnunci();
        
        ContenitoreParametriAnnuncio c = new ContenitoreParametriAnnuncio();
        c.setParametroCitta("Pavia");
        c.setParametroCosto(600);
        c.setParametroCucina(2, true);
        c.setParametroDistCentro(5, 2000);
        c.setParametroNBagni(4, 1);
        c.setParametroNLocali(2, 2);
        c.setParametroSessoCasa(1, HouseGender.MISTA);
        c.setParametroTipoCamera(4, 1);
        
        RicercaAnnuncio r = new RicercaAnnuncio(annunciTotali, c.getParametriRicerca());
        
        ArrayList<AnnuncioRisultante> a = r.eseguiRicerca(); 
        
        for (AnnuncioRisultante annuncioRisultante : a) {
            System.out.println(annuncioRisultante.toString());
        }
    }
    

}
