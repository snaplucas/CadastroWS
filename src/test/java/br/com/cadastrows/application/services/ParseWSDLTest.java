package br.com.cadastrows.application.services;


import br.com.cadastrows.domain.model.entities.WSDL;
import br.com.cadastrows.domain.model.services.ParseWSDL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParseWSDLTest {

    private ParseWSDL parseWSDL = new ParseWSDL();

    @Before
    public void setUp() {
    }


    @Test
    public void ParseWSDLSucessTest() {
        WSDL wsdl = parseWSDL.doParse("http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx");
        Assert.assertNotNull(wsdl);
    }
}