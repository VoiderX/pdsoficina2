/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.FXML;

import codeplayer.ControleUI;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
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
    Thread mpplay;
    boolean Pausado=false;
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
    MediaPlayer mp;
    @FXML
     public void play(){
        String path = codeplayer.Mp3Buf.getInstance().getPathMusic();
        Media media = new Media(path);
        mp = new MediaPlayer(media);
        mp.play();
     }
     @FXML
     public void stop(){
         mp.stop();
     }
     @FXML
     public void pause(){
      mp.pause();
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
