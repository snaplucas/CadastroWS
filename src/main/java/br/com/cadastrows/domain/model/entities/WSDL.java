package br.com.cadastrows.domain.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WSDL {

    private UUID id;
    private String url;
    private String wsdl;
    private List<Operacao> operacaoList;
    private boolean isValid;

    public WSDL(String url) {
        this.url = url;
        operacaoList = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public String getWsdl() {
        return wsdl;
    }

    public void setWsdl() {
        this.wsdl = url + "?wsdl";
    }

    public List<Operacao> getOperacaoList() {
        return operacaoList;
    }

    public void setOperacaoList(List<Operacao> operacaoList) {
        this.operacaoList = operacaoList;
    }

    public void adicionarOperacao(Operacao operacao) {
        operacaoList.add(operacao);
    }


    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
