package vue;

import java.awt.Graphics;
import modele.Tortue;

/*
 * @author Thomas PERISSIER et Justine GROLEAU
 */
public class Cercle implements Forme {

    @Override
    public void drawForme(Graphics g, Tortue tortue) {     
        g.fillOval(tortue.getX(),tortue.getY(),10,10);
    }   
}