/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;


import java.util.ArrayList;
import javafx.collections.MapChangeListener;
import javafx.scene.media.EqualizerBand;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 *
 * @author Gabriel
 */
//Classe para manipulação do player MP3 também em singleton
public final class Mp3Buf {
   
   private static Mp3Buf INSTANCE = null;

   public static Mp3Buf getInstance(){
      return((INSTANCE == null)?INSTANCE = new Mp3Buf():INSTANCE);
   }    
      
            
    ArrayList<Musica> Musicas; //ArrayList para guardar os caminhos das musicas a serem reproduzidas

    public ArrayList<Musica> getMusicas() {
        return Musicas;
    }

    public void setMusicas(ArrayList<Musica> Musicas) {
        this.Musicas = Musicas;
    }
    
    String pathMusic;//Caminho da música a ser reproduzida no momento
    public String getPathMusic(){
        return pathMusic;
    }

    public void setPathMusic(String pathMusic) {
        this.pathMusic = pathMusic;
    }

     Media media=null;//Classe Media uitlizada para iniciar o MediaPlayer e também para puxar os metadados

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }   
     
    MediaPlayer mp=null; //MediaPlayer, classe responsável pela reprodução em si
    public MediaPlayer checkmp(){
        return mp;
    }
    public MediaPlayer getMp(){
        if(mp==null){
         carregaMusica();
        }
        return mp;
    }

    public void setMp(MediaPlayer mp) {
        this.mp = mp;
    }
    
    int PosTocando;//Indica a posição que está sendo reproduzida na playlist

    public int getPosTocando() {
        return PosTocando;
    }

    public void setPosTocando(int PosTocando) {
        this.PosTocando = PosTocando;
    }
    ArrayList<Banda> bandas=null; //Array Contendo os valores das bandas

    public ArrayList<Banda> getBandas() { 
        return bandas;
    }

    public void setBandas(ArrayList<Banda> bandas) {
        this.bandas = bandas;
    }
    
    public void carregaMusica(){//Classe para carregar a música no Media e no MediaPlayer
        if(PosTocando>=Musicas.size()){ //Caso chegue na ultima música retorna ao inicio
            PosTocando=0;
        }
        if(PosTocando<0){ //Caso o usuário esteja na ultima música e aperta o anterior vai para a  ultima da lista
            PosTocando=Musicas.size()-1;
        }
        mp=null;
        media=null;
        System.gc();
        pathMusic= Musicas.get(PosTocando).getPathMusic();//Puxa o pathmusic da musica a ser tocada
        media = new Media(pathMusic); //Carrega a Media
        mp=new MediaPlayer(media);
        mp.setOnEndOfMedia(new CheckNextMusic());
        //Metodo para verificar as alterações nos metadados
        Mp3Buf.getInstance().getMp().getMedia().getMetadata().addListener(new MapChangeListener<String,Object>(){
           @Override
           public void onChanged(MapChangeListener.Change<? extends String, ? extends Object> change) {
           ControleUI.getInstance().getPlayerController().setInfos(
                   Mp3Buf.getInstance().getMedia().getMetadata());
           }
        });
        mp.getAudioEqualizer().getBands().clear();
        double mid=(EqualizerBand.MAX_GAIN - EqualizerBand.MIN_GAIN)/2;
        double freq=20;
        for(int i=0;i<=9;i++){
            Mp3Buf.getInstance().getMp().getAudioEqualizer().getBands().add(new EqualizerBand(freq,freq/2,0));
            freq *= 2;
        }
        //ControleUI.getInstance().getPlayerController().setInfos(media.getMetadata());
        if(bandas!=null){ //Verifica se o array já foi inicializado
            for(int i=0;i<bandas.size();i++){// Carrega os dados no array
                
                mp.getAudioEqualizer().getBands().get(i).setGain(bandas.get(i).getValor());
            }
        }
        
    }
}
