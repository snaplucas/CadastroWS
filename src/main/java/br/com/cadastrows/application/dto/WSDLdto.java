package br.com.cadastrows.application.dto;

import br.com.cadastrows.domain.model.entities.Operacao;

public class WSDLdto {

    private String url;
    private Operacao operacao;

    public String getUrl() {
        return url;
    }

    public Operacao getOperacao() {
        return operacao;
    }
}
