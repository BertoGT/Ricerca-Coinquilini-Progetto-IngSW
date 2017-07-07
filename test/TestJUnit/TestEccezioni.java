/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestJUnit;

import Casa.AnnuncioCasa;
import Casa.Citta;
import Casa.HouseGenerality;
import Exceptions.AnnuncioException;
import Exceptions.CameraNonTrovataException;
import ProfiloUtente.DatiUtente;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazione;
import ProfiloUtente.Occupazione;
import ProfiloUtente.ProfileManager;
import ProfiloUtente.Sesso;
import ProfiloUtente.Utente;
import java.text.ParseException;
import java.util.Date;
import org.junit.*;

/**
 *
 * @author Delbo
 */
public class TestEccezioni {
    
    @Test(expected=java.lang.Exception.class)
    public void TestEccezioneCreazioneAnnuncioProfileManager() throws AnnuncioException, ParseException{
        Date data = new Date();
        DatiUtente du = new DatiUtente("String", "String", Sesso.F, "String", "String", 0, 0, 0, "String",
                Nazione.AFRICA, Occupazione.ALTRO, Facolta.AGRARIA, false, false, false, Citta.AGRIGENTO, false);
        Utente u = new Utente(1, du);
        AnnuncioCasa a1= new AnnuncioCasa(1, "casa", 0, 0, "nomeCognomeProprietario", "cellulareProprietario", "emailProprietario", data);
        ProfileManager pm = new ProfileManager(u, a1);
        pm.creaAnnuncio("descrizioneAggiuntiva", 0, 0, "nomeCognomeProprietario", "cellulareProprietario", "emailProprietario");
   
    }
    
}
