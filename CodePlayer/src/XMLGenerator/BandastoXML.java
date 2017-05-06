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
/**
 *
 * @author Gabriel
 */
public class BandastoXML {
    private XStream xstream;
    private String NomePerfil;
    
    public BandastoXML(String NomePerfil){
        this.NomePerfil=NomePerfil;
        xstream= new XStream(new DomDriver());
        xstream.alias(NomePerfil, BandaXML.class);    
    }
    
    public  String geraXMLString(BandaXML bandas){
        return(xstream.toXML(bandas));        
    }
    public void geraXMLfile(BandaXML bandas){
        String textoxml=xstream.toXML(bandas);
        try{
            PrintWriter writer = new PrintWriter(NomePerfil+".xml", "UTF-8");
            writer.print(textoxml);
            writer.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    public  BandaXML xmltoBanda(String xml){
        return (BandaXML)xstream.fromXML(xml);
    }
    
    public BandaXML xmltoBanda(File xml){
        return (BandaXML)xstream.fromXML(xml);
    }    

}
