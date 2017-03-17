/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

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
    
    String pathMusic=null;

    public String getPathMusic() {
        return pathMusic;
    }

    public void setPathMusic(String pathMusic) {
        this.pathMusic = pathMusic;
    }
    
    public Player player;
   
    public void criaPlayer(){
        try{
        pathMusic="res/musica.mp3";
        File mp3File = new File(pathMusic);
	FileInputStream fis = new FileInputStream(mp3File);
	BufferedInputStream bis = new BufferedInputStream(fis);
        this.player = new Player(bis);
        }
        catch(Exception e){
            e.printStackTrace();
        }
   }
    public Player getPlayer(){
        if(isIsPlaying()==false){
            isPlaying=true;
            criaPlayer();
        }
        return this.player;
    }   
   
}
