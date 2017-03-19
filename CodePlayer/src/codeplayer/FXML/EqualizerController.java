/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.FXML;

import codeplayer.Mp3Buf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.AudioEqualizer;
import javafx.scene.media.EqualizerBand;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class EqualizerController implements Initializable {
    ObservableList<EqualizerBand> bands;
    @FXML
    public void equalizar(){
        for(int i=0;i<bands.size();i++){
            bands.get(i).setGain(EqualizerBand.MAX_GAIN);
        }
    }
    @FXML
    public void equalizar2(){
        for(int i=0;i<bands.size();i++){
            bands.get(i).setGain(EqualizerBand.MIN_GAIN);
        }
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
      bands=Mp3Buf.getInstance().getMp().getAudioEqualizer().getBands();
      }    
    
}
