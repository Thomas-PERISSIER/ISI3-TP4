package modele;

import java.util.Observable;

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
    private Comportement comp;

    public Tortue() {
        comp = new Fleche(this);
        comp.reset();
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

    public Comportement getComp() {
        return comp;
    }

    public void setComp(Comportement comp) {
        this.comp = comp;
    }

    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
    }
    
    public void couleur(int n) {
        colInt = n % 12;
    }
}