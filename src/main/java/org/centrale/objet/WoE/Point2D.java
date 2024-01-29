package org.centrale.objet.WoE;

/**
 * La classe Point2D représente un point bidimensionnel dans un espace cartésien.
 * Elle permet de gérer les coordonnées x et y d'un point ainsi que de réaliser
 * des opérations courantes sur des points 2D.
 * 
 * @author CRUZ Sacha
 * @author ESPINOZA Mario.
 * 
 */
public class Point2D {
    private int x;
    private int y;

    /**
     * Constructeur pour créer un point 2D avec des coordonnées spécifiées.
     * @param x La coordonnée x du point.
     * @param y La coordonnée y du point.
     */
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructeur de copie pour créer un point 2D en copiant un autre point 2D.
     * @param p Le point 2D à copier pour créer un nouveau point 2D.
     */
    public Point2D(Point2D p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Constructeur par défaut pour créer un point 2D avec des valeurs par défaut (0, 0).
     */
    public Point2D() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Déplace le point selon un déplacement en x et en y donné.
     * 
     * @param dx Le déplacement en x.
     * @param dy Le déplacement en y.
     */
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Affiche les coordonnées du point sous forme "[x ; y]".
     */
    public void affiche() {
        System.out.println("[ " + this.x + " ; " + this.y + " ]");
    }

    /**
     * Calcule la distance entre ce point et un autre point spécifié.
     * @param p Le point avec lequel calculer la distance.
     * @return La distance entre ce point et le point spécifié.
     */
    public float distance(Point2D p) {
        int lx = this.x - p.getX();
        int ly = this.y - p.getY();
        return (int) Math.sqrt(lx * lx + ly * ly);
    }
    
    /**
     * Différencier deux Points 2D par leurs coordonnées (et non pas leur place dans la mémoire)
     * Utile pour après tester ArrayList.contains()
     * @param obj l'autre objet à comparer
     * (préférablement de classe Point2D) 
     * @return si les coordonnées sont les mêmes ou ás
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        if (obj == null || getClass() != obj.getClass()) 
            return false;
        Point2D p = (Point2D) obj;
        return (x == p.getX()) && (y == p.getY());
    }

    /**
     * réécriture de la fonction obligatoire après redéfinition de equals
     * @return le code pour la table de hashage interne
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.x;
        hash = 31 * hash + this.y;
        return hash;
    }

    // ----------------------------
    //      Getters & Setters
    // ----------------------------
    
    /**
     * Obtient la coordonnée x du point.
     * 
     * @return La coordonnée x du point.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtient la coordonnée y du point.
     * 
     * @return La coordonnée y du point.
     */
    public int getY() {
        return y;
    }
    
    /**
     * Définit la coordonnée x du point.
     * 
     * @param x La nouvelle coordonnée x du point.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Définit la coordonnée y du point.
     * 
     * @param y La nouvelle coordonnée y du point.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Définit les coordonnées x et y du point.
     * 
     * @param x La nouvelle coordonnée x du point.
     * @param y La nouvelle coordonnée y du point.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
