package vue;

import controleur.ControleurIHM2;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import modele.Tortue;

/**
 * @author Thomas PERISSIER et Justine GROLEAU
 */

/**
 * ***********************************************************************
 *
 * Un petit Logo minimal qui devra etre ameliore par la suite
 *
 * J. Ferber - 1999-2001
 *
 * Cours de DESS TNI - Montpellier II
 *
 * @version 2.0
 * @date 25/09/
 *
 *
 *************************************************************************
 */
public final class IHM2 extends JFrame implements Observer {
    
    public static final Dimension VGAP = new Dimension(1, 5);
    public static final Dimension HGAP = new Dimension(5, 1);
    
    private TortueDessin tortueDessin;
    private final ArrayList<Tortue> tortues = new ArrayList<>();
    
    private final ControleurIHM2 controleurIHM;
        
    public IHM2(int mode) {
        super("Tortues");
        createTortues();
        controleurIHM = new ControleurIHM2(this, tortues, mode);
        ihmInit();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }
    
    public TortueDessin getTortueDessin() {
        return tortueDessin;
    }
    
    public void ihmInit() {
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu menuFile = new JMenu("File");
        menubar.add(menuFile);
        
        addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);
        
        JMenu menuHelp = new JMenu("Aide");
        menubar.add(menuHelp);
        
        addMenuItem(menuHelp, "Aide", "Help", -1);
        addMenuItem(menuHelp, "A propos", "About", -1);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        tortueDessin = new TortueDessin();
        tortueDessin.setBackground(Color.white);
        tortueDessin.setSize(new Dimension(600, 400));
        tortueDessin.setPreferredSize(new Dimension(600, 400));
        
        tortues.stream().forEach((tortue) -> {
            tortueDessin.addTortue(tortue);
        });
        
        getContentPane().add(tortueDessin, "Center");
        
        pack();
        setVisible(true);
    }
    
    public void addMenuItem(JMenu m, String label, String command, int key) {
        JMenuItem menuItem = new JMenuItem(label);
        menuItem.setActionCommand(command);
        menuItem.addActionListener(controleurIHM);
        m.add(menuItem);
        
        if (key > 0) {
            if (key != KeyEvent.VK_DELETE) {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            } else {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
            }
        }
    }
    
    public void createTortues() {
        Tortue tortue;
        Dimension size = new Dimension(600, 400);
        //Création d'un ensemble de tortues
        for (int i=0; i<10; i++) {
            int nombreAleatoire = 1 + (int)(Math.random() * 3);
            switch (nombreAleatoire) {
                case 1:
                    tortue = new Tortue(Tortue.FormeTortue.POLYGONE);
                    break;
                case 2:
                    tortue = new Tortue(Tortue.FormeTortue.CERCLE);
                    break;
                case 3:
                    tortue = new Tortue(Tortue.FormeTortue.CARRE);
                    break;
                default:
                    tortue = new Tortue(Tortue.FormeTortue.POLYGONE);
                    break;
            }
            
            nombreAleatoire = (int)(Math.random() * 11);
            tortue.setColInt(nombreAleatoire);
            tortue.setPosition(size.width/2, size.height/2);
            tortues.add(tortue);
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        tortueDessin.repaint();
    }
}