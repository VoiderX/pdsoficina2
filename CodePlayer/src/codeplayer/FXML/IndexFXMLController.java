/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.FXML;

import XMLGenerator.SpectrumXML;
import codeplayer.ControleUI;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author noda2
 */
public class IndexFXMLController implements Initializable {

    @FXML
    ChoiceBox<String> perfExistente;
    @FXML
    Button go;
    @FXML
    Button save;
    @FXML
    TextField newName;
    @FXML
    Pane newUser;
    @FXML
    Pane login;

    @FXML
    public void criaUsuario() {
        new File("User" + newName.getText()).mkdir();
        carregaPerfis();
        perfExistente.setValue(newName.getText());
        newName.clear();
        perfExistente.requestFocus();
    }

    @FXML
    public void go() {
        if(perfExistente.getValue()!=null && !perfExistente.getValue().equals("")){
            codeplayer.ExchangeInfos.getInstance().setUseratual(perfExistente.getValue());
            ControleUI.getInstance().mostraPlayer();
            ControleUI.getInstance().getFifthStage().hide();
        }else{
            System.err.println("Selecione um usuário");
        }
    }

    @FXML
    private void goEnter(KeyEvent key){
        if(key.getCode()==KeyCode.ENTER){
            go();
        }
    }
    
    @FXML
    private void criarUsuariaEnter(KeyEvent key){
        if(key.getCode()==KeyCode.ENTER){
            criaUsuario();
        }
    }
    
    public void carregaPerfis() {
        try{
        ArrayList<String> aux = SpectrumXML.procuraArquivosXML("User");
        ObservableList<String> oal = FXCollections.observableArrayList();
        for (int i = 0; i < aux.size(); i++) {
            StringBuilder str = new StringBuilder();
            str.insert(0, aux.get(i));
            str.replace(0, 4, "");
            oal.add(str.toString());
        }
        perfExistente.setItems(oal);
        if(!perfExistente.getItems().isEmpty()){
            perfExistente.setValue(perfExistente.getItems().get(0));
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaPerfis();
    }

}
