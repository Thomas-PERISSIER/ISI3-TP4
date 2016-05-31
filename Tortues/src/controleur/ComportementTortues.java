package controleur;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logoInit.Constante;
import modele.Tortue;
import vue.IHM2;

/**
 * @author Thomas PERISSIER et Justine GROLEAU
 */
public class ComportementTortues extends Thread {
    
    private static final int distance = 45, angle = 90;
    
    private ArrayList<Tortue> tortues = new ArrayList<>();
    private Tortue tortue;
    private final IHM2 ihm;
    private final int mode;
    
    public ComportementTortues(ArrayList<Tortue> tortues, IHM2 ihm, int mode) {
        this.tortues = tortues;
        this.ihm = ihm;
        this.mode = mode;
    }
    
    @Override
    public void run() {
        switch (mode) {
            case 1:
                runAutonome();
                break;
            case 2:
                runFlocking();
                break;
        }
    }
    
    public void runAutonome() {
        boolean go = true;
        int i = 0;
        while (go) {
            tortue = tortues.get(i);
            int nombreAleatoire = 1 + (int)(Math.random() * 3);
            int distang = 1 + (int)(Math.random() * 45);
            switch (nombreAleatoire) {
                case 1:
                    System.out.println("Tortue " + i + " : Avance de " + distang);
                    tortue.avancer(distang);
                    break;
                case 2:
                    System.out.println("Tortue " + i + " : Tourne à droite de " + distang);
                    tortue.droite(distang);
                    break;
                case 3:
                    System.out.println("Tortue " + i + " : Tourne à gauche de " + distang);
                    tortue.gauche(distang);
                    break;
            }
            tortue.addObserver(ihm);
            System.out.println("Tortue " + i + " : Position " + tortue.getX() + " - " + tortue.getY());
            i++;
            
            if (i == tortues.size()) {
                synchronized(this){
                    try {
                        wait(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ComportementTortues.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                i = 0;
            }
        }
    }
    
    public void runFlocking() {
        boolean go = true;
        int i = 0;
        while (go) {
            tortue = tortues.get(i);
            double vitesse = calculVitesse(tortue);
            //A compléter...
        }
    }
    
    public double calculVitesse(Tortue tortue) {
        double vitesse = 0.0;
        ArrayList<Tortue> tor = searchTortues(tortue);
        
        //A compléter...
        
        return vitesse;
    }
    
    private ArrayList<Tortue> searchTortues(Tortue tortue) {
        ArrayList<Tortue> tor = new ArrayList<>();
        
        int direction = tortue.getDirection();
        int directionMin = direction - angle, directionMax = direction + angle;
        
        for (Tortue torImp:tortues) {
            //A compléter...
        }
        
        return tor;
    }
}
