package controleur;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Tortue;
import vue.IHM2;

/**
 * @author Thomas PERISSIER et Justine GROLEAU
 */
public class ComportementTortues extends Thread {
    
    private ArrayList<Tortue> tortues = new ArrayList<>();
    private final IHM2 ihm;
    
    public ComportementTortues(ArrayList<Tortue> tortues, IHM2 ihm) {
        this.tortues = tortues;
        this.ihm = ihm;
    }
    
    @Override
    public void run() {
        boolean go = true;
        int i = 0;
        while (go) {
            int nombreAleatoire = 1 + (int)(Math.random() * 3);
            int distang = 1 + (int)(Math.random() * 45);
            switch (nombreAleatoire) {
                case 1:
                    System.out.println("Tortue " + i + " : Avance de " + distang);
                    tortues.get(i).avancer(distang);
                    break;
                case 2:
                    System.out.println("Tortue " + i + " : Tourne à droite de " + distang);
                    tortues.get(i).droite(distang);
                    break;
                case 3:
                    System.out.println("Tortue " + i + " : Tourne à gauche de " + distang);
                    tortues.get(i).gauche(distang);
                    break;
            }
            i++;
            
            if (i == tortues.size()) {
                synchronized(this){
                    try {
                        wait(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ComportementTortues.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                i = 0;
            }
        }
    }
}
