package com.rpgvdl.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mover extends JPanel implements KeyListener { 

    private static final long serialVersionUID = 1L; 
    int xPos; 
    int yPos; 
    int offset; 
    BufferedImage ball; 

    public static void main(final String[] args) { 
        final Mover m = new Mover(); 
        m.init(); 
    } 

    public void init() { 
        createBall(); 
        final JFrame container = new JFrame("Mover"); 
        container.setSize(400, 400); 
        //container.setDefaultCloseOperation(J... 
        setBackground(Color.BLACK); 

        //the KeyListener must be added to the JFrame containing 
        //this JPanel. 
        container.addKeyListener(this); 

        container.add(this); 
        container.setVisible(true); 
    } 

    public void createBall() { 

        // create a BufferedImage for drawing a ball into, 
        // or you could load an image into it from an image file.. 
        ball = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); 

        final Graphics2D charG = ball.createGraphics(); 
        charG.setColor(Color.blue); 
        charG.fillOval(0, 0, ball.getWidth(), ball.getHeight()); 
        xPos = 0; 
        yPos = 0; 

        //offset is used to position the centre of the ball over the 
        //xPos and yPos coordinates 
        offset = Math.round(ball.getWidth()/2);	
    } 

    @Override 
    public void paintComponent(final Graphics g) { 
        super.paintComponent(g); 
        final Graphics2D g2d = (Graphics2D)g; 

        //this is required in case the component tries 
        //to repaint before the ball has been instantiated. 
        if (ball == null) {
            createBall();
        } 

        g2d.drawImage(ball, null, xPos-offset, yPos-offset); 
    } 


    //the keyPressed, keyReleased and keyTyped are all required 
    //for the KeyListener interface. 
    //I will just be using the KeyPressed method 
    public void keyPressed(final KeyEvent arg0) { 
        final int c = arg0.getKeyCode(); 
        switch (c) { 
        case KeyEvent.VK_LEFT: 
            xPos -= 5; 
            break; 
        case KeyEvent.VK_RIGHT: 
            xPos += 5; 
            break; 
        case KeyEvent.VK_DOWN: 
            yPos += 5; 
            break; 
        case KeyEvent.VK_UP: 
            yPos -= 5; 
            break; 
        } 

        //repaint this JPanel to reflect the change in the 
        //balls position 
        this.repaint(); 

    } 


    public void keyReleased(final KeyEvent arg0) {	
    } 

    public void keyTyped(final KeyEvent arg0) { 
    } 
} 
