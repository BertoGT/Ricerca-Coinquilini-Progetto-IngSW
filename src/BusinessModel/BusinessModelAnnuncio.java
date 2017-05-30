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
import Exceptions.NessunAnnuncioException;
import ProfiloUtente.DatiUtente;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazione;
import ProfiloUtente.Occupazione;
import ProfiloUtente.Sesso;
import ProfiloUtente.Utente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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
    
    public ArrayList<AnnuncioCasa> getAnnunciCase(Citta citta, int costo) throws SQLException, NessunAnnuncioException {
        
        ArrayList<AnnuncioCasa> annunci = new ArrayList<>();
        
        db.apriConnesione();
        int costoConTolleranza;
        if(costo == 0)
            costoConTolleranza = 0;
        else 
            costoConTolleranza = costo + 50;
        ResultSet rs = db.getAnnunciInfoCasa(citta.name(), costoConTolleranza);
        while(rs.next()) {
            int idCasa = rs.getInt(1);
            AnnuncioCasa annuncio = new AnnuncioCasa(rs.getString(4), rs.getInt(2), rs.getInt(5),
                    rs.getString(16) +" "+rs.getString(17), rs.getString(18), rs.getString(15));
            annuncio.creaInfo(rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getBoolean(11),
                    Citta.valueOf(rs.getString(12)), rs.getString(13), HouseGenerality.valueOf(rs.getString(10)));
            ResultSet rCamere = db.getCamere(idCasa);
            while(rCamere.next()) {
                annuncio.creaCamera(rCamere.getInt(3), rCamere.getInt(4));
                
            }
            ResultSet rElettro = db.getElettrodomestici(idCasa);
            while(rElettro.next()) {
                annuncio.creaElettrodomestico(ElettroDomestico.valueOf(rElettro.getString(1)));
            }
            annunci.add(annuncio);
        }
        if(annunci.isEmpty())
            throw new NessunAnnuncioException("Nessun annuncio soddisfa i criteri di ricerca inseriti");
        
        return annunci;
    }
    
    public ArrayList<Utente> getAnnunciCoinquilini(Citta citta, ProfiloUtente.Sesso sesso) throws SQLException, NessunAnnuncioException {
        try {
            ArrayList<Utente> annunciUtenti = new ArrayList<>();

            db.apriConnesione();
            String sessoString = null;
            if(sesso != null)
                sessoString = sesso.name();
            ResultSet rs = db.getAnnunciCoinquilini(citta.name(), sessoString);
            while(rs.next()) {
                int idCasa = rs.getInt(1);
                String[] data = rs.getString(4).split("-");
                DatiUtente dati = new DatiUtente(rs.getString(2), rs.getString(3), Sesso.valueOf(rs.getString(5)),
                        rs.getString(14), null, Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]), rs.getString(8), Nazione.valueOf(rs.getString(6)),
                        Occupazione.valueOf(rs.getString(12)), Facolta.valueOf(rs.getString(13)),rs.getBoolean(9), rs.getBoolean(10),
                        rs.getBoolean(11), Citta.valueOf(rs.getString(7)), true);
                Utente annuncio = new Utente(rs.getInt(1), dati);
                annunciUtenti.add(annuncio);
            }
            if(annunciUtenti.isEmpty())
                throw new NessunAnnuncioException("Nessun annuncio soddisfa i criteri di ricerca inseriti");

            return annunciUtenti;
        } catch (ParseException ex) {
            return null;
        }
    }
}
