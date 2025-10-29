package main;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    
    Clip clip;// am thanh duoc chuyen vao trong clip
    URL soundURL[] = new URL[30];// mang chua duong dan toi am thanh
    public Sound(){
        soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
        // soundURL[5] = getClass().getResource("/res/sound/hitmonster.wav");
        // soundURL[6] = getClass().getResource("/res/sound/receivedamage.wav");
    
    }
    public void setFile(int i){
        try{
            AudioInputStream ais = 
            AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch ( Exception e){
            e.printStackTrace();
        }
    }
    
    public void play(){
        clip.start();
    }
    // lặp lại âm thanh
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
