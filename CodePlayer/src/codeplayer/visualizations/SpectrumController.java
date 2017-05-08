/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.visualizations;

import codeplayer.ControleUI;
import codeplayer.Mp3Buf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class SpectrumController implements Initializable {
    @FXML
    Canvas spec;
    @FXML
    Pane pane;
    
    private final int spaceX = 50,spaceY = 50,bands = 80,strokeW = 2,tresh=-80,treshfx=-tresh;
    private final double inter=0.02;
    /**
     * Initializes the controller class.
     */
    
    public void staticElements(GraphicsContext gc){
        //linha vertical
        gc.strokeLine(spaceX, 0, spaceX, (spec.getHeight()-spaceY));
        //linha horizontal
        gc.strokeLine(spaceX,(spec.getHeight()-spaceY),(spec.getWidth()),(spec.getHeight()-spaceY));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GraphicsContext gc = spec.getGraphicsContext2D();
        gc.setFill(Color.ROYALBLUE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(strokeW);
        pane.widthProperty().addListener(event -> teste());
        pane.heightProperty().addListener(event -> teste());
        start(gc);
    }

    private void teste(){
        System.out.println("width: "+pane.getWidth());
        System.out.println("height: "+pane.getHeight());
    }
    
    public double prop(float mag){
        return ((mag/(treshfx))*(spec.getHeight()-spaceY));
    }
    
    private void start(GraphicsContext gc){
        staticElements(gc);
        
        if(Mp3Buf.getInstance().checkmp()!=null){
            Mp3Buf.getInstance().getMp().setAudioSpectrumThreshold(tresh);
            Mp3Buf.getInstance().getMp().setAudioSpectrumNumBands(bands);
            Mp3Buf.getInstance().getMp().setAudioSpectrumInterval(inter);
            Mp3Buf.getInstance().getMp().setAudioSpectrumListener(new AudioSpectrumListener() {
                @Override
                public void spectrumDataUpdate(double d, double d1, float[] mag, float[] phase) {
                    int displaceX=spaceX+strokeW;
                    gc.clearRect(0, 0, spec.getWidth(), spec.getHeight());
                    staticElements(gc);
                    for(int i=0;i<bands;i++){
                        gc.fillRect((displaceX),((spec.getHeight()-strokeW)-(prop(mag[i]+treshfx))),(spec.getWidth()/bands),(prop(mag[i]+treshfx)-spaceY));
                        displaceX+=(spec.getWidth()-spaceX-strokeW)/bands;
                        
                    }
                }
            });
        }
        
    }
    
}
