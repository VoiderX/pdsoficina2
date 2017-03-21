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
public class CheckMetadata implements Runnable{
    public void run(){
        while(Mp3Buf.getInstance().getMp().getStatus()!=PLAYING){
            try{
                this.wait(1000);
            }
            catch(Exception e){
                
            }
        }
       ControleUI.getInstance().getPlayerController().setInfos(Mp3Buf.getInstance().getMedia().getMetadata());
    }
    
}
