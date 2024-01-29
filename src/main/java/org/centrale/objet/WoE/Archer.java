package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Random;
/**
 * La classe Archer représente un type de personnage dans le jeu. Elle hérite de
 * la classe Personnage et possède des caractéristiques spécifiques.
 * Un Archer a un nombre de flèches qu'il peut utiliser pour attaquer.
 *
 * @author CRUZ Sacha
 * @author ESPINOZA Mario
 */
public class Archer extends Personnage implements Combattant {

    /**
     * Le nombre de flèches que l'Archer possède.
     */
    private int nbFleches;

    // --------------------------------------------------------------------------------
    // Constructeurs
    // --------------------------------------------------------------------------------

    /**
     * Constructeur pour créer un Archer avec des attributs spécifiés.
     *
     * @param nbFleche Le nombre de flèches que l'Archer possède.
     * @param nom Le nom de l'Archer.
     * @param ptVie Les points de vie de l'Archer.
     * @param degAtt Les dégâts d'attaque de l'Archer.
     * @param ptPar Les points de parade de l'Archer.
     * @param pageAtt La portée d'attaque de l'Archer.
     * @param pagePar La portée de parade de l'Archer.
     * @param distAttMax La distance d'attaque maximale de l'Archer.
     * @param pos La position de l'Archer.
     */
    public Archer(int nbFleche, String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos);
        this.nbFleches = nbFleche;
    }

    /**
     * Constructeur de copie pour créer un Archer en copiant un autre Archer.
     * @param a L'Archer à copier.
     */
    public Archer(Archer a) {
        super(a);
        this.nbFleches = a.getNbFleches();
    }

    /**
     * Constructeur par défaut pour créer un Archer avec des valeurs par défaut.
     * Le nombre de flèches par défaut est 0.
     */
    public Archer() {
        super();
        this.nbFleches = 0;
    }

    // --------------------------------------------------------------------------------
    // Méthodes
    // --------------------------------------------------------------------------------

    /**
     * Permet à l'Archer de perdre une munition (flèche). Si l'Archer n'a pas de
     * flèches, une notification est affichée.
     */
    @Override
    public void perdreMunition() {
        int n = this.getNbFleches();
        if (n < 1) {
            System.out.println("Echec : Munitions insuffisantes");
        } else {
            this.setNbFleches(n - 1);
        }
    }

    /**
     * Obtient le nombre de flèches que l'Archer possède.
     * @return Le nombre de flèches de l'Archer.
     */
    @Override
    public int getMunition() {
        return this.nbFleches;
    }

    /**
     * Affiche les informations de l'Archer, y compris le nombre de flèches.
     */
    @Override
    public void affiche() {
        super.affiche();
        System.out.println("Nombre de flèches: " + this.getNbFleches());
    }

    /**
     * Attribue des attributs aléatoires à l'archer.
     */
    @Override
    public void attributsAleatoires() {
        Random rd = new Random();
        super.attributsAleatoires();
        this.setNbFleches(5 + rd.nextInt(6));  // Nombre de flèches entre 5 et 10
        this.setDistAttMax(4 + rd.nextInt(4)); // Distance d'attque entre 4 et 7
        this.setDegAtt(5 + rd.nextInt(20));    // Pour équilibrer le jeu, on réduit son attaque entre 5 et 20
    }

    @Override
    public void readAttributs(ArrayList<Object> listatt) {
        super.readAttributs(listatt);
        this.setNbFleches((int) listatt.get(10));
    }

    /**
     * Méthode de combat de l'Archer.
     * @param comb L'entité avec laquelle l'Archer combat.
     */
 @Override
    public void combat(ElementDeJeu comb) {
        // Declare n and c variables
        NuageToxique n;
        Creature c;
        // Si ce n'est pas une créature, rien de défini
        if (!(comb instanceof Creature)) {
            if (comb instanceof NuageToxique) {
                n = (NuageToxique) comb;
                int ouch = n.getToxicite();
                System.out.println(this.getNom() + " attaque le Nuage Toxique avec sa Dague");
                System.out.println("C'est inutile !");
                System.out.println(this.getNom() + " perd " + ouch + " points de vie dû à la toxicité du nuage");
                this.addPtVie(-1 * ouch);
            } else
                System.out.println("Vous ne pouvez pas combattre une entité de type " + comb.getNomClasse());
        }
        // Si c'est une créature, combat classique
        else {
            c = (Creature) comb;
            Random alea = new Random();
            int ouch = 0;
            double d = this.getPos().distance(c.getPos());
            // Si la créature ciblée n'a plus de points de vie
            if (c.getPtVie() < 1) {
                System.out.println("La créature visée n'a plus de points de vie");
            // Si la créature ciblée est trop pour être attaquée
            } else if (d > this.getDistAttMax()) {
                System.out.println("La créature visée est trop loin pour être attaquée");
            // Sinon, on attaque 
            } else {
                // Combat à proximité
                if (d == 1) {
                    int patt = alea.nextInt(101);
                    System.out.println(this.getNom() + " attaque avec sa Dague");
                    if (patt > this.getPageAtt()) {
                        System.out.println("L'attaque échoue");
                    } else {
                        System.out.println("L'attaque est réussie");
                        int ppar = alea.nextInt(101);
                        if (ppar > c.getPagePar()) {
                            System.out.println("La parade échoue");
                            ouch = this.getDegAtt() - c.getPtPar();
                        } else {
                            System.out.println("La parade est réussie");
                            ouch = Math.max(0, this.getDegAtt() - c.getPtPar());
                        }
                    }
                    // Combat à distance
                } else {
                    // Si l'archer a suffisament de munitions
                    if (nbFleches > 0) {
                        int patt = alea.nextInt(101);
                        System.out.println(this.getNom() + " utilise son arc");
                        this.setNbFleches(this.getNbFleches() - 1);
                        if (patt > this.getPageAtt()) {
                            System.out.println("L'attaque échoue");
                        } else {
                            System.out.println("L'attaque est réussie");
                            ouch = this.getDegAtt();
                        }
                        // Sinon : combat à distance impossible
                    } else {
                        System.out.println(this.getNom() + " n'a pas de flèches !");
                    }
                }

                System.out.println("L'attaque inflige " + ouch + " points de dégâts");
                if (ouch >= 0.5 * c.getPtVie()) {
                    System.out.println("C'est super efficace");
                }
                if (ouch <= 0.1 * c.getPtVie()) {
                    System.out.println("Ce n'est pas très efficace");
                }
                c.addPtVie(-1 * ouch);
                System.out.println("Il reste " + c.getPtVie() + " points de vie à " + c.getNomClasse());
            }
        }
    }

    
    @Override
    public String toString(){
        return (super.toString()+" "+nbFleches);
    }

    // --------------------------------------------------------------------------------
    // Getters & Setters
    // --------------------------------------------------------------------------------

    /**
     * Obtient le nombre de flèches que l'Archer possède.
     * @return Le nombre de flèches de l'Archer.
     */
    private int getNbFleches() {
        return this.nbFleches;
    }

    /**
     * Définit le nombre de flèches de l'Archer.
     * @param nbFleches Le nouveau nombre de flèches de l'Archer.
     */
    public void setNbFleches(int nbFleches) {
        this.nbFleches = nbFleches;
    }

}
