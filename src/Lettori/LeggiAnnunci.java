/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lettori;

import Casa.AnnuncioCasa;
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
    private BufferedReader br;
    private ArrayList<AnnuncioCasa> annunci;
    
    public void leggiCase(){
        annunci = new ArrayList<AnnuncioCasa>();
        try {
            br = new BufferedReader(new FileReader("file/annunci.txt"));
            while(br.ready()){
               String[] str = br.readLine().split("\t");
               annunci.add(new AnnuncioCasa(str[0], Integer.parseInt(str[1]), Integer.parseInt(str[2])));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeggiAnnunci.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeggiAnnunci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String stampaAnnunci(){
        StringBuilder sb = new StringBuilder();
        for (AnnuncioCasa a : annunci) {
            sb.append(a);
            sb.append("\n");
        }
        return sb.toString();
    }
}
