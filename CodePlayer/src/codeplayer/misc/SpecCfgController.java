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
    public void setBack(){
        System.out.println(BackgroundColor.getValue().toString());
            if(ControleUI.getInstance().getSpectrumControl()!=null){
            try{
                ControleUI.getInstance().getSpectrumControl().setBackgroundC(BackgroundColor.getValue().toString());
            }
            catch(Exception e){
               //vazio
            }
        }
    }
    @FXML
    public void setLabel(){
        if(ControleUI.getInstance().getSpectrumControl()!=null){
            System.out.println(LabelColor.getValue().toString());
             try{
              ControleUI.getInstance().getSpectrumControl().setLabelC(LabelColor.getValue().toString());
             }
             catch(Exception e){
                 //vazio
             }
        }
    }
    @FXML
    public void setBar(){
        if(ControleUI.getInstance().getSpectrumControl()!=null){
            System.out.println(BarColor.getValue().toString());
            try{
               ControleUI.getInstance().getSpectrumControl().setBlockC(BarColor.getValue().toString());  
            }
            catch(Exception e){
                //Vazio
            }
        }
    }
    @FXML
    public void setNumBands(){
        if(ControleUI.getInstance().getSpectrumControl()!=null){
            System.out.println((int)Numbands.getValue());
            ControleUI.getInstance().getSpectrumControl().setBands((int)Numbands.getValue());
            try{
            ControleUI.getInstance().getSpectrumControl().start(
            ControleUI.getInstance().getSpectrumControl().getGc());
            }
            catch(Exception e){
                //Faz nada
            }
        }
    }
    @FXML
    public void setIntervalo(){
        if(ControleUI.getInstance().getSpectrumControl()!=null){
            System.out.println(Intervalo.getValue());
            ControleUI.getInstance().getSpectrumControl().setInter(1/Intervalo.getValue());
            try{
                ControleUI.getInstance().getSpectrumControl().start(
                ControleUI.getInstance().getSpectrumControl().getGc());
            }
            catch(Exception e){
                //Faz nada
            }
        }
    }
    @FXML
    public void salvaPerfil(){
        if(!NomePerfil.getText().isEmpty()){
            new SpectrumXML(NomePerfil.getText()).geraXMLfile(new SpectrumCfg(BackgroundColor.getValue().toString(),
            LabelColor.getValue().toString(),BarColor.getValue().toString(),(int)Numbands.getValue(),
            Intervalo.getValue()));
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
          System.out.println(aux);
      }
      Perfil.setItems((ObservableList<String>)itemselect);
      //Detectando alterações de estado no ChoiceBox
     
      Perfil.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
          //Listener identifica mudanças na seleção
          @Override
          public void changed(ObservableValue<? extends String> ov, String t, String t1) {
              if(Perfil.getValue()!=null){
              if(Perfil.getValue().equals("Default")){
                    BackgroundColor.setValue(Color.web("201D1D"));
                    LabelColor.setValue(Color.web("FFFFFF"));
                    BarColor.setValue(Color.web("4169E1"));
                    Intervalo.setValue(1/0.02);
                    Numbands.setValue(64);
                    setNumBands();
                    setIntervalo();
                    setBack();
                    setLabel();
                    setBar();
                    codeplayer.ExchangeInfos.getInstance().setSpecCfg("Default");
              }else{
                SpectrumCfg aux=
                        new SpectrumXML(Perfil.getValue()).xmltoSpec(new File("PerfSpec"+Perfil.getValue()+".xml"));
                Numbands.setValue(aux.getNumBands());
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
                codeplayer.ExchangeInfos.getInstance().setSpecCfg(Perfil.getValue());
              }
              }
          }
      });
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Numbands.valueProperty().addListener(listener->setNumBands());
       Intervalo.valueProperty().addListener(listerner->setIntervalo());
       carregarPerfis();
       BackgroundColor.setValue(Color.web("201D1D"));
       LabelColor.setValue(Color.web("FFFFFF"));
       BarColor.setValue(Color.web("4169E1"));
       Intervalo.setValue(1/0.02);
       Numbands.setValue(64);
       Perfil.setValue(codeplayer.ExchangeInfos.getInstance().getSpecCfg());
    }    
    
}
