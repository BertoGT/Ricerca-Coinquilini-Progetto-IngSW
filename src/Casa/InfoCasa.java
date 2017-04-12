package Casa;

import Exceptions.CameraNonInseritaException;
import Exceptions.CameraNonTrovataException;
import java.util.ArrayList;

/**
 *
 * @author alberto
 */
public class InfoCasa {
    private int metriQuadri, nLocali, numeroBagni;
    private boolean cucinaSeparata;
    private ArrayList<CameraDisponibile> camere;

    public InfoCasa(int metriQuadri, int nLocali, int numeroBagni, boolean cucinaSeparata) {
        this.metriQuadri = metriQuadri;
        this.nLocali = nLocali;
        this.numeroBagni = numeroBagni;
        this.cucinaSeparata = cucinaSeparata;
        this.camere = new ArrayList<>();
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

    public boolean isCucinaSeparata() {
        return cucinaSeparata;
    }

    public ArrayList<CameraDisponibile> getCamere() {
        return camere;
    }
    
    
}
