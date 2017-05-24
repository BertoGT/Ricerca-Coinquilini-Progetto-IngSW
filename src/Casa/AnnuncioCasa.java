/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casa;

import Exceptions.CameraNonInseritaException;
import Exceptions.CameraNonTrovataException;
import ProfiloUtente.DatiUtente;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Niko
 */
public class AnnuncioCasa {
    private InfoCasa casa;
    private String descrizioneAggiuntiva;
    private int idAnnuncio;
    private int costo;
    private String nomeCognomeProprietario;
    private String cellulareProprietario, emailProprietario;
    private String dataCreazioneAnnuncio;

    public AnnuncioCasa(String descrizioneAggiuntiva, int idAnnuncio, int costo, String nomeCognomeProprietario, String cellulareProprietario, String emailProprietario) {
        this.descrizioneAggiuntiva = descrizioneAggiuntiva;
        this.idAnnuncio = idAnnuncio;
        this.costo = costo;
        this.nomeCognomeProprietario = nomeCognomeProprietario;
        this.cellulareProprietario = cellulareProprietario;
        this.emailProprietario = emailProprietario;
        this.dataCreazioneAnnuncio = Calendar.getInstance().toString();
    }

    public String getDataCreazioneAnnuncio() {
        return dataCreazioneAnnuncio;
    }
    
    public void creaCamera(int idCamera, int postiLetto, int postiLettoDisponibili) throws CameraNonInseritaException{
        casa.creaCamera(idCamera, postiLetto, postiLettoDisponibili);
    }
    
    public void rimuoviCamera(int idCamera) throws CameraNonTrovataException{
        casa.rimuoviCamera(idAnnuncio);
    }
    
    public void creaInfo(int metriQuadri, int nLocali, int numeroBagni,int distanzaCentro, boolean cucinaSeparata, Citta citta, String indirizzo, HouseGenerality sessoCasa){
        casa = new InfoCasa(metriQuadri, nLocali, numeroBagni,distanzaCentro, cucinaSeparata, citta, indirizzo, sessoCasa);
    }
    
    public void creaElettrodomestico(ElettroDomestico elettrodomestico) {
        casa.addElettroDomestico(elettrodomestico);
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
