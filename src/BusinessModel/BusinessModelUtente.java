/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import Casa.AnnuncioCasa;
import Casa.Citta;
import Casa.ElettroDomestico;
import Casa.HouseGenerality;
import Database.Database;
import Exceptions.CameraNonInseritaException;
import Exceptions.LoginException;
import Exceptions.NessunAnnuncioException;
import Exceptions.PasswordException;
import Exceptions.RegistrazioneException;
import ProfiloUtente.DatiUtente;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazione;
import ProfiloUtente.Occupazione;
import ProfiloUtente.Sesso;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niko
 */
public class BusinessModelUtente {
    private Database db;

    public BusinessModelUtente() throws SQLException {
        db = new Database();
    }
    
    public int registrazione(String email, String password, boolean candidato) throws SQLException, RegistrazioneException {
        db.apriConnesione();
        ResultSet rs;
        rs = db.registrazione(email, password, 1, candidato);
        if(rs != null && rs.next()) {
            int result = rs.getInt(1);
            db.chiudiConnessione();
            return result;
        }
        else {
            db.chiudiConnessione();
            throw new RegistrazioneException("Email gia in uso");
        }
    }
    
    public int login(String email, String password) throws SQLException, LoginException {
        db.apriConnesione();
        ResultSet rs;
        rs = db.login(email, password);
        if(rs.next()) {
            int result = rs.getInt(1);
            db.chiudiConnessione();
            return result;
        }else {
            db.chiudiConnessione();
            throw new LoginException("Email o password errata"); // dati sbagliati
        }          
    }
    
    
    public void modificaPassword(int idUtente, String vecchiaPassword, String nuovaPassword) throws SQLException, PasswordException {
        db.apriConnesione();
        int result = db.modificaPassword(idUtente, vecchiaPassword, nuovaPassword);
        db.chiudiConnessione();
        if(result == 0) 
            throw new PasswordException("Vecchia password errata"); // modifica non avvenuta, vecchia password errata
    }
    
    public boolean setCandidatura(int idUtente, boolean candidatura) throws SQLException {
        db.apriConnesione();
        int result = db.setCandidatura(idUtente, candidatura);
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    
    public boolean inserisciAnagraficaUtente(int idUtente, DatiUtente dati) throws SQLException {
        db.apriConnesione();
        Calendar c = Calendar.getInstance();
        c.set(dati.getDataDiNascita().getAnno(), dati.getDataDiNascita().getMese()-1, dati.getDataDiNascita().getGiorno());
        Date data = new Date(c.getTimeInMillis());
        int result = db.setDatiAnagrafici(idUtente, dati.getNome(), dati.getCognome(), data, dati.getSesso().name(),
                dati.getNazionalita().name(), dati.getCittaDiRicerca().name(), dati.getNumeroDiTelefono());
        db.chiudiConnessione();
        if(result == 0)
            return false;
        else
            return true;
    }
    
    public boolean modificaCittaDiRicerca(int idUtente, Citta nuovaCitta) throws SQLException {
        db.apriConnesione();
        int result = db.modificaCitaDiRicerca(idUtente, nuovaCitta.name());
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    public boolean inserisciInfoUtente(int idUtente, DatiUtente dati) throws SQLException {
        db.apriConnesione();
        
        String fac;
        if(dati.getOccupazione() == Occupazione.STUDENTE)
            fac = dati.getFacolta().name();
        else 
            fac = null;
        
        int result = db.setInfoUtente(idUtente, dati.isFumatore(), dati.isCuoco(), dati.isSportivo(), 
                dati.getOccupazione().name(), fac);
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    public boolean modificaInfoUtente(int idUtente, DatiUtente dati) throws SQLException {
        db.apriConnesione();
        
        String fac;
        if(dati.getOccupazione() == Occupazione.STUDENTE)
            fac = dati.getFacolta().name();
        else 
            fac = null;
        
        int result = db.modificaInfoUtente(idUtente, dati.isFumatore(), dati.isCuoco(), dati.isSportivo(), 
                dati.getOccupazione().name(), fac);
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    public AnnuncioCasa getAnnuncioUtente(int idUtenteProprietario) throws SQLException, NessunAnnuncioException {
        
        db.apriConnesione();
        ResultSet rs = db.getAnnuncioUtente(idUtenteProprietario);
        if(rs.next()) {
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
            return annuncio;
        } else
            throw new NessunAnnuncioException("Nessun annuncio creato");
    }
}
