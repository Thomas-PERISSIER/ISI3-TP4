package vue;

import java.awt.Graphics;
import modele.Tortue;

/**
 * @author Thomas PERISSIER et Justine GROLEAU
 */
public class Carre implements Forme {

    @Override
    public void drawForme(Graphics g, Tortue tortue) {
        g.drawRect(tortue.getX(), tortue.getY(),10,10);
    }   
}