/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casa;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Niko
 */
public class AnnuncioCasa {
    private int idProprietario;
    private InfoCasa casa;
    private String descrizioneAggiuntiva;
    private int idAnnuncio;
    private int costo;
    private String nomeCognomeProprietario;
    private String cellulareProprietario, emailProprietario;
    private String dataCreazioneAnnuncio;
    /**
     * Istanzia un oggetto che permette di creare un AnnuncioCasa
     * @param idProprietario  id del proprietario dell'annuncio
     * @param descrizioneAggiuntiva descrizione dell'annuncio della casa
     * @param idAnnuncio id dell'annuncio della casa
     * @param costo costo della casa
     * @param nomeCognomeProprietario nome e cognome del proprietario della casa
     * @param cellulareProprietario cellulare del proprietario della casa
     * @param emailProprietario email del proprietario della casa
     * @param data data creazione annuncio
     */
    public AnnuncioCasa(int idProprietario, String descrizioneAggiuntiva, int idAnnuncio, int costo, 
            String nomeCognomeProprietario, String cellulareProprietario, 
            String emailProprietario, Date data) {
        this.idProprietario = idProprietario;
        this.descrizioneAggiuntiva = descrizioneAggiuntiva;
        this.idAnnuncio = idAnnuncio;
        this.costo = costo;
        this.nomeCognomeProprietario = nomeCognomeProprietario;
        this.cellulareProprietario = cellulareProprietario;
        this.emailProprietario = emailProprietario;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        this.dataCreazioneAnnuncio = df.format(data);
    }

    public String getDataCreazioneAnnuncio() {
        return dataCreazioneAnnuncio;
    }
    /**
     * Crea una camera impostando il numero dei posti letto totali e il numero dei posti letto disponibili
     * @param postiLetto posti letto che la camera contiene
     * @param postiLettoDisponibili posri letto disponibili della camera
     */
    public void creaCamera(int postiLetto, int postiLettoDisponibili) {
        casa.creaCamera(this.idAnnuncio, postiLetto, postiLettoDisponibili);
    }

    /**
     * Permette di impostare tutte le informazioni necessarie di un annuncio
     * @param metriQuadri metri quadrati della casa dell'annuncio
     * @param nLocali numero dei locali della casa dell'annuncio
     * @param numeroBagni numero dei bagni della casa dell'annuncio
     * @param distanzaCentro distanza della casa dal centro
     * @param cucinaSeparata indica la tipologia di cucina della casasa dell'annuncio
     * @param citta citta della casa dell'annuncio
     * @param indirizzo indirizzo della casa dell'annuncio
     * @param sessoCasa indica il sesso delle persone all'interno della casa dell'annuncio
     * @param idCasa Id della casa univoco all'interno del database.
     */
    public void creaInfo(int idCasa, int metriQuadri, int nLocali, int numeroBagni,int distanzaCentro, boolean cucinaSeparata, Citta citta, String indirizzo, HouseGenerality sessoCasa){
        casa = new InfoCasa(idCasa, metriQuadri, nLocali, numeroBagni,distanzaCentro, cucinaSeparata, citta, indirizzo, sessoCasa);
    }
    /**
     * Permette di inserire un elettrodomestico all'interno della casa dell'annuncio
     * @param elettrodomestico elettrodomestico della casa dell'annuncio
     */
    public void creaElettrodomestico(ElettroDomestico elettrodomestico) {
        casa.addElettroDomestico(elettrodomestico);
    }
    /**
     * Permette di rimuovere un elettrodomestico all'interno della casa dell'annuncio
     * @param elettrodomestico  elettrodomestico della casa dell'annuncio
     */
    public void rimuoviElettrodomestico(ElettroDomestico elettrodomestico) {
        casa.rimuoviElettroDomestico(elettrodomestico);
    }

    public InfoCasa getCasa() {
        return casa;
    }

    public String getDescrizioneAggiuntiva() {
        return descrizioneAggiuntiva;
    }
  
    public int getIdAnnuncio() {
        return idAnnuncio;
    }
    
    public int getCosto() {
        return costo;
    }
  
    public Citta getCitta() {
        return casa.getCitta();
    }

    public int getMetriQuadri() {
        return casa.getMetriQuadri();
    }

    public int getnLocali() {
        return casa.getnLocali();
    }

    public int getNumeroBagni() {
        return casa.getNumeroBagni();
    }

    public int getDistanzaCentro(){
        return casa.getDistanzaCentro();
    }
    
    public int getIdCasa() {
        return casa.getIdCasa();
    }

    public int getIdProprietario() {
        return idProprietario;
    }
    public boolean isCucinaSeparata() {
        return casa.isCucinaSeparata();
    }

    public ArrayList<CameraDisponibile> getCamere() {
        return casa.getCamere();
    }
  
    public HouseGenerality getSessoCasa() {
        return casa.getSessoCasa();
    }

    public String getNomeCognomeProprietario() {
        return nomeCognomeProprietario;
    }

    public String getCellulareProprietario() {
        return cellulareProprietario;
    }
 
    public String getEmailProprietario() {
        return emailProprietario;
    }
    
    public ArrayList<ElettroDomestico> getElettroDomestici() {
        return casa.getElettroDomestici();
    }
    
    public String getIndirizzoCasa() {
        return casa.getIndirizzo();
    }
    
    public void setDescrizioneAggiuntiva(String descrizioneAggiuntiva) {
        this.descrizioneAggiuntiva = descrizioneAggiuntiva;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
  
    public void setNomeCognomeProprietario(String nomeCognomeProprietario) {
        this.nomeCognomeProprietario = nomeCognomeProprietario;
    }
  
    public void setCellulareProprietario(String cellulareProprietario) {
        this.cellulareProprietario = cellulareProprietario;
    }
  
    public void setEmailProprietario(String emailProprietario) {
        this.emailProprietario = emailProprietario;
    }
    
}
