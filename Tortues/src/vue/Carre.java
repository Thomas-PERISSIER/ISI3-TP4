/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Graphics;
import modele.Tortue;

/**
 *
 * @author Epulapp
 */
public class Carre implements Forme{

    @Override
    public void drawForme(Graphics g, Tortue tortue) {
        g.drawRect(tortue.getX(), tortue.getY(),10,10);
    }
    
}
