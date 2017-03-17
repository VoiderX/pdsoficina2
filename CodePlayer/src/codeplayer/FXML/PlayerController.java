/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.FXML;

import codeplayer.Mp3Play;
import codeplayer.Mp3Stop;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class PlayerController implements Initializable {
    Thread mpplay;
    boolean Pausado=false;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
