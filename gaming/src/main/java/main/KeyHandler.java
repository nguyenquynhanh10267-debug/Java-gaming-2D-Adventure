package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    
    //debug
    boolean checkDrawTime = false;
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // return so phim duoc nhan
        if(code == KeyEvent.VK_W){
            upPressed = true;
            //System.out.println("Up");
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
           // System.out.println("Down");
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
            //System.out.println("Left");
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
           // System.out.println("Right");
        }
        //Debug
        if(code == KeyEvent.VK_T){
            if(checkDrawTime == false){
                checkDrawTime = true;
            }
            else if(checkDrawTime == true){
                checkDrawTime = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode(); // return so phim duoc nhan
        if(code == KeyEvent.VK_W){
            upPressed = false;
            //System.out.println("Up");
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
           // System.out.println("Down");
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
            //System.out.println("Left");
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
           // System.out.println("Right");
        }
    }
    
}
