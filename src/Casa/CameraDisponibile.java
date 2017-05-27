package Casa;

/**
 *
 * @author Alberto
 */
public class CameraDisponibile {
    private int postiLetto, postiLettoDisponibili, idCamera, idAnnuncio; 

    public CameraDisponibile(int idAnnuncio, int idCamera, int postiLetto, int postiLettoDisponibili) {
        this.postiLetto = postiLetto;
        this.postiLettoDisponibili = postiLettoDisponibili;
        this.idCamera = idCamera;
        this.idAnnuncio=idAnnuncio;
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

    public void setIdCamera(int idCamera) {
        this.idCamera = idCamera;
    }
    
        public void setPostiLettoDisponibili(int postiLettoDisponibili) {
        this.postiLettoDisponibili = postiLettoDisponibili;
    }

    public void setPostiLetto(int postiLetto) {
        this.postiLetto = postiLetto;
    }
    
    
    
    
}
