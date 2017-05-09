/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;

/**
 *
 * @author Gabriel
 */
public final class ExchangeInfos {
    
    private static ExchangeInfos INSTANCE=null;
    
    public static ExchangeInfos getInstance(){
      return((INSTANCE == null)?INSTANCE = new ExchangeInfos():INSTANCE);
   }
   
   String PerfilEq="Zerar";
   String SpecCfg="Default";

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
   
}
