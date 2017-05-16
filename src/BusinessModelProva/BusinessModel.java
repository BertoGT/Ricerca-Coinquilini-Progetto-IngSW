/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModelProva;

import Database.Database;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Niko
 */
public class BusinessModel {
    private Database db;

    public BusinessModel() throws SQLException {
        db = new Database();
    }
    
    public int registrazione(String email, String password, int power, boolean candidato) throws SQLException {
        db.apriConnesione();
        ResultSet rs;
        rs = db.registrazione(email, password, power, candidato);
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
        if(rs.next())
            return rs.getInt(1);
        else 
            return -1; // dati sbagliati
    }
    public boolean modificaPassword(int idUtente, String vecchiaPassword, String nuovaPassword) throws SQLException {
        db.apriConnesione();
        int result = db.modificaPassword(idUtente, vecchiaPassword, nuovaPassword);
        if(result == 0) 
            return false; // modifica non avvenuta, vecchia password errata
        else 
            return true;
    }
    
    public boolean setCandidatura(int idUtente, boolean candidatura) throws SQLException {
        db.apriConnesione();
        int result = db.setCandidatura(idUtente, candidatura);
        if(result == 0) 
            return false;
        else 
            return true;
    }
    public boolean inserisciAnagraficaUtente(int idUtente, String nome, String cognome, int giorno, int mese, int anno, String sesso, 
            String nazionalita, String cittaDiRicerca) throws SQLException {
        db.apriConnesione();
        Calendar c = Calendar.getInstance();
        c.set(anno, mese-1, giorno);
        Date data = new Date(c.getTimeInMillis());
        int result = db.setDatiAnagrafici(idUtente, nome, cognome, data, sesso, nazionalita, cittaDiRicerca);
        if(result == 0)
            return false;
        else
            return true;
    }
    
    public boolean modificaCittaDiRicerca(int idUtente, String nuovaCitta) throws SQLException {
        db.apriConnesione();
        int result = db.modificaCitaDiRicerca(idUtente, nuovaCitta);
        if(result == 0) 
            return false;
        else 
            return true;
    }
}
