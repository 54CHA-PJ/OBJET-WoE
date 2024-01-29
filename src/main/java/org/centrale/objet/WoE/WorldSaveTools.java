package org.centrale.objet.WoE;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe WorldSaveTools offre des outils pour initialiser, enregistrer et jouer une partie de WoECN
 * 
 * @author ESPINOZA Mario.
 * @author CRUZ Sacha.
 */
public class WorldSaveTools {
    
     /**
      * Constructeur de la classe WorldSaveTools, permet d'accéder à tous les outils pour faire fonctionner le jeu
      */
    WorldSaveTools(){}

    // --------------------------------------------------------------------------------------------------------
    // Méthodes
    // --------------------------------------------------------------------------------------------------------

    /**
     * Menu principal du jeu, initialise le monde sur lequel le jeu va se dérouler
     * @throws IOException Exception
     */
    public static void init() throws IOException {
        String nomFichier1 = "Sauvegarde-WoE.txt";
        String nomFichier2 = "Sauvegarde-WoE-2.txt";
        World world = new World();

        System.out.println("\n=============================");
        System.out.println("World of ECN - Initialisation ");
        System.out.println("=============================\n");
        System.out.println("Menu principal :");
        System.out.println("\n[1] Nouveau Monde\n[2] Ouvrir sauvegarde 1(exemple du TP6) : 'Sauvegarde-WoE.txt'\n[3] Ouvrir sauvegarde 2(sauvegarde locale) : 'Sauvegarde-WoE-2.txt'\n[4] Ouvrir une autre sauvegarde (sauvegarde locale)\n");

        int i = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                i = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Réessayez svp :\n");
            }
        } while (i < 1);

        switch (i) {
            case 1:
                System.out.println("\nGénération du nouveau monde");
                world.init();
                jeu(world);
                break;
            case 2:
                System.out.println("\nOuverture de la sauvegarde 1 :" + nomFichier1);
                try {
                    populate(world, read(nomFichier1));
                    jeu(world);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("\nFermeture du jeu");
                }
                break;
            case 3:
                System.out.println("\nOuverture de la sauvegarde 2 :" + nomFichier2);
                System.out.println();
                try {
                    populate(world, read(nomFichier2));
                    jeu(world);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("\nFermeture du jeu");
                }
                break;
            case 4:
                Scanner sc = new Scanner(System.in);
                System.out.println("\nEcrivez le nom de la sauvegarde :");
                String nomFichier = sc.nextLine();
                System.out.println("\nOuverture de : " + nomFichier);
                System.out.println();
                try {
                    populate(world, read(nomFichier));
                    jeu(world);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("\nFermeture du jeu");
                }
                break;
            default:
                System.out.println("\nFermeture du jeu");

        }
    }

    /**
     * Boucle principale du jeu, incrémente le nombre de tours à chaque tour de boucle
     * La boucle s'arrête dans deux cas:
     *  - Le joueur arrête délibérément le jeu
     *  - Le joueur n'a plus de points de vie
     * @param w le monde où prend place le jeu
     * @throws IOException Exception
     */
    public static void jeu(World w) throws IOException{
        System.out.println("\n -------------------- DEBUT DU JEU -------------------- \n");
            int tour = 0;
            Boolean jeu;
            do {
                jeu = w.tour_de_jeu(tour);
                tour++;
            } while (jeu);
    }

    /**
     * Lit le fichier et donne une version plus manipulable sous forme de liste de 
     * @param nomFichier le nom du fichier à lire
     * @return la liste des lignes, chacune de type String
     */
    public static ArrayList<String> read(String nomFichier) {
        String ligne;
        ArrayList<String> lignesFichier = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(nomFichier);
            BufferedReader br = new BufferedReader(fileReader);
            while ((ligne = br.readLine()) != null) {
                //System.out.println(ligne);
                lignesFichier.add(ligne);
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("\n=========== ERREUR ===========\n");
            System.out.println("Assurez-vous d'avoir ce fichier dans le répértoire du jeu !");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lignesFichier;
    }


    /**
     * Lit les données du fichier de sauvegarde, remplit monde avec les entités correspondantes
     * et remplit l'inventaire du joueur principal.
     * @param w Le monde à peupler avec les entités lues depuis le fichier de sauvegarde.
     * @param texte Les données lues depuis le fichier de sauvegarde.
     */
    public static void populate(World w, ArrayList<String> texte){
        
        // --------------------------------------------------------------------------------------------------------
        // Dimensions
        
        String sLarg;
        String sHaut;
                
        // Si la dimension a 2 chiffres, pas d'erreur
        // Sinon, la dimension a 1 seul chiffre
        try{
            sLarg = texte.get(0).substring(8, 10);
        } catch (Exception e) {
            sLarg = texte.get(0).substring(8, 9);
        }
        // Si la dimension a 2 chiffres, pas d'erreur
        // Sinon, la dimension a 1 seul chiffre
        try{
            sHaut = texte.get(1).substring(8, 10);
        } catch (Exception e) {
            sHaut = texte.get(1).substring(8, 9);
        }
        int larg = Integer.parseInt(sLarg);
        int haut = Integer.parseInt(sHaut);
        w.setLargeurMonde(larg);
        w.setHauteurMonde(haut);

        // --------------------------------------------------------------------------------------------------------
        // Déclaration des variables
        
        ArrayList<Object>   listatt;  
        Joueur              mainJoueur  = w.getPlayer1();
        ArrayList<Creature> listecrea   = w.getListeCreatures();  
        ArrayList<Objet>    listeobj    = w.getListeObjets(); 
        ArrayList<Point2D>  positions   = w.getPositionsOccupees(); 
        ArrayList<Objet>    inventaire  = mainJoueur.getInventaire();
        
        // FORMES DES LA LISTE "listatt" :
        // (On pourrait optimiser l'ordre des attributs pour que les fonctions soient moins lourdes)
        //                       0    1       2      3     4       5        6        7          8     9       10   
        // Archer           - Classe nom    ptVie degAtt ptPar   pageAtt pagePar distAttMax     x     y    nbFleches
        // Guerrier         - Classe nom    ptVie degAtt ptPar   pageAtt pagePar distAttMax     x     y 
        // Paysan           - Classe nom    ptVie degAtt ptPar   pageAtt pagePar distAttMax     x     y 
        // Loup             - Classe ptVie  degAtt ptPar pageAtt pagePar distAtt    x           y 
        // Lapin            - Classe ptVie  degAtt ptPar pageAtt pagePar distAtt    x           y 
        // Nuage            - Classe nom    toxici x     y  
        // PotionSoin       - Classe nom    gPtVie x     y 
        // Epee             - Classe nom    degAtt x     y       portee
        // Tajine           - Classe nom    tours  degAtt  ptPar   x       y
        // Champi           - Classe nom    tours  degAtt  ptPar   x       y
        
            int count = 0;
        for (String ligne : texte) {
            System.out.println(ligne);
            count+=1;
            listatt = stringToList(ligne);
            String nomClasse = (String) listatt.get(0);
            switch (nomClasse) {
                case "" : {break;} 
                case "Guerrier" : {
                    Guerrier g = new Guerrier();
                    g.readAttributs(listatt);
                    listecrea.add(g);
                    positions.add(g.getPos());
                    break;
                }
                case "Archer" : {
                    Archer a = new Archer();
                    a.readAttributs(listatt);
                    listecrea.add(a);
                    positions.add(a.getPos());
                    break;
                }
                case "Paysan" : {
                    Paysan p = new Paysan();
                    p.readAttributs(listatt);
                    listecrea.add(p);
                    positions.add(p.getPos());
                    break;
                }
                case "Loup" : {
                    Loup l = new Loup();
                    l.readAttributs(listatt);
                    listecrea.add(l);
                    positions.add(l.getPos());
                    break;
                }
                case "Lapin" : {
                    Lapin lap = new Lapin();
                    lap.readAttributs(listatt);
                    listecrea.add(lap);
                    positions.add(lap.getPos());
                    break;
                }
                case "NuageToxique" : {
                    NuageToxique nuage = new NuageToxique();
                    nuage.readAttributs(listatt);
                    listeobj.add(nuage);
                    positions.add(nuage.getPos());
                    break;
                }
                case "PotionSoin" : {
                    PotionSoin potion = new PotionSoin();
                    potion.readAttributs(listatt);
                    listeobj.add(potion);
                    positions.add(potion.getPos());
                    break;
                }
                case "Epee" : {
                    Epee epee = new Epee();
                    epee.readAttributs(listatt);
                    listeobj.add(epee);
                    positions.add(epee.getPos());
                    break;
                }
                case "TajinePoulet" : {
                    TajinePoulet poulet = new TajinePoulet();
                    poulet.readAttributs(listatt);
                    listeobj.add(poulet);
                    positions.add(poulet.getPos());
                    break;
                }
                case "ChampiVenimeux" : {
                    ChampiVenimeux champi = new ChampiVenimeux();
                    champi.readAttributs(listatt);
                    listeobj.add(champi);
                    positions.add(champi.getPos());
                    break;
                }
                case "Joueur" :{
                    String nomClasseJoueur = (String) listatt.get(1);
                    listatt.remove(0);
                    switch (nomClasseJoueur) {
                        case "Guerrier" : {
                            Guerrier g = new Guerrier();
                            g.readAttributs(listatt);
                            listecrea.add(g);
                            positions.add(g.getPos());
                            mainJoueur.setPerso(g);
                            break;
                        }
                        case "Archer" : {
                            Archer a = new Archer();
                            a.readAttributs(listatt);
                            listecrea.add(a);
                            positions.add(a.getPos());
                            mainJoueur.setPerso(a);
                            break;
                        }
                        case "Paysan" : {
                            Paysan p = new Paysan();
                            p.readAttributs(listatt);
                            listecrea.add(p);
                            positions.add(p.getPos());
                            mainJoueur.setPerso(p);
                            break;
                        }
                        default : {
                            System.out.println("Erreur à la lecture de la ligne" + count);
                            count -=1;
                        }
                    }
                    break;
                }
                case "Inventaire" : {
                    String nomClasseJoueur = (String) listatt.get(1);
                    switch (nomClasseJoueur) {
                        case "PotionSoin" : {
                            PotionSoin potion = new PotionSoin();
                            potion.readAttributsInv(listatt);
                            inventaire.add(potion);
                            break;
                        }
                        case "Epee" : {
                            Epee epee = new Epee();
                            epee.readAttributsInv(listatt);
                            inventaire.add(epee);
                            break;
                        }
                        case "TajinePoulet" : {
                            TajinePoulet poulet = new TajinePoulet();
                            poulet.readAttributsInv(listatt);
                            inventaire.add(poulet);
                            break;
                        }
                        case "ChampiVenimeux" : {
                            ChampiVenimeux champi = new ChampiVenimeux();
                            champi.readAttributsInv(listatt);
                            inventaire.add(champi);
                            break;
                        }
                        default : {
                            System.out.println("Erreur à la lecture de la ligne" + count);
                            count -=1;
                        }
                    }
                    break;
                }
                
                default : {
                    System.out.println("Erreur à la lecture de la ligne" + (count+2));
                    count -=1;
                }
            }
        }
        System.out.println("\n=============================");
        System.out.println(count + " entités créées avec succès");
        System.out.println("=============================\n\n");
    }

    /**
     * Transforme une chaîne de caractères en une liste d'objets, en essayant de convertir les parties en entiers.
     * @param input La chaîne de caractères à transformer en liste d'objets.
     * @return Une liste d'objets résultant de la transformation de la chaîne d'entrée.
     */
    public static ArrayList<Object> stringToList(String input) {
        String[] parts = input.split(" ");
        ArrayList<Object> list = new ArrayList<>();
        for (String part : parts) {
            try {
                // Si c'est un entier, pas d'erreur
                int intValue = Integer.parseInt(part);
                // On convertit en int
                list.add(intValue);
            } catch (NumberFormatException e) {
                // Sinon, c'est une chaîne de caractères
                list.add(part);
            }
        }
        return list;
    }
}