/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.FXML;

import codeplayer.ControleUI;
import codeplayer.Mp3Buf;
import codeplayer.Mp3Play;
import codeplayer.Mp3Stop;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.Mp3File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class PlayerController implements Initializable {
    Thread mpplay;
    boolean Pausado=false;
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
     public void play(){
        if(Pausado==false){
            Mp3Play mppl=new Mp3Play();
            mpplay=new Thread(mppl);
            mpplay.start();
            mpplay.interrupt();
        }else{
            mpplay.resume();
            Pausado=false;
        }
        
        //Codigo para carregar as informações da musica
        try{
            Mp3File mp3file = new Mp3File(Mp3Buf.getInstance().getPathMusic());
            if (mp3file.hasId3v1Tag()) {
              ID3v1 id3v1Tag = mp3file.getId3v1Tag();
              Faixa.setText(id3v1Tag.getTrack());
              Artista.setText(id3v1Tag.getArtist());
              Titulo.setText(id3v1Tag.getTitle());
              Album.setText(id3v1Tag.getAlbum());
              Ano.setText(id3v1Tag.getYear());
}      
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //Fim do código para carregar informações da música
     }
     @FXML
     public void stop(){
         Mp3Stop mpstp=new Mp3Stop();
         Thread mpstopth=new Thread(mpstp);
        if(Pausado==true){
            Pausado=false;
            mpplay.resume();
            mpstopth.start();
            mpstopth.interrupt();
        }else{            
            mpstopth.start();
            mpstopth.interrupt();
        }
     }
     @FXML
     public void pause(){
         Pausado=true;
         mpplay.suspend();         
     }
     @FXML
     public void exibeEqualizer(){
         ControleUI.getInstance().mostraEqualizer();
     }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
