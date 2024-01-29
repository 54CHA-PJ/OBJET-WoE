/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 * La classe TajinePoulet est une nourriture qui offre des bonus au personnage qui la consomme.
 * Le joueur gagne des points d'attaque pendant quelques tours.
 * @author ESPINOZA Mario.
 * @author CRUZ Sacha.
 */
public class TajinePoulet extends Nourriture{

    public TajinePoulet(String nom, String rar, Point2D pos) {
        super(nom, pos);
        setGainDegAtt(30);
    }

    public TajinePoulet(TajinePoulet ob) {
        super(ob);
    }

    public TajinePoulet() {
        super();
        setGainDegAtt(30);
    }
}
