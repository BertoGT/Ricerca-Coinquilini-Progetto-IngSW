/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lettori;

import Casa.AnnuncioCasa;
import Casa.InfoCasa;
import Exceptions.CameraNonInseritaException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niko
 */
public class LeggiAnnunci {
    private BufferedReader br_case, br_info, br_camera;
    private ArrayList<AnnuncioCasa> annunci;
 
    private void leggiAnnunci(){  
        try {
            annunci = new ArrayList<AnnuncioCasa>();
            br_case = new BufferedReader(new FileReader("file/annunci.txt"));
            while(br_case.ready()){
               String[] str = br_case.readLine().split(";");
               annunci.add(new AnnuncioCasa(str[0], Integer.parseInt(str[1]), Integer.parseInt(str[2])));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeggiAnnunci.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeggiAnnunci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void leggiInfoCasa(){
        try {
            br_info = new BufferedReader(new FileReader("file/infoCasa.txt"));
            int contatore = 0;
            while(br_info.ready()){
                String[] str = br_info.readLine().split(";");
                boolean cucina = true;
                switch(str[3]){
                    case "cucina separata":
                        cucina = true;
                        break;
                    case "cucina a vista":
                        cucina = false;
                        break;
                    default:
                        break;
                }
                annunci.get(contatore).creaInfo(Integer.parseInt(str[0]),
                        Integer.parseInt(str[1]), Integer.parseInt(str[2]), cucina, str[4], str[5]);
                contatore++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeggiAnnunci.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeggiAnnunci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void leggiCamere(){
        try {
            br_camera = new BufferedReader(new FileReader("file/camere.txt"));
            while(br_camera.ready()){
                String[] str = br_camera.readLine().split(";");
                annunci.get(Integer.parseInt(str[0])).creaCamera(Integer.parseInt(str[1]), Integer.parseInt(str[2]),Integer.parseInt(str[3]));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeggiAnnunci.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeggiAnnunci.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CameraNonInseritaException ex) {
            Logger.getLogger(LeggiAnnunci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void leggiFile(){
        leggiAnnunci();
        leggiInfoCasa();
        leggiCamere();
    }

    public ArrayList<AnnuncioCasa> getAnnunci() {
        return annunci;
    }
    
    public String stampaAnnunci(){
        StringBuilder sb = new StringBuilder();
        for (AnnuncioCasa a : annunci) {
            sb.append(a.getDescrizioneAggiuntiva()+" "+a.getIdAnnuncio()+" "+a.getCosto());
            sb.append("\n");
        }
        return sb.toString();
    }
}
