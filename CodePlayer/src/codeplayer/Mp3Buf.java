/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;

import java.util.ArrayList;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.MapChangeListener;
import javafx.scene.media.EqualizerBand;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.util.StringConverter;

/**
 *
 * @author Gabriel
 */
//Classe para manipulação do player MP3 também em singleton
public final class Mp3Buf {

    private static Mp3Buf INSTANCE = null;

    public static Mp3Buf getInstance() {
        return ((INSTANCE == null) ? INSTANCE = new Mp3Buf() : INSTANCE);
    }

    ArrayList<Musica> Musicas; //ArrayList para guardar os caminhos das musicas a serem reproduzidas
    String pathMusic;//Caminho da música a ser reproduzida no momento
    Media media = null;//Classe Media uitlizada para iniciar o MediaPlayer e também para puxar os metadados
    MediaPlayer mp = null; //MediaPlayer, classe responsável pela reprodução em si
    int PosTocando;//Indica a posição que está sendo reproduzida na playlist
    ArrayList<Banda> bandas = null; //Array Contendo os valores das bandas
    private double lastseekValue; //Ultimo Valor selecionado no tracker
    private double valorTrackerAnterior; //Valor no tracker antes da seleção

    //Classe para converter double em horário  
    private StringConverter<Double> conversorSliderLabel = new StringConverter<Double>() {
        @Override
        public String toString(Double object) {
            int ValorMin = (int) (double) (object);
            String ParteInt = Integer.toString(ValorMin);
            int ValorSeg = (int) (60 * (object - ValorMin));
            String ParteSegs = Integer.toString(ValorSeg);
            if (ValorSeg > 9) {
                return (ParteInt + ":" + ParteSegs);
            } else {
                return (ParteInt + ":0" + ParteSegs);
            }
        }
        
        @Override
        public Double fromString(String string) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };   

    //Prepara o slider do Seeker
    public void setSeekerSlider() {
        //Valor maximo é a duração da música
        ControleUI.getInstance().getPlayerController().getTracker().setMax(
                mp.getMedia().getDuration().toMinutes());
        //Valor minimo é zero
        ControleUI.getInstance().getPlayerController().getTracker().setMin(0);
        setValorTrackerAnterior(0); //Limpa as váriaveis de controle
        setLastseekValue(0);
        //Reseta o tracker
        ControleUI.getInstance().getPlayerController().getTracker().setValue(0);
        //Formata e mostra os labels 
        ControleUI.getInstance().getPlayerController().getTracker().setLabelFormatter(conversorSliderLabel);
        ControleUI.getInstance().getPlayerController().getTracker().setShowTickLabels(true);
    }
    //Classe listener para a posição atual da música
    InvalidationListener teste = (Observable observable) -> {
        /*Identifica se o usuário retrocedeu ou avançou, e aguarda a atualização do seeker para não
        Retornar o slider ao valor anterior*/
        if (getLastseekValue() >= getValorTrackerAnterior()) {
            if (mp.getCurrentTime().toMinutes() >= getLastseekValue()) {
                setTrackerSliderPos();
            }
        } else {
            if (mp.getCurrentTime().toMinutes() <= getValorTrackerAnterior()) {
                setTrackerSliderPos();
            }
        }
    };

    //Metodo para preparar o equalizador
    private void preparaEq() {
        mp.getAudioEqualizer().getBands().clear(); //Limpa as bandas do equalizador
        double freq = 20;
        for (int i = 0; i <= 9; i++) {
            Mp3Buf.getInstance().getMp().getAudioEqualizer().getBands().add(new EqualizerBand(freq, freq / 2, 0));
            freq *= 2;
        }
        if (bandas != null) { //Verifica se o array já foi inicializado
            for (int i = 0; i < bandas.size(); i++) {// Carrega os dados no array
                mp.getAudioEqualizer().getBands().get(i).setGain(bandas.get(i).getValor());
            }
        }
    }

    //metodo para preparar a media
    private void preparaMp() {
        mp = null;
        media = null;
        System.gc(); //Garbage collector para tirar os mediaplayer já mortos
        pathMusic = Musicas.get(PosTocando).getPathMusic();//Puxa o pathmusic da musica a ser tocada
        media = new Media(pathMusic); //Carrega a Media
        mp = new MediaPlayer(media);
        mp.setOnEndOfMedia(new CheckNextMusic()); //Chama a próxima musica ao terminar a atual
    }

    //Metodo para iniciar os listener dos sliders
    private void startListenerSlider() {
        //Prepara o Slider
        mp.getMedia().durationProperty().addListener(listener -> setSeekerSlider());
        //Mantem o slider seguindo a música
        mp.currentTimeProperty().addListener(teste);
    }

    //Metodo para adicionar o listener para verificar alterações nos metadados
    private void addListenerMetadata() {
        Mp3Buf.getInstance().getMp().getMedia().getMetadata().addListener(
                (MapChangeListener.Change<? extends String, ? extends Object> change) -> {
                    ControleUI.getInstance().getPlayerController().setInfos(
                            Mp3Buf.getInstance().getMedia().getMetadata());
                });
    }

    public void carregaMusica() {//Classe para carregar a música no Media e no MediaPlayer
        if (PosTocando >= Musicas.size()) { //Caso chegue na ultima música retorna ao inicio
            PosTocando = 0;
        }
        if (PosTocando < 0) { //Caso o usuário esteja na ultima música e aperta o anterior vai para a  ultima da lista
            PosTocando = Musicas.size() - 1;
        }
        preparaMp();
        addListenerMetadata();
        preparaEq();
        startListenerSlider();

    }

    //Códigos para controle dos trackers
    private void setTrackerSliderPos() {
        ControleUI.getInstance().getPlayerController().getTracker().setValue(mp.getCurrentTime().toMinutes());
    }

    public void removeListenerTracker() {
        mp.currentTimeProperty().removeListener(teste);
        ControleUI.getInstance().getPlayerController().getTracker().setValue(lastseekValue);
    }

    public void addListenerTracker() {
        ControleUI.getInstance().getPlayerController().getTracker().setValue(lastseekValue);
        mp.currentTimeProperty().addListener(teste);
    }

    public void seekTime(double time) {
        //System.out.println(ControleUI.getInstance().getPlayerController().getTracker().getValue());
        mp.seek(Duration.minutes(time));
    }

    //Getter e setter das váriaveis
    public double getLastseekValue() {
        return lastseekValue;
    }

    public void setLastseekValue(double lastseekValue) {
        this.lastseekValue = lastseekValue;
    }

    public double getValorTrackerAnterior() {
        return valorTrackerAnterior;
    }

    public void setValorTrackerAnterior(double valorTrackerAnterior) {
        this.valorTrackerAnterior = valorTrackerAnterior;
    }

    public ArrayList<Musica> getMusicas() {
        return Musicas;
    }

    public void setMusicas(ArrayList<Musica> Musicas) {
        this.Musicas = Musicas;
    }

    public String getPathMusic() {
        return pathMusic;
    }

    public void setPathMusic(String pathMusic) {
        this.pathMusic = pathMusic;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public MediaPlayer checkmp() {
        return mp;
    }

    public MediaPlayer getMp() {
        if (mp == null) {
            carregaMusica();
        }
        return mp;
    }

    public void setMp(MediaPlayer mp) {
        this.mp = mp;
    }

    public int getPosTocando() {
        return PosTocando;
    }

    public void setPosTocando(int PosTocando) {
        this.PosTocando = PosTocando;
    }

    public ArrayList<Banda> getBandas() {
        return bandas;
    }

    public void setBandas(ArrayList<Banda> bandas) {
        this.bandas = bandas;
    }

    public StringConverter<Double> getConversorSliderLabel() {
        return conversorSliderLabel;
    }  

}
