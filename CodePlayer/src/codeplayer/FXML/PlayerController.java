/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.FXML;

import codeplayer.ControleUI;
import codeplayer.Mp3Buf;
import codeplayer.Musica;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;


/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class PlayerController implements Initializable {

    @FXML
    Text Faixa;
    @FXML
    Text Artista;
    @FXML
    Text Titulo;
    @FXML
    Text Album;
    @FXML
    Text Ano;
    Image CapaAlbum;
    @FXML
    ImageView imagem;
    @FXML
    Slider SlidVol;
    @FXML
    Button Btnext;
    @FXML
    Button Btlast;
    @FXML
    TableView<codeplayer.Musica> Tabelamusicas;
    @FXML
    TableColumn<codeplayer.Musica,String> ColunaNome;
    @FXML
    TableColumn<codeplayer.Musica,String> ColunaPath;
    @FXML
    TableColumn<codeplayer.Musica,Integer> ColunaIndice;
    //Metodo para preparação da musica
    //Caso esteja tocando e uma nova musica seja carregada encerra o MediaPlayer atual
    public void prepararMusica(){
        try{
            if(Mp3Buf.getInstance().getMp()!=null && Mp3Buf.getInstance().getPathMusic()!=null){
                Mp3Buf.getInstance().getMp().dispose();
                Mp3Buf.getInstance().setMp(null);
                play();
                changeSliderVol();
            }
        }catch(Exception e){
            // NOTHING TO DO HERE
        }
    }
    //Metodo play, inicia a reprodução e a busca dos metadados
    @FXML
     public void play(){
        try{
            if(Mp3Buf.getInstance().getMp()==null){
            }else{
                //Recepção de metadadados atraves da Thread pois as informações chegam de forma Assincrona
            Mp3Buf.getInstance().getMp().play();
            if(ControleUI.getInstance().getFourthStage().isShowing()){
                ControleUI.getInstance().getSpectrumControl().start(
                ControleUI.getInstance().getSpectrumControl().getGc());
               }
           }
        }catch(Exception e){
        }
     }
     //Metodo para dar stop na musica
     @FXML
     public void stop(){
        try{    
             if(Mp3Buf.getInstance().getMp()!=null){
                Mp3Buf.getInstance().getMp().stop();
            }
        }catch(Exception e){
            // NOTHING TO DO HERE
        }
     }
     //Metodo para dar pause na musica
     @FXML
     public void pause(){
        try{
         if(Mp3Buf.getInstance().getMp()!=null){
             Mp3Buf.getInstance().getMp().pause();
         }
        }catch(Exception e){
            // NOTHING DO TO HERE
        }
     }
     //Metodo para chamar a proxima musica
     @FXML
     public void next(){
         Btnext.setDisable(true);
         //Seta a posição para a próxima musica
         Mp3Buf.getInstance().setPosTocando(Mp3Buf.getInstance().getPosTocando() + 1);
         //Musica deve ser carregada
         prepararMusica();
         //Inicia a música
         play();
         Btnext.setDisable(false);
     }
     public void last(){
         //Idem  ao metodo next, no entanto seta a música anterior
         Btlast.setDisable(true);
         Mp3Buf.getInstance().setPosTocando(Mp3Buf.getInstance().getPosTocando() - 1);
         prepararMusica();
         play();
         Btlast.setDisable(false);
     }
     //Metodo para chamar a classe de exibição do equalizador
     @FXML
     public void exibeEqualizer(){
         ControleUI.getInstance().mostraEqualizer();
     }
     //Chama o File Chooser para carregar as músicas
     @FXML
     public void loadMusicDialog(){
        FileChooser fc= new FileChooser();
        try{
            List musicas = fc.showOpenMultipleDialog(null); //Chama o FileChooser e armazena os FIles num list
            Musica aux;//Objeto Musica para auxiliar a inserção
            ArrayList<Musica> temp= new ArrayList<>();
            for(int i=0;i<musicas.size();i++){//Enquanto houver File's carrega as músicas
                //Criando o ArrayList de músicas
                aux=new Musica((File)musicas.get(i),i);
                temp.add(aux);
            }
            Mp3Buf.getInstance().setMusicas(temp); //Enviando o Array para a Mp3Buf
            Mp3Buf.getInstance().setPosTocando(0);//Inicia na posição 0
            carregarTabela(); //Carrega a tabela de exibição de músicas
            prepararMusica();//Prepara a primeira música
        }
        catch(Exception e){ //Caso venha vazio(sem seleção) não faz nada
            System.out.println("Sem musicas selecionadas!");
        }
     }
     //Metodo chamado dentro da CheckMetada para exibir os metadados
     public void setInfos(ObservableMap<String,Object> metadata){
          Artista.setText("Nenhum");
          Titulo.setText("Nenhum");
          Album.setText("Nenhum");
          Ano.setText("Nenhum");
          Faixa.setText("Nenhum");
          imagem.setImage(null);
          
         if(metadata.get("artist")!=null){
                Artista.setText(metadata.get("artist").toString());
            }
            if(metadata.get("title")!=null){
                Titulo.setText(metadata.get("title").toString());
            }
            if(metadata.get("album")!=null){
                Album.setText(metadata.get("album").toString());
            }
            if(metadata.get("year")!=null){
                Ano.setText(metadata.get("year").toString());
            }
            if(metadata.get("track")!=null){
                Faixa.setText(metadata.get("track").toString());
            }
            if(metadata.get("image")!=null){
                CapaAlbum=(Image) metadata.get("image");
                imagem.setImage(CapaAlbum);
            }
     }
     //Carrega a tabela a para exibição
     public void carregarTabela(){
         //Array temporário para armazenar os  dados
         ArrayList<Musica> tempdados = Mp3Buf.getInstance().getMusicas();
         //ObservableList para carregar os dados na tabela
         ObservableList<Musica> temp2dados= FXCollections.observableArrayList();
         //Transfere os dados do array para o ObservableList
         for(int i=0;i<tempdados.size();i++){
             temp2dados.add(tempdados.get(i));
         }
         //Binda as colunas da tabela nos componentes da Classe Musica
         ColunaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
         ColunaPath.setCellValueFactory(new PropertyValueFactory<>("PathMusic"));
         ColunaIndice.setCellValueFactory(new PropertyValueFactory<>("Index"));
         Tabelamusicas.setItems(temp2dados);//Seta o ObservableList na tabela
     }
     @FXML
     public void selecionaMusica(MouseEvent evt){//Metodo para reproduzir uma música selecionada na tabela a partir do Index
        int aux;
        try{
            if(evt.getClickCount()==2){
                aux= Tabelamusicas.getSelectionModel().getSelectedItem().getIndex();

               if(aux!=Mp3Buf.getInstance().getPosTocando()){ //Verifica se a música já está tocando
                   Mp3Buf.getInstance().setPosTocando(aux);//Seta a posição a ser tocada
                   prepararMusica();//Musica deve ser preparada 
                   play();//Inicia a música
               }else{
                   //Faz nada
               }
           }
        }catch(Exception e){
            //
        }
     }
     @FXML
     public void changeSliderVol(){ //Metodo para controle de volume
         if(Mp3Buf.getInstance().checkmp()!=null){ //Verifica se o media player está instanciado
            Double volume=SlidVol.getValue(); //Pega o valor do slider
            volume=volume/100;//Divide por 100, pos o volume vai de 0 a 1;
            Mp3Buf.getInstance().getMp().setVolume(volume);//Passa o volume para o media player
         }
     }
     @FXML
     public void carregaOsciloscope(){
         ControleUI.getInstance().mostraOsciloscope();
     }
     @FXML
     public void carregaSpectrum(){
         ControleUI.getInstance().mostraSpectrum();
     }
     @FXML
     public void mostraInfos(){
         ControleUI.getInstance().mostraInfos();
     }
     @FXML
     public void mostraSpectrumCfg(){
         ControleUI.getInstance().mostraSpectrumCfg();
     }     
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imagem.setSmooth(true);
        SlidVol.setValue(100);
    }
    @FXML
    public void testIndex(){
        ControleUI.getInstance().mostraIndex();
    }
    
}
