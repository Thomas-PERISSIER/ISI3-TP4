package modele;

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

    private int x, y, direction, colInt, vitesse;
    
    public enum FormeTortue {POLYGONE, CERCLE, CARRE}; 
    
    private FormeTortue formeTortue;

    public Tortue(FormeTortue forme) {
        this.formeTortue = forme;
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
    
    public int getVitesse() {
        return vitesse;
    }
    
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
    }
    
    public void couleur(int n) {
        colInt = n % 12;
    }
    
    public void avancer(int dist) {
        this.vitesse = dist;
        
        int newX = (int) Math.round(this.x + dist * Math.cos(Constante.RATIODEGRAD * this.direction));
        int newY = (int) Math.round(this.y + dist * Math.sin(Constante.RATIODEGRAD * this.direction));
        
        if (newX <= 600 && newX >= 0) {
            this.x = newX;
        } else if (newX > 600) {
            this.x = newX - 600;
        } else if (newX < 0) {
            this.x = 600 + newX;
        }
        
        if (newY <= 400 && newY >= 0) {
            this.y = newY;
        } else if (newY > 400) {
            this.y = newY - 400;
        } else if (newY < 0) {
            this.y = 400 + newY;
        }
        
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
    
    public void reset(int leX, int leY) {
        this.x = leX;
        this.y = leY;
        this.direction = 0;
        this.colInt = 0;
        
        this.notifier();
    }
    
    public void notifier(){
        setChanged();
        notifyObservers();
    }

    public FormeTortue getFormeTortue() {
        return formeTortue;
    }

    public void setFormeTortue(FormeTortue formeTortue) {
        this.formeTortue = formeTortue;
    }

}