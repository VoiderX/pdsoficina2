/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.FXML;

import codeplayer.ControleUI;
import codeplayer.Mp3Buf;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

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
        Mp3Buf.getInstance().getMp().play();
        ObservableMap<String,Object> metadata= Mp3Buf.getInstance().getMedia().getMetadata();
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
     @FXML
     public void stop(){
        Mp3Buf.getInstance().getMp().stop();
     }
     @FXML
     public void pause(){
        Mp3Buf.getInstance().getMp().pause();
     }
     @FXML
     public void exibeEqualizer(){
         ControleUI.getInstance().mostraEqualizer();
     }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
