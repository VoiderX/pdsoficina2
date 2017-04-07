/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.visualizations;

import codeplayer.Mp3Buf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.media.AudioSpectrumListener;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class SpectrumController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       if(Mp3Buf.getInstance().checkmp()!=null){
            Mp3Buf.getInstance().getMp().setAudioSpectrumNumBands(10);
            Mp3Buf.getInstance().getMp().setAudioSpectrumInterval(0.5);
            Mp3Buf.getInstance().getMp().setAudioSpectrumListener(new AudioSpectrumListener() {
                @Override
                public void spectrumDataUpdate(double d, double d1, float[] floats, float[] floats1) {
                    for(int i=0;i<floats.length;i++){
                        System.out.println(d);
                        System.out.println(d1);
                        System.out.println(floats[i]);
                        System.out.println(floats[i]);
                    }
                }
            });
        }
    }    
    
}
