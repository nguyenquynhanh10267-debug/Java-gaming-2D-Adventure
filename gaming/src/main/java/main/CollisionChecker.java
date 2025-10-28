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
    // kiem tra va cham giua enity va object
    public int checkObject(Enity enity, boolean player){
        int index = 999;// khong co object
        for(int i =0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                // vi tri enity
                enity.solidArea.x = enity.worldX + enity.solidArea.x;
                enity.solidArea.y = enity.worldY + enity.solidArea.y;
                // vi tri object
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
                //check va cham
                switch(enity.direction){
                    case "up":
                        enity.solidArea.y -= enity.speed;
                        if(enity.solidArea.intersects(gp.obj[i].solidArea)){
                            
                            if(gp.obj[i].collision == true){
                                enity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        enity.solidArea.y += enity.speed;
                        if(enity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                enity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        enity.solidArea.x -= enity.speed;
                        if(enity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                enity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        enity.solidArea.x += enity.speed;
                        if(enity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                enity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }
                // if(enity.solidArea.intersects(gp.obj[i].solidArea)){
                //     if(gp.obj[i].collision == true){
                //         enity.collisionOn = true;
                //     }
                //     if(player == true){
                //         index = i;
                //     }
                // }
                // reset lai vi tri sau khi kiem tra
                enity.solidArea.x = enity.solidAreaDefaultX;
                enity.solidArea.y = enity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            
            }
        }
        return index;
    }   
}
