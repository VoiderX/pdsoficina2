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
        if(e.getSource().equals(Sliders.get(0))){
            System.out.println("Slider 0");
        }
        if(e.getSource().equals(Sliders.get(1))){
            System.out.println("Slider 1");
        }        
        if(e.getSource().equals(Sliders.get(2))){
            System.out.println("Slider 2");
        }
        if(e.getSource().equals(Sliders.get(3))){
            System.out.println("Slider 3");
        }
        if(e.getSource().equals(Sliders.get(4))){
            System.out.println("Slider 4");
        }        
        if(e.getSource().equals(Sliders.get(5))){
            System.out.println("Slider 5");
        }
        if(e.getSource().equals(Sliders.get(6))){
            System.out.println("Slider 6");
        }
        if(e.getSource().equals(Sliders.get(7))){
            System.out.println("Slider 7");
        }        
        if(e.getSource().equals(Sliders.get(8))){
            System.out.println("Slider 8");
        }
        if(e.getSource().equals(Sliders.get(9))){
            System.out.println("Slider 9");
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
      Sliders.add(Slider1);
      Sliders.add(Slider2);
      Sliders.add(Slider3);
      Sliders.add(Slider4);
      Sliders.add(Slider5);
      Sliders.add(Slider6);
      Sliders.add(Slider7);
      Sliders.add(Slider8);
      Sliders.add(Slider9);
    }    
    
}
