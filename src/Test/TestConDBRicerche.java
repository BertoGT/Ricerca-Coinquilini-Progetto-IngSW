/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import BusinessModel.BusinessModelAnnuncio;
import Casa.AnnuncioCasa;
import Casa.Citta;
import Casa.HouseGenerality;
import Exceptions.CameraNonInseritaException;
import RicercaAnnuncio.AnnuncioRisultante;
import RicercaAnnuncio.ContenitoreParametriAnnuncio;
import RicercaAnnuncio.RicercaAnnuncio;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cl428444
 */
public class TestConDBRicerche {
    
    public static void main(String[] args) throws SQLException, CameraNonInseritaException {
        
        ContenitoreParametriAnnuncio c = new ContenitoreParametriAnnuncio();
        c.setParametroCucina(5, true);
        c.setParametroNBagni(1, 3);
        c.setParametroNLocali(5, 2);
        c.setParametroSessoCasa(4, HouseGenerality.MISTA);
        c.setParametroTipoCamera(3, 1);
        c.setParametroDistCentro(2, 1000);
        ArrayList<AnnuncioRisultante> annunciRisultanti = new RicercaAnnuncio(c, Citta.PAVIA, 340).eseguiRicerca();
        
        System.out.println("cioao");
    }
}
