package br.com.cadastrows.port.adapters.soap.utils;

import br.com.cadastrows.application.dto.ParametroDTO;
import br.com.cadastrows.application.dto.WSDLdto;

import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;

public class SoapMessage {

    private final WSDLdto wsdl;

    public SoapMessage(WSDLdto wsdl) {
        this.wsdl = wsdl;
    }

    public String enviarMensagemSoap() throws Exception {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        String url = wsdl.getUrl();
        SOAPMessage soapResponse = soapConnection.call(criarSoapRequest(), url);

        soapConnection.close();

        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        soapResponse.writeTo(writer);
        return new String(writer.toByteArray());
    }


    private SOAPMessage criarSoapRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = wsdl.getUrl();

        SOAPEnvelope envelope = montarSoapEnvelope(soapPart, serverURI);
        montarSoapBody(envelope);
        montarSoapHeaders(soapMessage, serverURI);

        soapMessage.saveChanges();

        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

    private void montarSoapHeaders(SOAPMessage soapMessage, String serverURI) {
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI + wsdl.getOperacao());
    }

    private void montarSoapBody(SOAPEnvelope envelope) throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElemParent = soapBody.addChildElement(wsdl.getOperacao(), "teste");
        for (ParametroDTO parametro : wsdl.getParametroDTOList()) {
            SOAPElement soapBodyElem = soapBodyElemParent.addChildElement(parametro.getNome(), "teste");
            soapBodyElem.addTextNode(parametro.getValor());
        }
    }

    private SOAPEnvelope montarSoapEnvelope(SOAPPart soapPart, String serverURI) throws SOAPException {
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("teste", serverURI);
        return envelope;
    }

}
