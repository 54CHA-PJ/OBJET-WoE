/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Random;

/** 
 * La classe NuageToxique représente un nuage qui peut se déplacer et infliger des dégâts au joueur.
 * Elle est de type objet, mais peut se déplacer et combattre.
 * @author ESPINOZA Mario
 * @author CRUZ Sacha
 */
public class NuageToxique extends Objet implements Deplacable, Combattant{
    private int toxicite;

    public NuageToxique(int toxicite, String nom, Point2D pos) {
        super(nom, pos);
        this.toxicite = toxicite;
    }
    public NuageToxique(NuageToxique nt) {
        super(nt);
        this.toxicite = nt.getToxicite();
    }
    public NuageToxique() {
        super();
        toxicite =20;
    }

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

    @Override
    public void combat(ElementDeJeu comb) {
        // Si ce n'est pas une créature, rien de défini
        if (!(comb instanceof Creature)){}
        // Si c'est une créature
        else {
            Creature c = (Creature) comb;
            double d = this.getPos().distance(c.getPos());
            if (d>0){
                System.out.println("La créature n'est pas dans le nuage toxique");
            }
            else {
                c.setPtVie(c.getPtVie()-toxicite);
                System.out.println(c.getClass()+" respire le gaz, "+ toxicite +" dégâts. Il lui reste "+c.getPtVie()+" points de vie");
            }
        }
    }
    
    @Override
    public void readAttributs(ArrayList<Object> listatt) {
        super.readAttributs(listatt);
        this.toxicite = (int) listatt.get(2);
    }
    
    @Override
    public String toString(){
        String s = super.toString();
        s += " ";
        s += this.toxicite;
        s += " ";
        s += this.getPos().getX();
        s += " ";
        s += this.getPos().getY();
        s += " ";
        return s;
    }
    
    @Override
    public String toStringInv() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public int getToxicite() {
        return toxicite;
    }

    public void setToxicite(int toxicite) {
        this.toxicite = toxicite;
    }
}
