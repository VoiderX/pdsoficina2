/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;


import java.util.ArrayList;
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
            
    ArrayList<Musica> Musicas;

    public ArrayList<Musica> getMusicas() {
        return Musicas;
    }

    public void setMusicas(ArrayList<Musica> Musicas) {
        this.Musicas = Musicas;
    }
    
    String pathMusic;
    public String getPathMusic(){
        return pathMusic;
    }

    public void setPathMusic(String pathMusic) {
        this.pathMusic = pathMusic;
    }

     Media media=null;

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
    
    public boolean metadataReady=false;
     
     MediaPlayer mp=null;

    public MediaPlayer getMp(){
        if(mp==null){
         carregaMusica();
        }
        return mp;
    }

    public void setMp(MediaPlayer mp) {
        this.mp = mp;
    }
    
    int PosTocando;

    public int getPosTocando() {
        return PosTocando;
    }

    public void setPosTocando(int PosTocando) {
        this.PosTocando = PosTocando;
    }
    
    public void carregaMusica(){
        if(PosTocando>=Musicas.size()){
            PosTocando=0;
        }
        if(PosTocando<0){
            PosTocando=Musicas.size()-1;
        }
        pathMusic= Musicas.get(PosTocando).getPathMusic();
        media = new Media(pathMusic);
        mp = new MediaPlayer(media);
        
    }
}
