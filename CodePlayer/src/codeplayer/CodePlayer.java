/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Gabriel
 */
public class CodePlayer extends Application {
    public static void main(String[] args) {
      CodePlayer.launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws Exception {
      ControleUI.getInstance().start(primaryStage); //Chama a inicialização da classe de controle
   }
}
