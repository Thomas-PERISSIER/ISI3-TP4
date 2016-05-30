package modele;

import vue.Cercle;
import vue.Polygone;
import java.awt.Dimension;
import java.util.Observable;
import logoInit.Constante;

/**
 * @author Thomas PERISSIER et Justine GROLEAU
 */

/**
 * ***********************************************************************
 *
 * Un petit Logo minimal qui devra etre ameliore par la suite
 *
 * Source originale : J. Ferber - 1999-2001
 *
 * Cours de DESS TNI - Montpellier II
 *
 * @version 2.0
 * @date 25/09/2001
 *
 *************************************************************************
 */
public final class Tortue extends Observable {

    private int x, y, direction, colInt;
    
    private String formeType;
    

    public Tortue(int leX, int leY, String forme) {
        this.x = leX;
        this.y = leY;
        this.formeType=forme;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setColInt(int n) {
        colInt = n;
    }

    public int getColInt() {
        return colInt;
    }

    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
    }
    
    public void couleur(int n) {
        colInt = n % 12;
    }
    
    public void avancer(int dist) {
        int newX = (int) Math.round(this.x + dist * Math.cos(Constante.RATIODEGRAD * this.direction));
        int newY = (int) Math.round(this.y + dist * Math.sin(Constante.RATIODEGRAD * this.direction));

        this.x = newX;
        this.y = newY;
        
        this.notifier();
    }

    public void droite(int ang) {
        this.direction = (direction + ang) % 360;
        this.notifier();
    }

    public void gauche(int ang) {
        this.direction = (direction - ang) % 360;
        this.notifier();
    }
    
    public void reset(Dimension size) {
        this.x = size.width/2;
        this.y = size.height/2;
        this.direction = 0;
        this.colInt = 0;
        
        this.notifier();
    }
    
    public void notifier(){
        setChanged();
        notifyObservers();
    }

    public String getFormeType() {
        return formeType;
    }

    public void setFormeType(String formeType) {
        this.formeType = formeType;
    }
    
    
    
    
}