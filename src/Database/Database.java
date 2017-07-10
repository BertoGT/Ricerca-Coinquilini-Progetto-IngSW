/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
    
    private static Database instance;
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private Connection conn;
    
    private Database(){};
    /**
     * 
     * @return  Ritorna l'istanza del database appena creata.
     */
    public static Database getInstance() {
      if(instance == null) {
         instance = new Database();
      }
      return instance;
   }
    /**
     * 
     * @return Ritorna la connessione al database.
     */
    public Connection getConn() {
        return conn;
    }
    /**
     * Metodo che apre la connessione al database.
     * @throws SQLException 
     */
    public void apriConnesione() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(CostantiDB.URL_DB, CostantiDB.username, CostantiDB.password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    /**
     * Metodo che chiude la connessione verso il database
     * @throws SQLException 
     */
    public void chiudiConnessione() throws SQLException{
        conn.close();
    }
    
    /* SEZIONE UTENTE */
    /**
     * 
     * @param email Email dell'utente che vuole registrarsi.
     * @param password Password scelta dall'utente che vuole registrarsi.
     * @param power Indica i permessi dell'utente all'interno del sistema.
     * @param candidato Indica che l'utente si candida come potenziale coinquilino.
     * @return Ritorna un oggetto ResultSet.
     * @throws SQLException 
     */
    public ResultSet registrazione(String email, String password, int power, boolean candidato) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.verificaEmail);
        ps.setString(1, email);
        if(ps.executeQuery().next())
            return null; // email già in uso
        else {
            try {
                ps = conn.prepareStatement(CostantiDB.registraUtente, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, email);
                ps.setString(2, ConvertitoreSha.SHA1(password));
                ps.setInt(3, power);
                ps.setBoolean(4, candidato);
                ps.executeUpdate();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ps.getGeneratedKeys();
        }
    }
    /**
     * 
     * @param email Email dell'utente che vuole registrarsi.
     * @param password Password scelta dall'utente che vuole registrarsi.
     * @return Ritorna un oggetto ResultSet.
     * @throws SQLException 
     */
    public ResultSet login(String email, String password) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.loggaUtente);
        try{
            ps.setString(1, email);
            ps.setString(2, ConvertitoreSha.SHA1(password));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps.executeQuery();
    }
    /**
     * 
     * @param idUtente Id univoco dell'utente all'interno del db.
     * @param vecchiaPassword Password precedente che si desidera cambiare.
     * @param nuovaPassword Nuova password con la quale si vuole sostituire quella precedente.
     * @return Ritorna le rows modificate dalla query se tutto va a buon fine, altrimenti 0.
     * @throws SQLException 
     */
    public int modificaPassword(int idUtente, String vecchiaPassword, String nuovaPassword) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.loggaConId);
        try{
            ps.setInt(1, idUtente);
            ps.setString(2, ConvertitoreSha.SHA1(vecchiaPassword));
            ResultSet rs = ps.executeQuery();
            if(rs.next()) { // dati giusti si procede con la modifica della password
                ps = conn.prepareStatement(CostantiDB.modificaPassword);
                ps.setString(1, ConvertitoreSha.SHA1(nuovaPassword));
                ps.setInt(2, idUtente);
                return ps.executeUpdate();
            } else 
                return 0;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    /**
     * 
     * @param idUtente Id dell'utente univoco all'interno del database.
     * @param candidatura Boolean che indica se l'utente vuole candidarsi come coinquilino o meno (true/false).
     * @return Ritorna le rows modificate dalla query se tutto va a buon fine, altrimenti 0.
     * @throws SQLException 
     */
    public int setCandidatura(int idUtente, boolean candidatura) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.setCandidatura);
        ps.setBoolean(1, candidatura);
        ps.setInt(2, idUtente);
        return ps.executeUpdate();
    }
    /**
     * 
     * @param idUtente Id dell'utente univoco all'interno del database.
     * @param nome Nome dell'utente.
     * @param cognome Cognome dell'utente.
     * @param dataNascita Oggetto Date, indica la data di nascita dell'utente.
     * @param sesso Sesso dell'utente.
     * @param nazionalita Nazionalità dell'utente.
     * @param cittaDiRicerca Città in cui l'utente ricerca casa o coinquilini.
     * @param numeroDiTelefono Numero di telefono dell'utente.
     * @return Ritorna le rows modificate dalla query se tutto va a buon fine, altrimenti 0.
     * @throws SQLException 
     */
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
    /**
     * 
     * @param idUtente Id dell'utente univoco all'interno del database.
     * @param nuovaCitta Nuova città in cui l'utente vuole cercare casa o coinquilini, sostituisce la vecchia.
     * @return Ritorna le rows modificate dalla query se tutto va a buon fine, altrimenti 0.
     * @throws SQLException 
     */
    public int modificaCitaDiRicerca(int idUtente, String nuovaCitta) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.modificaCittaRicerca);
        ps.setString(1, nuovaCitta);
        ps.setInt(2, idUtente);
        return ps.executeUpdate();
    }
    /**
     * 
     * @param idUtente Id dell'utente univoco all'interno del database.
     * @param fumatore Booleano che indica se l'utente è fumatore abituale o meno.
     * @param cuoco Booleano che indica se l'utente è un cuoco abituale o meno.
     * @param sportivo Booleanco che indica se l'utente è uno sportivo abituale o meno.
     * @param occupazione Occupazione dell'utente.
     * @param facolta Facoltà dell'utente, se studente, altrimenti è possibile scegliere "NESSUNA".
     * @return Ritorna le rows modificate dalla query se tutto va a buon fine, altrimenti 0.
     * @throws SQLException 
     */
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
    /**
     * Modifica delle informazioni dell'utente.
     * 
     * @param idUtente Id dell'utente univoco all'interno del database.
     * @param fumatore Booleano che indica se l'utente è fumatore abituale o meno.
     * @param cuoco Booleano che indica se l'utente è un cuoco abituale o meno.
     * @param sportivo Booleanco che indica se l'utente è uno sportivo abituale o meno.
     * @param occupazione Occupazione dell'utente.
     * @param facolta Facoltà dell'utente, se studente, altrimenti è possibile scegliere "NESSUNA".
     * @return  Ritorna le rows modificate dalla query se tutto va a buon fine, altrimenti 0.
     * @throws SQLException 
     */
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
    
    public ResultSet getAnnuncioUtente(int idUtenteProprietario) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.getAnnuncioUtente);
        ps.setInt(1, idUtenteProprietario);
        return ps.executeQuery();
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
    /**
     * 
     * @param idAnnuncio Id univoco dell'annuncio all'interno del database.
     * @param descrizione Descizione dell'annuncio.
     * @param costo Costo mensile dell'annuncio in euro.
     * @return Ritorna le rows modificate dalla query se tutto va a buon fine, altrimenti 0.
     * @throws SQLException 
     */
    
    public int modificaAnnuncioCasa(int idAnnuncio, String descrizione, int costo) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.modificaAnnuncioCasa);
        ps.setString(1, descrizione);
        ps.setInt(2, costo);
        ps.setInt(3, idAnnuncio);
        return ps.executeUpdate();
    }
    /**
     * Elimina l'annuncio della casa all'interno del db.
     * 
     * @param idCasa Id della casa univoco all'interno del database.
     * @return Ritorna le rows modificate dalla query se tutto va a buon fine, altrimenti 0.
     * @throws SQLException 
     */
    public int eliminaAnnuncioCasa(int idCasa) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.eliminaAnnuncioCasa);
        ps.setInt(1, idCasa);
        return ps.executeUpdate();
    }
    /**
     * 
     * @param m2 Metri quadrati dell'appartamento.
     * @param nLocali Numero di locali dell'appartamento.
     * @param nBagni Numero di bagni dell'appartamento.
     * @param distanzaCentro Distanza dal centro dell'appartamento.
     * @param sessoCasa Sesso presente all'interno della casa: MASCHI, FEMMINE, MISTA.
     * @param cucinaSeparata Indica se la cucina è separata, ossia in una stanza a parte (true), oppure no (false).
     * @param citta Città in cui la casa è presente.
     * @param indirizzo Indirizzo della casa.
     * @return returns a ResultSet object containing the auto-generated key(s) generated by the execution of this Statement object.
     * @throws SQLException 
     */
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
    /**
     * Metodo che modifica le informazioni della casa sul database.
     * 
     * @param idCasa Id della casa univoco all'interno del database.
     * @param m2 Metri quadrati dell'appartamento.
     * @param nLocali Numero di locali dell'appartamento.
     * @param nBagni Numero di bagni dell'appartamento.
     * @param distanzaCentro Distanza dal centro dell'appartamento.
     * @param sessoCasa Sesso presente all'interno della casa: MASCHI, FEMMINE, MISTA.
     * @param cucinaSeparata Indica se la cucina è separata, ossia in una stanza a parte (true), oppure no (false).
     * @param citta Città in cui la casa è presente.
     * @param indirizzo Indirizzo della casa.
     * @return Ritorna le rows modificate dalla query se tutto va a buon fine, altrimenti 0.
     * @throws SQLException 
     */
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
    /**
     * Metodo che modifica la camera, posti letto e posti disponibili, all'interno del database.
     * 
     * @param idCasa Id della casa univoco all'interno del database.
     * @param idCamera Id della camera, associato a quello della casa.
     * @param postiTotali
     * @param postiDisponibili
     * @return Ritorna le rows modificate dalla query se tutto va a buon fine, altrimenti 0.
     * @throws SQLException 
     */
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
    /**
     * Metodo che elimina l'elettrodomestico all'interno del db, dato un idCasa.
     * @param idCasa Id della casa univoco all'interno del database.
     * @param tipo Tipo dell'elettrodomestico che si desidera eliminare all'interno dell'appartamento.
     * @return Ritorna le rows modificate dalla query se tutto va a buon fine, altrimenti 0.
     * @throws SQLException 
     */
    public int eliminaElettrodomestico(int idCasa, String tipo) throws SQLException {
        
        PreparedStatement ps = conn.prepareStatement(CostantiDB.eliminaElettrodomestico);       
        ps.setInt(1, idCasa);
        ps.setString(2, tipo);
        return ps.executeUpdate();
    }
    /**
     * Elimino tutti gli elettrodomestici abbinati ad un dato idCasa.
     * @param idCasa Id della casa univoco all'interno del database.
     * @return Ritorna le rows modificate dalla query se tutto va a buon fine, altrimenti 0.
     * @throws SQLException 
     */
    public int eliminaTuttiElettrodomestici(int idCasa) throws SQLException {
        
        PreparedStatement ps = conn.prepareStatement(CostantiDB.eliminaTuttiElettrodomestici);       
        ps.setInt(1, idCasa);
        return ps.executeUpdate();
    }
    
    /* SEZIONE LETTURA ANNUNCI */
    /**
     * Metodo che carica gli annunci dal database data la città ed il costo.
     * 
     * @param citta Città di ricerca degli annunci.
     * @param costo Costo di ricerca per gli annunci. 
     * @return Ritorna le rows modificate dalla query se tutto va a buon fine, altrimenti 0.
     * @throws SQLException 
     */
    public ResultSet getAnnunciInfoCasa(String citta, int costo) throws SQLException {
        
        PreparedStatement ps;
        if(costo == 0) {
            ps = conn.prepareStatement(CostantiDB.getAnnunciSenzaCosto);
            ps.setString(1, citta);
        } else {
            ps = conn.prepareStatement(CostantiDB.getAnnunciJoinInfoCasa);
            ps.setString(1, citta);
            ps.setInt(2, costo);
        }
        return ps.executeQuery();   
    }
    /**
     * Metodo che verifica la presenza di un annuncio sul database dato un idUtente.
     * @param idUtenteProprietario Id dell'utente, univoco all'interno del database.
     * @return Ritorna le rows modificate dalla query se tutto va a buon fine, altrimenti 0.
     * @throws SQLException 
     */
    public ResultSet verificaPresenzaAnnuncio(int idUtenteProprietario) throws SQLException {
        
        PreparedStatement ps = conn.prepareStatement(CostantiDB.verificaPresenzaAnnuncio);
        ps.setInt(1, idUtenteProprietario);
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
    
    public ResultSet getAnnunciCoinquilini(String citta, String sesso) throws SQLException {
        
        PreparedStatement ps;
        if(sesso == null) {
            ps = conn.prepareStatement(CostantiDB.getAnnunciCoinquiliniSenzaSesso);
            ps.setString(1, citta);
        } else {
            ps = conn.prepareStatement(CostantiDB.getAnnunciCoinquilini);
            ps.setString(1, citta);
            ps.setString(2, sesso);
        }
        return ps.executeQuery(); 
    }
    
    public ResultSet getDatiUtente(int idUtente) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CostantiDB.getDatiUtente);
        ps.setInt(1, idUtente);
        return ps.executeQuery();
    }
    
}
