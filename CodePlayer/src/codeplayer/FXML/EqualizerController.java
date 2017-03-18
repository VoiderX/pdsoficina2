/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.FXML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Equalizer;
import javazoom.jl.player.AudioDeviceBase;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class EqualizerController implements Initializable {

    Equalizer eq;
    @FXML
    public void equalizar(){
      System.out.println(eq.getBandCount());
     for(int i=0;i<31;i++){
          eq.setBand(i, -1);
          System.out.println(eq.getBand(i));
      }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       eq= new Equalizer();
       
      
    }    
    
}
