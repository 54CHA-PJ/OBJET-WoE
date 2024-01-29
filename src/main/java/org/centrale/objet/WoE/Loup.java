package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * La classe Loup représente un type de monstre, spécifiquement un loup, dans le jeu.
 * Elle hérite de la classe Monstre.
 * 
 * Cette classe permet de créer et de gérer des loups en tant que monstres pour le jeu.
 * @author ESPINOZA Mario
 * @author CRUZ Sacha
 */
public class Loup extends Monstre implements Combattant {

    /**
     *Constructeur de Loup
     * @param ptVie Point de vie du loup
     * @param degAtt Dégâts d'attaque du loup
     * @param ptPar Points de parade du loup
     * @param pageAtt Pourcentage de chance de toucher avec une attaque
     * @param pagePar Pourcentage de chance de parer une attaque
     * @param pos Position du loup
     */
    public Loup(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
    }

    /**
     * Constructeur de copie pour créer un loup en copiant un autre loup.
     *
     * @param loup Le loup à copier pour créer un nouveau loup.
     */
    public Loup(Loup loup) {
        super(loup);
    }

    /**
     * Constructeur par défaut pour créer un loup avec des valeurs par défaut.
     * 
     * @see Monstre#Monstre()
     */
    public Loup() {
        super();
    }

    @Override
    public void combat(ElementDeJeu comb) {
       // Si ce n'est pas une créature, rien de défini
       if (!(comb instanceof Creature)){}
       // Si c'est une créature, combat classique
       else {
           Creature c = (Creature) comb;
            Random alea = new Random();
            int ouch;
            System.out.println(" La créature de type " + this.getNomClasse() + " attaque !");
            double d = this.getPos().distance(c.getPos());
            if (c.getPtVie()<1)        System.out.println("La créature visée n'a plus de points de vie");
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
}




