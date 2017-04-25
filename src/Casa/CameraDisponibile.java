package Casa;

/**
 *
 * @author Alberto
 */
public class CameraDisponibile {
    private int postiLetto, postiLettoDisponibili, idCamera; 

    public CameraDisponibile(int idCamera, int postiLetto, int postiLettoDisponibili) {
        this.postiLetto = postiLetto;
        this.postiLettoDisponibili = postiLettoDisponibili;
        this.idCamera = idCamera;
    }

    public int getPostiLetto() {
        return postiLetto;
    }

    public int getPostiLettoDisponibili() {
        return postiLettoDisponibili;
    }

    public int getIdCamera() {
        return idCamera;
    }

    public void setPostiLettoDisponibili(int postiLettoDisponibili) {
        this.postiLettoDisponibili = postiLettoDisponibili;
    }
    
    
    
}
