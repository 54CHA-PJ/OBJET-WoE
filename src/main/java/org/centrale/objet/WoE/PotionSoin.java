package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * La classe PotionSoin représente un type d'objet, spécifiquement une potion de soin, dans le jeu.
 * Elle hérite de la classe Objet.
 * 
 * Cette classe permet de créer et de gérer des potions de soin pour le jeu.
 * Les potions de soin ont une quantité de soin et un prix.
 * 
 * @author ESPINOZA Mario.
 * @author CRUZ Sacha.
 */
public class PotionSoin extends Objet {
    private int quantiteSoin;

    /**
     * Constructeur pour créer une potion de soin avec des attributs spécifiés.
     * @param quantiteSoin  La quantité de soin que la potion restaure.
     * @param nom           Le nom de la potion.
     * @param pos           La position de la potion
     */
    public PotionSoin(int quantiteSoin, String nom, Point2D pos) {
        super(nom, pos);
        this.quantiteSoin = quantiteSoin;
    }

    /**
     * Constructeur de copie pour créer une potion de soin en copiant une autre potion de soin.
     * @param potion La potion à copier pour créer une nouvelle potion de soin.
     */
    public PotionSoin(PotionSoin potion) {
        super(potion);
        this.quantiteSoin = potion.quantiteSoin;
    }
    
    /**
     * Constructeur par défaut pour créer une potion de soin avec des valeurs par défaut.
     * @see Objet#Objet()
     */
    public PotionSoin() {
        super();
        this.quantiteSoin = 20;
    }
    
    
    @Override
    public void affiche(){ 
        super.affiche();
        System.out.println("Point de Soin = " + this.getQuantiteSoin());
    }
    
    @Override
    public void readAttributs(ArrayList<Object> listatt) {
        super.readAttributs(listatt);
        this.setQuantiteSoin((int) listatt.get(2));
    }
    
    @Override
    public void readAttributsInv(ArrayList<Object> listatt) {
        super.readAttributsInv(listatt);
        this.setQuantiteSoin((int) listatt.get(3));
    }
    
    
    @Override
    public String toString(){
        String s = super.toString();
        s += " ";
        s += this.quantiteSoin;
        s += " ";
        s += this.getPos().getX();
        s += " ";
        s += this.getPos().getY();
        s += " ";
        return s;
    }
    
    @Override
    public String toStringInv(){
        String s = super.toString();
        s += " ";
        s += this.quantiteSoin;
        return s;
    }

    /**
     * Obtient la quantité de soin que la potion restaure.
     * @return La quantité de soin de la potion.
     */
    public int getQuantiteSoin() {
        return quantiteSoin;
    }

    /**
     * Définit la quantité de soin que la potion restaure.
     * @param quantiteSoin La quantité de soin de la potion.
     */
    public void setQuantiteSoin(int quantiteSoin) {
        this.quantiteSoin = quantiteSoin;
    }


}
