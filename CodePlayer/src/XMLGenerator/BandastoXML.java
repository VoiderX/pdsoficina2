/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLGenerator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class BandastoXML {

    /*
    Declaração de váriaveis
     */
    private final XStream xstream;
    private final String NomePerfil;

    /*
        Fim da declaração das váriaveis
     */
 /*
        Inicio dos metodos
     */
    //Metodo para inicializaçao da classe
    public BandastoXML(String NomePerfil) {
        this.NomePerfil = NomePerfil;
        xstream = new XStream(new DomDriver());
        xstream.alias(NomePerfil, BandaXML.class);
    }

    //Metodo para gerar texto XML
    public String geraXMLString(BandaXML bandas) {
        return (xstream.toXML(bandas));
    }

    //Metodo para gerar arquivo XML
    public void geraXMLfile(BandaXML bandas) {
        String textoxml = xstream.toXML(bandas);
        try {
            PrintWriter writer = new PrintWriter("User" + codeplayer.ExchangeInfos.getInstance().getUseratual()
                    + "/PerfEQ" + NomePerfil + ".xml", "UTF-8");
            writer.print(textoxml);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo para gerar um objeto XML a partir de um texto
    public BandaXML xmltoBanda(String xml) {
        return (BandaXML) xstream.fromXML(xml);
    }

    //Metodo para gerar um objeto xml a partir do arquivo
    public BandaXML xmltoBanda(File xml) {
        return (BandaXML) xstream.fromXML(xml);
    }

    //Metodo para procurar os arquivos de perfil de equalização
    public static ArrayList<String> procuraArquivosXML() {
        ArrayList<String> nomesarq = new ArrayList<>();
        File aux = new File("User" + codeplayer.ExchangeInfos.getInstance().getUseratual());
        File auxvet[] = aux.listFiles((File file) -> {
            return file.getName().contains("PerfEQ");
        });
        for (File auxvet1 : auxvet) {
            nomesarq.add(auxvet1.getName());
        }
        return nomesarq;
    }
    /*
        Fim dos metodos
     */
}
