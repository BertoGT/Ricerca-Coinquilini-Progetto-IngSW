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
    private HouseGender sessoCasa;
    private boolean cucinaSeparata;
    private ArrayList<CameraDisponibile> camere;
    private String citta, indirizzo;

    public InfoCasa(int metriQuadri, int nLocali, int numeroBagni,int distanzaCentro, boolean cucinaSeparata, String citta, String indirizzo, HouseGender sessoCasa) {
        this.metriQuadri = metriQuadri;
        this.nLocali = nLocali;
        this.numeroBagni = numeroBagni;
        this.distanzaCentro=distanzaCentro;
        this.cucinaSeparata = cucinaSeparata;
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.camere = new ArrayList<>();
        this.sessoCasa = sessoCasa;
        
    }

    public void creaCamera(int idCamera, int postiLetto, int postiLettoDisponibili) throws CameraNonInseritaException {
        boolean flag = false;
        for (CameraDisponibile camera : camere) {
            if(camera.getIdCamera() == idCamera) {
               flag = true;
               break;
            }
        }
        if(flag)
            throw new CameraNonInseritaException("Camera già presente");
        else 
            camere.add(new CameraDisponibile(idCamera, postiLetto, postiLettoDisponibili));
    }
    
    public void rimuoviCamera(int idCamera) throws CameraNonTrovataException {
        boolean flag = false;
        for (int i = 0; i < camere.size(); i++) {
            if(camere.get(i).getIdCamera() == idCamera) {
                camere.remove(i);
                flag = true;
                break;
            }
        }
        if(flag == false)
            throw new CameraNonTrovataException("Camera non presente");
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

    public HouseGender getSessoCasa() {
        return sessoCasa;
    }

    public boolean isCucinaSeparata() {
        return cucinaSeparata;
    }

    public ArrayList<CameraDisponibile> getCamere() {
        return camere;
    }

    public String getCitta() {
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

    public void setSessoCasa(HouseGender sessoCasa) {
        this.sessoCasa = sessoCasa;
    }

    public void setCucinaSeparata(boolean cucinaSeparata) {
        this.cucinaSeparata = cucinaSeparata;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    
    
    
}
