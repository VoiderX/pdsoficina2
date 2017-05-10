/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.visualizations;

import codeplayer.ControleUI;
import codeplayer.Mp3Buf;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.ResourceBundle;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
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
    @FXML
    AnchorPane anchor;
    
    MenuItem fs = new MenuItem("FullScreen");
    MenuItem callConfig= new MenuItem("Configurações");
    ContextMenu contMenu= new ContextMenu(callConfig,fs);
    
    //variáveis para o funcionamento(NÃO MEXER)
    private final int spaceX1 = 50,spaceX2=30,spaceY1 = 10,spaceY2=25,strokeW = 2;
    private final int maxf=25600;
    private double origin[] = new double[2];
    private boolean needScale = true;
    
    //variáveis para a visualização
    private int bands = 64,tresh=-80,typeFill=1,numC=3,typeDraw=1;
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

    public int getTypeFill() {
        return typeFill;
    }

    public void setTypeFill(int typeFill) {
        this.typeFill = typeFill;
    }

    public int getNumC() {
        return numC;
    }

    public void setNumC(int numC) {
        this.numC = numC;
    }

    public int getTypeDraw() {
        return typeDraw;
    }

    public void setTypeDraw(int typeDraw) {
        this.typeDraw = typeDraw;
    }

    public String getBlockC1() {
        return blockC1;
    }

    public void setBlockC1(String blockC1) {
        this.blockC1 = blockC1;
    }

    public String getBlockC2() {
        return blockC2;
    }

    public void setBlockC2(String blockC2) {
        this.blockC2 = blockC2;
    }

    public String getBlockC3() {
        return blockC3;
    }

    public void setBlockC3(String blockC3) {
        this.blockC3 = blockC3;
    }
    
    
    /**
     * Initializes the controller class.
     * @param gc
     */
    
    public void staticElements(GraphicsContext gc){
        //cor dos labels
        gc.setStroke(Color.WHITE);
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
        System.out.println("teste1");
        fs.setOnAction(e->fs());
        callConfig.setOnAction(e->openConfig());
        System.out.println("teste2");
        specT.setOnContextMenuRequested(event->contMenu.show(anchor, event.getScreenX(), event.getScreenY()));
        System.out.println("teste3");
        contMenu.setAutoHide(true);
        gc = specT.getGraphicsContext2D();
        gc.setStroke(Color.web(labelC));
        gc.setLineWidth(strokeW);
        specT.widthProperty().bind(pane.widthProperty());
        specT.heightProperty().bind(pane.heightProperty());
        pane.widthProperty().addListener(event -> start(gc));
        pane.heightProperty().addListener(event -> start(gc));        
        setBackgroundC(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getBackgroundColor());
        setBands(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getNumBands());
        setBlockC(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getBarColor());
        setBlockC1(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getBarColor());
        setBlockC2(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getSegundacorgrad());
        setBlockC3(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getTerceiracorgrad());
        setInter(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getIntervalo());
        setLabelC(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getLabelColor());
        setNumC(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getNumC());
        setTypeDraw(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getTipoDesenho());
        setTypeFill(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getPreenchimento());
        start(gc);
    }
    
    public double prop(double mag){
        return ((mag/(-tresh))*(specT.getHeight()-spaceY2-spaceY1));
    }
    
    private void fillMetod(int type){
        if(type==0){
            gc.setFill(Color.web(blockC));
        }else{
            if(numC==2){
                if(typeDraw!=2){
                    gc.setFill(new LinearGradient(0, 0, 0, 1, true, 
                        CycleMethod.NO_CYCLE,
                        Arrays.asList(
                                new Stop(0.3,Color.web(blockC1)),
                                new Stop(1,Color.web(blockC2)))));
                }else{
                    gc.setFill(new RadialGradient(0, 0, 
                            0.45, 
                            0.45,
                            0.5, 
                            true, 
                            CycleMethod.NO_CYCLE, 
                            Arrays.asList(
                                new Stop(0.3,Color.web(blockC1)),
                                new Stop(1,Color.web(blockC2)))));
                }
            }else{
                if(typeDraw!=2){
                    gc.setFill(new LinearGradient(0, 0, 0, 1, true, 
                            CycleMethod.NO_CYCLE,
                            Arrays.asList(
                                    new Stop(0.3,Color.web(blockC1)),
                                    new Stop(0.6,Color.web(blockC2)),
                                    new Stop(1.0, Color.web(blockC3)))));
                }else{
                    gc.setFill(new RadialGradient(0, 0, 
                            0.5, 
                            0.5,
                            0.5, 
                            true, 
                            CycleMethod.NO_CYCLE, 
                            Arrays.asList(
                                    new Stop(0.3,Color.web(blockC1)),
                                    new Stop(0.6,Color.web(blockC2)),
                                    new Stop(1.0, Color.web(blockC3)))));
                }
            }
        }
    }
    
    private void drawMetod(int typeDraw, GraphicsContext gc, double displaceX, float[] mag){
        if(typeDraw==0){
            needScale = true;
            for(int i=0;i<bands;i++){
                gc.fillRect((displaceX),((specT.getHeight()-strokeW)-prop((mag[i]-tresh))),((specT.getWidth()-spaceX1-strokeW-spaceX2)/bands),(prop((mag[i]-tresh))-spaceY2));
                displaceX+=(specT.getWidth()-spaceX1-strokeW-spaceX2)/bands;            
            }
        }else if(typeDraw==1){
            needScale = true;
            double[] cordX = new double[((bands*2)+3)];
            double[] cordY = new double[((bands*2)+3)];
            cordX[0] = (displaceX);
            cordY[0] = (specT.getHeight()-spaceY2-strokeW);

            for(int i=0;i<bands;i++){
                cordX[i+1] = displaceX;
                cordY[i+1] = (specT.getHeight()-spaceY2-prop((mag[i]-tresh))-strokeW);
                displaceX+=(specT.getWidth()-spaceX1-strokeW-spaceX2)/bands;

            }
            cordX[bands+1] = (displaceX+1);
            cordY[bands+1] = (specT.getHeight()-spaceY2-strokeW);
            cordX[bands+2] = (specT.getWidth()-spaceX2);
            cordY[bands+2] = (specT.getHeight()-spaceY2-strokeW);
            gc.fillPolygon(cordX, cordY,(bands+3));
        }else{
            needScale=false;
            fillMetod(typeFill);
            origin[0] = (pane.getWidth()/2);
            origin[1] = ((pane.getHeight()/2)-5);
            double baseR=(pane.getHeight()*0.20),uBaseR=(pane.getHeight()*0.22),expanMax=0;
            
            double[] cordX=new double[(bands+1)*2];
            Deque<Double> innerCordX = new ArrayDeque<>();
            double[] cordY=new double[(bands+1)*2];
            Deque<Double> innerCordY = new ArrayDeque<>();
            double passo = ((double) 360)/bands,dispAng=0;
            for(int i=0;i<(bands+1);i++){
                innerCordX.push(origin[0]+getX(baseR,dispAng));
                innerCordY.push(origin[1]+getY(baseR,dispAng));
                if(i<bands){
                    expanMax=(((mag[i]-tresh)/(-tresh))*(origin[1]-uBaseR));
                    cordX[i]=(origin[0]+getX((expanMax+uBaseR),dispAng));
                    cordY[i]=(origin[1]+getY((expanMax+uBaseR),dispAng));
                }else{
                    expanMax=(((mag[0]-tresh)/(-tresh))*(origin[1]-uBaseR));
                    cordX[i]=(origin[0]+getX(expanMax+uBaseR,dispAng));
                    cordY[i]=(origin[1]+getY(expanMax+uBaseR,dispAng));
                }
                dispAng+=passo;
                
            }

            int i=1;
            while(!innerCordX.isEmpty()){
                cordX[bands+i]=innerCordX.pop();
                cordY[bands+i]=innerCordY.pop();
                i++;
            }
            gc.fillPolygon(cordX, cordY, ((bands+1)*2));
        }
    }
    
    public void start(GraphicsContext gc){
        if(Mp3Buf.getInstance().checkmp()!=null){
            Mp3Buf.getInstance().getMp().setAudioSpectrumThreshold(tresh);
            Mp3Buf.getInstance().getMp().setAudioSpectrumNumBands(bands);
            Mp3Buf.getInstance().getMp().setAudioSpectrumInterval(inter);
            Mp3Buf.getInstance().getMp().setAudioSpectrumListener(new AudioSpectrumListener() {
                @Override
                public void spectrumDataUpdate(double d, double d1, float[] mag, float[] phase) {
                    
                    double displaceX=spaceX1+strokeW;
                    //limpa o canvas
                    gc.clearRect(0, 0, specT.getWidth(), specT.getHeight());
                    //redesenha o fundo
                    gc.setFill(Color.web(backgroundC));
                    gc.fillRect(0, 0, specT.getWidth(), specT.getHeight());
                    
                    //desenha os labels se precisar
                    if(needScale){
                        staticElements(gc);
                    }
                    //cor do grafico
                    fillMetod(typeFill);
                    
                    //desenha o grafico
                    drawMetod(typeDraw,gc,displaceX,mag);
                }
            });
        }
        
    }
    
    private double getX(double V,double ang){
        return(V*Math.sin(Math.toRadians(ang)));
    }
    
    private double getY(double V,double ang){
        return(V*Math.cos(Math.toRadians(ang)));
    }
    
    @FXML
    private void fs(){
        ControleUI.getInstance().getFourthStage().setFullScreen(!ControleUI.getInstance().getFourthStage().isFullScreen());
    }
    
    @FXML
    private void setFS(MouseEvent evt){//Metodo para reproduzir uma música selecionada na tabela a partir do Index
        try{
            if(contMenu.isShowing()){
                contMenu.hide();
            }
            if(evt.getClickCount()==2){
                ControleUI.getInstance().getFourthStage().setMaximized(!ControleUI.getInstance().getFourthStage().isMaximized());
           }
        }catch(Exception e){
            //
        }
     }

    @FXML
    private void openConfig(){
        ControleUI.getInstance().mostraSpectrumCfg();
    }
}
