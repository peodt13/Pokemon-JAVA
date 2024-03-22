package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.Painel;

public class TileManager {

    Painel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(Painel gp) {
    	 this.gp = gp;
    	    tile = new Tile[10];
    	    
    	    // Inicialize o array mapTileNum antes de chamar loadMap
    	    mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
    	    
    	    getTileImage();
    	    loadMap();
        
        

    }

    public void getTileImage() {
        //salvando imagem na posição 
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grama (1).png"));

            tile[1] = new Tile();
            tile[1].image =ImageIO.read(getClass().getResourceAsStream("/tiles/bordas.png"));
              
            tile[2] = new Tile();
            tile[2].image =ImageIO.read(getClass().getResourceAsStream("/tiles/arvorep.png"));
            tile[2].collision = true;
                    
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/areia.png"));
            
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/areia_esquerda.png"));
            
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/areia_direita.png"));
            
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/areiaCima.png"));
            
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/areiaBaixo.png"));
        
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/npcp.png"));
            tile[8].collision = true;
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    
public void loadMap() {
	try {
        InputStream is = getClass().getResourceAsStream("/maps/map1.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        int row = 0; // Inicialize a variável row aqui

        // Leia o arquivo linha por linha
        String line;
        while ((line = br.readLine()) != null && row < gp.maxScreenRow) {
            String[] numbers = line.split(" ");
            for (int col = 0; col < gp.maxScreenCol && col < numbers.length; col++) {
                int num = Integer.parseInt(numbers[col]);
                mapTileNum[col][row] = num;
            }
            row++; // Avance para a próxima linha
        }
       
        br.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void draw(Graphics2D g2) {
    	if (mapTileNum == null) {
            System.out.println("Error: mapTileNum is null in draw method");
            return;
        }
    	
    int col = 0;
    int row = 0;
    int x = 0;
    int y = 0;

    while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
        int tileNum = mapTileNum[col][row];
        g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
        col++;
        x += gp.tileSize;
        if (col == gp.maxScreenCol) {
            col = 0;
            x = 0;
            row++;
            y += gp.tileSize;
        }
    }
}
}
