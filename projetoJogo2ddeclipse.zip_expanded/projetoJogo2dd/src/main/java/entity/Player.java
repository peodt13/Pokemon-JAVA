package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Painel;

public class Player extends Entity {

    Painel gp;
    KeyHandler KeyH;

    public Player(Painel gp, KeyHandler KeyH) {

        this.gp = gp;
        this.KeyH = KeyH;
        setDefaultalues();
        getPlayerImage();
        solidArea = new Rectangle(8, 16, 32, 32);
    }

    public void setDefaultalues() {
        //posição inicia do player 
        x = 100;
        y = 100;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {
        String diretorioAtual = System.getProperty("user.dir");
        System.out.println(diretorioAtual);
        diretorioAtual = diretorioAtual + "\\src\\test\\java\\player\\";
        try {

        	 up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
             up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
             down1 =ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
             down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
             left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
             left2 =ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
             right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
             right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
             
        } catch (IOException e) {
            System.out.println("ERRO A CArREGAR IMAGENS");
            System.out.println(e);
            System.out.println(" --- ");
            e.printStackTrace();
        }
    }

    public void update() {
        //velocidade da movimenação
        if (KeyH.upPressed == true || KeyH.downPressed == true || KeyH.leftPressed == true || KeyH.rightPressed == true) {

            if (KeyH.upPressed == true) {
                direction = "up";

            } else if (KeyH.downPressed == true) {
                direction = "down";

            } else if (KeyH.leftPressed == true) {
                direction = "left";

            } else if (KeyH.rightPressed) {
                direction = "right";

            }

            //verificação de colisão
            collisionOn = false;
            gp.cChecker.checkTile(this);

            if (collisionOn == false) {

                switch (direction) {
                    case "up":
                        y -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "left":
                        x -= speed;
                        break;
                    case "right":
                        x += speed;
                        break;
                }

                spriteCounter++; //contador para mudar o valor da vaiavél 
                if (spriteCounter > 12) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }

        }
    }

    

    

    public void draw(Graphics2D g2) {
        //player 

        //g2.setColor(Color.WHITE);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        //sistema de animação baseado no contador 
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;

                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }

                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }

                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }

                break;

        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

}
