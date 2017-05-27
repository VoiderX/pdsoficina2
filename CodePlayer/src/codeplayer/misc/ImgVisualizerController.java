/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.misc;

import codeplayer.ControleUI;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class ImgVisualizerController implements Initializable {

    /*
    Variaveis FXML
     */
    @FXML
    private ImageView Imagem;

    /*
    Fim das variaveis FXML
     */
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Imagem.setImage(codeplayer.ControleUI.getInstance().getPlayerController().getImageAlbum());
        ControleUI.getInstance().getSeventhStage().setResizable(false);
    }
    @FXML
    private void fechaStage(){
        ControleUI.getInstance().getSeventhStage().hide();
    }
    /*
        Metodos normais
     */
    public void setImage() {
        Imagem.setImage(codeplayer.ControleUI.getInstance().getPlayerController().getImageAlbum());

        ControleUI.getInstance().getSeventhStage().setHeight(Imagem.getImage().getHeight() + 50);

        ControleUI.getInstance().getSeventhStage().setWidth(Imagem.getImage().getWidth());
        Imagem.setPreserveRatio(true);
    }
    /*
        Fim dos metodos normais
     */
}
