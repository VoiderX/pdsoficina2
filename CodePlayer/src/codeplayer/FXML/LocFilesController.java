/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.FXML;

import codeplayer.ControleUI;
import codeplayer.Mp3Buf;
import codeplayer.Musica;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class LocFilesController implements Initializable {

    @FXML
    private TreeView arvore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            if (new File("Musicas").exists()) {
                System.out.println("batata");
            } else {
                System.out.println("batatelse");
                new File(("Musicas")).mkdir();
            }
            arvore.setEditable(true);
            arvore.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
            CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<>("Musicas");
            rootItem.setExpanded(true);
            File arq[] = new File("Musicas").listFiles();
            for (int i = 0; i < arq.length; i++) {
                final CheckBoxTreeItem<String> item = new CheckBoxTreeItem<>(arq[i].getName());
                rootItem.getChildren().add(item);
            }
            arvore.setRoot(rootItem);
        } catch (Exception e) {
            System.out.println("deu erro na tree");
        }

    }

    @FXML
    private void mostraPlayer() {
        ArrayList<String> musicas = new ArrayList<>();
        ObservableList<CheckBoxTreeItem<?>> checkedItems = FXCollections.observableArrayList();
        findCheckedItems((CheckBoxTreeItem<?>) arvore.getRoot(), checkedItems);
        for (int i = 0; i < checkedItems.size(); i++) {
            System.out.println(checkedItems.get(i).getValue());
            if (!checkedItems.get(i).getValue().equals("Musicas")) {
                musicas.add("Musicas/" + checkedItems.get(i).getValue());
            }
        }
        try {
            Musica aux;//Objeto Musica para auxiliar a inserção
            ArrayList<Musica> temp = new ArrayList<>();
            for (int i = 0; i < musicas.size(); i++) {//Enquanto houver File's carrega as músicas
                //Criando o ArrayList de músicas
                aux = new Musica(new File(musicas.get(i)), i);
                temp.add(aux);
            }
            Mp3Buf.getInstance().setMusicas(temp); //Enviando o Array para a Mp3Buf
            Mp3Buf.getInstance().setPosTocando(0);//Inicia na posição 0          
            ControleUI.getInstance().mostraPlayer();
            ControleUI.getInstance().getPlayerController().carregarTabela(); //Carrega a tabela de exibição de músicas
            ControleUI.getInstance().getPlayerController().prepararMusica();//Prepara a primeira música
        } catch (Exception e) { //Caso venha vazio(sem seleção) não faz nada
            System.out.println("Erro carregando a musica");
        }
    }

    private void findCheckedItems(CheckBoxTreeItem<?> item, ObservableList<CheckBoxTreeItem<?>> checkedItems) {
        if (item.isSelected()) {
            checkedItems.add(item);
        }
        for (TreeItem<?> child : item.getChildren()) {
            findCheckedItems((CheckBoxTreeItem<?>) child, checkedItems);
        }
    }

}
