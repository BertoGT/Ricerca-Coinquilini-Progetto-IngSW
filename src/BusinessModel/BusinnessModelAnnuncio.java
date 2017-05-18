/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import Casa.Citta;
import Casa.HouseGenerality;
import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alberto
 */
public class BusinnessModelAnnuncio {
    private Database db;

    public BusinnessModelAnnuncio() throws SQLException {
        db = new Database();
    }
    
    public boolean inserisciAnnuncioCasa(int idCasa, int idUtenteProprietario, 
            String descrizione, int costo) throws SQLException {
        
        db.apriConnesione();
        int result = db.setAnnuncioCasa(idCasa, idUtenteProprietario, descrizione, costo);
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    public boolean modificaAnnuncioCasa(int idAnnuncio, String descrizione, int costo) throws SQLException {
        db.apriConnesione();
        int result = db.modificaAnnuncioCasa(idAnnuncio, descrizione, costo);
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    public int inserisciInfoCasa(int m2, int nLocali, int nBagni, int distanzaCentro, 
            HouseGenerality sessoCasa, boolean cucinaSeparata, Citta citta, String indirizzo) throws SQLException {
        // se crea l'infoCasa restituisce il suo id AI
        db.apriConnesione();
        ResultSet rs;
        rs = db.setInfoCasa(m2, nLocali, nBagni, distanzaCentro, String.valueOf(sessoCasa), 
                cucinaSeparata, String.valueOf(citta), indirizzo);
        if(rs != null && rs.next()) {
            int result = rs.getInt(1);
            db.chiudiConnessione();
            return result;
        }
        else {
            db.chiudiConnessione();
            return -1;
        }
    }
    
    public boolean modificaInfoCasa(int idCasa, int m2, int nLocali, int nBagni, int distanzaCentro, 
            HouseGenerality sessoCasa, boolean cucinaSeparata, Citta citta, String indirizzo) throws SQLException {
        
        db.apriConnesione();
        int result = db.modificaInfoCasa(idCasa, m2, nLocali, nBagni, distanzaCentro, 
                String.valueOf(sessoCasa), cucinaSeparata, String.valueOf(citta), indirizzo);
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
}
