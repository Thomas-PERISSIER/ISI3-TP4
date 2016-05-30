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
            case "Effacer":
                effacer();
                break;
            case "Quitter":
                quitter();
                break;
            default:
                JComboBox cb = (JComboBox) e.getSource();
                int n = cb.getSelectedIndex();
                tortue.setColInt(n);
                break;
        }

    }
    
    
    //Efface tout et reinitialise la feuille
    public void effacer() {
        ihm.getTortueDessin().getTortues().stream().forEach((tor) -> {
            tor.reset();
        });
        

        //Replace la tortue au centre
        Dimension size = ihm.getTortueDessin().getSize();
        tortue.setPosition(size.width / 2, size.height / 2);
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