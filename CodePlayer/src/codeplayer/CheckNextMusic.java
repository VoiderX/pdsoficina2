/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Gabriel
 */
public class CheckNextMusic implements Runnable{
    @Override    
    public void run(){
        ControleUI.getInstance().getPlayerController().next();
    }
}
