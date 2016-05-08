package vue;

import controleur.ControleurIHM;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
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
public final class IHM extends JFrame implements Observer {
    
    public static final Dimension VGAP = new Dimension(1, 5);
    public static final Dimension HGAP = new Dimension(5, 1);
    
    private TortueDessin tortueDessin;
    private Tortue tortue;
    
    private JTextField inputValue;
    private ControleurIHM controleurIHM;
    
    public IHM() {
        super("Tortues");
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
    
    public String getInputValue() {
        String s = inputValue.getText();
        return (s);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IHM fenetre = new IHM();
            fenetre.setVisible(true);
        });

    }
    
    public void ihmInit() {
        getContentPane().setLayout(new BorderLayout(10, 10));
        
        JToolBar toolBar = new JToolBar();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(toolBar);
        
        getContentPane().add(buttonPanel, "North");
        
        addButton(toolBar, "Effacer", "Nouveau dessin", "/icons/index.png");
        
        toolBar.add(Box.createRigidArea(HGAP));
        inputValue = new JTextField("45", 5);
        toolBar.add(inputValue);
        addButton(toolBar, "Avancer", "Avancer 50", null);
        addButton(toolBar, "Droite", "Droite 45", null);
        addButton(toolBar, "Gauche", "Gauche 45", null);

        String[] colorStrings = {"noir", "bleu", "cyan", "gris fonce", "rouge",
            "vert", "gris clair", "magenta", "orange", "gris", "rose", "jaune"};
        
        toolBar.add(Box.createRigidArea(HGAP));
        JLabel colorLabel = new JLabel("   Couleur: ");
        toolBar.add(colorLabel);
        JComboBox colorList = new JComboBox(colorStrings);
        toolBar.add(colorList);
        
        colorList.addActionListener(controleurIHM);
        
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu menuFile = new JMenu("File");
        menubar.add(menuFile);
        
        addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
        addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);
        
        JMenu menuCommandes = new JMenu("Commandes");
        menubar.add(menuCommandes);
        
        addMenuItem(menuCommandes, "Avancer", "Avancer", -1);
        addMenuItem(menuCommandes, "Droite", "Droite", -1);
        addMenuItem(menuCommandes, "Gauche", "Gauche", -1);
        
        JMenu menuHelp = new JMenu("Aide");
        menubar.add(menuHelp);
        
        addMenuItem(menuHelp, "Aide", "Help", -1);
        addMenuItem(menuHelp, "A propos", "About", -1);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tortueDessin = new TortueDessin();
        tortueDessin.setBackground(Color.white);
        tortueDessin.setSize(new Dimension(600, 400));
        tortueDessin.setPreferredSize(new Dimension(600, 400));
        
        getContentPane().add(tortueDessin, "Center");
        
        //Création de la tortue
        tortue = new Tortue();
        
        // Deplacement de la tortue au centre de la feuille
        tortue.setPosition(500 / 2, 400 / 2);
        tortueDessin.addTortue(tortue);
        
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
    
    public void addButton(JComponent p, String name, String tooltiptext, String imageName) {
        JButton b;
        if ((imageName == null) || (imageName.equals(""))) {
            b = (JButton) p.add(new JButton(name));
        } else {
            URL u = this.getClass().getResource(imageName);
            if (u != null) {
                ImageIcon im = new ImageIcon(u);
                b = (JButton) p.add(new JButton(im));
            } else {
                b = (JButton) p.add(new JButton(name));
            }
            b.setActionCommand(name);
        }

        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0, 0, 0, 0));
        b.addActionListener(controleurIHM);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        //A compléter...
    }
}