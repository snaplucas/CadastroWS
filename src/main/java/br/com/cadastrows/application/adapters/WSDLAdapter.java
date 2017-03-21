package br.com.cadastrows.application.adapters;

import br.com.cadastrows.application.dto.WSDLdto;
import br.com.cadastrows.domain.model.entities.WSDL;

public class WSDLAdapter {

    public static WSDL toDomainModel(WSDLdto wsdLdto) {
        return new WSDL("");
    }

    public static WSDLdto toDto(WSDL wsdl) {
        return new WSDLdto();
    }
}
