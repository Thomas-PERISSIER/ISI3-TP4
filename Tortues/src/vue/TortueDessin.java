package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import modele.Tortue;
import modele.Tortue.FormeTortue;

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
    
    public static Color decodeColor(int c) {
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
        for(Tortue t:tortues){
            drawTurtle(g, t);
        }       
    }
    
    public void drawTurtle(Graphics g, Tortue tortue) {
        Forme forme;
 
        switch (tortue.getFormeTortue()) {           
            case CERCLE:
                forme = new Cercle();
                break;
            case POLYGONE:
                forme = new Polygone();
                break;
            default:
                forme = new Polygone();
        }
        forme.drawForme(g, tortue);    
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