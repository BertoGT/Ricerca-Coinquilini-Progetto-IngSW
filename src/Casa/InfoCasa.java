package Casa;

import Exceptions.CameraNonInseritaException;
import Exceptions.CameraNonTrovataException;
import java.util.ArrayList;

/**
 *
 * @author alberto
 */
public class InfoCasa {
    private int metriQuadri, nLocali, numeroBagni,distanzaCentro;
    private HouseGenerality sessoCasa;
    private ArrayList<ElettroDomestico> elettroDomestici;
    private boolean cucinaSeparata;
    private ArrayList<CameraDisponibile> camere;
    private Citta citta;
    private String indirizzo;

    public InfoCasa(int metriQuadri, int nLocali, int numeroBagni,int distanzaCentro, boolean cucinaSeparata, Citta citta, String indirizzo, HouseGenerality sessoCasa) {
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
        camere.add(new CameraDisponibile(idAnnuncio, indice, postiLetto, postiLettoDisponibili));
    }
    
    public void rimuoviCamera(int idCamera) throws CameraNonTrovataException {
       boolean flag=false;
       int indice=0;
      
       for(int i=0;i<this.camere.size();i++){
           if(this.camere.get(i).getIdCamera()==idCamera){
               indice=i;
               flag=true;
               break;
           }
       }
       if(flag){
           this.camere.remove(indice);
           for(int j=indice;j<this.camere.size();j++){
               this.camere.get(j).setIdCamera(j);
           }
       }
        else
            throw new CameraNonTrovataException("Camera non presente");
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
