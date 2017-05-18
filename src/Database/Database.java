/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niko
 */
public class Database {
    
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private Connection conn;

    public Connection getConn() {
        return conn;
    }
    
    public void apriConnesione() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(CostantiDB.URL_DB, CostantiDB.username, CostantiDB.password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    public void chiudiConnessione() throws SQLException{
        conn.close();
    }
    
    public ResultSet registrazione(String email, String password, int power, boolean candidato) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.verificaEmail);
        ps.setString(1, email);
        if(ps.executeQuery().next())
            return null; // email già in uso
        else {
            ps = conn.prepareStatement(CostantiDB.registraUtente, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setInt(3, power);
            ps.setBoolean(4, candidato);
            ps.executeUpdate();
            return ps.getGeneratedKeys();
        }
    }
    
    public ResultSet login(String email, String password) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.loggaUtente);
        ps.setString(1, email);
        ps.setString(2, password);
        return ps.executeQuery();
    }
    
    public int modificaPassword(int idUtente, String vecchiaPassword, String nuovaPassword) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.loggaConId);
        ps.setInt(1, idUtente);
        ps.setString(2, vecchiaPassword);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) { // dati giusti si procede con la modifica della password
            ps = conn.prepareStatement(CostantiDB.modificaPassword);
            ps.setString(1, nuovaPassword);
            ps.setInt(2, idUtente);
            return ps.executeUpdate();
        } else 
            return 0;
    }
    
    public int setCandidatura(int idUtente, boolean candidatura) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.setCandidatura);
        ps.setBoolean(1, candidatura);
        ps.setInt(2, idUtente);
        return ps.executeUpdate();
    }
    
    public int setDatiAnagrafici(int idUtente, String nome, String cognome, Date dataNascita, String sesso, 
            String nazionalita, String cittaDiRicerca) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement(CostantiDB.inserisciAnagraficaUtente);
            ps.setInt(1, idUtente);
            ps.setString(2, nome);
            ps.setString(3, cognome);
            ps.setDate(4, dataNascita);
            ps.setString(5, sesso);
            ps.setString(6, nazionalita);
            ps.setString(7, cittaDiRicerca);
            return ps.executeUpdate();
        } catch(MySQLIntegrityConstraintViolationException e) {
            return 0;
        }
    }
    
    public int modificaCitaDiRicerca(int idUtente, String nuovaCitta) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.modificaCittaRicerca);
        ps.setString(1, nuovaCitta);
        ps.setInt(2, idUtente);
        return ps.executeUpdate();
    }
    
    public int setInfoUtente(int idUtente, boolean fumatore, boolean cuoco, boolean sportivo,
            String occupazione, String facolta) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement(CostantiDB.inserisciInfoUtente);
            ps.setInt(1, idUtente);
            ps.setBoolean(2, fumatore);
            ps.setBoolean(3, cuoco);
            ps.setBoolean(4, sportivo);
            ps.setString(5, occupazione);
            ps.setString(6, facolta);
            return ps.executeUpdate();
        } catch(MySQLIntegrityConstraintViolationException e) {
            return 0;
        }
    }
    
    public int modificaInfoUtente(int idUtente, boolean fumatore, boolean cuoco, boolean sportivo,
            String occupazione, String facolta) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.modificaInfoUtente);
        ps.setBoolean(1, fumatore);
        ps.setBoolean(2, cuoco);
        ps.setBoolean(3, sportivo);
        ps.setString(4, occupazione);
        ps.setString(5, facolta);
        ps.setInt(6, idUtente);
        return ps.executeUpdate();
    }
}
