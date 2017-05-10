/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLGenerator;

/**
 *
 * @author Gabriel
 */
public class SpectrumCfg {
    private String BackgroundColor;
    private String LabelColor;
    private String BarColor;
    private int numBands;
    private double intervalo;
    private int preenchimento; //Cor Solida ou Gradiente
    private int numC; //Cores do gradiente 3
    private String segundacorgrad;
    private String terceiracorgrad;
    private int TipoDesenho;

    public int getTipoDesenho() {
        return TipoDesenho;
    }

    public void setTipoDesenho(int TipoDesenho) {
        this.TipoDesenho = TipoDesenho;
    }

    public int getPreenchimento() {
        return preenchimento;
    }

    public void setPreenchimento(int preenchimento) {
        this.preenchimento = preenchimento;
    }

    public int getNumC() {
        return numC;
    }

    public void setNumC(int numC) {
        this.numC = numC;
    }

    public String getSegundacorgrad() {
        return segundacorgrad;
    }

    public void setSegundacorgrad(String segundacorgrad) {
        this.segundacorgrad = segundacorgrad;
    }

    public String getTerceiracorgrad() {
        return terceiracorgrad;
    }

    public void setTerceiracorgrad(String terceiracorgrad) {
        this.terceiracorgrad = terceiracorgrad;
    }
    

    public double getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(double intervalo) {
        this.intervalo = intervalo;
    }

    public String getBackgroundColor() {
        return BackgroundColor;
    }

    public void setBackgroundColor(String BackgroundColor) {
        this.BackgroundColor = BackgroundColor;
    }

    public String getLabelColor() {
        return LabelColor;
    }

    public void setLabelColor(String LabelColor) {
        this.LabelColor = LabelColor;
    }

    public String getBarColor() {
        return BarColor;
    }

    public void setBarColor(String BarColor) {
        this.BarColor = BarColor;
    }

    public int getNumBands() {
        return numBands;
    }

    public void setNumBands(int numBands) {
        this.numBands = numBands;
    }

    public SpectrumCfg(){
        
    }
    
    
}
