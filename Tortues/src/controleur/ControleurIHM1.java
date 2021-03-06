package controleur;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComboBox;
import modele.Tortue;
import vue.IHM1;

/**
 * @author Thomas PERISSIER et Justine GROLEAU
 */
public class ControleurIHM1 implements ActionListener, MouseListener {
    
    private final IHM1 ihm;
    private Tortue tortue;
    private int n;
    
    public ControleurIHM1(IHM1 ihm, Tortue tortue) {
        this.ihm = ihm;
        this.tortue = tortue;      
    }
    
    /**
     * La gestion des actions des boutons
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        
        JComboBox cb;
        switch (c) {
            case "Avancer":
                System.out.println("commande avancer");
                try {
                    int v = Integer.parseInt(ihm.getInputValue());                    
                    tortue.avancer(v);                    
                } catch (NumberFormatException ex) {
                    System.err.println("Ceci n'est pas un nombre : " + ihm.getInputValue());
                }
                break;
            case "Droite":
                try {
                    int v = Integer.parseInt(ihm.getInputValue());
                    tortue.droite(v);
                } catch (NumberFormatException ex) {
                    System.err.println("Ceci n'est pas un nombre : " + ihm.getInputValue());
                }
                break;
            case "Gauche":
                try {
                    int v = Integer.parseInt(ihm.getInputValue());
                    tortue.gauche(v);
                } catch (NumberFormatException ex) {
                    System.err.println("Ceci n'est pas un nombre : " + ihm.getInputValue());
                }
                break;
            case "Ajouter":
                ajouter(n);               
                break;
            case "Effacer":
                effacer();
                break;
            case "Quitter":
                quitter();
                break;
            default:
                cb = (JComboBox) e.getSource();
                n = cb.getSelectedIndex();
                break;
        }

    }
    
    public Tortue creerTortue(Tortue.FormeTortue forme){          
        this.setTortue(new Tortue(forme));
        return tortue;
    }
    
    //Crée une nouvelle tortue
    public void ajouter(int n) {
        Dimension size = ihm.getTortueDessin().getSize();
        Tortue newTortue = new Tortue(Tortue.FormeTortue.CERCLE);
        newTortue.setPosition(size.width/2, size.height/2);
        newTortue.setColInt(n);
        
        ihm.getTortueDessin().addTortue(newTortue);
        this.setTortue(newTortue);
        
        tortue.notifier();
    }
    
    //Efface tout et reinitialise la feuille
    public void effacer() {
        for (Tortue t:ihm.getTortueDessin().getTortues()){
            Dimension size = ihm.getTortueDessin().getSize();
            t.reset(size.width/2, size.height/2);
        }
    }  
    
    public void couleur(int n) {
        tortue.setColInt(n);
    }
    
    private void quitter() {
        System.exit(0);
    }

    public Tortue getTortue() {
        return tortue;
    }
    
    public void setTortue(Tortue tortue) {
        this.tortue = tortue;
        tortue.addObserver(ihm);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        
        for (Tortue t:this.ihm.getTortueDessin().getTortues()){
            if(x< t.getX()+10 && x>t.getX()-10 && y<t.getY()+10 && y>t.getY()-10){
                this.setTortue(t);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}