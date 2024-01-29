/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;


/**
 * La classe ChampiVenimeux représente un type de nourriture qui représente un champignon venimeux.
 * Il inflige des dégâts de venin au joueur qui le consomme.
 * 
 * @author ESPINOZA Mario
 * @author CRUZ Sacha
 */
public class ChampiVenimeux extends Nourriture {

    private int degatVenin = 10; // Les dégâts de venin infligés par ce champignon venimeux.

    /**
     * Constructeur de la classe ChampiVenimeux avec un nom et une position spécifiés.
     * @param nom Le nom du champignon venimeux.
     * @param pos La position du champignon venimeux.
     */
    public ChampiVenimeux(String nom, Point2D pos) {
        super(nom, pos);
        this.setGainPtPar(-20);
    }

    /**
     * Constructeur de copie de la classe ChampiVenimeux.
     * @param ob L'objet ChampiVenimeux à copier.
     */
    public ChampiVenimeux(ChampiVenimeux ob) {
        super(ob);
        this.degatVenin = ob.getDegatVenin();
    }

    /**
     * Constructeur par défaut de la classe ChampiVenimeux.
     * Il crée un champignon venimeux sans nom spécifique et lui attribue des dégâts de venin.
     */
    public ChampiVenimeux() {
        super();
        this.setGainPtPar(-20);
        this.setNom("ChampignonPasSuspect");
    }

    /**
     * Obtient les dégâts de venin infligés par ce champignon venimeux.
     * @return Les dégâts de venin.
     */
    public int getDegatVenin() {
        return degatVenin;
    }

    /**
     * Modifie les dégâts de venin infligés par ce champignon venimeux.
     * @param degatVenin Les nouveaux dégâts de venin.
     */
    public void setDegatVenin(int degatVenin) {
        this.degatVenin = degatVenin;
    }
}
