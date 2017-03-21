package br.com.cadastrows.domain.model.services;

import br.com.cadastrows.application.dto.WSDLdto;
import br.com.cadastrows.domain.model.entities.WSDL;
import br.com.cadastrows.domain.model.interfaces.IWSDLService;

public class WSDLService implements IWSDLService {

    @Override
    public void cadastrarWebService(WSDLdto webServiceDto) {

    }

    @Override
    public WSDLdto invocarWebService(WSDL webService) {
        return null;
    }
}
