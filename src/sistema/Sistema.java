/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import BusinessModel.BusinessModelAnnuncio;
import BusinessModel.BusinessModelUtente;
import RicercaAnnuncio.ContenitoreParametriAnnuncio;
import RicercaAnnuncio.RicercaAnnuncio;
import RicercaCoinquilino.ContenitoreParametriCoinquilino;
import RicercaCoinquilino.RicercaCoinquilino;
import Utenti.*;
import java.sql.SQLException;

/**
 *
 * @author Marco La Salvia
 */
public class Sistema {
    private WebSurfer webSurfer;
    private Guest guest;
    private User user;
    private ContenitoreParametriAnnuncio parametriAnnuncio;
    private RicercaAnnuncio ricercaAnnuncio;
    private ContenitoreParametriCoinquilino parametriCoinquilino;
    private RicercaCoinquilino ricercaCoinquilino;
    private BusinessModelUtente bmUtente;
    private BusinessModelAnnuncio bmAnnuncio;

    public Sistema() throws SQLException {
        this.webSurfer = new WebSurfer();
        this.bmUtente = new BusinessModelUtente();
        this.bmAnnuncio = new BusinessModelAnnuncio();
        this.guest = null;
        this.user = null;
        this.parametriAnnuncio = null;
        this.parametriCoinquilino = null;
        this.ricercaAnnuncio = null;
        this.ricercaCoinquilino = null;
    }
    public String logIn(String eMail, String password) throws SQLException{
        int result = this.bmUtente.login(eMail, password);
        if(result<0)
            return "DATI INSERITI NON CORRETTI!\n";
        return "LOGIN EFFETTUATO CON SUCCESSO!\n";
    }
    
    
    
    
    
    
}
