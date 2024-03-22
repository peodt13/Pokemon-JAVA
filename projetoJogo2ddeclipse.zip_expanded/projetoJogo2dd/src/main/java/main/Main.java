package main;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class Main extends JFrame {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //abertura do JFrame 
        window.setResizable(false);
        window.setTitle("Pokémon game");

        Painel painel = new Painel();
        window.add(painel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        painel.startGameThread();
        
         

    }

}

