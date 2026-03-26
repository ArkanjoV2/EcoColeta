package br.com.ecocoleta.model;

import java.io.Serializable;
import java.util.List;

public class PontoDeColeta implements  Serializable {

    private int id;
    private String nome;
    private String endereco;
    private List<String> tiposDeResiduos;

    public PontoDeColeta(int id, String nome, String endereco, List<String> tiposDeResiduos) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tiposDeResiduos = tiposDeResiduos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<String> getTiposDeResiduos() {
        return tiposDeResiduos;
    }

    public void setTiposDeResiduos(List<String> tiposDeResiduos) {
        this.tiposDeResiduos = tiposDeResiduos;
    }

    @Override
    public String toString() {
        return "PontoDeColeta {" +
                "ID=" + id +
                ", Nome= '" + nome + '\'' +
                ", Endereço='" + endereco + '\'' +
                "Aceita=" + tiposDeResiduos +
                '}';
    }

}