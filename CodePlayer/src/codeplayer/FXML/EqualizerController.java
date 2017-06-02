/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer.FXML;

import XMLGenerator.BandaXML;
import XMLGenerator.BandastoXML;
import codeplayer.Banda;
import codeplayer.Mp3Buf;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.media.EqualizerBand;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class EqualizerController implements Initializable {

    /*
        Inicio das variaveis do FXML
     */
    @FXML
    private ArrayList<Slider> Sliders = new ArrayList<>();
    @FXML
    private ArrayList<Text> Freqs = new ArrayList<>();
    @FXML
    private Slider Slider0; //Inicialização de Sliders
    @FXML
    private Slider Slider1;
    @FXML
    private Slider Slider2;
    @FXML
    private Slider Slider3;
    @FXML
    private Slider Slider4;
    @FXML
    private Slider Slider5;
    @FXML
    private Slider Slider6;
    @FXML
    private Slider Slider7;
    @FXML
    private Slider Slider8;
    @FXML
    private Slider Slider9;
    @FXML
    private Text freq0; //Texto para mostrar as faixas de frequencias
    @FXML
    private Text freq1;
    @FXML
    private Text freq2;
    @FXML
    private Text freq3;
    @FXML
    private Text freq4;
    @FXML
    private Text freq5;
    @FXML
    private Text freq6;
    @FXML
    private Text freq7;
    @FXML
    private Text freq8;
    @FXML
    private Text freq9;
    @FXML
    private ChoiceBox<String> Seletor;
    @FXML
    private TextField NomePerfil;
    @FXML
    private Text HelperNomePerfil;
    @FXML
    private Button BotaoOk;
    /*
        Fim das variaveis do FXML
     */

 /*
        Inicio das variaveis normais
     */
    private double freqn = 50;

    /*
        Fim das Variaveis normais
     */
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Adiciona os sliders nos Arrays
        Sliders.add(Slider0);
        Freqs.add(freq0);

        Sliders.add(Slider1);
        Freqs.add(freq1);

        Sliders.add(Slider2);
        Freqs.add(freq2);

        Sliders.add(Slider3);
        Freqs.add(freq3);

        Sliders.add(Slider4);
        Freqs.add(freq4);

        Sliders.add(Slider5);
        Freqs.add(freq5);

        Sliders.add(Slider6);
        Freqs.add(freq6);

        Sliders.add(Slider7);
        Freqs.add(freq7);

        Sliders.add(Slider8);
        Freqs.add(freq8);

        Sliders.add(Slider9);
        Freqs.add(freq9);
        if (Mp3Buf.getInstance().getBandas() == null) {
            Mp3Buf.getInstance().setBandas(new ArrayList<>());
            for (int i = 0; i < Sliders.size(); i++) {//Inicia todos os valores nos sliders com os valores do array de bandas
                Banda aux = new Banda();
                aux.setValor(0);
                Mp3Buf.getInstance().getBandas().add(aux);
                Freqs.get(i).setText(Double.toString(freqn));
                freqn *= 2;
            }
        } else {
            for (int i = 0; i < Sliders.size(); i++) {//Inicia todos os valores nos sliders com os valores do 
                Sliders.get(i).setValue(Mp3Buf.getInstance().getBandas().get(i).getValor());
            }
        }
        //Preaparação do choicebox para seleção de perfis
        carregaPerfis();
        //Preparação do TextField
        NomePerfil.setDisable(true);
        NomePerfil.setVisible(false);
        HelperNomePerfil.setVisible(false);
        BotaoOk.setDisable(true);
        BotaoOk.setVisible(false);
        Seletor.setValue(codeplayer.ExchangeInfos.getInstance().getPerfilEq());
    }

    /*
        Inicio dos metodos FXML
     */
    @FXML
    private void salvarPerfil() {
        HelperNomePerfil.setText("Digite um nome para o perfil:");
        NomePerfil.setVisible(true);
        NomePerfil.setDisable(false);
        HelperNomePerfil.setVisible(true);
        BotaoOk.setVisible(true);
        BotaoOk.setDisable(false);
    }

    @FXML
    private void confirmaSalvarPerfil() {
        BandaXML temp;

        if (NomePerfil.getText().isEmpty()) {
            HelperNomePerfil.setText("O nome não pode ser vazio!");
        } else {
            //Deve-se verificar se a strig começa com um número
            StringBuilder builderaux = new StringBuilder();
            builderaux.insert(0, NomePerfil.getText());
            String aux = builderaux.substring(0, 1);
            boolean auxverif;
            try {
                Integer.parseInt(aux);
                auxverif = true;
            } catch (Exception e) {
                auxverif = false;
            }
            if (auxverif) {
                HelperNomePerfil.setText("O nome não pode começar com número");
            } else {
                temp = new BandaXML(Slider0.getValue(),
                        Slider1.getValue(),
                        Slider2.getValue(),
                        Slider3.getValue(),
                        Slider4.getValue(),
                        Slider5.getValue(),
                        Slider6.getValue(),
                        Slider7.getValue(),
                        Slider8.getValue(),
                        Slider9.getValue());
                BandastoXML btx = new BandastoXML(NomePerfil.getText());
                try {
                    btx.geraXMLfile(temp);
                    carregaPerfis();
                    Seletor.setValue(NomePerfil.getText());
                    HelperNomePerfil.setText("Perfil salvo com sucesso!");
                    NomePerfil.setDisable(true);
                    NomePerfil.clear();
                    NomePerfil.setVisible(false);
                    BotaoOk.setDisable(true);
                    BotaoOk.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void excluirPerfil() {
        try {
            new File("User" + codeplayer.ExchangeInfos.getInstance().getUseratual() + "/PerfEQ" + Seletor.getValue() + ".xml").delete();
            carregaPerfis();
            Seletor.setValue("Zerar");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void equalizar() { //Metodo para setar a equalização para o máximo
        for (int i = 0; i < Mp3Buf.getInstance().getBandas().size(); i++) {
            Mp3Buf.getInstance().getBandas().get(i).setValor(12);
        }
        atualizaSlider(); //Metodo para atualizar a visualização dos sliders
        atualizaEqualizer();//Metodo para atualizar o equalizador do Media Player
    }

    @FXML
    private void equalizar2() {//Metodo para equalizar ao minimo
        for (int i = 0; i < Mp3Buf.getInstance().getBandas().size(); i++) {
            Mp3Buf.getInstance().getBandas().get(i).setValor(-24);
        }
        atualizaSlider();
        atualizaEqualizer();

    }

    @FXML
    private void zeroAll() { //Metodo para retornar todas os ganhos nas faixas para zero
        for (int i = 0; i < Mp3Buf.getInstance().getBandas().size(); i++) {
            Mp3Buf.getInstance().getBandas().get(i).setValor(0);
        }
        atualizaSlider();//Atualiza a visualização dos sliders
        atualizaEqualizer();//Atualiza o equalizador
    }

    /*
        Fim dos metodos FXML
     */
 /*
        Inicio dos metodos normais
     */
    public void changeSlider(Event e) { //Metodo para identificar caso o Slider seja alterado
        for (int i = 0; i < Sliders.size(); i++) {
            if (e.getSource() == Sliders.get(i)) {
                //Seta o valor do slider no array
                Mp3Buf.getInstance().getBandas().get(i).setValor(Sliders.get(i).getValue());
                codeplayer.ExchangeInfos.getInstance().setPerfilEq("Novo Perfil");
                Seletor.setValue("Novo Perfil");
            }
        }
        atualizaEqualizer();//Atualiza o equalizador
    }

    private void atualizaSlider() {//Metodo simples para mudar a visualização do Slider
        for (int i = 0; i < Sliders.size(); i++) {
            Sliders.get(i).setValue(Mp3Buf.getInstance().getBandas().get(i).getValor());
        }
    }

    private void atualizaEqualizer() {//Metodo para transferir os dados do array para o equalizador
        ObservableList<EqualizerBand> bandseq;
        if (Mp3Buf.getInstance().checkmp() != null) {
            //Obtendo as bandas do equalizador
            bandseq = Mp3Buf.getInstance().getMp().getAudioEqualizer().getBands();
            for (int i = 0; i < Mp3Buf.getInstance().getBandas().size(); i++) {
                //Corre o ArrayList e seta os valores dos ganhos no equalizador diretamente
                bandseq.get(i).setGain(Mp3Buf.getInstance().getBandas().get(i).getValor());
            }
        }
    }

    private void carregaPerfis() { //Metodo para preparar o choicebox para seleção de perfis
        ObservableList<String> itemselect = FXCollections.observableArrayList();
        itemselect.add("Zerar"); //Valores hardcoded
        itemselect.add("Maximizar");
        itemselect.add("Minimizar");
        itemselect.add("Novo Perfil");
        ArrayList<String> nomesperfis = BandastoXML.procuraArquivosXML();

        for (int i = 0; i < nomesperfis.size(); i++) { //Limpa o nome do arquivo e deixa só o nome do pefil
            StringBuilder aux = new StringBuilder();
            aux.insert(0, nomesperfis.get(i));
            aux.replace(0, 6, "");
            aux.reverse();
            aux.replace(0, 4, "");
            aux.reverse();
            itemselect.add(aux.toString());
        }
        Seletor.setItems((ObservableList<String>) itemselect);

        //Detectando alterações de estado no ChoiceBox
        Seletor.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
            codeplayer.ExchangeInfos.getInstance().setPerfilEq(Seletor.getValue());
            if (Seletor.getValue() == null) {
                //Faz nada
            } else if (Seletor.getValue().equals("Maximizar")) { //Estados Hardcoded
                equalizar();
                codeplayer.ExchangeInfos.getInstance().setPerfilEq("Maximizar");
            } else if (Seletor.getValue().equals("Minimizar")) {
                equalizar2();
                codeplayer.ExchangeInfos.getInstance().setPerfilEq("Minimizar");
            } else if (Seletor.getValue().equals("Zerar")) {
                zeroAll();
                codeplayer.ExchangeInfos.getInstance().setPerfilEq("Zerar");
            } else if (!codeplayer.ExchangeInfos.getInstance().getPerfilEq().equals("Novo Perfil")
                    && !Seletor.getValue().equals("Novo Perfil")) {  //Obtendo valores do XML e passando para o Array de bandas na classe da Mp3
                File arq = new File("User" + "" + codeplayer.ExchangeInfos.getInstance().getUseratual() + "/PerfEQ" + Seletor.getValue() + ".xml");
                BandaXML bxml = new BandastoXML(Seletor.getValue()).xmltoBanda(arq);
                Mp3Buf.getInstance().getBandas().get(0).setValor(bxml.getGanho0());
                Mp3Buf.getInstance().getBandas().get(1).setValor(bxml.getGanho1());
                Mp3Buf.getInstance().getBandas().get(2).setValor(bxml.getGanho2());
                Mp3Buf.getInstance().getBandas().get(3).setValor(bxml.getGanho3());
                Mp3Buf.getInstance().getBandas().get(4).setValor(bxml.getGanho4());
                Mp3Buf.getInstance().getBandas().get(5).setValor(bxml.getGanho5());
                Mp3Buf.getInstance().getBandas().get(6).setValor(bxml.getGanho6());
                Mp3Buf.getInstance().getBandas().get(7).setValor(bxml.getGanho7());
                Mp3Buf.getInstance().getBandas().get(8).setValor(bxml.getGanho8());
                Mp3Buf.getInstance().getBandas().get(9).setValor(bxml.getGanho9());
                atualizaSlider(); //Metodo para atualizar a visualização dos sliders
                atualizaEqualizer();//Metodo para atualizar o equalizador do Media Player
                codeplayer.ExchangeInfos.getInstance().setPerfilEq(Seletor.getValue());
            } else {
                //
            }
        } //Listener identifica mudanças na seleção
        );
    }

    /*
        Fim dos metodos normais
     */
}
