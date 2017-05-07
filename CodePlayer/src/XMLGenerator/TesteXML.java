/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLGenerator;

import java.io.File;

/**
 *
 * @author Gabriel
 */
public class TesteXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BandaXML banda=new BandaXML(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        BandastoXML teste=new BandastoXML("Batate32131231");
        System.out.println(teste.geraXMLString(banda));
        teste.geraXMLfile(banda);
    //   System.out.println(teste.geraXMLString(teste.xmltoBanda(new File("Batatateste.xml"))));
       teste.procuraArquivosXML();
    }
    
}
