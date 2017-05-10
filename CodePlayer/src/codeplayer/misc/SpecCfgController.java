/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.misc;

import XMLGenerator.BandaXML;
import XMLGenerator.BandastoXML;
import XMLGenerator.SpectrumCfg;
import XMLGenerator.SpectrumXML;
import codeplayer.ControleUI;
import codeplayer.Mp3Buf;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
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
    private int IndexFill;
    @FXML
    private int TipoDesIndex;
    SpectrumCfg aux;
    @FXML
    public void setTipoCor(){
        int aux=1;
        if(TipoCor.getValue()==null){
            //
        }
        else if(TipoCor.getValue().equals("Solid")){
            CorGrad.setDisable(true);
            NumeroGradiente.setDisable(true);
            aux=0;

        }
        else if(TipoCor.getValue().equals("Gradiente")){
            CorGrad.setDisable(false);
            NumeroGradiente.setDisable(false);
            aux=1;
        }
        if(ControleUI.getInstance().getSpectrumControl()!=null){
            try{
                ControleUI.getInstance().getSpectrumControl().setTypeFill(aux);
                ControleUI.getInstance().getSpectrumControl().start(
                    ControleUI.getInstance().getSpectrumControl().getGc());
                IndexFill=aux;
                codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setPreenchimento(IndexFill);
                codeplayer.ExchangeInfos.getInstance().setSpecCfg("Perfil nao salvo");
            }
            catch(Exception e){
               //vazio
            }
        }
    }
    @FXML
    public void setNumGrad(){
        int aux=3;
        if(NumeroGradiente.getValue()==null){    
            //
        }
        else if(NumeroGradiente.getValue()==2){
            aux=2;
            CorGrad3.setDisable(true);
        }
        else if(NumeroGradiente.getValue()==3){
            aux=3;
            CorGrad3.setDisable(false);
        }
        if(ControleUI.getInstance().getSpectrumControl()!=null){
            try{
                ControleUI.getInstance().getSpectrumControl().setNumC(aux);
                ControleUI.getInstance().getSpectrumControl().start(
                    ControleUI.getInstance().getSpectrumControl().getGc());
                codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setNumC(aux);
                codeplayer.ExchangeInfos.getInstance().setSpecCfg("Perfil nao salvo");                
            }
            catch(Exception e){
                
            }
        }
    }
    @FXML
    public void setTipoDes(){
        int aux=0;
        if(TipoDesenho.getValue()==null){
            //
        }
        else if(TipoDesenho.getValue().equals("Barras")){
            aux=0;
        }
        else if(TipoDesenho.getValue().equals("Suave")){
            aux=1;
        }else if(TipoDesenho.getValue().equals("Radial")){
            aux=2;
        }
        if(ControleUI.getInstance().getSpectrumControl()!=null){
            try{
              ControleUI.getInstance().getSpectrumControl().setTypeDraw(aux);
               ControleUI.getInstance().getSpectrumControl().start(
                ControleUI.getInstance().getSpectrumControl().getGc());
              TipoDesIndex=aux;
              codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setTipoDesenho(TipoDesIndex);
              codeplayer.ExchangeInfos.getInstance().setSpecCfg("Perfil nao salvo");
            }
            catch(Exception e){
               //vazio
            }
        }
    }
    @FXML
    public void setGrad2(){
         if(ControleUI.getInstance().getSpectrumControl()!=null){
            try{
                ControleUI.getInstance().getSpectrumControl().setBlockC2(CorGrad2.getValue().toString());
                 ControleUI.getInstance().getSpectrumControl().start(
                 ControleUI.getInstance().getSpectrumControl().getGc());
                 codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setSegundacorgrad(
                 CorGrad2.getValue().toString());
                 codeplayer.ExchangeInfos.getInstance().setSpecCfg("Perfil nao salvo");
            }
            catch(Exception e){
               //vazio
            }
        }
    }
    @FXML
    public void setGrad3(){
        if(ControleUI.getInstance().getSpectrumControl()!=null){
            try{
                ControleUI.getInstance().getSpectrumControl().setBlockC3(CorGrad3.getValue().toString());
                 ControleUI.getInstance().getSpectrumControl().start(
                 ControleUI.getInstance().getSpectrumControl().getGc());
                 codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setTerceiracorgrad(
                 CorGrad3.getValue().toString());
                 codeplayer.ExchangeInfos.getInstance().setSpecCfg("Perfil nao salvo");
            }
            catch(Exception e){
               //vazio
            }
        }        
    }
    @FXML
    public void setBack(){
            if(ControleUI.getInstance().getSpectrumControl()!=null){
            try{
                ControleUI.getInstance().getSpectrumControl().setBackgroundC(BackgroundColor.getValue().toString());
                 ControleUI.getInstance().getSpectrumControl().start(
                 ControleUI.getInstance().getSpectrumControl().getGc());
                 codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setBackgroundColor(BackgroundColor
                 .getValue().toString());
                 codeplayer.ExchangeInfos.getInstance().setSpecCfg("Perfil nao salvo");
            }
            catch(Exception e){
               //vazio
            }
        }
    }
    @FXML
    public void setLabel(){
        if(ControleUI.getInstance().getSpectrumControl()!=null){
             try{
              ControleUI.getInstance().getSpectrumControl().setLabelC(LabelColor.getValue().toString());
              ControleUI.getInstance().getSpectrumControl().start(
                ControleUI.getInstance().getSpectrumControl().getGc());
             codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setLabelColor(
                     LabelColor.getValue().toString());
             codeplayer.ExchangeInfos.getInstance().setSpecCfg("Perfil nao salvo");
             }
             catch(Exception e){
                 //vazio
             }
        }
    }
    @FXML
    public void setBar(){
        if(ControleUI.getInstance().getSpectrumControl()!=null){
            try{
               ControleUI.getInstance().getSpectrumControl().setBlockC(BarColor.getValue().toString()); 
               ControleUI.getInstance().getSpectrumControl().setBlockC1(BarColor.getValue().toString());
               codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setBarColor(BarColor.getValue().toString());
               codeplayer.ExchangeInfos.getInstance().setSpecCfg("Perfil nao salvo");
            }
            catch(Exception e){
                //Vazio
            }
        }
    }
    @FXML
    public void setNumBands(){
        if(ControleUI.getInstance().getSpectrumControl()!=null){
            ControleUI.getInstance().getSpectrumControl().setBands((int)Numbands.getValue());
            try{
            ControleUI.getInstance().getSpectrumControl().start(
            ControleUI.getInstance().getSpectrumControl().getGc());
            codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setNumBands(
            (int)Numbands.getValue());
            codeplayer.ExchangeInfos.getInstance().setSpecCfg("Perfil nao salvo");
                
            }
            catch(Exception e){
                //Faz nada
            }
        }
    }
    @FXML
    public void setIntervalo(){
        if(ControleUI.getInstance().getSpectrumControl()!=null){
            ControleUI.getInstance().getSpectrumControl().setInter(1/Intervalo.getValue());
            try{
                ControleUI.getInstance().getSpectrumControl().start(
                ControleUI.getInstance().getSpectrumControl().getGc());
                codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().setIntervalo(1/Intervalo.getValue());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void salvaPerfil(){
        if(!NomePerfil.getText().isEmpty()){
            aux=new SpectrumCfg();
            aux.setBackgroundColor(BackgroundColor.getValue().toString());
            aux.setBarColor(BarColor.getValue().toString());
            aux.setIntervalo(Intervalo.getValue());
            aux.setLabelColor(LabelColor.getValue().toString());
            aux.setNumBands((int)Numbands.getValue());
            aux.setNumC(NumeroGradiente.getValue());
            aux.setPreenchimento(IndexFill);
            aux.setSegundacorgrad(CorGrad2.getValue().toString());
            aux.setTerceiracorgrad(CorGrad3.getValue().toString());
            aux.setTipoDesenho(TipoDesIndex);
            carregarPerfis();
            Perfil.setValue(NomePerfil.getText());
        }else{
            System.out.println("Campo Vazio");
        }
    }
    @FXML
    public void excluiPerfil(){
        
    }
    public void carregarPerfis(){
      ObservableList<String> itemselect=FXCollections.observableArrayList();
      itemselect.add("Default");
      ArrayList<String> nomesperfis=SpectrumXML.procuraArquivosXML();

      for(int i=0;i<nomesperfis.size();i++){ //Limpa o nome do arquivo e deixa só o nome do pefil
          StringBuilder aux=new StringBuilder();
          aux.insert(0, nomesperfis.get(i));
          aux.replace(0, 8, "");
          aux.reverse();
          aux.replace(0, 4, "");
          aux.reverse();
          itemselect.add(aux.toString());
      }
      Perfil.setItems((ObservableList<String>)itemselect);
      //Detectando alterações de estado no ChoiceBox
     
      Perfil.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
          //Listener identifica mudanças na seleção
          @Override
          public void changed(ObservableValue<? extends String> ov, String t, String t1) {
              if(Perfil.getValue()!=null){
              if(Perfil.getValue().equals("Default")){
                    setDefault();
                   codeplayer.ExchangeInfos.getInstance().setSpecCfg("Default");
              }else{
            //    SpectrumCfg aux=
                        //new SpectrumXML(Perfil.getValue()).xmltoSpec(new File("PerfSpec"+Perfil.getValue()+".xml"));
              /*  Numbands.setValue(aux.getNumBands());
                setNumBands();
                Intervalo.setValue(aux.getIntervalo());
                setIntervalo();
                BackgroundColor.setValue(Color.web(aux.getBackgroundColor()));
                setBack();
                LabelColor.setValue(Color.web(aux.getLabelColor()));
                setLabel();
                BarColor.setValue(Color.web(aux.getBarColor()));
                setBar();
                NomePerfil.setText(Perfil.getValue());
                codeplayer.ExchangeInfos.getInstance().setSpecCfg(Perfil.getValue());*/
              }
              }
          }
      });
    }
    
    public void setDefault(){
        BackgroundColor.setValue(Color.web("201D1D"));
        LabelColor.setValue(Color.web("FFFFFF"));
        BarColor.setValue(Color.web("4169E1"));
        Intervalo.setValue(1/0.02);
        Numbands.setValue(64);
        this.TipoCor.setValue("Gradiente");
        this.TipoDesenho.setValue("Suave");
        NumeroGradiente.setValue(3);
        CorGrad2.setValue(Color.web("0000FF"));
        CorGrad3.setValue(Color.web("AACCFF"));
        setNumBands();
        setIntervalo();
        setBack();
        setLabel();
        setBar();
        codeplayer.ExchangeInfos.getInstance().setSpecCfg("Default");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        codeplayer.ExchangeInfos.getInstance();
       Numbands.valueProperty().addListener(listener->setNumBands());
       Intervalo.valueProperty().addListener(listerner->setIntervalo());
       carregarPerfis();
       
       
       ObservableList<String> TipoCor= FXCollections.observableArrayList();
       TipoCor.add("Solid");
       TipoCor.add("Gradiente");
       this.TipoCor.setItems(TipoCor);
      
       
       ObservableList NumGrad=FXCollections.observableArrayList();
       NumGrad.add(2);
       NumGrad.add(3);
       NumeroGradiente.setItems(NumGrad);
       NumeroGradiente.setValue(3);
       
       ObservableList TipoDesenho=FXCollections.observableArrayList();
       TipoDesenho.add("Barras");
       TipoDesenho.add("Suave");
       TipoDesenho.add("Radial");
       this.TipoDesenho.setItems(TipoDesenho);

       
       Perfil.setValue(codeplayer.ExchangeInfos.getInstance().getSpecCfg());
       
       this.TipoCor.valueProperty().addListener(listener->setTipoCor());
       this.TipoDesenho.valueProperty().addListener(lister->setTipoDes());
       this.NumeroGradiente.valueProperty().addListener(listener->setNumGrad());
       this.CorGrad2.valueProperty().addListener(listener->setGrad2());
       this.CorGrad3.valueProperty().addListener(listener->setGrad3());
       
       if("Default".equals(codeplayer.ExchangeInfos.getInstance().getSpecCfg())){
           setDefault();
       }
        else if(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj()==null){
            codeplayer.ExchangeInfos.getInstance().setSpecCfgObj(new SpectrumCfg());
            System.err.println("vai3");
       }
       else if("Perfil nao salvo".equals(codeplayer.ExchangeInfos.getInstance().getSpecCfg())){
            BackgroundColor.setValue(Color.web(codeplayer.ExchangeInfos.getInstance().
                    getSpecCfgObj().getBackgroundColor()));
            BarColor.setValue(Color.web(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getBarColor()));
            CorGrad2.setValue(Color.web(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getSegundacorgrad()));
            CorGrad3.setValue(Color.web(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getTerceiracorgrad()));
            Intervalo.setValue(1/codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getIntervalo());
            LabelColor.setValue(Color.web(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getLabelColor()));
            Numbands.setValue(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getNumBands());
            NumeroGradiente.setValue(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getNumC());
            if(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getTipoDesenho()==0){
                this.TipoDesenho.setValue("Barras");
            }
            else if(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getTipoDesenho()==1){
                this.TipoDesenho.setValue("Suave");
            }
            else if(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getTipoDesenho()==2){
                this.TipoDesenho.setValue("Radial");
            }
            if(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getPreenchimento()==0){
                this.TipoCor.setValue("Solid");
            }
            else if(codeplayer.ExchangeInfos.getInstance().getSpecCfgObj().getPreenchimento()==1){
                this.TipoCor.setValue("Gradiente");
            }
            
            
       }
       else{
           
       }
       
    }    
    
}
