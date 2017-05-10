/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLGenerator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileFilter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class SpectrumXML {
    private XStream xstream;
    private String NomePerfil;
    
    public SpectrumXML(String NomePerfil){
        this.NomePerfil=NomePerfil;
        xstream= new XStream(new DomDriver());
        xstream.alias(NomePerfil, SpectrumCfg.class);
    }
    
    public  String geraXMLString(SpectrumCfg cfg){
        return(xstream.toXML(cfg));        
    }
    public void geraXMLfile(SpectrumCfg cfg){
        String textoxml=xstream.toXML(cfg);
        try{
            PrintWriter writer = new PrintWriter("PerfSpec"+NomePerfil+".xml", "UTF-8");
            writer.print(textoxml);
            writer.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    public  SpectrumCfg xmltoSpec(String xml){
        return (SpectrumCfg)xstream.fromXML(xml);
    }
    
    public SpectrumCfg xmltoSpec(File xml){
        return (SpectrumCfg)xstream.fromXML(xml);
    }    
    public static ArrayList<String> procuraArquivosXML(){
        ArrayList<String> nomesarq= new ArrayList<>();
        File aux= new File(".");
        File auxvet[]=aux.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                if(file.getName().contains("PerfSpec")){
                    return true;
                }
                return false;
            }
        });
        for(int i=0;i<auxvet.length;i++){
           nomesarq.add(auxvet[i].getName());
        }
        return nomesarq;
    }
        public static ArrayList<String> procuraArquivosXML(String Tipo){
        ArrayList<String> nomesarq= new ArrayList<>();
        File aux= new File(".");
        File auxvet[]=aux.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                if(file.getName().contains(Tipo)){
                    return true;
                }
                return false;
            }
        });
        for(int i=0;i<auxvet.length;i++){
           nomesarq.add(auxvet[i].getName());
        }
        return nomesarq;
    }    
}
