/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lettori;

import Casa.AnnuncioCasa;
import Casa.InfoCasa;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fari
 */
public class LeggiAnnunci {
    private BufferedReader br_case, br_info;
    private ArrayList<AnnuncioCasa> annunci;
    private ArrayList<InfoCasa> specificheCasa;
    
    public void leggiCase(){  
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
    
    
    public void leggiInfoCasa(){
        try {
            specificheCasa = new ArrayList<InfoCasa>();
            br_info = new BufferedReader(new FileReader("file/infoCasa.txt"));
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
                specificheCasa.add(new InfoCasa(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]), cucina, str[4], str[5]));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeggiAnnunci.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeggiAnnunci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String stampaInfoAnnunci(){
        StringBuilder sb = new StringBuilder();
        for (InfoCasa i : specificheCasa) {
            String cucina;
            if(i.isCucinaSeparata())
                cucina = "cucina separata";
            else cucina = "cucina a vista";
            sb.append(i.getCitta()+" "+i.getIndirizzo()+"; "+"numero camere: "+i.getnLocali()+"; numero bagni: "+i.getNumeroBagni()+"; cucina: "+cucina);
            sb.append("\n");
        }
        return sb.toString();
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
