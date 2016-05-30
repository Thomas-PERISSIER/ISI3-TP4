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
                    tortue.addObserver(ihm);
                    tortue.avancer(v);                    
                } catch (NumberFormatException ex) {
                    System.err.println("Ceci n'est pas un nombre : " + ihm.getInputValue());
                }
                break;
            case "Droite":
                try {
                    int v = Integer.parseInt(ihm.getInputValue());
                    tortue.addObserver(ihm);
                    tortue.droite(v);
                } catch (NumberFormatException ex) {
                    System.err.println("Ceci n'est pas un nombre : " + ihm.getInputValue());
                }
                break;
            case "Gauche":
                try {
                    int v = Integer.parseInt(ihm.getInputValue());
                    tortue.addObserver(ihm);
                    tortue.gauche(v);
                } catch (NumberFormatException ex) {
                    System.err.println("Ceci n'est pas un nombre : " + ihm.getInputValue());
                }
                break;
            case "Ajouter":
                tortue = ajouter(n);
                tortue.addObserver(ihm);
                tortue.notifier();
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
    
    //Crée une nouvelle tortue
    public Tortue ajouter(int n) {
        Tortue newTortue = new Tortue();
        newTortue.setColInt(n);
        newTortue.setPosition(500/2, 400/2);
        
        ihm.getTortueDessin().addTortue(newTortue);
        
        return newTortue;
    }
    
    //Efface tout et reinitialise la feuille
    public void effacer() {
        ihm.getTortueDessin().getTortues().stream().forEach((tor) -> {
            tor.reset();
        });

        //Replace la tortue au centre
        Dimension size = ihm.getTortueDessin().getSize();
        tortue.setPosition(size.width/2, size.height/2);
    }
    
    public void reset() {
        ihm.getTortueDessin().getTortues().stream().forEach((tor) -> {
            tor.reset();
        });
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
    }
}