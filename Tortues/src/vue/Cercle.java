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
public class Cercle implements Forme{

    @Override
    public void drawForme(Graphics g, Tortue tortue) {     
        g.fillOval(tortue.getX(),tortue.getY(),10,10);

    }
    
}
