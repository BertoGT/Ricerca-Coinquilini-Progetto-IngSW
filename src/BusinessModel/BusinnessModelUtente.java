/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import Database.Database;
import ProfiloUtente.DatiUtente;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazionalita;
import ProfiloUtente.Occupazione;
import ProfiloUtente.Sesso;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Niko
 */
public class BusinnessModelUtente {
    private Database db;

    public BusinnessModelUtente() throws SQLException {
        db = new Database();
    }
    
    public int registrazione(String email, String password, boolean candidato) throws SQLException {
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
            return -1;
        }
    }
    
    public int login(String email, String password) throws SQLException {
        db.apriConnesione();
        ResultSet rs;
        rs = db.login(email, password);
        if(rs.next()) {
            int result = rs.getInt(1);
            db.chiudiConnessione();
            return result;
        }else {
            db.chiudiConnessione();
            return -1; // dati sbagliati
        }          
    }
    
    
    public boolean modificaPassword(int idUtente, String vecchiaPassword, String nuovaPassword) throws SQLException {
        db.apriConnesione();
        int result = db.modificaPassword(idUtente, vecchiaPassword, nuovaPassword);
        db.chiudiConnessione();
        if(result == 0) 
            return false; // modifica non avvenuta, vecchia password errata
        else 
            return true;
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
        // TODO conversione enum della cittaRicerca
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
    
    public boolean modificaCittaDiRicerca(int idUtente, String nuovaCitta) throws SQLException {
        db.apriConnesione();
        // TODO conversione enum della cittaRicerca
        int result = db.modificaCitaDiRicerca(idUtente, nuovaCitta);
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
}
