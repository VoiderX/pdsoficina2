/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.misc;

import codeplayer.ControleUI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class SpecCfgController implements Initializable {
    @FXML
    private ColorPicker BackgroundColor;
    @FXML
    private ColorPicker LabelColor;
    @FXML
    private ColorPicker BarColor;
    @FXML
    private Slider Numbands; 
    @FXML
    private Slider Intervalo;
    @FXML
    public void setBack(){
        System.out.println(BackgroundColor.getValue().toString());
        ControleUI.getInstance().getSpectrumControl().setBackgroundC(BackgroundColor.getValue().toString());
    }
    @FXML
    public void setLabel(){
         System.out.println(LabelColor.getValue().toString());
         ControleUI.getInstance().getSpectrumControl().setLabelC(LabelColor.getValue().toString());
    }
    @FXML
    public void setBar(){
         System.out.println(BarColor.getValue().toString());
         ControleUI.getInstance().getSpectrumControl().setBlockC(BarColor.getValue().toString());         
    }
    @FXML
    public void setNumBands(){
        System.out.println((int)Numbands.getValue());
        ControleUI.getInstance().getSpectrumControl().setBands((int)Numbands.getValue());
        ControleUI.getInstance().getSpectrumControl().start(
        ControleUI.getInstance().getSpectrumControl().getGc());
    }
    @FXML
    public void setIntervalo(){
        System.out.println(Intervalo.getValue());
        ControleUI.getInstance().getSpectrumControl().setInter(1/Intervalo.getValue());
        ControleUI.getInstance().getSpectrumControl().start(
        ControleUI.getInstance().getSpectrumControl().getGc());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Numbands.valueProperty().addListener(listener->setNumBands());
       Intervalo.valueProperty().addListener(listerner->setIntervalo());
    }    
    
}
