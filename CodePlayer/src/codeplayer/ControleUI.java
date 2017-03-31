/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;

import codeplayer.FXML.PlayerController;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Gabriel
 */
//Classe para controle geral das interfaces em singleton
public final class ControleUI{

   private ControleUI(){}; //Declaração de método construtor privado

   private static ControleUI INSTANCE = null; //Declaração de uma intanscia para a classe

   public static ControleUI getInstance(){//Verifica se já possui instancia ativa
       //Caso não possua inicia uma caso contrário chama a instancia já aberta
      return((INSTANCE == null)?INSTANCE = new ControleUI():INSTANCE);
   }

   public void start(Stage primaryStage) throws Exception {//Chama a parimeira tela
      mainStage = primaryStage;
      initUI();
   }

   private static Parent playerFXML; //Classes parent para manipulação do Fxml
   private static Parent equalizerFXML;

   private static Scene playerScene; //Scenes (Interfaces já construídas)
   private static Scene equalizerScene;
   
   private static Stage mainStage; //Stages: Janelas Abertas
   private static Stage secondStage;
   
   public PlayerController playControl; //Instanciação da classe controller do player para mudanças de interface
   //Em tempo real
   
   public PlayerController getPlayerController(){ //Metodo get permitir que o controller seja modificado em
       //outras classes
       return playControl;
   }
   
   public Stage getSecondStage(){ //Metodo para permitir a chama de uma segunda janela por outros stages
       return secondStage;
   }


   private void initUI(){ //Metódo inicializador
      mainStage.centerOnScreen();
      mainStage.setTitle("CodePlayer 2017");
      secondStage= new Stage();
      
      mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {//Metodo para finalizar tudo ao apertar o "x"
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }

        });
      try {
          FXMLLoader loader=new FXMLLoader();//Prepara um loader para o arquivo fxml
          loader.setLocation(getClass().getResource("FXML/Player.fxml"));//Puxa o arquivo fxml
          playerFXML = (Parent)loader.load(); //Carrega o arquivo FXML na classe pai
          playControl=loader.getController();//Puxa o o controller para o determinado FXMl

      } catch (IOException ex) {
         //fazer algo para mostrar erro
         System.out.println("Erro" + ex);
      }
        playerScene = new Scene(playerFXML); //Transforma a classe parent em um objeto do tipo Scene
       
      mostraPlayer();
   }

   public void mostraPlayer(){ //Método para chamar o login
      mainStage.setScene(playerScene); //Coloca a scene no stage
      mainStage.show();//Exibe o stage
   }
   public void mostraEqualizer(){
       try{
        equalizerFXML=FXMLLoader.load(getClass().getResource("FXML/Equalizer.fxml"));
        equalizerScene=new Scene(equalizerFXML);
       }
       catch(Exception e){
           
       }
      secondStage.setScene(equalizerScene);
      secondStage.show();
   }
   
}