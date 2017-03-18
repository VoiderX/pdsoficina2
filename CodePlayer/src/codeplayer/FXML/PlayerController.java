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
