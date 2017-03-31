/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;

import static javafx.scene.media.MediaPlayer.Status.PLAYING;

/**
 *
 * @author Gabriel
 */
//Thread para aguardar a chegada dos metadados
public class CheckMetadata implements Runnable{
    public void run(){ //Override de metodo run 
        while(Mp3Buf.getInstance().getMp().getStatus()!=PLAYING){//Espera a musica começar a tocar
            try{
                this.wait(1000);//delay para aguardar
            }
            catch(Exception e){
                
            }
        }
        //Seta as informações
       ControleUI.getInstance().getPlayerController().setInfos(Mp3Buf.getInstance().getMedia().getMetadata());
    }
    
}
