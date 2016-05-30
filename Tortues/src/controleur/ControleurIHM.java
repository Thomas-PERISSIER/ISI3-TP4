package controleur;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import modele.Tortue;
import vue.IHM;

/**
 * @author Thomas PERISSIER et Justine GROLEAU
 */
public class ControleurIHM implements ActionListener {
    
    private final IHM ihm;
    private Tortue tortue;
    private int n;
    
    public ControleurIHM(IHM ihm, Tortue tortue) {
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
        
        JComboBox cb = new JComboBox();
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
    
    public Tortue creerTortue(Dimension size){          
        this.setTortue(new Tortue(size.width/2, size.height/2, "polygone"));
        return tortue;
    }
    
    //Crée une nouvelle tortue
    public void ajouter(int n) {
        Dimension size = ihm.getTortueDessin().getSize();
        Tortue newTortue = new Tortue(size.width/2, size.height/2, "polygone");
        newTortue.setColInt(n);      
        
        ihm.getTortueDessin().addTortue(newTortue);
        this.setTortue(newTortue);
        
        tortue.notifier();
    }
    
    //Efface tout et reinitialise la feuille
    public void effacer() {
        for(Tortue t:ihm.getTortueDessin().getTortues()){
            t.reset(ihm.getTortueDessin().getSize());
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
}