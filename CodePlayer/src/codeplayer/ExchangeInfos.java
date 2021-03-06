/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;

import XMLGenerator.SpectrumCfg;

/**
 *
 * @author Gabriel
 */
public final class ExchangeInfos {
    
    /*
        Variáveis
    */
    private static ExchangeInfos INSTANCE = null;
    private String useratual;
    private String PerfilEq = "Zerar";
    private String SpecCfg = "Default";
    private SpectrumCfg SpecCfgObj = new SpectrumCfg();
    /*
        Fim variáveis
    */
    
    /*
        Construtor
    */
    private ExchangeInfos() {
        SpecCfgObj.setBackgroundColor("201D1D");
        SpecCfgObj.setBarColor("4169E1");
        SpecCfgObj.setIntervalo(0.02);
        SpecCfgObj.setLabelColor("FFFFFF");
        SpecCfgObj.setNumBands(64);
        SpecCfgObj.setNumC(3);
        SpecCfgObj.setPreenchimento(1);
        SpecCfgObj.setSegundacorgrad("0000FF");
        SpecCfgObj.setTerceiracorgrad("AACCFF");
        SpecCfgObj.setTipoDesenho(1);
        SpecCfgObj.setStopColor1(0.3);
        SpecCfgObj.setStopColor2(0.5);
        SpecCfgObj.setStopColor3(0.6);
    }
    /*
        Fim construtor
    */
    
    /*
        Métodos
    */
    public static ExchangeInfos getInstance() {
        return ((INSTANCE == null) ? INSTANCE = new ExchangeInfos() : INSTANCE);
    }
    /*
        Fim métodos
    */
    
    /*
        Getters e Setters
    */
    public String getUseratual() {
        return useratual;
    }

    public void setUseratual(String useratual) {
        this.useratual = useratual;
    }

    

    public SpectrumCfg getSpecCfgObj() {
        return SpecCfgObj;
    }

    public void setSpecCfgObj(SpectrumCfg SpecCfgObj) {
        this.SpecCfgObj = SpecCfgObj;
    }

    public String getPerfilEq() {
        return PerfilEq;
    }

    public void setPerfilEq(String PerfilEq) {
        this.PerfilEq = PerfilEq;
    }

    public String getSpecCfg() {
        return SpecCfg;
    }

    public void setSpecCfg(String SpecCfg) {
        this.SpecCfg = SpecCfg;
    }
    /*
        Fim getters e Setters
    */
}
