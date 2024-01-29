package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * La classe Epee représente une arme dans le jeu.
 * Elle hérite de la classe Objet.
 * 
 * Cette classe contient des attributs et des méthodes pour gérer les épées des personnages.
 * Une épée possède des points de dégâts, une portée et une vitesse d'attaque.
 * Cette classe permet de créer et de gérer des épées pour le jeu.
 * 
 * @author ESPINOZA Mario
 * @author CRUZ Sacha
 */
public class Epee extends Objet {

    // --------------------------------------------------------------------------------
    // Attributs
    // --------------------------------------------------------------------------------
    
    private int pointDegat;
    private int portee;

    /**
     * Constructeur pour créer une épée avec des attributs spécifiés.
     * 
     * @param nom         Le nom de l'épée.
     * @param rarete      La rareté de l'épée.
     * @param pointDegat  Les points de dégâts de l'épée.
     * @param portee      La portée de l'épée.
     * @param pos         La position de l'objet
     */
    public Epee(String nom, String rarete, int pointDegat, int portee, Point2D pos) {
        super(nom, pos);
        this.pointDegat = pointDegat;
        this.portee = portee;
    }

    /**
     * Constructeur par défaut pour créer une épée avec des valeurs par défaut.
     * @see Objet#Objet()
     */
    public Epee() {
        super();
        this.setNom("EpeeBasique");
    }
    
    // --------------------------------------------------------------------------------
    // Méthodes
    // --------------------------------------------------------------------------------
    
    @Override
    public void readAttributs(ArrayList<Object> listatt) {
        super.readAttributs(listatt);
        this.setPointDegat((int) listatt.get(2));
        this.setPortee((int) listatt.get(5));
    }
    
    @Override
    public void readAttributsInv(ArrayList<Object> listatt) {
        super.readAttributsInv(listatt);
        this.setPointDegat((int) listatt.get(3));
        this.setPortee((int) listatt.get(4));
    }
    
    @Override
    public String toString(){
        String s = super.toString();
        s += " ";
        s += this.pointDegat;
        s += " ";
        s += this.getPos().getX();
        s += " ";
        s += this.getPos().getY();
        s += " ";
        s += this.portee;
        return s;
    }
    
    @Override
    public String toStringInv(){
        String s = super.toStringInv();
        s += " ";
        s += this.pointDegat;
        s += " ";
        s += this.portee;
        return s;
    }
    
    
    // --------------------------------------------------------------------------------
    // Getters & Setters
    // --------------------------------------------------------------------------------
    
    /**
     * Obtient les points de dégâts de l'épée.
     * 
     * @return Les points de dégâts de l'épée.
     */
    public int getPointDegat() {
        return pointDegat;
    }

    /**
     * Définit les points de dégâts de l'épée.
     * 
     * @param pointDegat Les points de dégâts de l'épée.
     */
    public void setPointDegat(int pointDegat) {
        this.pointDegat = pointDegat;
    }

    /**
     * Obtient la portée de l'épée.
     * 
     * @return La portée de l'épée.
     */
    public int getPortee() {
        return portee;
    }

    /**
     * Définit la portée de l'épée.
     * 
     * @param portee La portée de l'épée.
     */
    public void setPortee(int portee) {
        this.portee = portee;
    }

    
}

