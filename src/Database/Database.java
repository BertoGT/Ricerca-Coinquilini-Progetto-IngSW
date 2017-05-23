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
    
    /* SEZIONE UTENTE */
    
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
            String nazionalita, String cittaDiRicerca, String numeroDiTelefono) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement(CostantiDB.inserisciAnagraficaUtente);
            ps.setInt(1, idUtente);
            ps.setString(2, nome);
            ps.setString(3, cognome);
            ps.setDate(4, dataNascita);
            ps.setString(5, sesso);
            ps.setString(6, nazionalita);
            ps.setString(7, cittaDiRicerca);
            ps.setString(8, numeroDiTelefono);
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
    
    
    /* SEZIONE ANNUNCI CASA */
    
    public int setAnnuncioCasa(int idCasa, int idUtenteProprietario, String descrizione, int costo) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.inserisciAnnuncioCasa);
        ps.setInt(1, idCasa);
        ps.setInt(2, idUtenteProprietario);
        ps.setString(3, descrizione);
        ps.setInt(4, costo);
        return ps.executeUpdate();
    }
    
    public int modificaAnnuncioCasa(int idAnnuncio, String descrizione, int costo) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.modificaAnnuncioCasa);
        ps.setString(1, descrizione);
        ps.setInt(2, costo);
        ps.setInt(3, idAnnuncio);
        return ps.executeUpdate();
    }

    public ResultSet setInfoCasa(int m2, int nLocali, int nBagni, int distanzaCentro, 
            String sessoCasa, boolean cucinaSeparata, String citta, String indirizzo) throws SQLException {
        
        PreparedStatement ps = conn.prepareStatement(CostantiDB.inserisciInfoCasa, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, m2);
        ps.setInt(2, nLocali);
        ps.setInt(3, nBagni);
        ps.setInt(4, distanzaCentro);
        ps.setString(5, sessoCasa);
        ps.setBoolean(6, cucinaSeparata);
        ps.setString(7, citta);
        ps.setString(8, indirizzo);
        ps.executeUpdate();
        return ps.getGeneratedKeys();
    }
    
    public int modificaInfoCasa(int idCasa, int m2, int nLocali, int nBagni, int distanzaCentro, 
            String sessoCasa, boolean cucinaSeparata, String citta, String indirizzo) throws SQLException {
        
        PreparedStatement ps = conn.prepareStatement(CostantiDB.modificaInfoCasa);
        ps.setInt(1, m2);
        ps.setInt(2, nLocali);
        ps.setInt(3, nBagni);
        ps.setInt(4, distanzaCentro);
        ps.setString(5, sessoCasa);
        ps.setBoolean(6, cucinaSeparata);
        ps.setString(7, citta);
        ps.setString(8, indirizzo);
        ps.setInt(9, idCasa);
        return ps.executeUpdate();
    }
    
    public int setCamera(int idCasa, int idCamera, int postiTotali, int postiDisponibili) throws SQLException {
        
        PreparedStatement ps = conn.prepareStatement(CostantiDB.inserisciCamera);
        ps.setInt(1, idCasa);
        ps.setInt(2, idCamera);
        ps.setInt(3, postiTotali);
        ps.setInt(4, postiDisponibili);
        return ps.executeUpdate();
    }
    
    public int modificaCamera(int idCasa, int idCamera, int postiTotali, int postiDisponibili) throws SQLException {
        
        PreparedStatement ps = conn.prepareStatement(CostantiDB.modificaCamera);       
        ps.setInt(1, postiTotali);
        ps.setInt(2, postiDisponibili);
        ps.setInt(3, idCasa);
        ps.setInt(4, idCamera);
        return ps.executeUpdate();
    }
    
    public int setElettrodomestico(int idCasa, String tipo) throws SQLException {
        
        PreparedStatement ps = conn.prepareStatement(CostantiDB.inserisciElettrodomestico);
        ps.setInt(1, idCasa);
        ps.setString(2, tipo);
        return ps.executeUpdate();
    }
    
    public int eliminaElettrodomestico(int idCasa, String tipo) throws SQLException {
        
        PreparedStatement ps = conn.prepareStatement(CostantiDB.eliminaElettrodomestico);       
        ps.setInt(1, idCasa);
        ps.setString(2, tipo);
        return ps.executeUpdate();
    }
    
    /* SEZIONE LETTURA ANNUNCI */
    
    public ResultSet getAnnunciInfoCasa(String citta, int costo) throws SQLException {
        
        PreparedStatement ps = conn.prepareStatement(CostantiDB.getAnnunciJoinInfoCasa);
        ps.setString(1, citta);
        ps.setInt(2, costo);
        return ps.executeQuery();   
    }
    
    public ResultSet getCamere(int idCasa) throws SQLException {
        
        PreparedStatement ps = conn.prepareStatement(CostantiDB.getCamere);
        ps.setInt(1, idCasa);
        return ps.executeQuery();
    }
    
    public ResultSet getElettrodomestici(int idCasa) throws SQLException {
        
        PreparedStatement ps = conn.prepareStatement(CostantiDB.getElettrodomestici);
        ps.setInt(1, idCasa);
        return ps.executeQuery();
    }
    
}
