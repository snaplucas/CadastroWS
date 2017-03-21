package br.com.cadastrows.domain.model.interfaces;

import br.com.cadastrows.application.dto.WSDLdto;
import br.com.cadastrows.domain.model.entities.WSDL;

public interface IWSDLService {

    void cadastrarWebService(WSDLdto webServiceDto);

    WSDLdto invocarWebService(WSDL webService);

}
