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
import Exceptions.NessunAnnuncioException;
import ProfiloUtente.DatiUtente;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazione;
import ProfiloUtente.Occupazione;
import ProfiloUtente.Sesso;
import ProfiloUtente.Utente;
import RicercaAnnuncio.AnnuncioRisultante;
import RicercaAnnuncio.ContenitoreParametriAnnuncio;
import RicercaAnnuncio.DistanzaCentro;
import RicercaAnnuncio.Elettrodomestico;
import RicercaAnnuncio.RicercaAnnuncio;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author cl428444
 */
public class TestAlgoritmoRicerca {
    
    @Test
    public void testCalcoloAffinitaElettrodomesticoAnnuncio() throws SQLException, NessunAnnuncioException{
        ArrayList<AnnuncioCasa> annunciTotali = new ArrayList<>();
        
        AnnuncioCasa annuncio1 = new AnnuncioCasa("casa arredata e vicino al centro", 1, 400, "Davide Delbo", "333333456", "delbus@gmail.com");
        AnnuncioCasa annuncio2 = new AnnuncioCasa("bella", 2, 600, "Albe Gt", "3459876", "albegt@gmail.com");
        AnnuncioCasa annuncio3 = new AnnuncioCasa("meno bella", 3, 500, "Nico Fari", "33845769", "nicofari@gmail.com");
        AnnuncioCasa annuncio4 = new AnnuncioCasa("piena di donne", 4, 700, "Marghe Rico", "37658445", "ricoz@gmail.com");
        annunciTotali.add(annuncio1);
        annunciTotali.add(annuncio2);
        annunciTotali.add(annuncio3);
        annunciTotali.add(annuncio4);
        
        annuncio1.creaInfo(50, 2, 1, 1000, false, Citta.PAVIA, "via ferri 4", HouseGenerality.MASCHI);
        annuncio2.creaInfo(80, 3, 2, 2000, true, Citta.MILANO, "via roma 56", HouseGenerality.MISTA);
        annuncio3.creaInfo(60, 2, 1, 1500, true, Citta.PAVIA, "via bollo 6", HouseGenerality.MASCHI);
        annuncio4.creaInfo(70, 3, 1, 3000, true, Citta.PAVIA, "via della spiga 23", HouseGenerality.FEMMINE);
        
        annuncio1.creaCamera(5, 4);
        annuncio2.creaCamera(3, 1);
        annuncio3.creaCamera(4, 4);
        annuncio4.creaCamera(2, 1);
        
        annuncio1.creaElettrodomestico(ElettroDomestico.LAVATRICE);
        annuncio2.creaElettrodomestico(ElettroDomestico.CONDIZIONATORE);
        annuncio3.creaElettrodomestico(ElettroDomestico.LAVASTOVIGLIE);
                
        Elettrodomestico elettro = new Elettrodomestico(4, ElettroDomestico.LAVATRICE);
        
        assertEquals(0, elettro.calcolaAffinità(annuncio4), 0);
        assertEquals(4, elettro.calcolaAffinità(annuncio1), 0);
    }
    
    @Test
    public void calcolaAffinitaFacoltaCoinquilino() throws ParseException{
        DatiUtente du1 = new DatiUtente("Niko", "Fari", Sesso.M, "nicofari@gmail.com", "ciao", 10, 03, 1995, "2345678", Nazione.ITALIA, Occupazione.STUDENTE, Facolta.INGEGNERIA, true, true, true, Citta.CATANZARO, true);
        DatiUtente du2 = new DatiUtente("Dade", "Del", Sesso.M, "dadedel@gmail.com", "ciao", 07, 11, 1995, "87654", Nazione.ITALIA, Occupazione.STUDENTE, Facolta.FARMACIA, true, false, true, Citta.PAVIA, true);
        DatiUtente du3 = new DatiUtente("ALBE", "Groppi", Sesso.M, "albegt@gmail.com", "zio", 8, 11, 1995, "32456789", Nazione.ITALIA, Occupazione.STUDENTE, Facolta.INGEGNERIA, false, true, true, Citta.PAVIA, true);
        
        Utente u1 = new Utente(1, du1);
        Utente u2 = new Utente(2, du2);
        Utente u3 = new Utente(3, du3);
        
        RicercaCoinquilino.Facolta f = new RicercaCoinquilino.Facolta(4, Facolta.INGEGNERIA);
        
        assertEquals(4, f.calcolaAffinità(u3), 0);
        assertEquals(0, f.calcolaAffinità(u2), 0);
        
    }
    
    @Test
    public void calcolaAffinitaDistanzaCentroAnnuncio(){
        AnnuncioCasa ann1 = new AnnuncioCasa("casa arredata e vicino al centro", 1, 400, "Davide Delbo", "333333456", "delbus@gmail.com");
        ann1.creaInfo(50, 2, 1, 1000, false, Citta.PAVIA, "via ferri 4", HouseGenerality.MASCHI);
    
        DistanzaCentro d=new DistanzaCentro(5,2000);
        assertEquals(2.5, d.calcolaAffinità(ann1), 0);
    }
}
