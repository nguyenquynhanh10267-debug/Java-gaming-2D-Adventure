package enity;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.KeyHandler;
//import java.awt.Color;
import main.UtilityTool;
import main.GamePanel;

public class Player extends Enity{
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0; // so chia khoa nguoi choi co duoc


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth /2 - (gp.tileSize /2);
        screenY = gp.screenHeight /2 - (gp.tileSize/2); // vi tri player luon o giua man hinh
        
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;// luu vi tri ban dau de reset khi can
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        // vi tri khoi dau cua player trong the gioi
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        
        up1 = setup("oldman_up_1");
        up2 = setup("oldman_up_2");
        down1 = setup("oldman_down_1");
        down2 = setup("oldman_down_2");
        left1 = setup("oldman_left_1");
        left2 = setup("oldman_left_2");
        right1 = setup("oldman_right_1");
        right2 = setup("oldman_right_2");
    }
    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void update(){ // call 60 times per second
         if(keyH.upPressed == true || keyH.downPressed == true ||
                     keyH.leftPressed == true || keyH.rightPressed == true){
            // neu co 1 phim duoc nhan thi moi di chuyen
            // thay doi vi tri player theo key pressed
            if(keyH.upPressed == true){
            // nguoi choi di chuyen len tren
                direction = "up";
                //worldY -= speed;
            
            } else if(keyH.downPressed == true){
                // nguoi choi di chuyen xuong duoi
                direction = "down";
                //worldY += speed;
            
            } else if(keyH.leftPressed == true){
                // nguoi choi di chuyen sang trai
                direction = "left";
                //worldX -= speed;
            
            } else if(keyH.rightPressed == true){
                // nguoi choi di chuyen sang phai
                direction = "right";
                //worldX += speed;
            
            }
            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            // check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
            // if collision is false, player can move
            if(collisionOn == false){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            // them bo dem de thay doi sprite
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum ==1){
                    spriteNum =2;
                } else if (spriteNum ==2){
                    spriteNum =1;
                }
            spriteCounter =0;// reset lai bo dem
            }
        }
        
    }
    public void pickUpObject(int index){
        // xu ly khi nguoi choi nhan duoc object
        if(index != 999){
            //gp.obj[index] = null;
            String objectName = gp.obj[index].name;
            switch(objectName){
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[index] = null;
                    //System.out.println("You got a key! \n" + "Keys in your bag: " + hasKey);
                    gp.ui.showMessage("You got a key! " );
                    break;
                case "Door":
                    if(hasKey >0){
                        gp.playSE(3);
                        gp.obj[index] = null;
                        hasKey--;
                        //System.out.println("You opened the door! \n" + "Keys left: " + hasKey);
                        gp.ui.showMessage("You opened the door! ");
                    } else {
                        //System.out.println("You need a key to open this door.");
                        gp.ui.showMessage("You need a key to open this door.");
                    }
                    break;
                case "Boots":// tang toc do di chuyen
                    gp.playSE(2);
                    speed +=2;
                    gp.obj[index] = null;
                    //System.out.println("Speed up! Your speed is now " + speed);
                    gp.ui.showMessage("Speed up! Your speed is now " + speed);
                    break;
                // co the tao vat pham khien nguoi choi cham hon
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    // gp.obj[index] = null;
                    // //System.out.println("You found the treasure! You win!");
                    // gp.ui.showMessage("You found the treasure! You win!");
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){

        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum ==1){
                    image = up1;
                }
                if(spriteNum ==2){
                    image = up2;
                }
        
                break;
            case "down":
                if(spriteNum ==1){
                    image = down1;
                }
                if(spriteNum ==2){
                    image = down2;
                }
                
                break;
            case "left":
                if(spriteNum ==1){
                    image = left1;
                }
                if(spriteNum ==2){
                    image = left2;
                }
                
                break;
            case "right":
                if(spriteNum ==1){
                    image = right1;
                }
                if(spriteNum ==2){
                    image = right2;
                }
                
                break;
        }
        g2.drawImage(image, screenX,screenY, null);
    }

}
