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
    public Tile[] tile;
    public int mapTileNum[][];
    
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10]; // tao 10 loai gach khac nhau
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];


        // co the thay doi so nay de tang so loai gach
        getTileImage();
        // co the thay doi duong dan de load map khac
        loadMap("/maps/world01.txt");
    }
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));
            
            tile[1] = new Tile();
            tile[1].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;//va cham
            
            tile[2] = new Tile();
            tile[2].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));
            tile[2].collision = true;
            
            tile[3] = new Tile();
            tile[3].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;
            
            tile[5] = new Tile();
            tile[5].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
            
            
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
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();// doc tung dong
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);// chuyen chuoi sang so nguyen

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
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
        int worldCol = 0;
        int worldRow = 0;
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];
            // check vi tri cua tile trong the gioi so voi vi tri cua player de tinh toan vi tri tren
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            // biet noi can ve tile tren man hinh o dau
            int screenX = worldX - gp.player.worldX + gp.player.screenX; // giu cho player luon o trung tam
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            // tao ranh gioi hien thi giup tiet kiem bo nho neu map too big
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
                {
                    // ve tile tai vi tri (x,y)
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            
                }
            
            worldCol++;
           
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
               
                worldRow++;
               
            }
        }
        
    }

}
