package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * La classe Personnage représente un personnage dans le jeu. Elle hérite de la
 * classe Creature.
 *
 * Cette classe contient des attributs et des méthodes pour gérer les
 * personnages du jeu. Un personnage possède un nom, des points de vie, des
 * dégâts d'attaque, des points de parade, une portée d'attaque, une portée de
 * parade, une distance d'attaque maximale et une position dans le monde.
 *
 * Cette classe permet de créer, gérer et afficher des personnages pour le jeu.
 *
 * @author ESPINOZA Mario
 * @author CRUZ Sacha
 */

public abstract class Personnage extends Creature {

    private String nom;

    // --------------------------------------------------------------------------------
    // Constructeurs
    // --------------------------------------------------------------------------------
    
    /**
     * Constructeur de copie pour créer un personnage en copiant un autre
     * personnage.
     *
     * @param perso Le personnage à copier.
     */
    public Personnage(Personnage perso) {
        super(perso);
        this.nom = perso.getNom();
    }

    /**
     * Constructeur par défaut pour créer un personnage avec des valeurs par
     * défaut. Le nom par défaut est "Sans_Nom".
     */
    public Personnage() {
        super();
        this.nom = this.getNomClasse();
    }
    
    public Personnage(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
    super(ptVie, degAtt, ptPar, pageAtt, pagePar,distAttMax, pos);
    this.nom = nom;
    }

    // --------------------------------------------------------------------------------
    // Méthodes
    // --------------------------------------------------------------------------------

    /**
     * Définit les nouveaux PtVie
     *
     * @param potion La potion de soin avec laquelle le personnage interagit
     */
    public void prendrePotionSoin(PotionSoin potion) {
        if (this.getPtVie() + potion.getQuantiteSoin() > 200) {
            this.setPtVie(200);
            this.affiche();
        } else {
            this.setPtVie(this.getPtVie() + potion.getQuantiteSoin());
            this.affiche();
        }
    }
    
    /**
     * Affiche les informations du personnage. Cette méthode affiche le nom du
     * personnage suivi de ses autres informations.
     */
    @Override
    public void affiche() {
        System.out.println("\nNom: " + this.getNom());
        super.affiche();
    }
    
    @Override
    public void readAttributs(ArrayList<Object> listatt) {
        this.setNom((String) listatt.get(1));
        this.setPtVie((int) listatt.get(2));
        this.setDegAtt((int) listatt.get(3));
        this.setPtPar((int) listatt.get(4));
        this.setPageAtt((int) listatt.get(5));
        this.setPagePar((int) listatt.get(6));
        this.setDistAttMax((int) listatt.get(7));
        int x = (int) listatt.get(8);
        int y = (int) listatt.get(9);
        this.setPos(new Point2D(x,y));
    }
    
    @Override
    public String toString(){
        if ("".equals(this.nom))
            this.nom = "NoName";
        String s = this.getNomClasse();
        s += " ";
        s += this.nom;
        s += " ";
        s += this.getPtVie();
        s += " ";
        s += this.getDegAtt();
        s += " ";
        s += this.getPtPar();
        s += " ";
        s += this.getPageAtt();
        s += " ";
        s += this.getPagePar();
        s += " ";
        s += this.getDistAttMax();
        s += " ";
        s += this.getPos().getX();
        s += " ";
        s += this.getPos().getY();
        return s;
    }
    
    // --------------------------------------------------------------------------------
    // Getters & Setters
    // --------------------------------------------------------------------------------
   
    /**
     * Obtient le nom du personnage.
     *
     * @return Le nom du personnage.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Définit le nom du personnage.
     *
     * @param nom Le nouveau nom du personnage.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
