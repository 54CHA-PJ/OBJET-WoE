package org.centrale.objet.WoE;

/**
 * La classe TestPoint2D est utilisée pour tester la classe Point2D.
 * Elle affiche les résultats de diverses opérations sur des objets Point2D.
 * @author CRUZ Sacha
 * @author ESPINOZA Mario
 */
public class TestPoint2D {
    public static void main(String[] args) {
        System.out.println("TEST");

        // Création de points 2D
        Point2D p1 = new Point2D();
        Point2D p2 = new Point2D(5, 10);
        Point2D p3 = new Point2D(p2);

        // Modification des coordonnées de p1 et p3
        p1.setX(5);
        p1.setY(5);
        p3.setX(10);
        p3.setY(10);

        // Calcul et affichage des distances entre les points
        float d12 = p1.distance(p2);
        System.out.println("Distance entre p1 et p2 : " + d12);
        float d13 = p1.distance(p3);
        System.out.println("Distance entre p1 et p3 : " + d13);

        // Obtention des coordonnées de p3
        int x3 = p3.getX();
        int y3 = p3.getY();

        // Affichage des coordonnées des points
        p1.affiche();
        p2.affiche();
        p3.affiche();
        System.out.println("Pour confirmer : p3 = " + x3 + "," + y3);
    }
}
