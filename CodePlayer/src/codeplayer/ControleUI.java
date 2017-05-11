/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;

import codeplayer.FXML.PlayerController;
import codeplayer.visualizations.SpectrumController;
import java.io.IOException;
import javafx.application.Platform;
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
public final class ControleUI {

    private ControleUI() {
    }
    ; //Declaração de método construtor privado

   private static ControleUI INSTANCE = null; //Declaração de uma intanscia para a classe

    public static ControleUI getInstance() {//Verifica se já possui instancia ativa
        //Caso não possua inicia uma caso contrário chama a instancia já aberta
        return ((INSTANCE == null) ? INSTANCE = new ControleUI() : INSTANCE);
    }

    public void start(Stage primaryStage) throws Exception {//Chama a parimeira tela
        mainStage = primaryStage;
        initUI();
    }

    private static Parent playerFXML; //Classes parent para manipulação do Fxml
    private static Parent equalizerFXML;
    private static Parent osciloscopeFXML;
    private static Parent spectrumFXML;
    private static Parent infoFXML;
    private static Parent spectrumcfgFXML;
    private static Parent indexFXML;
    
    private static Scene playerScene; //Scenes (Interfaces já construídas)
    private static Scene equalizerScene;
    private static Scene osciloscopeScene;
    private static Scene spectrumScene;
    private static Scene infoScene;
    private static Scene spectrumcfgScene;
    private static Scene indexScene;

    private static Stage mainStage; //Stages: Janelas Abertas
    private static Stage secondStage;
    private static Stage thirdStage;
    private static Stage fourthStage;
    private static Stage fifthStage;
    private static Stage sixthStage;

    public PlayerController playControl; //Instanciação da classe controller do player para mudanças de interface
    //Em tempo real
    public SpectrumController spectrumControl;

    public SpectrumController getSpectrumControl() {
        return spectrumControl;
    }

    public void setSpectrumControl(SpectrumController spectrumControl) {
        this.spectrumControl = spectrumControl;
    }


    public void setPlayControl(PlayerController playControl) {
        this.playControl = playControl;
    }

    public PlayerController getPlayerController() { //Metodo get permitir que o controller seja modificado em
        //outras classes
        return playControl;
    }

    public Stage getSecondStage() { //Metodo para permitir a chama de uma segunda janela por outros stages
        return secondStage;
    }

    public Stage getThirdStage() {
        return thirdStage;
    }

    public Stage getFourthStage() {
        return fourthStage;
    }

    public Stage getFifthStage() {
        return fifthStage;
    }
    
    public Stage getSixthStage() {
        return sixthStage;
    }

    private void initUI() { //Metódo inicializador
        mainStage.centerOnScreen();
        mainStage.setTitle("CodePlayer 2017");
        secondStage = new Stage();
        thirdStage = new Stage();
        fourthStage = new Stage();
        fifthStage = new Stage();
        sixthStage = new Stage();
        mainStage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        } //Metodo para finalizar tudo ao apertar o "x"
        );
        try {
            FXMLLoader loader = new FXMLLoader();//Prepara um loader para o arquivo fxml
            loader.setLocation(getClass().getResource("FXML/Player.fxml"));//Puxa o arquivo fxml
            playerFXML = (Parent) loader.load(); //Carrega o arquivo FXML na classe pai
            playControl = loader.getController();//Puxa o o controller para o determinado FXMl

        } catch (IOException ex) {
            //fazer algo para mostrar erro
            System.out.println("Erro" + ex);
        }
        playerScene = new Scene(playerFXML); //Transforma a classe parent em um objeto do tipo Scene

        mostraIndex();
    }

    public void mostraPlayer() { //Método para chamar o login
        mainStage.setScene(playerScene); //Coloca a scene no stage
        mainStage.show();//Exibe o stage
    }

    public void mostraEqualizer() {
        try {
            equalizerFXML = FXMLLoader.load(getClass().getResource("FXML/Equalizer.fxml"));
            equalizerScene = new Scene(equalizerFXML);
        } catch (Exception e) {

        }
        secondStage.setScene(equalizerScene);
        secondStage.setTitle("Equalizador");
        secondStage.show();
    }

    public void mostraOsciloscope() {
        try {
            osciloscopeFXML = FXMLLoader.load(getClass().getResource("visualizations/Osciloscope.fxml"));
            osciloscopeScene = new Scene(osciloscopeFXML);
        } catch (Exception e) {

        }
        thirdStage.setScene(osciloscopeScene);
        thirdStage.setTitle(("Osciloscópio"));
        thirdStage.show();
    }

    public void mostraSpectrum() {
        try {
            FXMLLoader loader = new FXMLLoader();//Prepara um loader para o arquivo fxml
            loader.setLocation(getClass().getResource("visualizations/Spectrum.fxml"));//Puxa o arquivo fxml
            spectrumFXML = (Parent) loader.load(); //Carrega o arquivo FXML na classe pai
            spectrumControl = loader.getController();//Puxa o o controller para o determinado FXMl
            spectrumScene = new Scene(spectrumFXML);

        } catch (Exception e) {

        }
        fourthStage.setMinWidth(460);
        fourthStage.setMinHeight(320);
        fourthStage.setScene(spectrumScene);
        fourthStage.setTitle(("Visualizador de Espectro"));
        fourthStage.show();
    }

    public void mostraInfos() {
        try {
            infoFXML = FXMLLoader.load(getClass().getResource("misc/Infos.fxml"));
            infoScene = new Scene(infoFXML);
            fifthStage.setScene(infoScene);
            fifthStage.setTitle(("Informações sobre o programa"));
            fifthStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostraSpectrumCfg() {
        try {
            spectrumcfgFXML = FXMLLoader.load(getClass().getResource("misc/SpecCfg.fxml"));
            spectrumcfgScene = new Scene(spectrumcfgFXML);
            sixthStage.setScene(spectrumcfgScene);
            sixthStage.setTitle(("Configurações do Visualizador Espectral"));
            sixthStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void mostraIndex(){
        try {
            indexFXML = FXMLLoader.load(getClass().getResource("FXML/indexFXML.fxml"));
            indexScene = new Scene(indexFXML);
            fifthStage.setScene(indexScene);
            fifthStage.setTitle(("Index"));
            fifthStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
}
