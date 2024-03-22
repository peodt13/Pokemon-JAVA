package main;

import entity.KeyHandler;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import tiles.TileManager;

public class Painel extends JPanel implements Runnable {

    //configuração de tela  
    final int originalTileSize = 16; //16x16
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48X48 tile 
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768 pixels de largura
    final int screenHeight = tileSize * maxScreenRow;//576 pixels de altura

    
    //fps
    int fps =60;
    TileManager tileM = new TileManager(this);
    KeyHandler KeyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    Player player = new Player(this,KeyH);

 

    public Painel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //GAMELOOP 
        
        double drawInterval = 1000000000/fps;
        double nextDrawTime= System.nanoTime()+ drawInterval;
        while (gameThread != null) {

            //atualizr posição de carcter - 1
            update();
            //desenhar a tela com as posições atuaizadas 
            repaint();
            
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // metodo de reesenhar a posição
                remainingTime = remainingTime/1000000;
                if(remainingTime < 0 ){
                    remainingTime = 0 ;
                }

                Thread.sleep((long) remainingTime);
                
                nextDrawTime += drawInterval;
            } catch (InterruptedException ex) {
                Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            

        }

    }

    public void update() {
        //velocidade da movimenação
        player.update();
        
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        
        tileM.draw(g2);
        //posições de cada conteudo de image da tela 
        player.draw(g2);
        
        g2.dispose();

    }
} 