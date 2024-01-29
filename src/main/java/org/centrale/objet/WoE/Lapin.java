package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * La classe Lapin représente un type de monstre, spécifiquement un lapin, dans le jeu.
 * Elle hérite de la classe Monstre.
 * 
 * Cette classe permet de créer et de gérer des lapins en tant que monstres pour le jeu.
 * @author ESPINOZA Mario 
 * @author CRUZ Sacha
 */
public class Lapin extends Monstre {

    /**
     * Constructeur de Lapin
     * @param ptVie Point de vie du lapin
     * @param degAtt Dégâts d'attaque du lapin
     * @param ptPar Points de parade du lapin
     * @param pageAtt Pourcentage de chance de toucher avec une attaque
     * @param pagePar Pourcentage de chance de parer une attaque
     * @param pos Position du lapin
     */
    public Lapin(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
    }

    /**
     * Constructeur de copie pour créer un lapin en copiant un autre lapin.
     *
     * @param lapin Le lapin à copier pour créer un nouveau lapin.
     */
    public Lapin(Lapin lapin) {
        super(lapin);
    }

    /**
     * Constructeur par défaut pour créer un lapin avec des valeurs par défaut.
     * 
     * @see Monstre#Monstre()
     */
    public Lapin() {
        super();
    }
}

