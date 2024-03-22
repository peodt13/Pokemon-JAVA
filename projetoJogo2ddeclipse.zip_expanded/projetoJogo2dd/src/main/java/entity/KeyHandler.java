package entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import telas.batalha1;
import telas.batalha2;
import telas.cadastro;
import telas.login;

/**
 *
 * @author Silva
 */
public class KeyHandler implements KeyListener {

    //metodo para movimentação (w,a,s,d)
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
      int code = e.getKeyCode();
      if (code== KeyEvent.VK_W){
          upPressed=true;
      }
      if (code== KeyEvent.VK_A){
          leftPressed=true;
      }
      if (code== KeyEvent.VK_S){
          downPressed=true;
      }
      if (code== KeyEvent.VK_D){
          rightPressed=true;
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code =  e.getKeyCode();
       
        if (code== KeyEvent.VK_W){
          upPressed=false;
      }
      if (code== KeyEvent.VK_A){
          leftPressed=false;
      }
      if (code== KeyEvent.VK_S){
          downPressed=false;
      }
      if (code== KeyEvent.VK_D){
          rightPressed=false;
      }
      if (code == KeyEvent.VK_F1) {
            // A tecla F1 foi pressionada, então abra a tela de Batalha1
            batalha1 telaBatalha1 = new batalha1(); // Substitua "Batalha1" pelo nome da classe real
            telaBatalha1.setVisible(true);
            
             
        } else if (code == KeyEvent.VK_F2) {
            // A tecla F2 foi pressionada, então abra a tela de Batalha2
            batalha2 Telabatalha2 = new batalha2(); // Substitua "Batalha2" pelo nome da classe real
            Telabatalha2.setVisible(true);
        }
      
    }    
    
}