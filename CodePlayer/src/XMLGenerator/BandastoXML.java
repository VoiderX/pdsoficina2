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

    private final XStream xstream;
    private final String NomePerfil;

    public BandastoXML(String NomePerfil) {
        this.NomePerfil = NomePerfil;
        xstream = new XStream(new DomDriver());
        xstream.alias(NomePerfil, BandaXML.class);
    }

    public String geraXMLString(BandaXML bandas) {
        return (xstream.toXML(bandas));
    }

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

    public BandaXML xmltoBanda(String xml) {
        return (BandaXML) xstream.fromXML(xml);
    }

    public BandaXML xmltoBanda(File xml) {
        return (BandaXML) xstream.fromXML(xml);
    }

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
}
