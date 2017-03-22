package br.com.cadastrows.application.dto;

import java.util.List;

public class WSDLdto {

    private String url;
    private String operacao;
    private List<ParametroDTO> parametroDTOList;
    private String resultado;


    public String getUrl() {
        return url;
    }

    public String getOperacao() {
        return operacao;
    }

    public List<ParametroDTO> getParametroDTOList() {
        return parametroDTOList;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
