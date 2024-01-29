package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * La classe ElementDeJeu représente une entité générique avec une position dans le monde.
 * Elle peut être utilisée comme base pour d'autres entités.
 * 
 * @author ESPINOZA Mario
 * @author CRUZ Sacha
 */
public abstract class ElementDeJeu {
    private Point2D pos;

    /**
     * Constructeur pour créer une entité avec une position spécifiée.
     * @param pos La position de l'entité dans le monde.
     */
    public ElementDeJeu(Point2D pos) {
        this.pos = pos;
    }

    /**
     * Constructeur de copie pour créer une entité en copiant une autre entité.
     * @param entite L'entité à copier.
     */
    public ElementDeJeu(ElementDeJeu entite) {
        this.pos = entite.getPos();
    }

    /**
     * Constructeur par défaut pour créer une entité avec des valeurs par défaut.
     */
    public ElementDeJeu() {
        this.pos = new Point2D();
    }
    
    /**
     * Obtient la position de l'entité dans le monde.
     * @return La position de l'entité.
     */
    public Point2D getPos() {
        return pos;
    }

    /**
     * Définit la position de l'entité dans le monde.
     * @param pos La nouvelle position de l'entité.
     */
    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public String getNomClasse(){
        return ((this.getClass()).toString()).substring(29);
    }
    
    public abstract void affiche();
    
    public abstract void attributsAleatoires();
    
    public abstract void readAttributs(ArrayList<Object> listatt);
    
    /**
     * Résume toutes les données d'un élement de jeu en une chaîne de caractères
     * Utile pour sauvegarder la créature en une seule ligne de texte
     * @return la chaîne de caractères qui résume l'élement de jeu
     */
    @Override
    public abstract String toString();

}

