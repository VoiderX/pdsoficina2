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
public class Musica {
    String PathMusic;
    String Nome;
     public Musica(File PathMusic){
         this.PathMusic = PathMusic.toURI().toString();
         this.Nome=PathMusic.getName();
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
    
}
