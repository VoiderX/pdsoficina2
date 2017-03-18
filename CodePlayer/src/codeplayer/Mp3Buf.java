/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;

import java.io.File;
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
    
    String pathMusic=new File("C:\\Users\\ecsan\\Dropbox\\GitHub\\PDS\\pdsoficina2\\CodePlayer\\res\\musica.mp3").toURI().toString();

    public String getPathMusic() {
        return pathMusic;
    }

    public void setPathMusic(String pathMusic) {
        this.pathMusic = pathMusic;
    }
    
}
