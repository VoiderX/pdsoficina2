/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeplayer;

import java.io.File;

/**
 *
 * @author Gabriel
 */
//Classe da Musica, contem o caminho da música, o nome do arquivo, e o Indice no vetor
public class Musica {

    String PathMusic;
    String Nome;
    int Index;

    public Musica(File PathMusic, int Index) { //Construtor recebe um objeto do tipo File e o Index
        this.PathMusic = PathMusic.toURI().toString(); //Converte o caminh da música para uma URI
        this.Nome = PathMusic.getName();//Adquire o nome do arquivo
        this.Index = Index;//Adquite o Index
    }

    public String getPathMusic() {
        return PathMusic;
    }

    public void setPathMusic(String PathMusic) {
        this.PathMusic = PathMusic;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int Index) {
        this.Index = Index;
    }

}
