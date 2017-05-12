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
public class SpectrumXML {

    /*
        Variaveis 
     */
    private final XStream xstream;
    private final String NomePerfil;

    /*
        Inicio dos metodos
     */
    //Metodo para criar setar o XStream Parser dentro do construtor
    public SpectrumXML(String NomePerfil) {
        this.NomePerfil = NomePerfil;
        xstream = new XStream(new DomDriver());
        xstream.alias(NomePerfil, SpectrumCfg.class);
    }

    //Metodo para gerar o Texto XML
    public String geraXMLString(SpectrumCfg cfg) {
        return (xstream.toXML(cfg));
    }

    //Metodo para gerar o arquivo XML
    public void geraXMLfile(SpectrumCfg cfg) {
        String textoxml = xstream.toXML(cfg);
        try {
            PrintWriter writer = new PrintWriter(
                    "User" + codeplayer.ExchangeInfos.getInstance().getUseratual() + "/PerfSpec" + NomePerfil + ".xml", "UTF-8");
            writer.print(textoxml);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo para retornar um String XML em objeto
    public SpectrumCfg xmltoSpec(String xml) {
        return (SpectrumCfg) xstream.fromXML(xml);
    }

    //Metodo para tornar um arquivo em objeto
    public SpectrumCfg xmltoSpec(File xml) {
        return (SpectrumCfg) xstream.fromXML(xml);
    }

    //Metodo para encontrar os arquivos xml de Perfil
    public static ArrayList<String> procuraArquivosXML() {
        ArrayList<String> nomesarq = new ArrayList<>();
        //Busca os arquivos no diretório do usuário atual
        File aux = new File("User" + codeplayer.ExchangeInfos.getInstance().getUseratual());
        File auxvet[] = aux.listFiles((File file) -> {
            return file.getName().contains("PerfSpec"); //Procura pelos arquivos com o padrão de nome
        });
        for (File auxvet1 : auxvet) {
            nomesarq.add(auxvet1.getName());
        }
        return nomesarq;
    }

    //Metodo para encontrar um tipo de arquivo com nome genérico
    public static ArrayList<String> procuraArquivosXML(String Tipo) {
        ArrayList<String> nomesarq = new ArrayList<>();
        File aux = new File(".");
        File auxvet[] = aux.listFiles((File file) -> {
            return file.getName().contains(Tipo);
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
