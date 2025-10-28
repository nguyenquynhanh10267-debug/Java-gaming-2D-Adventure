package main;
import enity.Enity;
public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    // kiem tra va cham giua enity va tile
    public void checkTile(Enity enity){
        // vi tri cua enity trong the gioi
        int enityLeftWorldX = enity.worldX + enity.solidArea.x;
        int enityRightWorldX = enity.worldX + enity.solidArea.x + enity.solidArea.width;
        int enityTopWorldY = enity.worldY + enity.solidArea.y;
        int enityBottomWorldY = enity.worldY + enity.solidArea.y + enity.solidArea.height;

        // vi tri
        int enityLeftCol = enityLeftWorldX / gp.tileSize;
        int enityRightCol = enityRightWorldX / gp.tileSize;
        int enityTopRow = enityTopWorldY / gp.tileSize;
        int enityBottomRow = enityBottomWorldY / gp.tileSize;
        int tileNum1, tileNum2;
        switch(enity.direction){
            case "up":
                enityTopRow = (enityTopWorldY - enity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[enityLeftCol][enityTopRow];
                tileNum2 = gp.tileM.mapTileNum[enityRightCol][enityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    enity.collisionOn = true;
                }
                break;
            case "down":
                enityBottomRow = (enityBottomWorldY + enity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[enityLeftCol][enityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[enityRightCol][enityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    enity.collisionOn = true;
                }
                break;
            case "left":
                enityLeftCol = (enityLeftWorldX - enity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[enityLeftCol][enityTopRow];
                tileNum2 = gp.tileM.mapTileNum[enityLeftCol][enityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    enity.collisionOn = true;
                }
                break;
            case "right":
                enityRightCol = (enityRightWorldX + enity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[enityRightCol][enityTopRow];
                tileNum2 = gp.tileM.mapTileNum[enityRightCol][enityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    enity.collisionOn = true;
                }
                break;
        }   
    }
}
