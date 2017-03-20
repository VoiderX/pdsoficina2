/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.FXML;

import codeplayer.Mp3Buf;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.AudioEqualizer;
import javafx.scene.media.EqualizerBand;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class EqualizerController implements Initializable {
    ObservableList<EqualizerBand> bands;
    @FXML
    ArrayList<Slider> Sliders=new ArrayList<>();
    @FXML
    ArrayList<Text> Freqs = new ArrayList<>();
    @FXML
    Slider Slider0;
    @FXML
    Slider Slider1;
   @FXML
    Slider Slider2;
   @FXML
    Slider Slider3;
   @FXML
    Slider Slider4;
   @FXML
    Slider Slider5;
   @FXML
    Slider Slider6;
   @FXML
    Slider Slider7;
   @FXML
    Slider Slider8;
   @FXML
    Slider Slider9;
   @FXML
   Text freq0;
   @FXML
   Text freq1;
   @FXML
   Text freq2;
   @FXML
   Text freq3;
   @FXML
   Text freq4;
   @FXML
   Text freq5;
   @FXML
   Text freq6;
   @FXML
   Text freq7;
   @FXML
   Text freq8;
   @FXML
   Text freq9;
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
    public void changeSlider(Event e){
        for(int i=0;i<Sliders.size();i++){
            if(e.getSource()==Sliders.get(i)){
                System.out.println("Slider "+i+": "+Sliders.get(i).getValue());
            }
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
      bands=Mp3Buf.getInstance().getMp().getAudioEqualizer().getBands();
      System.out.println(bands.size());
      Sliders.add(Slider0);
      Freqs.add(freq0);
      
      Sliders.add(Slider1);
      Freqs.add(freq1);
      
      Sliders.add(Slider2);
      Freqs.add(freq2);
      
      Sliders.add(Slider3);
      Freqs.add(freq3);
      
      Sliders.add(Slider4);
      Freqs.add(freq4);
      
      Sliders.add(Slider5);
      Freqs.add(freq5);
      
      Sliders.add(Slider6);
      Freqs.add(freq6);
      
      Sliders.add(Slider7);
      Freqs.add(freq7);
      
      Sliders.add(Slider8);
      Freqs.add(freq8);
      
      Sliders.add(Slider9);
      Freqs.add(freq9);
      
      for(int i=0;i<Sliders.size();i++){
       Freqs.get(i).setText(Double.toString(bands.get(i).getBandwidth()));
      }
      System.out.println(EqualizerBand.MAX_GAIN);
      System.out.println(EqualizerBand.MIN_GAIN);
    }    
    
}
