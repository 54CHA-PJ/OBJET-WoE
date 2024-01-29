package org.centrale.objet.WoE;

import java.util.ArrayList;
/** 
 * La classe nourriture est un type d'objet qui, lorsqu'elle est consommée par un personnage, lui confère des effets temporaires
 * 
 * @author ESPINOZA Mario
 * @author CRUZ Sacha
 */
public class Nourriture extends Objet {
    private int nbToursEffet; 
    private int gainDegAtt;
    private int gainPtPar;

    public Nourriture(String nom, Point2D pos) {
        super(nom, pos);
        this.nbToursEffet = 5;
    }

    public Nourriture(Nourriture n) {
        super(n);
        this.nbToursEffet = 5;
    }

    public Nourriture() {
        super();
        this.nbToursEffet = 5;
    }

    @Override
    public void readAttributs(ArrayList<Object> listatt) {
        this.setNom((String) listatt.get(1));
        this.nbToursEffet = ((int) listatt.get(2));
        this.gainDegAtt = ((int) listatt.get(3));
        this.gainPtPar = ((int) listatt.get(4));
        int x = (int) listatt.get(5);
        int y = (int) listatt.get(6);
        this.setPos(new Point2D(x,y));
    }
    
    @Override
    public void readAttributsInv(ArrayList<Object> listatt) {
        this.setNom((String) listatt.get(2));
        this.nbToursEffet = ((int) listatt.get(3));
        this.gainDegAtt = ((int) listatt.get(4));
        this.gainPtPar = ((int) listatt.get(5));
    }
    
    @Override
    public String toString(){
        String s = super.toString();
        s += " ";
        s += this.nbToursEffet;
        s += " ";
        s += this.gainDegAtt;
        s += " ";
        s += this.gainPtPar;
        s += " ";
        s += this.getPos().getX();
        s += " ";
        s += this.getPos().getY();
        return s;
    }
    
    @Override
    public String toStringInv(){
        String s = super.toStringInv();
        s += " ";
        s += this.nbToursEffet;
        s += " ";
        s += this.gainDegAtt;
        s += " ";
        s += this.gainPtPar;
        return s;
    }
    
    
    
    // --------------------------------------------------------------------------------
    // Getters & Setters
    // --------------------------------------------------------------------------------
    
    
    public int getNbToursEffet() {
        return nbToursEffet;
    }

    public void setNbToursEffet(int nbToursEffet) {
        this.nbToursEffet = nbToursEffet;
    }

    public int getGainDegAtt() {
        return gainDegAtt;
    }

    public void setGainDegAtt(int gainDegAtt) {
        this.gainDegAtt = gainDegAtt;
    }

    public int getGainPtPar() {
        return gainPtPar;
    }

    public void setGainPtPar(int gainPtPar) {
        this.gainPtPar = gainPtPar;
    }
    
    
}