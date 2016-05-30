/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.ControleurIHM2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import modele.Tortue;

/**
 *
 * @author Epulapp
 */
public class IHM2 extends JFrame implements Observer {

    private TortueDessin tortueDessin;
    private Tortue tortue;
    private ControleurIHM2 controleurIHM;
    
    /**
     * Creates new form IHM2
     */
    public IHM2() {
        initComponents();        
        controleurIHM = new ControleurIHM2(this, tortue);
        ihmInit();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }
    
    public void ihmInit(){
        getContentPane().setLayout(new BorderLayout(10, 10));
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tortueDessin = new TortueDessin();
        tortueDessin.setBackground(Color.white);
        tortueDessin.setSize(new Dimension(600, 400));
        tortueDessin.setPreferredSize(new Dimension(600, 400));
        
        getContentPane().add(tortueDessin, "Center");
        
        //Création de la tortue
        tortue = controleurIHM.creerTortue(Tortue.FormeTortue.POLYGONE);
        
        Dimension size = tortueDessin.getSize();
        tortue.setPosition(size.width/2, size.height/2);

        tortueDessin.addTortue(tortue);
        
        Random r = new Random();
        int nbAleatoires = r.nextInt(30 - 5 + 1) + 5;
        
        tortue.avancer(nbAleatoires);
        
        pack();
        setVisible(true);
    }

    public TortueDessin getTortueDessin() {
        return tortueDessin;
    }

    public void setTortueDessin(TortueDessin tortueDessin) {
        this.tortueDessin = tortueDessin;
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        tortueDessin.repaint();
    }
}
