package modele;

import logoInit.Constante;

/**
 * @author Thomas PERISSIER et Justine GROLEAU
 */
public class Comportement {
    
    private int state;
    private Tortue tortue;
    
    public Comportement() {}
    public Comportement(Tortue tortue) {
        this.tortue = tortue;
    }
    
    public int getState() {
        return state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public void avancer(int dist) {
        int newX = (int) Math.round(tortue.getX() + dist * Math.cos(Constante.RATIODEGRAD * tortue.getDirection()));
        int newY = (int) Math.round(tortue.getY() + dist * Math.sin(Constante.RATIODEGRAD * tortue.getDirection()));

        tortue.setX(newX);
        tortue.setY(newY);
    }

    public void droite(int ang) {
        tortue.setDirection((tortue.getDirection() + ang) % 360);
    }

    public void gauche(int ang) {
        tortue.setDirection((tortue.getDirection() + ang) % 360);
    }
    
    public void reset() {
        tortue.setX(0);
        tortue.setY(0);
        tortue.setDirection(-90);
        tortue.setColInt(0);
    }
}