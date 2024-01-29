package org.centrale.objet.WoE;
//@author CRUZ Sacha
//@author ESPINOZA Mario

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * La classe World représente le monde dans lequel évoluent les créatures.
 * 
 * @author ESPINOZA Mario.
 * @author CRUZ Sacha.
 */
public class World {

    // --------------------------------------------------------------------------------------------------------
    // Attributs
    // --------------------------------------------------------------------------------------------------------
    private int hauteurMonde = 10;
    private int largeurMonde = 15;
    private ArrayList<Point2D> positionsOccupees;    // Liste des positions occupées
    private ArrayList<Creature> listeCreatures;      // Liste des créatures présentes dans le monde
    private ArrayList<Objet> listeObjets;            // Liste des objets présents dans le monde 
    private Joueur player1;                          // Joueur principal

    // --------------------------------------------------------------------------------------------------------
    // Constructeurs
    // --------------------------------------------------------------------------------------------------------
    public World() {
        player1 = new Joueur();
        positionsOccupees = new ArrayList<>();
        listeCreatures = new ArrayList<>();
        listeObjets = new ArrayList<>();
        
    }
 
    // --------------------------------------------------------------------------------------------------------
    // Méthodes
    // --------------------------------------------------------------------------------------------------------

    public void init() {
        Random rand = new Random();
        // Choisir type clavier
        player1.choisirPersonnageJouable(this);
        // Définition des limites du monde
        defMonde();
        // Constantes de quantité par classe
        int nbGu = 3 + rand.nextInt((largeurMonde + hauteurMonde) / 3);
        int nbAr = 3 + rand.nextInt((largeurMonde + hauteurMonde) / 6);
        int nbPa = 3 + rand.nextInt((largeurMonde + hauteurMonde) / 6);
        int nbLo = 3 + rand.nextInt((largeurMonde + hauteurMonde) / 6);
        int nbLa = 3 + rand.nextInt((largeurMonde + hauteurMonde) / 6);
        int nbNt = 3 + rand.nextInt((largeurMonde + hauteurMonde) / 2);
        int nbEp = 3 + rand.nextInt((largeurMonde + hauteurMonde) / 6);
        int nbPs = 3 + rand.nextInt((largeurMonde + hauteurMonde) / 4);
        int nbTp = 3 + rand.nextInt((largeurMonde + hauteurMonde) / 10);
        int nbCv = 3 + rand.nextInt((largeurMonde + hauteurMonde) / 10);
        // Initialisation des elements par classe
        ajoutElements(1, nbGu, listeCreatures);     // Ajoute Guerriers à listeCreatures
        ajoutElements(2, nbAr, listeCreatures);     // Ajoute Archers à listeCreatures
        ajoutElements(3, nbPa, listeCreatures);     // Ajoute Paysans à listeCreatures
        ajoutElements(4, nbLo, listeCreatures);     // Ajoute Loups à listeCreatures
        ajoutElements(5, nbLa, listeCreatures);     // Ajoute Lapins à listeCreatures
        ajoutElements(6, nbNt, listeObjets);        // Ajoute Nuages Toxiques à listeObjets
        ajoutElements(7, nbEp, listeObjets);        // Ajoute Épées à listeObjets
        ajoutElements(8, nbPs, listeObjets);        // Ajoute Potions de soin à listeObjets
        ajoutElements(9, nbTp, listeObjets);        // Ajoute TajinePoulet à listeObjets
        ajoutElements(10, nbCv, listeObjets);       // Ajoute ChampiVenimeux à listeObjets
        // Répartition des élélents dans les limites du monde
        distribAleatoire();
    }

    private void defMonde() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEntrez la dimension du monde : ");
        System.out.println("(dimension > 6)");
        try {
            int dim = sc.nextInt();
            this.hauteurMonde = dim;
            this.largeurMonde = dim;
        } catch (Exception e) {
            System.out.println("Ecrivez uniquement un entier :\n(Exemple : 15)");
            defMonde();
        }
    }

    /**
     * Ajoute des entités du type (1,2, ..., 10) spécifié à une liste d'éléments
     * de type T (Objet ou Creature)
     * Liste des types : 
     * 1 : Guerrier 
     * 2 : Archer 
     * 3 : Paysan 
     * 4 : Loup 
     * 5 : Lapin 
     * 6 : NuageToxique 
     * 7 : Epee 
     * 8 : PotionSoin 
     * 9 : TajinePoulet 
     * 10 : ChampiVenimeux
     *
     * @param <T> type de la liste, soit Objet (pour listeObjets) soit Creature
     * (pour listeCreatures)
     * @param type type de l'entité (1, ..., 10)
     * @param qte quantité d'entités de cette classe à ajouter
     * @param list liste où ajouter ces entités
     */
    public static <T extends ElementDeJeu> void ajoutElements(int type, int qte, List<T> list) {
        for (int i = 0; i < qte; i++) {
            ElementDeJeu elem = null;
            switch (type) {
                case 1:
                    elem = new Guerrier();
                    break;
                case 2:
                    elem = new Archer();
                    break;
                case 3:
                    elem = new Paysan();
                    break;
                case 4:
                    elem = new Loup();
                    break;
                case 5:
                    elem = new Lapin();
                    break;
                case 6:
                    elem = new NuageToxique();
                    break;
                case 7:
                    elem = new Epee();
                    break;
                case 8:
                    elem = new PotionSoin();
                    break;
                case 9:
                    elem = new TajinePoulet();
                    break;
                case 10:
                    elem = new ChampiVenimeux();
                    break;
                default:
                    System.out.println("Type d'entité non reconnu : " + type);
                    break;
            }
            if (elem != null) {
                elem.attributsAleatoires();
                list.add((T) elem); // On s'assure d'avoir le type T
            }
        }
    }

    public boolean estOQP(Point2D p) {
        return positionsOccupees.contains(p);
    }

    public boolean estOQPCreatures(Point2D p) {
        for (Creature c : listeCreatures) {
            if (c.getPos().equals(p)) {
                return true;
            }
        }
        return false;
    }

    public void assignPosAlea(ElementDeJeu elem) {
        Random random = new Random();
        Point2D pTest;
        do {
            pTest = new Point2D(random.nextInt(largeurMonde), random.nextInt(hauteurMonde));
        } while (estOQP(pTest));
        positionsOccupees.add(pTest);
        elem.setPos(pTest);
    }

    /**
     * Crée un monde aléatoire en plaçant les créatures à des positions
     * aléatoires.
     */
    public void distribAleatoire() {
        assignPosAlea(player1.getPerso());
        for (ElementDeJeu elem : listeCreatures) {
            assignPosAlea(elem);
        }
        for (ElementDeJeu elem : listeObjets) {
            assignPosAlea(elem);
        }
    }

    public void afficheCarte() {
        StringBuilder result = new StringBuilder();
        Map<Point2D, Character> abbreviations = new HashMap<>();
        // On met d'abord les objets, comme ça si il y a une créature à la même place alors elle remplace sa position
        for (Objet o : listeObjets) {
            String nomc = o.getNomClasse();
            Character abrev = nomc.toLowerCase().charAt(0);
            switch (nomc) {
                case "NuageToxique" :
                    abbreviations.put(o.getPos(), '#');
                    break;
                case "PotionSoin" :
                    abbreviations.put(o.getPos(), 's');
                    break;
                default :
                    abbreviations.put(o.getPos(), abrev);
            }
        }
        for (Creature c : listeCreatures) {
            String nomc = c.getNomClasse();
            Character abrev = nomc.charAt(0);
            switch (nomc) {
                case "Lapin" :
                    abbreviations.put(c.getPos(), 'B');
                    break;
                case "Loup" :
                    abbreviations.put(c.getPos(), 'W');
                    break;
                default :
                    abbreviations.put(c.getPos(), abrev);
            }
        }
        result.append("    ");
        for (int x = 0; x < largeurMonde; x++) {
            result.append(String.format("%3d", x));
        }
        result.append("\n    ");
        result.append("___".repeat(Math.max(0, largeurMonde)));
        for (int y = 0; y < hauteurMonde; y++) {
            result.append("\n");
            result.append(String.format("%2d", y)).append(" | ");
            for (int x = 0; x < largeurMonde; x++) {
                Point2D point = new Point2D(x, y);
                if (this.estOQP(point)) {
                    if (player1.getPerso().getPos().equals(point)) {
                        result.append("(X)");
                    } else {
                        Character abbreviation = abbreviations.get(point);
                        result.append(" ").append(abbreviation != null ? abbreviation : ".").append(" ");
                    }
                } else {
                    result.append(" . ");
                }
            }
        }
        result.append("\n\n====================================================\n                Boîte de texte :             \n");
        System.out.print(result);
    }

    /**
     *
     * @param nbTour : le tour du jeu
     * @return Boolean : indique si on continue le jeu ou pas (à la fin du tour)
     * @throws java.io.IOException exception
     */
    public Boolean tour_de_jeu(int nbTour) throws IOException {

        // ---------------------------------------------------------------
        // Variables initiales
        
        Personnage mainPerso = player1.getPerso();
        Point2D mainPersoPos = mainPerso.getPos();
        ArrayList<Nourriture> effets = mainPerso.getEffets();  // Liste des effets actifs
        List<Objet> inv = player1.getInventaire();
        String typeClavier = player1.getTypeClavier();
        Scanner sc = new Scanner(System.in);

        boolean attq = false;      // repond à : le joueur a été attaqué dpendant ce tour ?
        boolean finDuTour = false; // repond à : le tour est fini ?
        boolean finDuJeu = false;  // repond à : le jeu est fini ?
        

        // ---------------------------------------------------------------
        // Mise à jour des Effets
        // Généralisable à toutes les créatures
        // Pour l'instant seulement pour le personnage principal
        
        int gainPtPar = 0;
        int gainDegAtt = 0;
        Nourriture effetaRetirer = null;
        for (Nourriture n : effets) {
            int t = n.getNbToursEffet();
            if (t < 1) {
                gainPtPar -= n.getGainPtPar();
                gainDegAtt -= n.getGainDegAtt();
                effetaRetirer = n;
            } else {
                n.setNbToursEffet(t - 1);
            }
        }
        effets.remove(effetaRetirer);
        // On applique les effets au joueur
        mainPerso.setDegAtt(mainPerso.getDegAtt() + gainDegAtt);
        mainPerso.setPtPar(mainPerso.getPtPar() + gainPtPar);

        if (mainPerso.getPtVie() < 1) {
            System.out.println("Vous avez perdu !");
            System.out.println("====================================================");
            finDuTour = true;
            finDuJeu = true;
        }
        while (!finDuTour) {
            
            // ---------------------------------------------------------------
            // " Interface utilisateur "
            
            System.out.println("\nTOUR : " + nbTour);
            System.out.println();
            System.out.println("Choisissez une action :");
            switch (typeClavier) {
                case "azerty" :
                    System.out.println("[z,q,s,d]");
                    break;
                case "querty" :
                    System.out.println("[w,a,s,d]");
                    break;
                default : {}
            }
            System.out.println("[c]    : Combattre\n[x]    : Inventaire\n[r]    : Afficher Personnage\n[b]    : Afficher Entités\n[n]    : Sauvegarder la partie\n[v]    : Sauvegarde Rapide\n[f]    : Fermer le Jeu\n");
            int action = -1;
            // afficher le monde
            this.afficheCarte();
            // Choix de l'action :
            // W,A,S,D :  Deplacement
            // C - Combat
            // X - Utiliser / Consommer un objet
            // R - Afficher le perso
            // B - Afficher les entites du monde
            // V - Sauvegarde rapide
            // N - Sauvegarder le monde
            // F - Fermer le jeu
            
            // ---------------------------------------------------------------
            // Choix de l'utilisateur

            char bouger = ' ';
            while (action == -1) {
                String input = sc.nextLine().toLowerCase(); // Convert input to lowercase here
                switch (input) {
                    case "z":
                    case "q":
                    case "w":
                    case "a":
                    case "s":
                    case "d":
                        bouger = input.charAt(0);
                        action = 1;
                        break;
                    case "c":
                        action = 2;
                        break;
                    case "x":
                        action = 3;
                        break;
                    case "f":
                        action = 9;
                        break;

                    case "b":
                        action = 7;
                        break;

                    case "r":
                        action = 8;
                        break;

                    case "v":
                        action = 6;
                        break;
                        
                    case "n":
                        action = 5;
                        break;

                    default:
                        System.out.println("Votre choix :");
                }
            }
            switch (action) {
                // DEPLACE
                case 1  : {
                    
                    // ---------------------
                    // DEPLACEMENT DU JOUEUR
                    // ---------------------
                    
                    Point2D nouvellePos = positionInput(bouger, mainPersoPos, typeClavier); // Controler le mouvement avec les touches 'w', 'a', 's', 'd' || 'z', 'q', 's', 'd' 
                    // Si on peut se deplacer vers cette case
                    if (peutDeplacer(nouvellePos)) {
                        positionsOccupees.remove(mainPersoPos);
                        positionsOccupees.add(nouvellePos);
                        mainPerso.setPos(nouvellePos);
                        // Fin du tour du joueur
                        finDuTour = true;
                        
                        // ---------------------------
                        // INTERACTION AVEC LES OBJETS
                        // ---------------------------
                        
                        Objet objetaRetirer = null; // Cette variable sert à retenir l'objet à retirer dans la liste d'objets, s'il y en a un qui est pris par le joueur
                        for (Objet o : listeObjets) {
                            // S'il y a un objet à cette position
                            if (o.getPos().equals(nouvellePos)) {
                                // Si c'est un nuage toxique :
                                // Par constrtuction il y a au plus un objet dans cette case
                                if (Objects.equals(o.getNom(), "NuageToxique")) {
                                    NuageToxique n = (NuageToxique) o;
                                    int ouch = n.getToxicite();
                                    System.out.println(" /!\\ ATTENTION /!\\ \nVous vous trouvez à l'intérieur d'un nuage toxique !");
                                    System.out.println("Vous perdez " + ouch + " points de Vie");
                                    mainPerso.addPtVie(-1 * ouch);
                                }
                                // Si c'est un objet autre que nuage toxique :
                                else {
                                    // On retire l'opjet du monde
                                    objetaRetirer = o;
                                    positionsOccupees.remove(o.getPos());
                                    o.setPos(null);
                                    // On ajoute l'objet a l'inventaire
                                    player1.getInventaire().add(o);
                                    System.out.println("Vous avez ramassé : " + o.getNomClasse());
                                }
                                break; // Par constrtuction il y a au plus un objet dans cette case
                            }
                        }
                        listeObjets.remove(objetaRetirer); // On enleve l'objet pris de la liste d'objets
                        
                        // ---------------------------
                        // TOUR DES CREATURES
                        // ---------------------------
                        
                        // Les créatures attaquent le joueur ou se déplacent
                        attq = tourCreatures(mainPerso, attq, nouvellePos);
                        // Les nuages toxiques aussi
                        for (Objet o : listeObjets) {
                            if (Objects.equals(o.getNom(), "NuageToxique")){
                                ((NuageToxique) o).seDeplacer(this);
                            }
                        }
                        
                    // Si on a pas réussi à se déplacer :
                    } else {
                        System.out.println("Déplacement invalide, réessayez.");
                        // Retour à la boucle initiale, le tour n'est pas encore terminé
                    }
                    break;
                }

                // COMBAT
                case 2 : {
                    
                    // ---------------------------
                    // TOUR DU JOUEUR
                    // ---------------------------
                    
                    ArrayList<ElementDeJeu> listePortee = new ArrayList<>();
                    System.out.println("Les créatures à portée d'attaque sont :");
                    // Compter les créatures à portée d'attaque
                    int i = 1;
                    for (Creature c : listeCreatures) {
                        Point2D posCrea = c.getPos();
                        // Si la distance entre la créature et le joueur est dans l'intervalle [1, distAttMax]
                        // On pourrait ajouter  
                        if (posCrea.distance(mainPersoPos) <= mainPerso.getDistAttMax() && c.getPos().distance(mainPersoPos) > 0 && c instanceof Combattant) {
                            listePortee.add(c);
                            System.out.println("[" + i + "] : " + c.getNomClasse() + " à la position " + "[" + posCrea.getX() + " , " + posCrea.getY() + "]");
                            // c.affiche();
                            i++;
                        }
                    }
                    for (Objet o : listeObjets) {
                        if (o instanceof NuageToxique) {
                            Point2D posNuage = o.getPos();
                            // Si la distance entre le nuage toxique et le joueur est dans l'intervalle [0, 1]
                            if (posNuage.distance(mainPersoPos) <= 1 && o.getPos().distance(mainPersoPos) > 0) {
                                listePortee.add(o);
                                System.out.println("[" + i + "] : Nuage Toxique à la position " + "[" + posNuage.getX() + " , " + posNuage.getY() + "]");
                                // c.affiche();
                                i++;
                            }
                        }
                    }
                    // S'il y a une ou plus entités avec qui on peut combattre :
                    if (i != 0) {
                        System.out.println("Choisissez une créature à combattre");
                        System.out.println("[0] Annuler");
                        int taille = listePortee.size();
                        int choix;
                        do {
                            System.out.println("\nVotre choix : \n");
                            choix = sc.nextInt();
                        } while (choix > taille || choix < 0);
                        if (choix != 0) {
                            // Combattre la créature choisie
                            ElementDeJeu defenseur = listePortee.get(choix - 1);
                            ((Combattant) mainPerso).combat(defenseur);
                            // Check if the defenseur is an instance of Creature
                            if (defenseur instanceof Creature) {
                                Creature c = (Creature) defenseur ;
                                if (c.getPtVie() < 1) {
                                    positionsOccupees.remove(c.getPos());
                                    listeCreatures.remove(c);
                                }
                            }
                            // Fin du tour du joueur
                            finDuTour = true; 

                            // ---------------------------
                            // TOUR DES CREATURES
                            // ---------------------------

                            // Les créatures attaquent le joueur ou se déplacent
                            attq = tourCreatures(mainPerso, attq, mainPersoPos);
                            // Les nuages toxiques aussi
                            for (Objet o : listeObjets) {
                                if (o instanceof NuageToxique){
                                    ((NuageToxique) o).seDeplacer(this);
                                }
                            }

                        // Si Choix = 0 : Retour à la boucle initiale
                        }
                    } else {
                        // Si pas de créature à portée : Retour à la boucle initiale
                        System.out.println("Pas de créature à portée");
                    }
                    break;
                }

                // INVENTAIRE
                case 3 : {
                    
                    // ---------------------------
                    // TOUR DU JOUEUR
                    // ---------------------------
                        
                    player1.afficheInventaire();
                    System.out.println("Choisissez un objet à utiliser :");
                    System.out.println("(Tapez 0 pour ne rien faire)\n");
                    int choix;
                    do {
                        System.out.print("Votre choix : ");
                        if (sc.hasNextInt()) {
                            choix = sc.nextInt();
                            if (choix < 0 || choix > inv.size()) {
                                System.out.println("Touche invalide, réessayez.");
                            }
                        } else {
                            sc.next(); // Prévoir le cas d'un input non entier
                            choix = -1;
                            System.out.println("Touche invalide, réessayez.");
                        }
                    } while (choix < 0);
                    if (choix != 0) {
                        // On récupère l'objet choisi
                        choix -= 1;
                        Objet obj = inv.get(choix);
                        if (obj instanceof Nourriture) {
                            Nourriture n = (Nourriture) obj;
                            // L'objet devient actif
                            inv.remove(obj);
                            effets.add(n);
                            // On active l'effet au joueur
                            int gDegAtt = n.getGainDegAtt();
                            int gPtPar = n.getGainPtPar();
                            mainPerso.setDegAtt(Math.max(0, mainPerso.getDegAtt() + gDegAtt));
                            mainPerso.setPtPar(Math.max(0, mainPerso.getPtPar() + gPtPar));
                            System.out.println("\nVous avez consommé : " + n.getNom());
                            System.out.println("    Dégâts d'attaque temporairement augmentés de : " + gDegAtt);
                            System.out.println("    Points de parade temporairement augmentés de : " + gPtPar);
                            System.out.println("    Durée des effets : " + n.getNbToursEffet());
                            finDuTour = true; // Fin du tour
                        } else if (obj instanceof PotionSoin) {
                            PotionSoin p = (PotionSoin) obj;
                            // L'objet devient actif
                            inv.remove(obj);
                            // On donne l'effet au joueur
                            int gVie = p.getQuantiteSoin();
                            mainPerso.setPtVie(mainPerso.getPtVie() + gVie);
                            System.out.println("\nVous avez consommé : " + p.getNom());
                            System.out.println("    Vous avez gagné " + gVie + " Points de Vie !");
                            finDuTour = true; // Fin du tour
                        } else {
                            System.out.println("Vous ne pouvez pas consommer cet élément");
                            // Retour à la boucle initiale
                        }

                        // ---------------------------
                        // TOUR DES CREATURES
                        // ---------------------------
                        
                        // Les créatures attaquent le joueur ou se déplacent
                        attq = tourCreatures(mainPerso, attq, mainPersoPos);
                        // Les nuages toxiques aussi
                        for (Objet o : listeObjets) {
                            if (o instanceof NuageToxique){
                                ((NuageToxique) o).seDeplacer(this);
                            }
                        }
                        
                    }
                    break;
                    // Si Choix = 0 : Retour à la boucle initiale
                } 
                // SAUVEGARDE AVEC NOM
                case 5 : {
                    System.out.println("Ecrivez le nom de la Sauvegarde");
                    String nomFichier2 = sc.nextLine();
                    this.save(nomFichier2);
                    // Et on retorune à la boucle initiale
                    break;
                }
                // SAUVEGARDE RAPIDE
                case 6 : {
                    String nomFichier = "Sauvegarde-WoE-2.txt";
                    this.save(nomFichier);
                    // Et on retorune à la boucle initiale
                    break;
                }
                // AFFICHER LES ENTITES
                case 7 : {
                    afficheEntites();
                    // Et on retorune à la boucle initiale
                    break;
                }
                // AFFICHER LE PERSONNAGE
                case 8 : {
                    System.out.println("Joueur : " + player1.getNom());
                    System.out.println("\n------- MON PERSONNAGE : -------\n");
                    mainPerso.affiche();
                    System.out.println("\nINVENTAIRE :");
                    player1.afficheInventaireSimple();
                    System.out.println("\nEFFETS :");
                    mainPerso.afficheEffetseSimple();
                    // Et on retorune à la boucle initiale
                    break;
                }
                // FIN DU JEU
                case 9 : {
                    finDuTour = true; // Fin du tour
                    finDuJeu = true;  // Fin du jeu*
                    System.out.println("Merci d'avoir joué !");
                    break;
                }
            }
            System.out.print("====================================================\n");
        }
        return (!finDuJeu);
    }

    private boolean tourCreatures(Personnage mainPerso, boolean attq, Point2D nouvellePos) {
        for (Creature c : listeCreatures) {
            if (!c.equals(mainPerso)){
                Point2D creaPos = c.getPos();
                // Dans l'ordre : Si la créature se trouve ajacente au joueur ET que le joueur n'a pas encore été attaqué ET que la creature peut combattre
                // ALORS elle combat le joueur
                if (posAdj(creaPos).contains(nouvellePos) && !attq && c instanceof Combattant) {
                    System.out.println("/!\\ Un " + c.getNomClasse() + " vous attaque!");
                    ((Combattant) c).combat(mainPerso);
                    attq = true;
                } else  // SINON elle se déplace
                    c.seDeplacer(this);
            }
        }
        return attq;
    }


    /**
     *
     * @param nouvellePosition la position envisagpee
     * @return si c'est possible ou non de se déplacer à cette position
     */
    public Boolean peutDeplacer(Point2D nouvellePosition) {
        if (nouvellePosition.getX() < 0  
            || nouvellePosition.getX() >= this.getLargeurMonde()  
            || nouvellePosition.getY() < 0 
            || nouvellePosition.getY() >= this.getHauteurMonde()) {
            return false; // En dehors des limites du monde
        }
        return !(estOQPCreatures(nouvellePosition));
    }
    
    /**
     * Donne les 8 cases adjacentes à une position (x,y)
     * @param posInitiale la position initiale
     * @return la liste des positions adjacentes
     */
    public ArrayList<Point2D> posAdj(Point2D posInitiale) {
        ArrayList<Point2D> listePos = new ArrayList<>();
        int x = posInitiale.getX();
        int y = posInitiale.getY();
        listePos.add(new Point2D(x, y - 1));
        listePos.add(new Point2D(x, y + 1));
        listePos.add(new Point2D(x - 1, y));
        listePos.add(new Point2D(x - 1, y - 1));
        listePos.add(new Point2D(x - 1, y + 1));
        listePos.add(new Point2D(x + 1, y));
        listePos.add(new Point2D(x + 1, y - 1));
        listePos.add(new Point2D(x + 1, y + 1));
        return listePos;
    }

    /**
     * Sauvegarde toutes les données pertinentes d'un monde dans un fichier texte
     * @param nomFichier le nom du fichier où l'on sauvegarde
     * @throws IOException exception
     */
    public void save(String nomFichier) throws IOException {
        FileWriter fileWriter = new FileWriter(nomFichier);
        try (BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write("Largeur " + largeurMonde);
            writer.newLine();
            writer.write("Hauteur " + hauteurMonde);
            writer.newLine();
            for (Creature c : listeCreatures) {
                if (!c.getPos().equals(player1.getPerso().getPos())) {
                    writer.write(c.toString());
                    writer.newLine();
                }
            }
            for (Objet object : listeObjets) {
                writer.write(object.toString());
                writer.newLine();
            }
            writer.write(player1.toString());
            writer.newLine();
            ArrayList<Objet> inventaire = player1.getInventaire();
            for (Objet item : inventaire) {
                writer.write("Inventaire " + item.toStringInv());
                writer.newLine();
            }
        }
        System.out.print("====================================================================\n");
        System.out.println("Monde sauvegardé avec succès sous le nom : " + nomFichier);
        System.out.print("====================================================================\n");
    }
    public Point2D positionInput(Character bouger, Point2D mainPersoPos, String typeClavier) {
        Point2D nouvellePos = mainPersoPos;
        if ("querty".equals(typeClavier)) {
            switch (bouger) {
                case 'w':
                    nouvellePos = new Point2D(mainPersoPos.getX(), mainPersoPos.getY() - 1);
                    break;
                case 'a':
                    nouvellePos = new Point2D(mainPersoPos.getX() - 1, mainPersoPos.getY());
                    break;
                case 's':
                    nouvellePos = new Point2D(mainPersoPos.getX(), mainPersoPos.getY() + 1);
                    break;
                case 'd':
                    nouvellePos = new Point2D(mainPersoPos.getX() + 1, mainPersoPos.getY());
                    break;
            }
        } else if ("azerty".equals(typeClavier)) {
            switch (bouger) {
                case 'z':
                    nouvellePos = new Point2D(mainPersoPos.getX(), mainPersoPos.getY() - 1);
                    break;
                case 'q':
                    nouvellePos = new Point2D(mainPersoPos.getX() - 1, mainPersoPos.getY());
                    break;
                case 's':
                    nouvellePos = new Point2D(mainPersoPos.getX(), mainPersoPos.getY() + 1);
                    break;
                case 'd':
                    nouvellePos = new Point2D(mainPersoPos.getX() + 1, mainPersoPos.getY());
                    break;
            }
        }
        return nouvellePos;
    }
    
    /**
     * Affiche la liste de toutes les entités présentes dans le monde
     */
    public void afficheEntites() {
        System.out.println(" \n------------ CREATURES ------------ ");
        for (Creature c : listeCreatures) {
            c.affiche();
        }
        System.out.println(" \n------------ OBJETS ------------ ");
        for (Objet o : listeObjets) {
            o.affiche();
        }
    }

    // --------------------------------------------------------------------------------------------------------
    //          Getters & Setters
    // --------------------------------------------------------------------------------------------------------
    
    public int getHauteurMonde() {
        return hauteurMonde;
    }

    public int getLargeurMonde() {
        return largeurMonde;
    }

    public void setHauteurMonde(int hauteurMonde) {
        this.hauteurMonde = hauteurMonde;
    }

    public void setLargeurMonde(int largeurMonde) {
        this.largeurMonde = largeurMonde;
    }

    public ArrayList<Point2D> getPositionsOccupees() {
        return positionsOccupees;
    }

    public ArrayList<Creature> getListeCreatures() {
        return listeCreatures;
    }

    public ArrayList<Objet> getListeObjets() {
        return listeObjets;
    }

    public Joueur getPlayer1() {
        return player1;
    }

}
