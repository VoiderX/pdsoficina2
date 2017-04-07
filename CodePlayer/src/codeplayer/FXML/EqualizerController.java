/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.FXML;

import codeplayer.Banda;
import codeplayer.Mp3Buf;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.EqualizerBand;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class EqualizerController implements Initializable {    
    @FXML
    ArrayList<Slider> Sliders=new ArrayList<>();
    @FXML
    ArrayList<Text> Freqs = new ArrayList<>();
    @FXML
    Slider Slider0; //Inicialização de Sliders
    @FXML
    Slider Slider1;
   @FXML
    Slider Slider2;
   @FXML
    Slider Slider3;
   @FXML
    Slider Slider4;
   @FXML
    Slider Slider5;
   @FXML
    Slider Slider6;
   @FXML
    Slider Slider7;
   @FXML
    Slider Slider8;
   @FXML
    Slider Slider9;
   @FXML
   Text freq0; //Texto para mostrar as faixas de frequencias
   @FXML
   Text freq1;
   @FXML
   Text freq2;
   @FXML
   Text freq3;
   @FXML
   Text freq4;
   @FXML
   Text freq5;
   @FXML
   Text freq6;
   @FXML
   Text freq7;
   @FXML
   Text freq8;
   @FXML
   Text freq9;
   @FXML
   public void equalizar(){ //Metodo para setar a equalização para o máximo
        for(int i=0;i<Mp3Buf.getInstance().getBandas().size();i++){
            Mp3Buf.getInstance().getBandas().get(i).setValor(12);
        }
        atualizaSlider(); //Metodo para atualizar a visualização dos sliders
        atualizaEqualizer();//Metodo para atualizar o equalizador do Media Player
   }
    @FXML
    public void equalizar2(){//Metodo para equalizar ao minimo
        for(int i=0;i<Mp3Buf.getInstance().getBandas().size();i++){
            Mp3Buf.getInstance().getBandas().get(i).setValor(-24);
        }
        atualizaSlider();
        atualizaEqualizer();
        
    }
    public void changeSlider(Event e){ //Metoodo para identificar caso o Slider seja alterado
        for(int i=0;i<Sliders.size();i++){
            if(e.getSource()==Sliders.get(i)){
                System.out.println("Slider "+i+": "+Sliders.get(i).getValue());
                //Seta o valor do slider no array
                Mp3Buf.getInstance().getBandas().get(i).setValor(Sliders.get(i).getValue());                
            }
        }
        atualizaEqualizer();//Atualiza o equalizador
    }
    public void atualizaSlider(){//Metodo simples para mudar a visualização do Slider
        for(int i=0;i<Sliders.size();i++){
            Sliders.get(i).setValue(Mp3Buf.getInstance().getBandas().get(i).getValor());
        }
    }
    public void atualizaEqualizer(){//Metodo para transferir os dados do array para o equalizador
         ObservableList<EqualizerBand> bandseq;
        if(Mp3Buf.getInstance().checkmp()!=null){
            //Obtendo as bandas do equalizador
             bandseq=Mp3Buf.getInstance().getMp().getAudioEqualizer().getBands();
            for(int i=0;i<Mp3Buf.getInstance().getBandas().size();i++){
                //Corre o ArrayList e seta os valores dos ganhos no equalizador diretamente
                bandseq.get(i).setGain(Mp3Buf.getInstance().getBandas().get(i).getValor());
            }
        }
    }
    @FXML
    public void zeroAll(){ //Metodo para retornar todas os ganhos nas faixas para zero
        for(int i=0;i<Mp3Buf.getInstance().getBandas().size();i++){
            Mp3Buf.getInstance().getBandas().get(i).setValor(0);
        }
        atualizaSlider();//Atualiza a visualização dos sliders
        atualizaEqualizer();//Atualiza o equalizador
    }     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){       
      //Adiciona os sliders nos Arrays
      Sliders.add(Slider0);
      Freqs.add(freq0);
      
      Sliders.add(Slider1);
      Freqs.add(freq1);
      
      Sliders.add(Slider2);
      Freqs.add(freq2);
      
      Sliders.add(Slider3);
      Freqs.add(freq3);
      
      Sliders.add(Slider4);
      Freqs.add(freq4);
      
      Sliders.add(Slider5);
      Freqs.add(freq5);
      
      Sliders.add(Slider6);
      Freqs.add(freq6);
      
      Sliders.add(Slider7);
      Freqs.add(freq7);
      
      Sliders.add(Slider8);
      Freqs.add(freq8);
      
      Sliders.add(Slider9);
      Freqs.add(freq9);
      if(Mp3Buf.getInstance().getBandas()==null){
        Mp3Buf.getInstance().setBandas(new ArrayList<Banda>());
        for(int i=0;i<Sliders.size();i++){//Inicia todos os valores nos sliders com os valores do 
            Banda aux=new Banda();
            aux.setValor(0);
            Mp3Buf.getInstance().getBandas().add(aux);
        }
      }else{
         for(int i=0;i<Sliders.size();i++){//Inicia todos os valores nos sliders com os valores do 
            Sliders.get(i).setValue(Mp3Buf.getInstance().getBandas().get(i).getValor());
        } 
      }
    }    
    
}
