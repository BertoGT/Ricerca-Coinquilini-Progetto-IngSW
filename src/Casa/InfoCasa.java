package Casa;

import Exceptions.CameraNonTrovataException;
import java.util.ArrayList;

/**
 *
 * @author alberto
 */
public class InfoCasa {
    private int idCasa, metriQuadri, nLocali, numeroBagni,distanzaCentro;
    private HouseGenerality sessoCasa;
    private ArrayList<ElettroDomestico> elettroDomestici;
    private boolean cucinaSeparata;
    private ArrayList<CameraDisponibile> camere;
    private Citta citta;
    private String indirizzo;
    /**
     * Istanzia un oggetto InfoCasa
     * @param idCasa identificativo della casa
     * @param metriQuadri metri quadrati della casa
     * @param nLocali numero locali della casa
     * @param numeroBagni numero di bagni della casa
     * @param distanzaCentro dictanza dal centro della casa
     * @param cucinaSeparata tipologia di cucina della casa
     * @param citta citta di collocamento della casa
     * @param indirizzo indirizzo della casa
     * @param sessoCasa  indica il sesso delle persone all'interno della casa dell'annuncio
     */
    public InfoCasa(int idCasa, int metriQuadri, int nLocali, int numeroBagni,int distanzaCentro, boolean cucinaSeparata, Citta citta, String indirizzo, HouseGenerality sessoCasa) {
        this.idCasa = idCasa;
        this.metriQuadri = metriQuadri;
        this.nLocali = nLocali;
        this.numeroBagni = numeroBagni;
        this.distanzaCentro=distanzaCentro;
        this.cucinaSeparata = cucinaSeparata;
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.camere = new ArrayList<>();
        this.elettroDomestici=new ArrayList<>();
        this.sessoCasa = sessoCasa;
        
    }
    
    public void creaCamera(int idAnnuncio, int postiLetto, int postiLettoDisponibili) {
        int indice=this.camere.size();
       
        if(indice!=0)
            indice=this.camere.get(indice-1).getIdCamera()+1;
        
        this.camere.add(new CameraDisponibile(idAnnuncio, indice, postiLetto, postiLettoDisponibili));
    }
   
    public void addElettroDomestico(ElettroDomestico elettroDomestico){
        this.elettroDomestici.add(elettroDomestico);
    }
    
    public void rimuoviElettroDomestico(ElettroDomestico elettroDomestico){
        int indice=0;
        boolean flag=false;
        
        for(int i=0; i<this.elettroDomestici.size();i++){
            if(this.elettroDomestici.get(i)==elettroDomestico){
                indice=i;
                flag=true;
            }
        }
        if(flag)
            this.elettroDomestici.remove(indice);
    }

    public int getMetriQuadri() {
        return metriQuadri;
    }

    public int getnLocali() {
        return nLocali;
    }

    public int getNumeroBagni() {
        return numeroBagni;
    }

    public int getDistanzaCentro() {
        return distanzaCentro;
    }

    public HouseGenerality getSessoCasa() {
        return sessoCasa;
    }

    public boolean isCucinaSeparata() {
        return cucinaSeparata;
    }

    public ArrayList<CameraDisponibile> getCamere() {
        return camere;
    }

    public ArrayList<ElettroDomestico> getElettroDomestici() {
        return elettroDomestici;
    }

    public Citta getCitta() {
        return citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setMetriQuadri(int metriQuadri) {
        this.metriQuadri = metriQuadri;
    }

    public void setnLocali(int nLocali) {
        this.nLocali = nLocali;
    }

    public void setNumeroBagni(int numeroBagni) {
        this.numeroBagni = numeroBagni;
    }

    public void setDistanzaCentro(int distanzaCentro) {
        this.distanzaCentro = distanzaCentro;
    }

    public void setSessoCasa(HouseGenerality sessoCasa) {
        this.sessoCasa = sessoCasa;
    }

    public void setCucinaSeparata(boolean cucinaSeparata) {
        this.cucinaSeparata = cucinaSeparata;
    }

    public void setCitta(Citta citta) {
        this.citta = citta;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    
    
    
}
