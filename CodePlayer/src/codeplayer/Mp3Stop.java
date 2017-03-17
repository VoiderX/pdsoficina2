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
public class Mp3Stop implements Runnable{
    public void run(){
        try{
            if(Mp3Buf.getInstance().isIsPlaying()==true){
                Mp3Buf.getInstance().player.close();
                Mp3Buf.getInstance().setIsPlaying(false);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
