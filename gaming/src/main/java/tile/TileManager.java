package tile;

import main.GamePanel;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.io.BufferedReader;
import java.io.InputStreamReader;  
import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];
    
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10]; // tao 10 loai gach khac nhau
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];


        // co the thay doi so nay de tang so loai gach
        getTileImage();
        // co the thay doi duong dan de load map khac
        loadMap("/maps/map01.txt");
    }
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));
            
            tile[1] = new Tile();
            tile[1].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            //tile[1].collision = true;
            
            tile[2] = new Tile();
            tile[2].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));
            //tile[2].collision = true;
            
            
            
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String tilePath){
        //doc file map tu day
        try{
            InputStream is = getClass().getResourceAsStream(tilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            //doc tung dong trong file map

            int col = 0;
            int row = 0;   
            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();// doc tung dong
                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);// chuyen chuoi sang so nguyen

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        // ve tile o day 
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];
            // ve tile tai vi tri (x,y)
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
        
    }

}
