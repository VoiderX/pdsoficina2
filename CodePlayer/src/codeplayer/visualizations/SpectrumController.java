/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.visualizations;

import codeplayer.Mp3Buf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.media.AudioSpectrumListener;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class SpectrumController implements Initializable {
    @FXML
    BarChart<String,Number> Spectrum;
    XYChart.Series series1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       if(Mp3Buf.getInstance().checkmp()!=null){
            Mp3Buf.getInstance().getMp().setAudioSpectrumNumBands(10);
            Mp3Buf.getInstance().getMp().setAudioSpectrumInterval(0.1);
            Mp3Buf.getInstance().getMp().setAudioSpectrumListener(new AudioSpectrumListener() {
                @Override
                public void spectrumDataUpdate(double d, double d1, float[] floats, float[] floats1) {
                    Spectrum.getData().clear();
                    series1 = new XYChart.Series();
                    //series1.getData().add(new XYChart.Data("austria", -25));
                    for(int i=0;i<floats.length;i++){
                        series1.getData().add(new XYChart.Data("freq"+i, -floats[i]));
                         System.out.println(floats[i]);
                    }
                    Spectrum.getData().add(series1);
                }
            });
        }
        Spectrum.setLegendVisible(false);
    }    
    
}
