package br.com.cadastrows.application.interfaces;

import br.com.cadastrows.domain.model.entities.WSDL;

public interface IParseWSDL {

    WSDL doParse(String url);
}
