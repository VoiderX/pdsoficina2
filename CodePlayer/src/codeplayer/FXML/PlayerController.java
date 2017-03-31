/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.FXML;

import codeplayer.CheckMetadata;
import codeplayer.CodePlayer;
import codeplayer.ControleUI;
import codeplayer.Mp3Buf;
import codeplayer.Musica;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    TableView<codeplayer.Musica> Tabelamusicas;
    @FXML
    TableColumn<codeplayer.Musica,String> ColunaNome;
    @FXML
    TableColumn<codeplayer.Musica,String> ColunaPath;
    public void prepararMusica(){
        if(Mp3Buf.getInstance().getMp()!=null && Mp3Buf.getInstance().getPathMusic()!=null){
            Mp3Buf.getInstance().getMp().dispose();
            Mp3Buf.getInstance().setMp(null);
            play();
        }
    }
    @FXML
     public void play(){
         if(Mp3Buf.getInstance().getMp()==null){
         }else{
            Thread tr=new Thread(new CheckMetadata());
            Mp3Buf.getInstance().getMp().play();
            tr.start();
         }
     }
     @FXML
     public void stop(){
        if(Mp3Buf.getInstance().getMp()!=null){
            Mp3Buf.getInstance().getMp().stop();
        }
     }
     @FXML
     public void pause(){
         if(Mp3Buf.getInstance().getMp()!=null){
             Mp3Buf.getInstance().getMp().pause();
         }
     }
     @FXML
     public void next(){
         Mp3Buf.getInstance().setPosTocando(Mp3Buf.getInstance().getPosTocando() + 1);
         prepararMusica();
         play();
     }
     public void last(){
         Mp3Buf.getInstance().setPosTocando(Mp3Buf.getInstance().getPosTocando() - 1);
         prepararMusica();
         play();
     }
     @FXML
     public void exibeEqualizer(){
         ControleUI.getInstance().mostraEqualizer();
     }
     @FXML
     public void loadMusicDialog(){
        FileChooser fc= new FileChooser();
        try{
        List musicas = fc.showOpenMultipleDialog(null);
        
        ArrayList<Musica> temp= new ArrayList<>();
            for (Iterator it = musicas.iterator(); it.hasNext();) {
                Object musica = it.next();
                Musica tempmusic;
                tempmusic = new Musica((File)musica);
                temp.add(tempmusic);
            }
        Mp3Buf.getInstance().setMusicas(temp);
        Mp3Buf.getInstance().setPosTocando(0);
        carregarTabela();
        prepararMusica();
        }
        catch(Exception e){
            e.printStackTrace();
        }
     }
     public void setInfos(ObservableMap<String,Object> metadata){
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
     }
     
     public void carregarTabela(){
         ArrayList<Musica> tempdados = Mp3Buf.getInstance().getMusicas();
         ObservableList<Musica> temp2dados= FXCollections.observableArrayList();
         for(int i=0;i<tempdados.size();i++){
             System.out.println("Teste:"+i+" "+tempdados.get(i).getPathMusic());
             temp2dados.add(tempdados.get(i));
         }
         
         ColunaNome.setCellValueFactory(new PropertyValueFactory<codeplayer.Musica,String>("Nome"));
         ColunaPath.setCellValueFactory(new PropertyValueFactory<codeplayer.Musica,String>("PathMusic"));
         Tabelamusicas.setItems(temp2dados);
     }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
