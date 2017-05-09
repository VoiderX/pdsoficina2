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
    
    
}
