package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modele.Tortue;
import vue.IHM2;

/**
 * @author Thomas PERISSIER et Justine GROLEAU
 */
public class ControleurIHM2 implements ActionListener {
    private final IHM2 ihm;
    private final ArrayList<Tortue> tortues;
    
    public ControleurIHM2(IHM2 ihm, ArrayList<Tortue> tortues, int mode) {
        this.ihm = ihm;
        this.tortues = tortues;
        
        comportementTortue(mode);
    }
    
    public ArrayList<Tortue> getTortue() {
        return tortues;
    }
    
    private void comportementTortue(int mode) {
        ComportementTortues comportementTortues = new ComportementTortues(tortues, ihm, mode);
        comportementTortues.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        
        switch (c) {
            case "Quitter":
                quitter();
                break;
            default:
                break;
        }
    }
    
    private void quitter() {
        System.exit(0);
    }
    
}