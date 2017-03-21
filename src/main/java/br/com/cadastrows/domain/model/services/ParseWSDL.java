package br.com.cadastrows.domain.model.services;

import br.com.cadastrows.domain.model.interfaces.IParseWSDL;
import br.com.cadastrows.domain.model.entities.Operacao;
import br.com.cadastrows.domain.model.entities.Parametro;
import br.com.cadastrows.domain.model.entities.WSDL;
import com.predic8.wsdl.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;


public class ParseWSDL implements IParseWSDL {

    // TODO: parse ReturnCode do webservice EmailVerNoTestEmail
    @Override
    public WSDL doParse(String url) {
        WSDL wsdl = new WSDL(url);
        wsdl.setWsdl();

        WSDLParser parser = new WSDLParser();
        Definitions defs = parser.parse(wsdl.getWsdl());

        for (PortType pt : defs.getPortTypes()) {
            if (pt.getName().contains("Soap")) {
                for (Operation op : pt.getOperations()) {
                    Operacao operacao = new Operacao();
                    operacao.setNome(op.getName());
                    try {
                        for (Part part : op.getInput().getMessage().getParts()) {
                            JSONObject json = new JSONObject(part.getElement().getAsJson());
                            operacao.setParametroList(getParametro(json));
                            wsdl.adicionarOperacao(operacao);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return wsdl;
    }

    private List<Parametro> getParametro(JSONObject parametroJson) {
        List<Parametro> parametroList = new ArrayList<>();

        Map<String, String> map = parse(parametroJson, new HashMap<>());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Parametro parametro = new Parametro();
            parametro.setNome(entry.getKey());
            parametro.setOpcional(entry.getValue().contains("?"));
            parametro.setTipo(definirTipoParametro(entry.getValue()));
            parametroList.add(parametro);
        }
        return parametroList;
    }

    private static Map<String, String> parse(JSONObject json, Map<String, String> out) throws JSONException {
        Iterator<String> keys = json.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String val = null;
            try {
                JSONObject value = json.getJSONObject(key);
                parse(value, out);
            } catch (Exception e) {
                val = json.getString(key);
            }
            if (val != null) {
                out.put(key, val);
            }
        }
        return out;
    }

    private String definirTipoParametro(String parametro) {
        return "String";
    }
}
