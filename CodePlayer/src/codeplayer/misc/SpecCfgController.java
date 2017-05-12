/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.misc;

import XMLGenerator.SpectrumCfg;
import XMLGenerator.SpectrumXML;
import codeplayer.ControleUI;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
    private ChoiceBox<String> Perfil;
    @FXML
    private TextField NomePerfil;
    @FXML
    private ChoiceBox<String> TipoCor;
    @FXML
    private ChoiceBox<String> TipoDesenho;
    @FXML
    private ChoiceBox<Integer> NumeroGradiente;
    @FXML
    private ColorPicker CorGrad2;
    @FXML
    private ColorPicker CorGrad3;
    @FXML
    private Pane CorGrad;
    @FXML
    private BorderPane root;
    @FXML
    private Slider colorStop1;
    @FXML
    private Slider colorStop2;
    @FXML
    private Slider colorStop3;
    @FXML
    private HBox topHBOX;
    @FXML
    private VBox leftVBOX;
    @FXML
    private VBox centerVBox;
    @FXML
    private VBox rightVBox;
    @FXML
    private HBox posCorGrad;
    @FXML
    private Region spacerCenter1;
    @FXML
    private Region spacerCenter2;
    @FXML
    private Region spacerCenter3;
    @FXML
    private Region spacerNorth1;
    @FXML
    private Region spacerNorth2;
    @FXML
    private Region spacerNorth3;
    @FXML
    private Region spacerNorth4;
    @FXML
    private Region spacerColor1;
    @FXML
    private Region spacerColor2;
    @FXML
    private Region spacerLeft1;
    @FXML
    private Region spacerLeft2;
    @FXML
    private Region spacerLeft3;
    @FXML
    private Region spacerLeft4;
    @FXML
    private Region spacerLeft5;
    @FXML
    private Region spacerCenterH1;
    @FXML
    private Region spacerCenterH2;
    @FXML
    private Region spacerCenterH3;
    @FXML
    private Region spacerCenterH4;
    @FXML
    private Region spacerRight1;
    @FXML
    private Region spacerRight2;
    @FXML
    private Region spacerRight3;
    @FXML
    private Region spacerRight4;
    
    private int IndexFill = 1;

    private int TipoDesIndex = 1;

    SpectrumCfg aux;
    boolean ativarPerfNSalvo;

    public void setPerfNSalvo(boolean ativarPerfNSalvo) {
        if (this.ativarPerfNSalvo) {
            Perfil.setValue("Novo Perfil");
            codeplayer.ExchangeInfos.getInstance().setSpecCfg("Novo Perfil");
        }
    }

    @FXML
    public void setTipoCor() {
        int aux = 1;
        if (TipoCor.getValue() == null) {
            //
        } else if (TipoCor.getValue().equals("Solid")) {
            CorGrad.setDisable(true);
            NumeroGradiente.setDisable(true);
            aux = 0;

        } else if (TipoCor.getValue().equals("Gradiente")) {
            CorGrad.setDisable(false);
            NumeroGradiente.setDisable(false);
            aux = 1;
        }
        if (ControleUI.getInstance().getSpectrumControl() != null) {
            try {
                ControleUI.getInstance().getSpectrumControl().setTypeFill(aux);
                ControleUI.getInstance().getSpectrumControl().start(
                        ControleUI.getInstance().getSpectrumControl().getGc());

            } catch (Exception e) {
                //vazio
            }
        }
        IndexFill = aux;
        codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setPreenchimento(IndexFill);
        setPerfNSalvo(ativarPerfNSalvo);

    }

    @FXML
    public void setNumGrad() {
        int aux = 3;
        if (NumeroGradiente.getValue() == null) {
            //
        } else if (NumeroGradiente.getValue() == 2) {
            aux = 2;
            CorGrad3.setDisable(true);
        } else if (NumeroGradiente.getValue() == 3) {
            aux = 3;
            CorGrad3.setDisable(false);
        }
        if (ControleUI.getInstance().getSpectrumControl() != null) {
            try {
                ControleUI.getInstance().getSpectrumControl().setNumC(aux);
                ControleUI.getInstance().getSpectrumControl().start(
                        ControleUI.getInstance().getSpectrumControl().getGc());

            } catch (Exception e) {

            }
        }
        codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setNumC(aux);
        setPerfNSalvo(ativarPerfNSalvo);
    }

    @FXML
    public void setTipoDes() {
        int aux = 1;
        if (TipoDesenho.getValue() == null) {
            //
        } else if (TipoDesenho.getValue().equals("Barras")) {
            aux = 0;
        } else if (TipoDesenho.getValue().equals("Suave")) {
            aux = 1;
        } else if (TipoDesenho.getValue().equals("Radial")) {
            aux = 2;
        }
        if (ControleUI.getInstance().getSpectrumControl() != null) {
            try {
                ControleUI.getInstance().getSpectrumControl().setTypeDraw(aux);
                ControleUI.getInstance().getSpectrumControl().start(
                        ControleUI.getInstance().getSpectrumControl().getGc());
            } catch (Exception e) {
                //vazio
            }
        }
        TipoDesIndex = aux;
        codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setTipoDesenho(TipoDesIndex);
        setPerfNSalvo(ativarPerfNSalvo);
    }

    @FXML
    public void setGrad2() {
        if (ControleUI.getInstance().getSpectrumControl() != null) {
            try {
                ControleUI.getInstance().getSpectrumControl().setBlockC2(CorGrad2.getValue().toString());
                ControleUI.getInstance().getSpectrumControl().start(
                        ControleUI.getInstance().getSpectrumControl().getGc());
            } catch (Exception e) {
                //vazio
            }
        }
        codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setSegundacorgrad(
                CorGrad2.getValue().toString());
        setPerfNSalvo(ativarPerfNSalvo);
    }

    @FXML
    public void setGrad3() {
        if (ControleUI.getInstance().getSpectrumControl() != null) {
            try {
                ControleUI.getInstance().getSpectrumControl().setBlockC3(CorGrad3.getValue().toString());
                ControleUI.getInstance().getSpectrumControl().start(
                        ControleUI.getInstance().getSpectrumControl().getGc());
            } catch (Exception e) {
                //vazio
            }
        }
        codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setTerceiracorgrad(
                CorGrad3.getValue().toString());
        setPerfNSalvo(ativarPerfNSalvo);
    }

    @FXML
    public void setBack() {
        if (ControleUI.getInstance().getSpectrumControl() != null) {
            try {
                ControleUI.getInstance().getSpectrumControl().setBackgroundC(BackgroundColor.getValue().toString());
                ControleUI.getInstance().getSpectrumControl().start(
                        ControleUI.getInstance().getSpectrumControl().getGc());
            } catch (Exception e) {
                //vazio
            }
        }
        codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setBackgroundColor(BackgroundColor
                .getValue().toString());
        setPerfNSalvo(ativarPerfNSalvo);
    }

    @FXML
    public void setLabel() {
        if (ControleUI.getInstance().getSpectrumControl() != null) {
            try {
                ControleUI.getInstance().getSpectrumControl().setLabelC(LabelColor.getValue().toString());
                ControleUI.getInstance().getSpectrumControl().start(
                        ControleUI.getInstance().getSpectrumControl().getGc());
            } catch (Exception e) {
                //vazio
            }
        }
        codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setLabelColor(
                LabelColor.getValue().toString());
        setPerfNSalvo(ativarPerfNSalvo);
    }

    @FXML
    public void setBar() {
        if (ControleUI.getInstance().getSpectrumControl() != null) {
            try {
                ControleUI.getInstance().getSpectrumControl().setBlockC(BarColor.getValue().toString());
                ControleUI.getInstance().getSpectrumControl().setBlockC1(BarColor.getValue().toString());
            } catch (Exception e) {
                //Vazio
            }
        }
        codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setBarColor(BarColor.getValue().toString());
        setPerfNSalvo(ativarPerfNSalvo);
    }

    @FXML
    public void setNumBands() {
        if (ControleUI.getInstance().getSpectrumControl() != null) {
            ControleUI.getInstance().getSpectrumControl().setBands((int) Numbands.getValue());
            try {
                ControleUI.getInstance().getSpectrumControl().start(
                        ControleUI.getInstance().getSpectrumControl().getGc());

            } catch (Exception e) {
                //Faz nada
            }
        }
        codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setNumBands(
                (int) Numbands.getValue());
        setPerfNSalvo(ativarPerfNSalvo);
    }

    @FXML
    public void setIntervalo() {
        if (ControleUI.getInstance().getSpectrumControl() != null) {
            ControleUI.getInstance().getSpectrumControl().setInter(1 / Intervalo.getValue());
            try {
                ControleUI.getInstance().getSpectrumControl().start(
                        ControleUI.getInstance().getSpectrumControl().getGc());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setIntervalo(1 / Intervalo.getValue());
        setPerfNSalvo(ativarPerfNSalvo);
    }

    @FXML
    public void salvaPerfil() {
        try {
            if (!NomePerfil.getText().isEmpty() && !NomePerfil.getText().equals("Novo Perfil")) {
                aux = new SpectrumCfg();
                aux.setBackgroundColor(BackgroundColor.getValue().toString());
                aux.setBarColor(BarColor.getValue().toString());
                aux.setIntervalo(Intervalo.getValue());
                aux.setLabelColor(LabelColor.getValue().toString());
                aux.setNumBands((int) Numbands.getValue());
                aux.setNumC(NumeroGradiente.getValue());
                if (TipoCor.getValue().equals("Solid")) {
                    aux.setPreenchimento(0);
                } else if (TipoCor.getValue().equals("Gradiente")) {
                    aux.setPreenchimento(1);
                }
                aux.setSegundacorgrad(CorGrad2.getValue().toString());
                aux.setTerceiracorgrad(CorGrad3.getValue().toString());
                aux.setTipoDesenho(TipoDesIndex);
                SpectrumXML xmltemp = new SpectrumXML(NomePerfil.getText());
                xmltemp.geraXMLfile(aux);
                carregarPerfis();
                Perfil.setValue(NomePerfil.getText());
            } else {
                System.out.println("Nome inválido");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void excluiPerfil() {
        new File("User" + codeplayer.ExchangeInfos.getInstance().getUseratual() + "/PerfSpec"
                + Perfil.getValue() + ".xml").delete();
        carregarPerfis();
        setDefault();
    }

    public void perfLoadCfg(boolean TemPerfil) {
        if (TemPerfil) {
            ativarPerfNSalvo = false;
            codeplayer.ExchangeInfos.getInstance().setSpecCfg(Perfil.getValue());
            SpectrumCfg cfg = new SpectrumXML(Perfil.getValue()).xmltoSpec(new File(
                    "User" + codeplayer.ExchangeInfos.getInstance().getUseratual() + "/PerfSpec" + Perfil.getValue() + ".xml"));
            Numbands.setValue(cfg.getNumBands());
            BackgroundColor.setValue(Color.web(cfg.getBackgroundColor()));
            BarColor.setValue(Color.web(cfg.getBarColor()));
            CorGrad2.setValue(Color.web(cfg.getSegundacorgrad()));
            CorGrad3.setValue(Color.web(cfg.getTerceiracorgrad()));
            if (cfg.getPreenchimento() == 0) {
                TipoCor.setValue("Solid");
            } else if (cfg.getPreenchimento() == 1) {
                TipoCor.setValue("Gradiente");
            }
            if (cfg.getTipoDesenho() == 0) {
                TipoDesenho.setValue("Barras");
            } else if (cfg.getTipoDesenho() == 1) {
                TipoDesenho.setValue("Suave");
            } else if (cfg.getTipoDesenho() == 2) {
                TipoDesenho.setValue("Radial");
            }
            NumeroGradiente.setValue(cfg.getNumC());
            LabelColor.setValue(Color.web(cfg.getLabelColor()));
            Intervalo.setValue(cfg.getIntervalo());
        } else {
            ativarPerfNSalvo = true;
            BackgroundColor.setValue(Color.web(codeplayer.ExchangeInfos.getInstance().
                    getSpecCfgObj().getBackgroundColor()));
            BarColor.setValue(Color.web(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getBarColor()));
            CorGrad2.setValue(Color.web(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getSegundacorgrad()));
            CorGrad3.setValue(Color.web(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getTerceiracorgrad()));
            Intervalo.setValue(1 / codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getIntervalo());
            LabelColor.setValue(Color.web(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getLabelColor()));
            Numbands.setValue(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getNumBands());
            NumeroGradiente.setValue(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getNumC());
            if (codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getTipoDesenho() == 0) {
                this.TipoDesenho.setValue("Barras");
            } else if (codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getTipoDesenho() == 1) {
                this.TipoDesenho.setValue("Suave");
            } else if (codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getTipoDesenho() == 2) {
                this.TipoDesenho.setValue("Radial");
            }
            if (codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getPreenchimento() == 0) {
                this.TipoCor.setValue("Solid");
                CorGrad.setDisable(true);
            } else if (codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getPreenchimento() == 1) {
                this.TipoCor.setValue("Gradiente");
                CorGrad.setDisable(false);
                if (codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getNumC() == 2) {
                    CorGrad3.setDisable(true);
                } else if (codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getNumC() == 3) {
                    CorGrad3.setDisable(false);
                }
            }
        }
    }

    public void carregarPerfis() {
        ObservableList<String> itemselect = FXCollections.observableArrayList();
        itemselect.add("Default");
        itemselect.add("Novo Perfil");
        ArrayList<String> nomesperfis = SpectrumXML.procuraArquivosXML();

        for (int i = 0; i < nomesperfis.size(); i++) { //Limpa o nome do arquivo e deixa só o nome do pefil
            StringBuilder aux = new StringBuilder();
            aux.insert(0, nomesperfis.get(i));
            aux.replace(0, 8, "");
            aux.reverse();
            aux.replace(0, 4, "");
            aux.reverse();
            itemselect.add(aux.toString());
        }
        Perfil.setItems((ObservableList<String>) itemselect);
        //Detectando alterações de estado no ChoiceBox

        Perfil.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            //Listener identifica mudanças na seleção
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (Perfil.getValue() != null) {
                    if (Perfil.getValue().equals("Default")) {
                        ativarPerfNSalvo = false;
                        setDefault();
                    } else if (Perfil.getValue().equals("Novo Perfil")) {
                        perfLoadCfg(false);
                    } else {
                        perfLoadCfg(true);
                    }
                    codeplayer.ExchangeInfos.getInstance().setSpecCfg(Perfil.getValue());
                    NomePerfil.setText(Perfil.getValue());
                }
                ativarPerfNSalvo = true;
            }
        });
    }

    public void setDefault() {
        ativarPerfNSalvo = false;
        BackgroundColor.setValue(Color.web("201D1D"));
        LabelColor.setValue(Color.web("FFFFFF"));
        BarColor.setValue(Color.web("4169E1"));
        Intervalo.setValue(1 / 0.02);
        Numbands.setValue(64);
        this.TipoCor.setValue("Gradiente");
        this.TipoDesenho.setValue("Suave");
        NumeroGradiente.setValue(3);
        CorGrad2.setValue(Color.web("0000FF"));
        CorGrad3.setValue(Color.web("AACCFF"));
        colorStop1.setValue(0.3);
        colorStop2.setValue(0.5);
        colorStop3.setValue(0.6);
        codeplayer.ExchangeInfos.getInstance().setSpecCfg("Default");
        Perfil.setValue("Default");
        ativarPerfNSalvo = true;
    }
    
    @FXML
    public void setStop1() {
        if (ControleUI.getInstance().getSpectrumControl() != null) {
            ControleUI.getInstance().getSpectrumControl().setColorStop1(colorStop1.getValue());
            try {
                ControleUI.getInstance().getSpectrumControl().start(
                        ControleUI.getInstance().getSpectrumControl().getGc());

            } catch (Exception e) {
                //Faz nada
            }
        }
        codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setStopColor1(colorStop1.getValue());
        setPerfNSalvo(ativarPerfNSalvo);
    }
    
    @FXML
    public void setStop2() {
        if (ControleUI.getInstance().getSpectrumControl() != null) {
            ControleUI.getInstance().getSpectrumControl().setColorStop2(colorStop2.getValue());
            try {
                ControleUI.getInstance().getSpectrumControl().start(
                        ControleUI.getInstance().getSpectrumControl().getGc());

            } catch (Exception e) {
                //Faz nada
            }
        }
        codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setStopColor2(colorStop2.getValue());
        setPerfNSalvo(ativarPerfNSalvo);
    }
    
    @FXML
    public void setStop3() {
        if (ControleUI.getInstance().getSpectrumControl() != null) {
            ControleUI.getInstance().getSpectrumControl().setColorStop3(colorStop3.getValue());
            try {
                ControleUI.getInstance().getSpectrumControl().start(
                        ControleUI.getInstance().getSpectrumControl().getGc());

            } catch (Exception e) {
                //Faz nada
            }
        }
        codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setStopColor3(colorStop3.getValue());
        setPerfNSalvo(ativarPerfNSalvo);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Configurações de interface
        root.prefWidthProperty().bind(ControleUI.getInstance().getSixthStage().widthProperty());
        root.prefHeightProperty().bind(ControleUI.getInstance().getSixthStage().heightProperty());

        topHBOX.setAlignment(Pos.CENTER);

        leftVBOX.setAlignment(Pos.CENTER);
        centerVBox.setAlignment(Pos.CENTER);
        rightVBox.setAlignment(Pos.CENTER);
        
        VBox.setVgrow(spacerCenter1, Priority.ALWAYS);
        VBox.setVgrow(spacerCenter2, Priority.ALWAYS);
        VBox.setVgrow(spacerCenter3, Priority.ALWAYS);
        
        HBox.setHgrow(spacerNorth1, Priority.ALWAYS);
        HBox.setHgrow(spacerNorth2, Priority.ALWAYS);
        HBox.setHgrow(spacerNorth3, Priority.ALWAYS);
        HBox.setHgrow(spacerNorth4, Priority.ALWAYS);
        
        HBox.setHgrow(spacerColor1, Priority.ALWAYS);
        HBox.setHgrow(spacerColor2, Priority.ALWAYS);
        
        VBox.setVgrow(spacerLeft1, Priority.ALWAYS);
        VBox.setVgrow(spacerLeft2, Priority.ALWAYS);
        VBox.setVgrow(spacerLeft3, Priority.ALWAYS);
        VBox.setVgrow(spacerLeft4, Priority.ALWAYS);
        VBox.setVgrow(spacerLeft5, Priority.ALWAYS);
        
        HBox.setHgrow(spacerCenterH1, Priority.ALWAYS);
        HBox.setHgrow(spacerCenterH2, Priority.ALWAYS);
        HBox.setHgrow(spacerCenterH3, Priority.ALWAYS);
        HBox.setHgrow(spacerCenterH4, Priority.ALWAYS);
        
        VBox.setVgrow(spacerRight1, Priority.ALWAYS);
        VBox.setVgrow(spacerRight2, Priority.ALWAYS);
        VBox.setVgrow(spacerRight3, Priority.ALWAYS);
        VBox.setVgrow(spacerRight4, Priority.ALWAYS);
        
        posCorGrad.setAlignment(Pos.CENTER);
        //Fim das configurações de interface
        
        codeplayer.ExchangeInfos.getInstance();
        
        //Configurando numero de bandas e intervalos  na inerface
        Numbands.valueProperty().addListener(listener -> setNumBands());
        Intervalo.valueProperty().addListener(listerner -> setIntervalo());
        //Carregando os perfis de usuario disponíveis
        carregarPerfis();
        
        //Iniciando o choice box com os tipos de preenchimento
        ObservableList<String> TipoCor = FXCollections.observableArrayList();
        TipoCor.add("Solid");
        TipoCor.add("Gradiente");
        this.TipoCor.setItems(TipoCor);
        //Iniciando o ChoiceBox com os numeros de gradientes
        ObservableList<Integer> NumGrad = FXCollections.observableArrayList();
        NumGrad.add((Integer) 2);
        NumGrad.add((Integer) 3);
        NumeroGradiente.setItems(NumGrad);
        NumeroGradiente.setValue((Integer) 3);
        //Iniciando o choicebox com os tipos de desenhos
        ObservableList<String> TipoDesenho = FXCollections.observableArrayList();
        TipoDesenho.add("Barras");
        TipoDesenho.add("Suave");
        TipoDesenho.add("Radial");
        this.TipoDesenho.setItems(TipoDesenho);
        //Pegando o perfil atual
        Perfil.setValue(codeplayer.ExchangeInfos.getInstance().getSpecCfg());
        //Adicionando listeners para os elementos de interface
        this.colorStop1.valueProperty().addListener(listener->setStop1());
        this.colorStop2.valueProperty().addListener(listener->setStop2());
        this.colorStop3.valueProperty().addListener(listener->setStop3());
        this.BackgroundColor.valueProperty().addListener(listener -> setBack());
        this.LabelColor.valueProperty().addListener(listener -> setLabel());
        this.BarColor.valueProperty().addListener(listener -> setBar());
        this.TipoCor.valueProperty().addListener(listener -> setTipoCor());
        this.TipoDesenho.valueProperty().addListener(lister -> setTipoDes());
        this.NumeroGradiente.valueProperty().addListener(listener -> setNumGrad());
        this.CorGrad2.valueProperty().addListener(listener -> setGrad2());
        this.CorGrad3.valueProperty().addListener(listener -> setGrad3());
        //Verificações ao carregar a interface
        if ("Default".equals(codeplayer.ExchangeInfos.getInstance().getSpecCfg())) {
            ativarPerfNSalvo = false;
            setDefault();
            ativarPerfNSalvo = true;
        } else if (codeplayer.ExchangeInfos.getInstance().getSpecCfgObj() == null
                && !codeplayer.ExchangeInfos.getInstance().getSpecCfg().equals("Default")) {
            codeplayer.ExchangeInfos.getInstance().setSpecCfgObj(new SpectrumCfg());
        } else if ("Novo Perfil".equals(codeplayer.ExchangeInfos.getInstance().getSpecCfg())) {
            ativarPerfNSalvo = false;
            perfLoadCfg(false);
            ativarPerfNSalvo = true;
        } else {
            ativarPerfNSalvo = false;
            perfLoadCfg(true);
            ativarPerfNSalvo = true;
        }

    }
}
