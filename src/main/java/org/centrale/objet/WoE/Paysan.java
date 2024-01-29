package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * La classe Paysan représente un type de personnage, spécifiquement un paysan, dans le jeu.
 * Elle hérite de la classe Personnage.
 * 
 * Cette classe permet de créer et de gérer des paysans en tant que personnages pour le jeu.
 * 
 * @author ESPINOZA Mario
 * @author CRUZ Sacha
 */
public class Paysan extends Personnage {

    /**
     * Constructeur pour créer un paysan avec des attributs spécifiés.
     * 
     * @param nom           Le nom du personnage
     * @param ptVie         Les points de vie du personnage
     * @param degAtt        Les dégâts d'attaque du personnage
     * @param ptPar         Les points de parade du personnage
     * @param pageAtt       La portée d'attaque du personnage
     * @param pagePar       La portée de parade du personnage
     * @param distAttMax    La distance d'attaque maximale du personnage
     * @param pos           La position du personnage
     */
    public Paysan(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos);
    }

    /**
     * Constructeur de copie pour créer un paysan en copiant un autre paysan.
     * @param p Le paysan à copier pour créer un nouveau paysan.
     */
    public Paysan(Paysan p) {
        super(p);
    }

    /**
     * Constructeur par défaut pour créer un paysan avec des valeurs par défaut.
     * @see Personnage#Personnage()
     */
    public Paysan() {
        super();
    }
}

