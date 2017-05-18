/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author Niko
 */
public class CostantiDB {
    //Nomi tabelle database
    final static String tabellaAnagraficaUtente = "anagraficaUtente";
    final static String tabellaAnnuncioCasa = "annuncioCasa";
    final static String tabellaCamere = "camere";
    final static String tabellaInfoCasa = "infoCasa";
    final static String tabellaInfoUtente = "infoUtente";
    final static String tabellaUtente = "utente";
    
    //IP server
    final static String URL_DB = "jdbc:mysql://roomingdb2.c7syuv3prdev.us-east-2.rds.amazonaws.com:3306/innodb";
    
    //Username e Password
    final static String username = "roomingdb";
    final static String password = "albo1000";
    
    //Query registrazione e login
    final static String verificaEmail = "select * from " +tabellaUtente+ " where email = ?";
    final static String registraUtente = "INSERT INTO "+tabellaUtente+"(email, password, power, candidato, dataRegistrazione) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
    final static String loggaUtente = "select idUtente from "+tabellaUtente+" where email = ? and password = ?";
    
    //Query modifica password
    final static String loggaConId = "select idUtente from "+tabellaUtente+" where idUtente = ? and password = ?";
    final static String modificaPassword = "update "+tabellaUtente+" set password = ? where idUtente = ?";
    
    //Query per candidarsi o scandidarsi
    final static String setCandidatura = "update "+tabellaUtente+" set candidato = ? where idUtente = ?";
    
    //Query per inserimento dati anagrafici
    final static String inserisciAnagraficaUtente = "INSERT INTO "+tabellaAnagraficaUtente+" (idUtente, nome, cognome, dataNascita,"
            + " sesso, nazionalita, cittaDiRicerca) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final static String modificaCittaRicerca = "update "+tabellaAnagraficaUtente+" set cittaDiRicerca = ? where idUtente = ?";
    
    //Query inserimento/modifica infoUtente
    final static String inserisciInfoUtente = "INSERT INTO "+tabellaInfoUtente+" VALUES (?, ?, ?, ?, ?, ?)";
    final static String modificaInfoUtente = "UPDATE "+tabellaInfoUtente+" SET fumatore = ?, cuoco = ?, sportivo = ?,"
            + "occupazione = ?, facolta = ? WHERE idUtente = ?";
    
    /* SEZIONE ANNUNCI */
    
   // Query inserimento/modifica annuncio
    final static String inserisciAnnuncioCasa = "INSERT INTO "+tabellaAnnuncioCasa+" (idCasa,"
            + "idUtenteProprietario, descrizione, costo) VALUES (?,?,?,?)";
    final static String modificaAnnuncioCasa = "UPDATE "+tabellaAnnuncioCasa+" SET descrizione = ?,"
            + "costo = ? WHERE idAnnuncio = ?";
    
    // Query inserimento/modifica infoCasa
    final static String inserisciInfoCasa = "INSERT INTO "+tabellaInfoCasa+" (m2, nLocali, nBagni,"
            + "distanzaCentro, sessoCasa, cucinaSeparata, citta, indirizzo)"
            + "VALUES (?,?,?,?,?,?,?,?)";
    final static String modificaInfoCasa = "UPDATE "+tabellaInfoCasa+" SET m2 = ?, nLocali = ?,"
            + "nBagni = ?, distanzaCentro = ?, sessoCasa = ?, cucinaSeparata = ?, citta = ?,"
            + "indirizzo = ? WHERE idCasa = ?";
}
