package org.centrale.objet.WoE;
//@author CRUZ, ESPINOZA

/**
 * La classe Monstre représente un type de créature dans le monde du jeu.
 * Elle hérite de la classe Creature et possède des caractéristiques spécifiques.
 */
public abstract class Monstre extends Creature {
 /**
     * Constructeur pour créer un Monstre avec des attributs spécifiés.
     * @param ptVie Les points de vie du Monstre.
     * @param degAtt Les dégâts d'attaque du Monstre.
     * @param ptPar Les points de parade du Monstre.
     * @param pageAtt La portée d'attaque du Monstre.
     * @param pagePar La portée de parade du Monstre.
     * @param pos La position du Monstre
     */
    public Monstre(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, 0, pos);
    }
    /**
     * Constructeur de copie pour créer un Monstre en copiant un autre Monstre.
     *
     * @param m Le Monstre à copier.
     */
    public Monstre(Monstre m) {
        super(m);
    }
        /**
     * Constructeur par défaut pour créer un Monstre avec des valeurs par défaut.
     */

    public Monstre(){
        super();
    }
    /**
     * Affiche les informations du Monstre.
     * Cette méthode affiche les informations générales de la créature et ajoute des informations spécifiques au Monstre.
     */
    @Override
    public void affiche(){
        System.out.println("");
        System.out.println(this.getNomClasse());
        super.affiche();
    }

}
