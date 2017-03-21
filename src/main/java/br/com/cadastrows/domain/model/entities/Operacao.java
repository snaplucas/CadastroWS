package br.com.cadastrows.domain.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Operacao {

    private String nome;
    private List<Parametro> parametroList;

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
}
