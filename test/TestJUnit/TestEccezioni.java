/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestJUnit;

import Casa.AnnuncioCasa;
import Casa.Citta;
import Casa.ElettroDomestico;
import Casa.HouseGenerality;
import Exceptions.AnnuncioException;
import Exceptions.InserimentoAnnuncioNonRiuscito;
import ProfiloUtente.DatiUtente;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazione;
import ProfiloUtente.Occupazione;
import Sistema.ProfileManager;
import ProfiloUtente.Sesso;
import ProfiloUtente.Utente;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import org.junit.*;

/**
 *
 * @author Delbo
 */
public class TestEccezioni {
    
    @Test(expected=java.lang.Exception.class)
    public void TestEccezioneCreazioneAnnuncioProfileManager() throws ParseException, SQLException, InserimentoAnnuncioNonRiuscito, AnnuncioException{
        Date data = new Date();
        ArrayList<ElettroDomestico> elettrodomestici = new ArrayList<>();
        int [][] postiLettoDisponibili = new int[2][2];
        DatiUtente du = new DatiUtente("String", "String", Sesso.F, "String", "String", 0, 0, 0, "String",
                Nazione.AFRICA, Occupazione.ALTRO, Facolta.AGRARIA, false, false, false, Citta.AGRIGENTO, false);
        Utente u = new Utente(1, du);
        AnnuncioCasa a1= new AnnuncioCasa(1, "casa", 0, 0, "nomeCognomeProprietario", "cellulareProprietario", "emailProprietario", data);
        ProfileManager pm = new ProfileManager(u, a1);
        pm.creaAnnuncio(elettrodomestici, postiLettoDisponibili, 0, 0, 0, 0, 0, 0, true, Citta.CALTANISSETTA, "cittaIndirizzo", HouseGenerality.MASCHI, "descrizioneAggiuntiva");

    }
    
}
