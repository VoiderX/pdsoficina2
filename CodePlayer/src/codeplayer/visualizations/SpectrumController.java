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
    
    private final int spaceX1 = 50,spaceX2=30,spaceY1 = 10,spaceY2=25,bands = 60,strokeW = 2,tresh=-100;
    private final int maxf=25600;
    private final double inter=0.02;
    /**
     * Initializes the controller class.
     */
    
    public void staticElements(GraphicsContext gc){
        //background
        gc.setFill(Color.web("201D1D"));
        
        gc.fillRect(0, 0, spec.getWidth(), spec.getHeight());
        //linha vertical
        gc.strokeLine(spaceX1, spaceY1, spaceX1, (spec.getHeight()-spaceY2));
        //linha horizontal
        gc.strokeLine(spaceX1,(spec.getHeight()-spaceY2),(spec.getWidth()-spaceX2),(spec.getHeight()-spaceY2));
        //labels do y
        int interv = 5;
        float text=-tresh;
        int passo=(-tresh)/interv,displaceY=spaceY1+5;
        gc.setLineWidth(1);
        for(int i=0;i<(interv+1);i++){
            gc.strokeText(""+text,11,displaceY+5);
            gc.strokeLine(45, displaceY, 50, displaceY);
            displaceY+=(spec.getHeight()-spaceY1-spaceY2-strokeW)/interv;
            text-=passo;
        }
        //labels do x
        interv=(bands<=10)?bands:10;
        text=0;
        passo=maxf/interv;
        int displaceX=spaceX1;
        for(int i=0;i<(interv+1);i++){
            if(text<1000){
                gc.strokeText(""+text,displaceX,(spec.getHeight()-spaceY2)+15);
            }else{
                gc.strokeText((text/1000)+"k",displaceX,(spec.getHeight()-spaceY2)+15);
            }
            gc.strokeLine(displaceX, (spec.getHeight()-spaceY2),displaceX, (spec.getHeight()-spaceY2+5));
            displaceX+=(spec.getWidth()-spaceX1-spaceX2-strokeW)/interv;
            text+=passo;
        }
        gc.setLineWidth(strokeW);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GraphicsContext gc = spec.getGraphicsContext2D();
        gc.setStroke(Color.WHITE);
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
        return ((mag/(-tresh))*(spec.getHeight()-spaceY2-spaceY1));
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
                    int displaceX=spaceX1+strokeW;
                    gc.clearRect(0, 0, spec.getWidth(), spec.getHeight());
                    staticElements(gc);
                    gc.setFill(Color.ROYALBLUE);
                    for(int i=0;i<bands;i++){
                        gc.fillRect((displaceX),((spec.getHeight()-strokeW)-(prop(mag[i]-tresh))),(spec.getWidth()/bands),(prop(mag[i]-tresh)-spaceY2));
                        displaceX+=(spec.getWidth()-spaceX1-strokeW-spaceX2)/bands;
                        
                    }
                }
            });
        }
        
    }
    
}
