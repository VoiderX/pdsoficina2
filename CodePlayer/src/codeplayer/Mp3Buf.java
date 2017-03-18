/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 *
 * @author Gabriel
 */
public final class Mp3Buf {


   private static Mp3Buf INSTANCE = null;

   public static Mp3Buf getInstance(){
      return((INSTANCE == null)?INSTANCE = new Mp3Buf():INSTANCE);
   }    
      
   public boolean isPlaying=false;

    public boolean isIsPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
    
    public boolean checkInstance=false;

    public boolean isCheckInstance() {
        return checkInstance;
    }

    public void setCheckInstance(boolean checkInstance) {
        this.checkInstance = checkInstance;
    }    
        
    String pathMusic=new File("C:\\Users\\Gabriel\\Documents\\NetBeansProjects\\FXPlayerTest\\res\\musica.mp3").toURI().toString();

    public String getPathMusic(){
        return pathMusic;
    }

    public void setPathMusic(String pathMusic) {
        this.pathMusic = pathMusic;
    }

     Media media;
     MediaPlayer mp;

    public MediaPlayer getMp(){
        if(checkInstance==false){
            checkInstance=true;
            carregaMusica();
        }
        return mp;
    }

    public void setMp(MediaPlayer mp) {
        this.mp = mp;
    }
     
    public void carregaMusica(){
        pathMusic = codeplayer.Mp3Buf.getInstance().getPathMusic();
        media = new Media(pathMusic);
        mp = new MediaPlayer(media);
    }
}
