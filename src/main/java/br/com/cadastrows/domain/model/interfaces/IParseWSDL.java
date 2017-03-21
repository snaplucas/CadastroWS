package br.com.cadastrows.domain.model.interfaces;

import br.com.cadastrows.domain.model.entities.WSDL;

public interface IParseWSDL {

    WSDL doParse(String url);
}
