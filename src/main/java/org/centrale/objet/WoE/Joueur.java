package org.centrale.objet.WoE;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * La classe Joueur représente un joueur dans le monde du jeu.
 * 
 * @author ESPINOZA Mario
 * @author CRUZ Sacha
 */
public class Joueur {

    private String nom; // Le nom du joueur
    private Personnage perso; // Le personnage du joueur
    private ArrayList<Objet> inventaire; // L'inventaire du joueur
    private String typeClavier; // Le type de clavier préféré du joueur

    /**
     * Constructeur par défaut de la classe Joueur.
     */
    public Joueur() {
        this.inventaire = new ArrayList<>();
        this.nom = demanderNom();
        this.typeClavier = choisirTypeClavier();
    }

    /**
     * Méthode pour demander le nom du joueur.
     *
     * @return Le nom saisi par le joueur.
     */
    private String demanderNom() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez votre nom : \n");
        return scanner.nextLine();
    }

    /**
     * Méthode pour choisir un personnage jouable.
     *
     * @param w Le monde dans lequel le joueur évolue.
     */
    public void choisirPersonnageJouable(World w) {
        int i;
        do {
            Scanner inputJoueur = new Scanner(System.in);
            System.out.print("\nChoisissez votre classe (tapez 1 ou 2): ");
            System.out.print("\n[1] Guerrier\n[2] Archer\n");
            i = inputJoueur.nextInt();
            switch (i) {
                case 1: {
                    this.perso = new Guerrier();
                    break;
                }
                case 2: {
                    this.perso = new Archer();
                    break;
                }
                default:
                    System.out.print("\nInsérez 1 ou 2...");
            }
        }
        while (i != 1 && i != 2);
        this.perso.setNom(this.nom);
        this.perso.attributsAleatoires();
        this.perso.addPtVie(100);
        w.getListeCreatures().add(this.perso);
        System.out.println("\nVous avez créé un personnage de la classe " + this.perso.getNomClasse() + " avec le nom " + this.getNom());
    }

    /**
     * Méthode pour choisir le type de clavier préféré du joueur.
     * @return Le type de clavier choisi ("azerty" ou "querty").
     */
    private String choisirTypeClavier() {
        int i = 0;
        String res = "azerty";
        System.out.println("Quel est votre type de clavier ?\n[1] AZERTY (Clavier Français)\n[2] QUERTY (Clavier Américain)");

        do {

            try {
                Scanner sc = new Scanner( System.in);
                i = sc.nextInt();
            } catch (Exception e) {}
            switch (i) {
                case 1: {
                    res = "azerty";
                    break;
                }
                case 2: {
                    res = "querty";
                    break;
                }
                default:
                    System.out.print("\nInsérez 1 ou 2...\n");
            }
        }
        while (i != 1 && i != 2);
        return res;
    }

    /**
     * Méthode pour afficher les informations du joueur.
     */
    public void affiche() {
        System.out.println("JOUEUR:\n" + nom);
        System.out.println("\nCLASSE:\n" + perso.getNomClasse());
        System.out.println("\nPOSITION:");
        perso.getPos().affiche();
    }

    /**
     * Méthode pour afficher l'inventaire du joueur.
     */
    public void afficheInventaire() {
        System.out.println("L'inventaire du joueur contient :");
        int k = 1;
        for (Objet o : this.inventaire) {
            System.out.println("[" + k + "] : " + o.getNom());
            k++;
        }
    }

    /**
     * Méthode pour afficher l'inventaire du joueur de manière simplifiée.
     */
    public void afficheInventaireSimple() {
        ArrayList<String> res = new ArrayList<>();
        for (Objet o : this.inventaire) {
            res.add(o.getNomClasse());
        }
        System.out.println(res);
    }

    /**
     * Redéfinition de la méthode toString pour afficher les informations du joueur.
     */
    @Override
    public String toString() {
        String s = "Joueur";
        s += " ";
        return (s + this.perso.toString());
    }

    // --------------------------
    // Getters & Setters
    // --------------------------

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Personnage getPerso() {
        return perso;
    }

    public String getTypeClavier() {
        return typeClavier;
    }

    public ArrayList<Objet> getInventaire() {
        return inventaire;
    }

    public void setPerso(Personnage perso) {
        this.perso = perso;
    }
    
    
}
