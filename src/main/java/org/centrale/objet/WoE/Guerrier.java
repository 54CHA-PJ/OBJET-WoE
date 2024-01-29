package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * La classe Guerrier représente un type de personnage dans le jeu, spécialisé dans le combat corps à corps.
 * Elle hérite de la classe Personnage.
 * Cette classe permet de créer et de gérer des guerriers pour le jeu.
 * 
 * @author ESPINOZA Mario
 * @author CRUZ Sacha
 */
public class Guerrier extends Personnage implements Combattant {

    /**
     * Constructeur pour créer un guerrier avec des attributs spécifiés.
     * 
     * @param nom       Le nom du guerrier
     * @param ptVie     Les points de vie du guerrier
     * @param degAtt    Les dégâts d'attaque du guerrier
     * @param ptPar     Les points de parade du guerrier
     * @param pageAtt   Le pourcentage d'attaque réussie du guerrier
     * @param pagePar   Le pourcentage de parade réussie du guerrier
     * @param distAttMax    La distance d'attaque maximale
     * @param pos           La position du guerrier
     */
    public Guerrier(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos);
    }

    /**
     * Constructeur de copie pour créer un guerrier en copiant un autre guerrier.
     *
     * @param perso Le personnage à copier pour créer le guerrier.
     */
    public Guerrier(Personnage perso) {
        super(perso);
    }

    /**
     * Constructeur par défaut pour créer un guerrier avec des valeurs par défaut.
     * 
     * @see Personnage#Personnage()
     */
    public Guerrier() {
        super();
    }
    @Override
    public void combat(ElementDeJeu comb) {
       // Si ce n'est pas une créature, rien de défini
       if (!(comb instanceof Creature)){
            if (comb instanceof NuageToxique){
                NuageToxique n = (NuageToxique) comb;
                int ouch = n.getToxicite();
                System.out.println(this.getNom() + " attaque le Nuage Toxique avec son Epée");
                System.out.println("C'est inutile !");
                System.out.println(this.getNom() + " perd " + ouch + "points de vie dû à la toxicité du nuage");
                this.addPtVie(-1*ouch);
            } else
                System.out.println("Vous ne pouvez pas combattre une entité de type " + comb.getNomClasse());
       } 
       // Si c'est une créature, combat classique
       else {
            Creature c = (Creature) comb;
            Random alea = new Random();
            int ouch;
            System.out.println(this.getNom() + " donne un coup d'Épée");
            double d = this.getPos().distance(c.getPos());
            if (c.getPtVie()<1)             System.out.println("La créature viséen'a plus de points de vie"); 
            else if (d !=1)                 System.out.println("La créature visée est trop loin pour être attaquée"); 
            else {
                int patt = alea.nextInt(101);
                if (patt > (this.getPageAtt())) {
                    System.out.println("L'attaque échoue");
                } else {
                    System.out.println("L'attaque est réussie");
                    int ppar = alea.nextInt(101);
                    if (ppar > (c.getPagePar())) {
                        System.out.println("La parade échoue");
                        ouch = this.getDegAtt();

                    } else {
                        System.out.println("La parade est réussie");
                        ouch =  Math.max(0,this.getDegAtt()-c.getPtPar());
                    }
                    System.out.println("L'attaque inflige " + ouch + "points de dégâts");
                if (ouch >= 0.5*c.getPtVie()) {
                    System.out.println("C'est super efficace");
                }
                if (ouch <= 0.1*c.getPtVie()) {
                    System.out.println("Ce n'est pas très efficace");
                }
                c.addPtVie(-1*ouch);
                System.out.println("Il reste " + c.getPtVie() + "points de vie");
                }
            }  
        }
    }
    
    @Override
    public void attributsAleatoires() {
        Random rd = new Random();
        this.setPtVie(125 + rd.nextInt(76));
        // Génère des Dégats d'Attaque entre 40 et 70
        this.setDegAtt(40 + rd.nextInt(31));
        // Génère des Points de Parade entre 10 et 30
        this.setPtPar(10 + rd.nextInt(21));
        // Génère un Pourcentage d'Attaque entre 60% et 100%
        this.setPageAtt(60 + rd.nextInt(41));
        // Génère un Pourcentage de Parade entre 20% et 100%
        this.setPagePar(20 + rd.nextInt(81));
    }
}


