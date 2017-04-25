/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmi;

import Casa.*;

/**
 *
 * @author Marco La Salvia
 */
public class DatiRicercaAnnuncio {
    private float distanzaCentroMax;
    private String citta;
    private HouseGender sessoCasa;
    private int postiLettoCamera;
    private int numeroBagni;
    private boolean bagnoInCamera, lavatrice, lavastovigle, microonde, cucinaSeparata;
    private float costoCameraMax;

    public DatiRicercaAnnuncio(float distanzaCentroMax, String citta, HouseGender sessoCasa,
                               int postiLettoCamera, int numeroBagni, boolean bagnoInCamera,
                               boolean lavatrice, boolean lavastovigle, boolean microonde, boolean cucinaSeparata, float costoCameraMax) {
        
        this.distanzaCentroMax = distanzaCentroMax;
        this.citta = citta;
        this.sessoCasa = sessoCasa;
        this.postiLettoCamera = postiLettoCamera;
        this.numeroBagni = numeroBagni;
        this.bagnoInCamera = bagnoInCamera;
        this.lavatrice = lavatrice;
        this.lavastovigle = lavastovigle;
        this.microonde = microonde;
        this.cucinaSeparata = cucinaSeparata;
        this.costoCameraMax = costoCameraMax;
    }

    public float getDistanzaCentroMax() {
        return distanzaCentroMax;
    }

    public void setDistanzaCentroMax(float distanzaCentroMax) {
        this.distanzaCentroMax = distanzaCentroMax;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public HouseGender getSessoCasa() {
        return sessoCasa;
    }

    public void setSessoCasa(HouseGender sessoCasa) {
        this.sessoCasa = sessoCasa;
    }

    public int getPostiLettoCamera() {
        return postiLettoCamera;
    }

    public void setPostiLettoCamera(int postiLettoCamera) {
        this.postiLettoCamera = postiLettoCamera;
    }

    public int getNumeroBagni() {
        return numeroBagni;
    }

    public void setNumeroBagni(int numeroBagni) {
        this.numeroBagni = numeroBagni;
    }

    public boolean isBagnoInCamera() {
        return bagnoInCamera;
    }

    public void setBagnoInCamera(boolean bagnoInCamera) {
        this.bagnoInCamera = bagnoInCamera;
    }

    public boolean isLavatrice() {
        return lavatrice;
    }

    public void setLavatrice(boolean lavatrice) {
        this.lavatrice = lavatrice;
    }

    public boolean isLavastovigle() {
        return lavastovigle;
    }

    public void setLavastovigle(boolean lavastovigle) {
        this.lavastovigle = lavastovigle;
    }

    public boolean isMicroonde() {
        return microonde;
    }

    public void setMicroonde(boolean microonde) {
        this.microonde = microonde;
    }

    public boolean isCucinaSeparata() {
        return cucinaSeparata;
    }

    public void setCucinaSeparata(boolean cucinaSeparata) {
        this.cucinaSeparata = cucinaSeparata;
    }

    public float getCostoCameraMax() {
        return costoCameraMax;
    }

    public void setCostoCameraMax(float costoCameraMax) {
        this.costoCameraMax = costoCameraMax;
    }
    
    
    
}
