/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;

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
