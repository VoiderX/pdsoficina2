/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.visualizations;

import codeplayer.ControleUI;
import codeplayer.Mp3Buf;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class SpectrumController implements Initializable {
//    @FXML
//    Canvas spec;
    @FXML
    Pane pane;
    
    //variáveis para o funcionamento(NÃO MEXER)
    private final int spaceX1 = 50,spaceX2=30,spaceY1 = 10,spaceY2=25,strokeW = 2;
    private final int maxf=25600;
    
    //variáveis para a visualização
    private int bands = 256,tresh=-100,type=1,numC=2;
    private double inter=0.02;
    private String backgroundC ="201D1D",labelC="FFFFFF",blockC="4169E1",blockC1="FF0000",blockC2="0000FF",blockC3="AACCFF";
    private GraphicsContext gc;
    

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }
    

    public int getBands() {
        return bands;
    }

    public void setBands(int bands) {
        this.bands = bands;
    }

    public double getInter() {
        return inter;
    }

    public void setInter(double inter) {
        this.inter = inter;
    }

    public String getBackgroundC() {
        return backgroundC;
    }

    public void setBackgroundC(String backgroundC) {
        this.backgroundC = backgroundC;
    }

    public String getLabelC() {
        return labelC;
    }

    public void setLabelC(String labelC) {
        this.labelC = labelC;
    }

    public String getBlockC() {
        return blockC;
    }

    public void setBlockC(String blockC) {
        this.blockC = blockC;
    }
    
    /**
     * Initializes the controller class.
     */
    
    public void staticElements(GraphicsContext gc){
        //background
        gc.setFill(Color.web(backgroundC));
        
        gc.fillRect(0, 0, specT.getWidth(), specT.getHeight());
        //linha vertical
        gc.strokeLine(spaceX1, spaceY1, spaceX1, (specT.getHeight()-spaceY2));
        //linha horizontal
        gc.strokeLine(spaceX1,(specT.getHeight()-spaceY2),(specT.getWidth()-spaceX2),(specT.getHeight()-spaceY2));
        gc.setStroke(Color.web(labelC));
        //labels do y
        int interv = 5;
        float text=0;
        int passo=(tresh)/interv,displaceY=spaceY1+5;
        gc.setLineWidth(1);
        for(int i=0;i<(interv+1);i++){
            gc.strokeText(""+text,11,displaceY+5);
            gc.strokeLine(45, displaceY, 50, displaceY);
            displaceY+=(specT.getHeight()-spaceY1-spaceY2-strokeW)/interv;
            text+=passo;
        }
        //labels do x
        interv=(bands<=10)?bands:10;
        text=0;
        passo=maxf/interv;
        int displaceX=spaceX1;
        for(int i=0;i<(interv+1);i++){
            if(text<1000){
                gc.strokeText(""+text,displaceX,(specT.getHeight()-spaceY2)+15);
            }else{
                gc.strokeText((text/1000)+"k",displaceX,(specT.getHeight()-spaceY2)+15);
            }
            gc.strokeLine(displaceX, (specT.getHeight()-spaceY2),displaceX, (specT.getHeight()-spaceY2+5));
            displaceX+=(specT.getWidth()-spaceX1-spaceX2-strokeW)/interv;
            text+=passo;
        }
        gc.setLineWidth(strokeW);
    }
    
    ResizableCanvas specT = new ResizableCanvas();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane.getChildren().add(specT);
        System.out.println(specT.isResizable());
        gc = specT.getGraphicsContext2D();
        gc.setStroke(Color.web(labelC));
        gc.setLineWidth(strokeW);
        specT.widthProperty().bind(pane.widthProperty());
        specT.heightProperty().bind(pane.heightProperty());
        pane.widthProperty().addListener(event -> teste());
        pane.heightProperty().addListener(event -> teste());
        pane.widthProperty().addListener(event -> staticElements(gc));
        pane.heightProperty().addListener(event -> staticElements(gc));        
        start(gc);
    }

    private void teste(){
        System.out.println("Pane width: "+pane.getWidth());
        System.out.println("Pane height: "+pane.getHeight());
        System.out.println("SpecT width: "+specT.getWidth());
        System.out.println("SpecT height: "+specT.getHeight());
    }
    
    public double prop(double mag){
        return ((mag/(-tresh))*(specT.getHeight()-spaceY2-spaceY1));
    }
    
    private void fillMetod(int type){
        if(type==0){
            gc.setFill(Color.web(blockC));
        }else{
            if(numC==2){
                gc.setFill(new LinearGradient(0, 0, 0, 1, true, 
                        CycleMethod.NO_CYCLE,
                        Arrays.asList(
                                new Stop(0.3,Color.web(blockC1)),
                                new Stop(1,Color.web(blockC2)))));
            }else{
                gc.setFill(new LinearGradient(0, 0, 0, 1, true, 
                        CycleMethod.NO_CYCLE,
                        Arrays.asList(
                                new Stop(0.3,Color.web(blockC1)),
                                new Stop(0.6,Color.web(blockC2)),
                                new Stop(1.0, Color.web(blockC3)))));
            }
        }
    }
    
    public void start(GraphicsContext gc){
        staticElements(gc);
        if(Mp3Buf.getInstance().checkmp()!=null){
            Mp3Buf.getInstance().getMp().setAudioSpectrumThreshold(tresh);
            Mp3Buf.getInstance().getMp().setAudioSpectrumNumBands(bands);
            Mp3Buf.getInstance().getMp().setAudioSpectrumInterval(inter);
            Mp3Buf.getInstance().getMp().setAudioSpectrumListener(new AudioSpectrumListener() {
                @Override
                public void spectrumDataUpdate(double d, double d1, float[] mag, float[] phase) {
                    
                    int displaceX=spaceX1+strokeW;
                    gc.clearRect(0, 0, specT.getWidth(), specT.getHeight());
                    staticElements(gc);
                    //cor do grafico
                    fillMetod(type);
                    
                    //desenha o grafico
                    for(int i=0;i<bands;i++){
                        gc.fillRect((displaceX),((specT.getHeight()-strokeW)-prop((mag[i]-tresh))),((specT.getWidth()-spaceX1-strokeW-spaceX2)/bands),(prop((mag[i]-tresh))-spaceY2));
                        displaceX+=(specT.getWidth()-spaceX1-strokeW-spaceX2)/bands;
                        
                    }
                }
            });
        }
        
    }
    
}
