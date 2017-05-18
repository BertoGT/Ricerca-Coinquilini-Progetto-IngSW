/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import Database.Database;
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
    
    
    public boolean inserisciAnagraficaUtente(int idUtente, String nome, String cognome, int giorno, int mese, int anno, Sesso sesso, 
            Nazionalita nazionalita, String cittaDiRicerca) throws SQLException {
        // TODO conversione enum della cittaRicerca
        db.apriConnesione();
        Calendar c = Calendar.getInstance();
        c.set(anno, mese-1, giorno);
        Date data = new Date(c.getTimeInMillis());
        int result = db.setDatiAnagrafici(idUtente, nome, cognome, data, String.valueOf(sesso),
                String.valueOf(nazionalita), cittaDiRicerca);
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
    
    public boolean inserisciInfoUtente(int idUtente, boolean fumatore, boolean cuoco, 
            boolean sportivo, Occupazione occupazione, Facolta facolta) throws SQLException {
        db.apriConnesione();
        
        String fac;
        if(occupazione == Occupazione.STUDENTE)
            fac = String.valueOf(facolta);
        else 
            fac = null;
        
        int result = db.setInfoUtente(idUtente, fumatore, cuoco, sportivo, 
                String.valueOf(occupazione), fac);
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    public boolean modificaInfoUtente(int idUtente, boolean fumatore, boolean cuoco, boolean sportivo,
            Occupazione occupazione, Facolta facolta) throws SQLException {
        db.apriConnesione();
        
        String fac;
        if(occupazione == Occupazione.STUDENTE)
            fac = String.valueOf(facolta);
        else 
            fac = null;
        
        int result = db.modificaInfoUtente(idUtente, fumatore, cuoco, sportivo,
                String.valueOf(occupazione), fac);
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
}
