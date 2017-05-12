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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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

    //vars do FXML
    @FXML
    Pane pane;
    @FXML
    AnchorPane anchor;

    //itens do menu de contexto
    //Fullscreen
    MenuItem fs = new MenuItem("FullScreen");
    //chamar configurações do spectro
    MenuItem callConfig = new MenuItem("Configurações");

    //Menu de contexto
    ContextMenu contMenu = new ContextMenu(callConfig, fs);

    //coloca me fullscreen
    @FXML
    private void fs() {
        ControleUI.getInstance().getFourthStage().setFullScreen(!ControleUI.getInstance().getFourthStage().isFullScreen());
    }

    //maximiza a tela no duplo clique
    //esconde o menu de contexto se detectar um clique fora dele
    @FXML
    private void setFS(MouseEvent evt) {
        try {
            if (contMenu.isShowing()) {
                contMenu.hide();
            }
            if (evt.getClickCount() == 2) {
                ControleUI.getInstance().getFourthStage().setMaximized(!ControleUI.getInstance().getFourthStage().isMaximized());
            }
        } catch (Exception e) {
            //
        }
    }

    //chama a tela de configuração e tira o spectro do fullscreen se tiver
    @FXML
    private void openConfig() {
        if (ControleUI.getInstance().getFourthStage().isFullScreen()) {
            ControleUI.getInstance().getFourthStage().setFullScreen(false);
        }
        ControleUI.getInstance().mostraSpectrumCfg();
    }

    //variáveis para o funcionamento(NÃO MEXER)
    private final int spaceX1 = 50, spaceX2 = 30, spaceY1 = 10, spaceY2 = 25, strokeW = 2;
    private final int maxf = 25600;
    private double origin[] = new double[2];
    private boolean needScale = true;
    private GraphicsContext gc;
    ResizableCanvas specT = new ResizableCanvas();

    //variáveis para a visualização
    //número de bandas
    private int bands = 64;
    //sensibilidade do spectro
    private int tresh = -80;
    //tipo de preenchemento das formas
    private int typeFill = 1;
    //numero de cores do gradiente
    private int numC = 3;
    //tipo de render do spectro
    private int typeDraw = 1;
    //intervalo entre os updates
    private double inter = 0.02;

    //variáveis para controlar as cores do spectrômetro
    private String backgroundC = "201D1D", labelC = "FFFFFF", blockC = "4169E1", blockC1 = "FF0000", blockC2 = "0000FF", blockC3 = "AACCFF";

    //variáveis para cotnrolar onde cada gradiente acaba
    double colorStop1 = 0.3, colorStop2 = 0.4, colorStop3 = 0.6;

    /**
     * Initializes the controller class.
     *
     * @param gc
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inicializa o Graphics Context
        gc = specT.getGraphicsContext2D();
        gc.setStroke(Color.web(labelC));
        gc.setLineWidth(strokeW);

        //configurações do menu de contexto
        fs.setOnAction(e -> fs());
        callConfig.setOnAction(e -> openConfig());
        contMenu.setAutoHide(true);
        specT.setOnContextMenuRequested(event -> contMenu.show(anchor, event.getScreenX(), event.getScreenY()));

        //coloca o canvas no pane e o configura
        pane.getChildren().add(specT);
        specT.widthProperty().bind(pane.widthProperty());
        specT.heightProperty().bind(pane.heightProperty());
        pane.widthProperty().addListener(event -> start(gc));
        pane.heightProperty().addListener(event -> start(gc));

        //pega os valores de configuração da spec config e os seta
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

    public void start(GraphicsContext gc) {
        //configura os dados do spectro e o desenha
        if (Mp3Buf.getInstance().checkmp() != null) {
            Mp3Buf.getInstance().getMp().setAudioSpectrumThreshold(tresh);
            Mp3Buf.getInstance().getMp().setAudioSpectrumNumBands(bands);
            Mp3Buf.getInstance().getMp().setAudioSpectrumInterval(inter);
            Mp3Buf.getInstance().getMp().setAudioSpectrumListener((double d, double d1, float[] mag, float[] phase) -> {

                //limpa o canvas
                gc.clearRect(0, 0, specT.getWidth(), specT.getHeight());

                //redesenha o fundo
                gc.setFill(Color.web(backgroundC));
                gc.fillRect(0, 0, specT.getWidth(), specT.getHeight());

                //desenha os labels se precisar
                if (needScale) {
                    staticElements(gc);
                }
                //cor do grafico
                fillMetod(typeFill);

                //desenha o grafico
                drawMetod(typeDraw, gc, mag);
            });
        }
    }

    //elementos estáticos da tela, como os labels...
    public void staticElements(GraphicsContext gc) {
        //cor dos labels
        gc.setStroke(Color.WHITE);
        //linha vertical
        gc.strokeLine(spaceX1, spaceY1, spaceX1, (specT.getHeight() - spaceY2));
        //linha horizontal
        gc.strokeLine(spaceX1, (specT.getHeight() - spaceY2), (specT.getWidth() - spaceX2), (specT.getHeight() - spaceY2));
        gc.setStroke(Color.web(labelC));
        //labels do y
        int interv = 5;
        float text = 0;
        int passo = (tresh) / interv, displaceY = spaceY1 + 5;
        gc.setLineWidth(1);
        for (int i = 0; i < (interv + 1); i++) {
            gc.strokeText("" + text, 11, displaceY + 5);
            gc.strokeLine(45, displaceY, 50, displaceY);
            displaceY += (specT.getHeight() - spaceY1 - spaceY2 - strokeW) / interv;
            text += passo;
        }
        //labels do x
        interv = (bands <= 10) ? bands : 10;
        text = 0;
        passo = maxf / interv;
        int displaceX = spaceX1;
        for (int i = 0; i < (interv + 1); i++) {
            if (text < 1000) {
                gc.strokeText("" + text, displaceX, (specT.getHeight() - spaceY2) + 15);
            } else {
                gc.strokeText((text / 1000) + "k", displaceX, (specT.getHeight() - spaceY2) + 15);
            }
            gc.strokeLine(displaceX, (specT.getHeight() - spaceY2), displaceX, (specT.getHeight() - spaceY2 + 5));
            displaceX += (specT.getWidth() - spaceX1 - spaceX2 - strokeW) / interv;
            text += passo;
        }
        gc.setLineWidth(strokeW);
    }

    //método para definir o preenchimento das formas
    private void fillMetod(int type) {
        if (type == 0) {
            //cor sólida
            gc.setFill(Color.web(blockC));
        } else {
            //2 cores para os gradientes
            if (numC == 2) {
                //gradiente linear
                if (typeDraw != 2) {
                    gc.setFill(new LinearGradient(0, 0, 0, 1, true,
                            CycleMethod.NO_CYCLE,
                            Arrays.asList(
                                    new Stop(colorStop1, Color.web(blockC1)),
                                    new Stop(colorStop2, Color.web(blockC2)))));
                } else {
                    //gradiente radial
                    gc.setFill(new RadialGradient(0, 0,
                            (pane.getWidth() / 2),
                            (pane.getHeight() / 2),
                            ((pane.getHeight() / 2)),
                            false,
                            CycleMethod.NO_CYCLE,
                            Arrays.asList(
                                    new Stop(colorStop1, Color.web(blockC1)),
                                    new Stop(colorStop2, Color.web(blockC2)))));
                }
                //3 cores para os gradientes
            } else {
                //gradiente linear
                if (typeDraw != 2) {
                    gc.setFill(new LinearGradient(0, 0, 0, 1, true,
                            CycleMethod.NO_CYCLE,
                            Arrays.asList(
                                    new Stop(colorStop1, Color.web(blockC1)),
                                    new Stop(colorStop2, Color.web(blockC2)),
                                    new Stop(colorStop3, Color.web(blockC3)))));
                } else {
                    //gradiente radial
                    gc.setFill(new RadialGradient(0, 0,
                            (pane.getWidth() / 2),
                            (pane.getHeight() / 2),
                            ((pane.getHeight() / 2)),
                            false,
                            CycleMethod.NO_CYCLE,
                            Arrays.asList(
                                    new Stop(colorStop1, Color.web(blockC1)),
                                    new Stop(colorStop2, Color.web(blockC2)),
                                    new Stop(colorStop3, Color.web(blockC3)))));
                }
            }
        }
    }

    //métodos de desenho do spectrômetro
    private void drawMetod(int typeDraw, GraphicsContext gc, float[] mag) {
        double displaceX = 0;
        switch (typeDraw) {
            //Gráfico de barras
            case 0:
                displaceX = spaceX1 + strokeW;
                needScale = true;
                for (int i = 0; i < bands; i++) {
                    gc.fillRect((displaceX), ((specT.getHeight() - strokeW) - prop((mag[i] - tresh))), ((specT.getWidth() - spaceX1 - strokeW - spaceX2) / bands), (prop((mag[i] - tresh)) - spaceY2));
                    displaceX += (specT.getWidth() - spaceX1 - strokeW - spaceX2) / bands;
                }
                break;
            case 1: {
                //Gráfico de barras suavizado utilizando poligono
                displaceX = spaceX1 + strokeW;
                needScale = true;
                double[] cordX = new double[((bands * 2) + 3)];
                double[] cordY = new double[((bands * 2) + 3)];
                cordX[0] = (displaceX);
                cordY[0] = (specT.getHeight() - spaceY2 - strokeW);
                for (int i = 0; i < bands; i++) {
                    cordX[i + 1] = displaceX;
                    cordY[i + 1] = (specT.getHeight() - spaceY2 - prop((mag[i] - tresh)) - strokeW);
                    displaceX += (specT.getWidth() - spaceX1 - strokeW - spaceX2) / bands;

                }
                cordX[bands + 1] = (displaceX + 1);
                cordY[bands + 1] = (specT.getHeight() - spaceY2 - strokeW);
                cordX[bands + 2] = (specT.getWidth() - spaceX2);
                cordY[bands + 2] = (specT.getHeight() - spaceY2 - strokeW);
                gc.fillPolygon(cordX, cordY, (bands + 3));
                break;
            }
            //Espectrômetro radial
            default: {
                needScale = false;
                fillMetod(typeFill);
                origin[0] = (pane.getWidth() / 2);
                origin[1] = ((pane.getHeight() / 2) - 5);
                double baseR = (pane.getHeight() * 0.20), uBaseR = (pane.getHeight() * 0.22), expanMax = 0;
                double[] cordX = new double[(bands + 1) * 2];
                Deque<Double> innerCordX = new ArrayDeque<>();
                double[] cordY = new double[(bands + 1) * 2];
                Deque<Double> innerCordY = new ArrayDeque<>();
                double passo = ((double) 360) / bands, dispAng = 0;
                for (int i = 0; i < (bands + 1); i++) {
                    innerCordX.push(origin[0] + getX(baseR, dispAng));
                    innerCordY.push(origin[1] + getY(baseR, dispAng));
                    if (i < bands) {
                        expanMax = (((mag[i] - tresh) / (-tresh)) * (origin[1] - uBaseR));
                        cordX[i] = (origin[0] + getX((expanMax + uBaseR), dispAng));
                        cordY[i] = (origin[1] + getY((expanMax + uBaseR), dispAng));
                    } else {
                        expanMax = (((mag[0] - tresh) / (-tresh)) * (origin[1] - uBaseR));
                        cordX[i] = (origin[0] + getX(expanMax + uBaseR, dispAng));
                        cordY[i] = (origin[1] + getY(expanMax + uBaseR, dispAng));
                    }
                    dispAng += passo;

                }
                int i = 1;
                while (!innerCordX.isEmpty()) {
                    cordX[bands + i] = innerCordX.pop();
                    cordY[bands + i] = innerCordY.pop();
                    i++;
                }
                gc.fillPolygon(cordX, cordY, ((bands + 1) * 2));
                break;
            }
        }
    }

    //a partir do angulo e magnitude da as cordenas X do ponto
    private double getX(double V, double ang) {
        return (V * Math.sin(Math.toRadians(ang)));
    }

    //a partir do angulo e magnitude da as cordenas Y do ponto
    private double getY(double V, double ang) {
        return (V * Math.cos(Math.toRadians(ang)));
    }

    //da a proporção da magnitude do spectro em relação ao espaço disponível 
    public double prop(double mag) {
        return ((mag / (-tresh)) * (specT.getHeight() - spaceY2 - spaceY1));
    }

    //Getters e Setters
    public GraphicsContext getGc() {
        return gc;
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
}
