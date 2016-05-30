/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.event.ActionListener;
import java.util.Random;
import modele.Tortue;
import vue.IHM2;

/**
 *
 * @author Epulapp
 */
public class ControleurIHM2{
    private final IHM2 ihm;
    private Tortue tortue;
    private int n;
    
    public ControleurIHM2(IHM2 ihm, Tortue tortue) {
        this.ihm = ihm;
        this.tortue = tortue;      
    }
    
    public Tortue creerTortue(Tortue.FormeTortue forme){          
        this.setTortue(new Tortue(forme));
        return tortue;
    }
    
    public Tortue getTortue() {
        return tortue;
    }
    
    public void setTortue(Tortue tortue) {
        this.tortue = tortue;
        tortue.addObserver(ihm);
    }
    
}
