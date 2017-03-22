package br.com.cadastrows.domain.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Operacao {

    private String nome;
    private List<Parametro> parametroList;

    private String input;
    private String output;

    public Operacao() {
        parametroList = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarParametro(Parametro parametro) {
        parametroList.add(parametro);
    }

    public List<Parametro> getParametroList() {
        return parametroList;
    }

    public void setParametroList(List<Parametro> parametroList) {
        this.parametroList = parametroList;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
