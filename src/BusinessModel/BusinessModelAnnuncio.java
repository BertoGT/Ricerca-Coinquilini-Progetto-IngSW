/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import Casa.AnnuncioCasa;
import Casa.CameraDisponibile;
import Casa.Citta;
import Casa.ElettroDomestico;
import Casa.HouseGenerality;
import Casa.InfoCasa;
import Database.Database;
import Exceptions.CameraNonInseritaException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alberto
 */
public class BusinessModelAnnuncio {
    private Database db;

    public BusinessModelAnnuncio() throws SQLException {
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
    
    public int inserisciInfoCasa(InfoCasa info) throws SQLException {
        // se crea l'infoCasa restituisce il suo id AI
        db.apriConnesione();
        ResultSet rs;
        rs = db.setInfoCasa(info.getMetriQuadri(), info.getnLocali(), info.getNumeroBagni(),
                info.getDistanzaCentro(), info.getSessoCasa().name(), info.isCucinaSeparata(),
                info.getCitta().name(), info.getIndirizzo());
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
    
    public boolean modificaInfoCasa(int idCasa, InfoCasa info) throws SQLException {
        
        db.apriConnesione();
        int result = db.modificaInfoCasa(idCasa, info.getMetriQuadri(), info.getnLocali(), info.getNumeroBagni(),
                info.getDistanzaCentro(), info.getSessoCasa().name(), info.isCucinaSeparata(),
                info.getCitta().name(), info.getIndirizzo());
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    public boolean inserisciCamera(int idCasa, CameraDisponibile camera) throws SQLException {
        
        db.apriConnesione();
        int result = db.setCamera(idCasa, camera.getIdCamera(), camera.getPostiLetto(), camera.getPostiLettoDisponibili());
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    public boolean modificaCamera(int idCasa, CameraDisponibile camera) throws SQLException {
        
        db.apriConnesione();
        int result = db.modificaCamera(idCasa, camera.getIdCamera(), camera.getPostiLetto(), camera.getPostiLettoDisponibili());
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    public boolean inserisciElettrodomestico(int idCasa, ElettroDomestico elettrodomestico) throws SQLException {
        
        db.apriConnesione();
        int result = db.setElettrodomestico(idCasa, elettrodomestico.name());
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    public boolean eliminaElettrodomestico(int idCasa, ElettroDomestico elettrodomestico) throws SQLException {
        
        db.apriConnesione();
        int result = db.eliminaElettrodomestico(idCasa, elettrodomestico.name());
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    /* SEZIONE LETTURA ANNUNCI */
    
    public ArrayList<AnnuncioCasa> getAnnunci(Citta citta, int costo) throws SQLException {
        
        ArrayList<AnnuncioCasa> annunci = new ArrayList<>();
        
        db.apriConnesione();
        ResultSet rs = db.getAnnunciInfoCasa(citta.name(), costo);
        while(rs.next()) {
            int idCasa = rs.getInt(1);
            AnnuncioCasa annuncio = new AnnuncioCasa(rs.getString(4), rs.getInt(2), rs.getInt(5),
                    rs.getString(16) +" "+rs.getString(17), rs.getString(18), rs.getString(15));
            annuncio.creaInfo(rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getBoolean(11),
                    Citta.valueOf(rs.getString(12)), rs.getString(13), HouseGenerality.valueOf(rs.getString(10)));
            ResultSet rCamere = db.getCamere(idCasa);
            while(rCamere.next()) {
                try {
                    annuncio.creaCamera(rCamere.getInt(2), rCamere.getInt(3), rCamere.getInt(4));
                } catch (CameraNonInseritaException ex) {
                    Logger.getLogger(BusinessModelAnnuncio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            ResultSet rElettro = db.getElettrodomestici(idCasa);
            while(rElettro.next()) {
                annuncio.creaElettrodomestico(ElettroDomestico.valueOf(rElettro.getString(1)));
            }
            annunci.add(annuncio);
        }
        
        return annunci;
    }
}
