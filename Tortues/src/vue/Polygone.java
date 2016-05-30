/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Graphics;
import vue.Forme;
import java.awt.Point;
import java.awt.Polygon;
import logoInit.Constante;
import modele.Tortue;

/**
 *
 * @author Epulapp
 */
public class Polygone implements Forme {
    
    @Override
    public void drawForme(Graphics g, Tortue tortue) {
       //Calcule les trois coins du triangle à partir de la position de la tortue
        Point p = new Point(tortue.getX(), tortue.getY());
        Polygon arrow = new Polygon();

        //Calcule des deux bases
        //Angle de la droite
        double theta = Constante.RATIODEGRAD * (-tortue.getDirection());
        //Demi angle au sommet du triangle
        double alpha = Math.atan((float) Constante.RB / (float) Constante.RP);
        //Rayon de la fleche
        double r = Math.sqrt(Constante.RP * Constante.RP + Constante.RB * Constante.RB);
        //Sens de la fleche

        //Pointe
        Point p2 = new Point((int) Math.round(p.x + r * Math.cos(theta)), (int) Math.round(p.y - r * Math.sin(theta)));
        arrow.addPoint(p2.x, p2.y);
        arrow.addPoint((int) Math.round(p2.x - r * Math.cos(theta + alpha)), (int) Math.round(p2.y + r * Math.sin(theta + alpha)));

        //Base2
        arrow.addPoint((int) Math.round(p2.x - r * Math.cos(theta - alpha)), (int) Math.round(p2.y + r * Math.sin(theta - alpha)));

        arrow.addPoint(p2.x, p2.y);
        g.setColor(TortueDessin.decodeColor(tortue.getColInt()));
        g.fillPolygon(arrow);
    }

    
}
