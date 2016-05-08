package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import javax.swing.JPanel;
import logoInit.Constante;
import modele.Tortue;

/**
 * @author Thomas PERISSIER et Justine GROLEAU
 */

/**
 * Titre : Logo Description : Un exemple de programme graphique utilisant la
 * celebre Tortue Logo Copyright : Copyright (c) 2000 Societe : LIRMM
 *
 * @author J. Ferber
 * @version 2.0
 */
public class TortueDessin extends JPanel {
    private final ArrayList<Tortue> tortues;
    
    public TortueDessin() {
        tortues = new ArrayList<>();
    }
    
    public ArrayList<Tortue> getTortues() {
        return tortues;
    }
    
    public void addTortue(Tortue tortue) {
        tortues.add(tortue);
    }
    
    public void removeTortue(Tortue tortue) {
        tortues.remove(tortue);
    }
    
    private Color decodeColor(int c) {
        switch (c) {
            case 0:
                return Color.black;
            case 1:
                return Color.blue;
            case 2:
                return Color.cyan;
            case 3:
                return Color.darkGray;
            case 4:
                return Color.red;
            case 5:
                return Color.green;
            case 6:
                return Color.lightGray;
            case 7:
                return Color.magenta;
            case 8:
                return Color.orange;
            case 9:
                return Color.gray;
            case 10:
                return Color.pink;
            case 11:
                return Color.yellow;
            default:
                return Color.black;
        }
    }
    
    public void showTurtles(Graphics g) {
        tortues.stream().forEach((tortue) -> {
            drawTurtle(g, tortue);
        });
    }
    
    public void drawTurtle(Graphics g, Tortue tortue) {
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
        g.setColor(Color.green);
        g.fillPolygon(arrow);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color c = g.getColor();

        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(c);

        showTurtles(g);
    }
}