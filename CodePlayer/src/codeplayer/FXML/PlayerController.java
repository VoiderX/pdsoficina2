/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.FXML;

import codeplayer.CheckMetadata;
import codeplayer.ControleUI;
import codeplayer.Mp3Buf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class PlayerController implements Initializable {
    @FXML
    Text Faixa;
    @FXML
    Text Artista;
    @FXML
    Text Titulo;
    @FXML
    Text Album;
    @FXML
    Text Ano;
    @FXML
     public void play(){ 
         if(Mp3Buf.getInstance().getMp()==null){
         }else{
            Thread tr=new Thread(new CheckMetadata());
            Mp3Buf.getInstance().getMp().play();
            tr.start();
         }
     }
     @FXML
     public void stop(){
        if(Mp3Buf.getInstance().getMp()!=null){
            Mp3Buf.getInstance().getMp().stop();
        }
     }
     @FXML
     public void pause(){
         if(Mp3Buf.getInstance().getMp()!=null){
             Mp3Buf.getInstance().getMp().pause();
         }
     }
     @FXML
     public void exibeEqualizer(){
         ControleUI.getInstance().mostraEqualizer();
     }
     @FXML
     public void loadMusicDialog(){
        FileChooser fc= new FileChooser();
        try{
        Mp3Buf.getInstance().setPathMusic(fc.showOpenDialog(ControleUI.getInstance().getSecondStage()).toURI().toString());
        if(Mp3Buf.getInstance().getMp()!=null && Mp3Buf.getInstance().getPathMusic()!=null){
            Mp3Buf.getInstance().getMp().dispose();
            Mp3Buf.getInstance().setMp(null);
            play();
        }
        }
        catch(Exception e){
            //e.printStackTrace();
        }
     }
     public void setInfos(ObservableMap<String,Object> metadata){
         if(metadata.get("artist")!=null){
                Artista.setText(metadata.get("artist").toString());
            }
            if(metadata.get("title")!=null){
                Titulo.setText(metadata.get("title").toString());
            }
            if(metadata.get("album")!=null){
                Album.setText(metadata.get("album").toString());
            }
            if(metadata.get("year")!=null){
                Ano.setText(metadata.get("year").toString());
            }
            if(metadata.get("track")!=null){
                Faixa.setText(metadata.get("track").toString());
            }
     }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
