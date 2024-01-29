
package org.centrale.objet.WoE;

import java.util.ArrayList;

/**
 * La classe Objet représente un objet générique dans le jeu.
 * Cette classe permet de créer et de gérer des objets pour le jeu.
 * Les objets ont un nom et une rareté.
 * 
 * @author ESPINOZA Mario
 * @author CRUZ Sacha
 */
public abstract class Objet extends ElementDeJeu{
    private String nom;

    public Objet(String nom, Point2D pos) {
        super(pos);
        this.nom = nom;
    }

    public Objet(Objet ob) {
        super(ob);
        this.nom = ob.getNom();
    }

    public Objet() {
        super();
        this.nom = this.getNomClasse();
    }
    
    @Override
    public void attributsAleatoires(){}

    /** Obtient le nom de l'objet.
     *  @return Le nom de l'objet.
     */
    public String getNom() {
        return nom;
    }

    /** Définit le nom de l'objet.
     *  @param nom Le nom de l'objet.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @Override
    public void affiche() {
        Class nclasse = this.getClass();
        System.out.println("Classe   = "+ getNomClasse());
        System.out.println("Nom "+ nom);
        System.out.println("Position ");
        this.getPos().affiche();
    }
    
    @Override
    public void readAttributs(ArrayList<Object> listatt) {
        // Seulement utile pour les classes d'objet à un seul attribut (mis à part du nom et de la position)
        this.setNom((String) listatt.get(1));
        int x = (int) listatt.get(3);
        int y = (int) listatt.get(4);
        this.setPos(new Point2D(x,y));
    }
    
    public void readAttributsInv(ArrayList<Object> listatt){
        this.setNom((String) listatt.get(2));
    }
    
    @Override
    public String toString(){
        String s = this.getNomClasse();
        s += " ";
        s += this.nom;
        return s;
    }
    
    public String toStringInv(){
        String s = this.getNomClasse();
        s += " ";
        s += this.nom;
        return s;
    }
}


