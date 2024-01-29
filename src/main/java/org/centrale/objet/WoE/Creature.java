package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Random;

/**
 * La classe Creature représente la classe de base pour generer des Personnages
 * ou bien des Monstres
 * 
 * @author ESPINOZA Mario
 * @author CRUZ Sacha
 */
public abstract class Creature extends ElementDeJeu implements Deplacable {

    // --------------------------------------------------------------------------------
    // Attributs
    // --------------------------------------------------------------------------------
    
    private int ptVie;
    private int degAtt;
    private int ptPar;
    private int pageAtt;
    private int pagePar;
    private int distAttMax;
    
    private ArrayList<Nourriture> effets;

    // Généralisable à tous les attributs

    // --------------------------------------------------------------------------------
    // Constructeurs
    // --------------------------------------------------------------------------------
    
    /**
     * Constructeur pour créer un creature avec des attributs spécifiés.
     *
     * @param ptVie Les points de vie      de la créature
     * @param degAtt Les dégâts d'attaque  de la créature
     * @param ptPar Les points de parade   de la créature
     * @param pageAtt La portée d'attaque  de la créature
     * @param pagePar La portée de parade  de la créature
     * @param distAttMax La distance d'attaque maximale  de la créature
     * @param pos La position de la créature
     *
     */
    public Creature(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar,int distAttMax, Point2D pos) {
        super(pos);
        this.ptVie = ptVie;
        this.degAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
        this.distAttMax= distAttMax;
        this.effets = new ArrayList<>();
    }
    
    /**
     * Constructeur de copie pour créer un creature en copiant un autre
     * creature.
     *
     * @param p Le personnage à copier.
     */
    public Creature(Creature p) {
        super(p);
        this.ptVie = p.getPtVie();
        this.degAtt = p.getDegAtt();
        this.ptPar = p.getPtPar();
        this.pageAtt = p.getPageAtt();
        this.pagePar = p.getPagePar();
        this.distAttMax = p.getDistAttMax();
        this.effets = new ArrayList<>();
    }

    /**
     * Constructeur par défaut pour créer un creature avec des valeurs par
     * défaut.
     *
     */
    public Creature() {
        super();
        this.ptVie = 1;
        this.distAttMax = 1;
        this.effets = new ArrayList<>();
    }

    // --------------------------------------------------------------------------------
    // Méthodes
    // --------------------------------------------------------------------------------
    
    /**
     * Affiche les informations du creature. Cette méthode affiche le nom du
     * personnage suivi de ses autres informations.
     */
    @Override
    public void affiche() {
        System.out.println("Classe   = " + this.getNomClasse());
        System.out.println("Pt. Vie  = " + this.ptVie);
        System.out.println("Deg.Att  = " + this.degAtt);
        System.out.println("Pt.Par   = " + this.ptPar);
        System.out.println("Page.Att = " + this.pageAtt);
        System.out.println("Page.Par = " + this.pagePar);
        System.out.println("Dist.Att = " + this.distAttMax);
        System.out.println("       x = " + (this.getPos().getX()));
        System.out.println("       y = " + (this.getPos().getY()));
    }

    /**
     * Fait perdre une munition du type d'arme à distance qu'utilise la créature en question
     * A besoin d'être définie dans les sous-classes si elles attaquent à distance
     */
    public void perdreMunition() {
        System.out.println("Erreur : pas de fonction perdreMunition définie");
    }

    /**
     * Permet à la créature de perdre de la munition.
     * @return 0 car pour l'instant la créature n'a pas d'attribut de munition
     */
    public int getMunition() {
        System.out.println("Erreur : Pas de fonction getMunition définie");
        return 0;
    }
    /**
     * Donne des attributs aléatoires dans un intervalle contrôlé
     */
    @Override
    public void attributsAleatoires() {
        Random rd = new Random();
        // Génère des Points de Vie entre 20 et 50
        this.setPtVie(20 + rd.nextInt(31));
        // Génère des Dégats d'Attaque entre 30 et 70
        this.setDegAtt(30 + rd.nextInt(41));
        // Génère des Points de Parade entre 10 et 30
        this.setPtPar(10 + rd.nextInt(21));
        // Génère un Pourcentage d'Attaque entre 60% et 100%
        this.setPageAtt(60 + rd.nextInt(41));
        // Génère un Pourcentage de Parade réussie entre 20% et 100%
        this.setPagePar(20 + rd.nextInt(81));
    }
    
    /** 
     * Lit les données de sauvegarde et les incorpore dans ses attributs
     * @param listatt la liste des données récupérées dans la sauvegarde
     */
    @Override
    public void readAttributs(ArrayList<Object> listatt) {
        this.setPtVie   ((int) listatt.get(1));
        this.setDegAtt  ((int) listatt.get(2));
        this.setPtPar   ((int) listatt.get(3));
        this.setPageAtt ((int) listatt.get(4));
        this.setPagePar ((int) listatt.get(5));
        this.setDistAttMax((int) listatt.get(6));
        int x = (int) listatt.get(7);
        int y = (int) listatt.get(8);
        this.setPos(new Point2D(x,y));
    }
    
    /**
     * Crée une chîne de caractères contenant toutes les données essentieles de la créature, afin de la sauvegarder
     * @return la chaîne de caractères représentant la créature
     */
    @Override
    public String toString(){
        String s = this.getNomClasse();
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
    
    /**
     * Permet à la créature de se déplacer aléatoirement à une distance maximale de 1 autour de sa position initiale
     * @param w le monde où la créature de séplace
     */
    @Override
    public void seDeplacer(World w) {
        Random rand = new Random();
        ArrayList<Point2D> listePos = w.getPositionsOccupees();
        Point2D currentPosition = this.getPos();
        // Entiers aléatoires parmis {-1 , 0 , +1)
        int rx = rand.nextInt(3) - 1;
        int ry = rand.nextInt(3) - 1;
        // Nouvelles positions si elles sont valides
        int newX = currentPosition.getX() + rx;
        int newY = currentPosition.getY() + ry;
        Point2D newPosition = new Point2D(newX, newY);
        // S'il y a rien dans cette position, se déplacer
        if (!listePos.contains(newPosition)){
            listePos.remove(currentPosition);
            listePos.add(newPosition);
            this.setPos(newPosition);
        }
    }
    
    // --------------------------------------------------------------------------------
    // Getters & Setters
    // --------------------------------------------------------------------------------
    
    /**
     * Affiche la liste des effets actifs sur la créature, avec le nombre de tours restants
     */
    public void afficheEffetseSimple(){
        for (Nourriture n : this.effets) {
            System.out.println(n.getNom() + " : " + "reste " + n.getNbToursEffet() + " tours");
        }
    }
    
    /**
     * Ajoute une quantité de points de vie, qui peut être négative
     * On contrôle que les points de vie de la créature ne soient pas négatifs
     * @param qte la quantité de points à ajouter
     * 
     */
    public void addPtVie(int qte){
        this.ptVie = Math.max(0, this.ptVie + qte);
    }
    
    /**
     * Ajoute une quantité de points d'attaque, qui peut être négative
     * On contrôle que les points d'attaque de la créature ne soient pas négatifs
     * @param qte la quantité de points à ajouter
     * 
     */
    public void addDegAtt(int qte){
        this.degAtt = Math.max(0, this.degAtt + qte);
    }
    
     /**
     * Ajoute une quantité de points de parade, qui peut être négative
     * On contrôle que les points de parade de la créature ne soient pas négatifs
     * @param qte la quantité de points à ajouter
     */
    public void addPtPar(int qte){
        this.ptPar = Math.max(0, this.ptPar + qte);
    }

     /**
     * Ajoute une quantité de pourcentage de parade effective, qui peut être négative
     * On contrôle que les pourcentages soient entre 0 et 100 inclus
     * @param qte la quantité de points à ajouter
     */
    public void addPagePar(int qte){
        this.pagePar = Math.min(100, Math.max(0, this.pagePar + qte));
    }
    
     /**
     * Ajoute une quantité de pourcentage d'attaque réussie, qui peut être négative
     * On contrôle que les pourcentages soient entre 0 et 100 inclus
     * @param qte la quantité de points à ajouter
     */
    public void addPageAtt(int qte){
        this.pagePar = Math.min(100, Math.max(0, this.pagePar + qte));
    }

    // --------------------------------------------------
    //               GETTERS & SETTERS
    // --------------------------------------------------

    /**
     * @return la liste des effets qui sont actifs sur la créature
     */
    public ArrayList<Nourriture> getEffets() {
        return effets;
    }

    /**
     * @param effets liste des effets qui sont actifs sur la créature
     */
    public void setEffets(ArrayList<Nourriture> effets) {
        this.effets = effets;
    }
    

    /**
     * Obtient les Points de Vie de la créature.
     * @return Les Points de Vie.
     */
    public int getPtVie() {
        return ptVie;
    }

    /**
     * Obtient les Dégats d'Attaque de la créature.
     * @return Les Dégats d'Attaque.
     */
    public int getDegAtt() {
        return degAtt;
    }

    /**
     * Obtient les Points de Parade de la créature.
     * @return Les Points de Parade.
     */
    public int getPtPar() {
        return ptPar;
    }

    /**
     * Obtient le Pourcentage d'Attaque de la créature.
     * @return Le Pourcentage d'Attaque.
     */
    public int getPageAtt() {
        return pageAtt;
    }

    /**
     * Obtient le Pourcentage de Parade de la créature.
     * @return Le Pourcentage de Parade.
     */
    public int getPagePar() {
        return pagePar;
    }

    /**
     * Obtient la Distance d'Attaque Maximale de la créature.
     * @return La Distance d'Attaque Maximale.
     */
    public int getDistAttMax() {
        return distAttMax;
    }

    /**
     * Modifie les Points de Vie de la créature.
     * @param ptVie Les nouveaux Points de Vie.
     */
    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    /**
     * Modifie les Dégats d'Attaque de la créature.
     * @param degAtt Les nouveaux Dégats d'Attaque.
     */
    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    /**
     * Modifie les Points de Parade de la créature.
     * @param ptPar Les nouveaux Points de Parade.
     */
    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    /**
     * Modifie le Pourcentage d'Attaque de la créature.
     * @param pageAtt Le nouveau Pourcentage d'Attaque.
     */
    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    /**
     * Modifie le Pourcentage de Parade de la créature.
     * @param pagePar Le nouveau Pourcentage de Parade.
     */
    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    /**
     * Modifie la Distance d'Attaque Maximale de la créature.
     * @param distAttMax La nouvelle Distance d'Attaque Maximale.
     */
    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }
}
