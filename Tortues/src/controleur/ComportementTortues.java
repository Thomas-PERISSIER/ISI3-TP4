package controleur;

import static java.lang.Math.sqrt;
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
    
    //Champ de vision de la tortue
    private static final int distance = 200, angle = 120;
    private static final int distanceMinimale = 20, distanceMaximale = 60;
    
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
                runAutonome();
                runFlocking();
                break;
        }
    }
    
    public void runAutonome() {
        boolean go = true;
        int i = 0; int flock = 0;
        while (go) {
            tortue = tortues.get(i);
            int nombreAleatoire = 1 + (int)(Math.random() * 3);
            int distang = 1 + (int)(Math.random() * 20);
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
                flock++;
                synchronized(this){
                    try {
                        wait(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ComportementTortues.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                i = 0;
            }
            
            if (mode == 2 && flock == 50)
                break;
        }
    }
    
    public void runFlocking() {
        boolean go = true;
        int i = 0;
        while (go) {
            tortue = tortues.get(i);
            int direction = calculDirection(tortue);
            int vitesse = (int)calculVitesse(tortue);
                
            if (vitesse == 0)
                vitesse = 1 + (int)(Math.random() * 20);
            
            if (direction != 0) {
                if ((tortue.getDirection() - direction) < 0) {
                    tortue.droite(tortue.getDirection() - direction);
                } else if ((tortue.getDirection() - direction) > 0) {
                    tortue.gauche(tortue.getDirection() - direction);
                }
            }
            tortue.avancer(vitesse);
            
            tortue.addObserver(ihm);
            i++;
            
            if (i == tortues.size()) {
                synchronized(this){
                    try {
                        wait(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ComportementTortues.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                i = 0;
            }
        }
    }
    
    public double calculVitesse(Tortue tortue) {
        double vitesse = 0.0;
        int nbTortue = 0;
        ArrayList<Tortue> tor = searchTortues(tortue);
        
        for (Tortue torImp:tor) {
            double dist = calculDistance(tortue, torImp);
            //Se rapprocher des tortues qui sont dans son champ de vision
            //tout en maintenant une distance minimale
            if (dist < distanceMinimale) {
                vitesse += 0;
            } else if (dist > distanceMaximale) {
                vitesse += torImp.getVitesse() + (dist - distanceMaximale);
            } else {
                vitesse += torImp.getVitesse();
            }
            
            nbTortue++;
        }
        
        if (nbTortue > 0) {
            vitesse /= nbTortue;
            System.out.println("Vitesse moyenne :" + vitesse);
            return vitesse;
        } else {
            return 0;
        }
    }
    
    public int calculDirection(Tortue tortue) {
        double direction = 0.0;
        int nbTortue = 0;
        ArrayList<Tortue> tor = searchTortues(tortue);
        
        for (Tortue torImp:tor) {
            direction += torImp.getDirection();
            nbTortue++;
        }
        
        if (nbTortue > 0) {
            direction /= nbTortue;
            System.out.println("Direction moyenne :" + direction);
            return (int)direction;
        } else {
            return 0;
        }
        
    }
    
    private ArrayList<Tortue> searchTortues(Tortue tortue) {
        ArrayList<Tortue> tor = new ArrayList<>();
        
        int direction = tortue.getDirection();
        //Calcul du champ de vision de la tortue
        int directionMin = (direction - angle) % 360; int directionMax = (direction + angle) % 360;
        //Avec le calcul des deux points
        int newX1 = (int) Math.round(tortue.getX() + distance * Math.cos(Constante.RATIODEGRAD * directionMin));
        int newY1 = (int) Math.round(tortue.getY() + distance * Math.cos(Constante.RATIODEGRAD * directionMin));
        
        int newX2 = (int) Math.round(tortue.getX() + distance * Math.cos(Constante.RATIODEGRAD * directionMax));
        int newY2 = (int) Math.round(tortue.getY() + distance * Math.cos(Constante.RATIODEGRAD * directionMax));
        
        tortues.stream().forEach((torImp) -> {
            double dist = calculDistance(tortue, torImp);
            if (dist < distance) {
                if (newX1 <= newX2 && newY1 >= newY2) {
                    if (torImp.getX() >= newX1 && torImp.getX() <= newX2 && torImp.getY() >= newY2 && torImp.getY() <= newY1) {
                        tor.add(torImp);
                    }
                } else if (newX1 <= newX2 && newY1 <= newY2) {
                    if (torImp.getX() >= newX1 && torImp.getX() <= newX2 && torImp.getY() >= newY1 && torImp.getY() <= newY2) {
                        tor.add(torImp);
                    }
                } else if (newX1 >= newX2 && newY1 <= newY2) {
                    if (torImp.getX() >= newX2 && torImp.getX() <= newX1 && torImp.getY() >= newY1 && torImp.getY() <= newY2) {
                        tor.add(torImp);
                    }
                } else if (newX1 >= newX2 && newY1 >= newY2) {
                    if (torImp.getX() >= newX2 && torImp.getX() <= newX1 && torImp.getY() >= newY2 && torImp.getY() <= newY1) {
                        tor.add(torImp);
                    }
                }
            }
        });
        
        return tor;
    }
    
    private double calculDistance(Tortue tortue, Tortue tor) {
        double dist = sqrt((tor.getX() - tortue.getX())^2 + (tor.getY() - tortue.getY())^2);
        return dist;
    }
}
