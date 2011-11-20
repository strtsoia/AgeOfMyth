package dice;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Dice.java
 * Created by Stijn Strickx on Feb. 16 2008
 * Copyright 2008 Stijn Strickx, All rights reserved
 */

public class Dice {
    private IconGetter getter;

	Icon image;
	Icon image1;
	
    public Dice(){
        //getter = new IconGetter();
    	 image = new ImageIcon("C://Users//priyanka//git//AgeOfMyth//src//dice//stone1.gif");
    	 image1 = new ImageIcon("C://Users//priyanka//git//AgeOfMyth//src//dice//stone1.gif");
    	    
    }
   

    public void start() {
        
    	JLabel dice1 = new JLabel(image);
        JLabel dice2 = new JLabel(image1);
        JButton button = new JButton("Throw");
        JLabel text = new JLabel("Total: 02");
        JFrame window = new JFrame("throw dice (c) Stijn Strickx");
        Container cp = window.getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(dice1);
        cp.add(dice2);
        cp.add(button);
        cp.add(text);
        
      button.addActionListener(new ButtonListener(dice1, dice2, text));
        
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
