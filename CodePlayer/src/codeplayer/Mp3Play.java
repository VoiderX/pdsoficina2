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
public class Mp3Play implements Runnable{
    
    @Override
    public void run(){
        try{
            if(Mp3Buf.getInstance().isIsPlaying()==false){
                Mp3Buf.getInstance().getPlayer().play();
                Mp3Buf.getInstance().setIsPlaying(false);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
