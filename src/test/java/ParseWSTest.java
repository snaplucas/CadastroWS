import br.com.cadastrows.domain.model.entities.Operacao;
import br.com.cadastrows.domain.model.entities.Parametro;
import br.com.cadastrows.domain.model.entities.WSDL;
import com.predic8.schema.Schema;
import com.predic8.wsdl.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.*;


public class ParseWSTest {

    @Test
    public void doParse() {
        WSDLParser parser = new WSDLParser();

        Definitions defs = parser.parse("http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx?wsdl");
//        Definitions defs = parser.parse("http://www.webservicex.net/isbn.asmx?wsdl");
//        Definitions defs = parser.parse("http://www.thomas-bayer.com/axis2/services/BLZService?wsdl");

        out("-------------- WSDL Details --------------");
        out("TargenNamespace: \t" + defs.getTargetNamespace());
        if (defs.getDocumentation() != null) {
            out("Documentation: \t\t" + defs.getDocumentation());
        }
        out("\n");

        out("Schemas: ");
        for (Schema schema : defs.getSchemas()) {
            out("  TargetNamespace: \t" + schema.getTargetNamespace());
        }
        out("\n");

        out("Messages: ");
        for (Message msg : defs.getMessages()) {
            out("  Message Name: " + msg.getName());
            out("  Message Parts: ");
            for (Part part : msg.getParts()) {
                out("    Part Name: " + part.getName());
                out("    Part Element: " + ((part.getElement() != null) ? part.getElement() : "not available!"));
                out("    Part Type: " + ((part.getType() != null) ? part.getType() : "not available!"));
                out("");
            }
        }
        out("");

        out("PortTypes: ");
        for (PortType pt : defs.getPortTypes()) {
            out("  PortType Name: " + pt.getName());
            out("  PortType Operations: ");
            for (Operation op : pt.getOperations()) {
                out("    Operation Name: " + op.getName());
                out("    Operation Input Name: " + ((op.getInput().getName() != null) ? op.getInput().getName() : "not available!"));
                out("    Operation Input Message: " + op.getInput().getMessage().getQname());
                out("    Operation Output Name: " + ((op.getOutput().getName() != null) ? op.getOutput().getName() : "not available!"));
                out("    Operation Output Message: " + op.getOutput().getMessage().getQname());
                out("    Operation Faults: ");
                if (op.getFaults().size() > 0) {
                    for (Fault fault : op.getFaults()) {
                        out("      Fault Name: " + fault.getName());
                        out("      Fault Message: " + fault.getMessage().getQname());
                    }
                } else out("      There are no faults available!");

            }
            out("");
        }
        out("");

        out("Bindings: ");
        for (Binding bnd : defs.getBindings()) {
            out("  Binding Name: " + bnd.getName());
            out("  Binding Type: " + bnd.getPortType().getName());
            out("  Binding Protocol: " + bnd.getBinding().getProtocol());

            if (bnd.getBinding() instanceof AbstractSOAPBinding)
                out("  Style: " + (((AbstractSOAPBinding) bnd.getBinding()).getStyle()));
            out("  Binding Operations: ");
            for (BindingOperation bop : bnd.getOperations()) {
                out("    Operation Name: " + bop.getName());
                if (bnd.getBinding() instanceof AbstractSOAPBinding) {
                    out("    Operation SoapAction: " + bop.getOperation().getSoapAction());
                    out("    SOAP Body Use: " + bop.getInput().getBindingElements().get(0).getUse());
                }
            }
            out("");
        }
        out("");

        out("Services: ");
        for (Service service : defs.getServices()) {
            out("  Service Name: " + service.getName());
            out("  Service Potrs: ");
            for (Port port : service.getPorts()) {
                out("    Port Name: " + port.getName());
                out("    Port Binding: " + port.getBinding().getName());
                out("    Port Address Location: " + port.getAddress().getLocation() + "\n");
            }
        }
        out("");
    }

    private static void out(String str) {
        System.out.println(str);
    }


    @Test
    public void doParseAgain() {
        WSDLParser parser = new WSDLParser();

        Definitions defs = parser.parse("http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx?wsdl");

        for (Operation operation : defs.getOperations()) {
            System.out.println(operation.getName());
        }
    }

    @Test
    public void parseOperations() {
        WSDLParser parser = new WSDLParser();

        Definitions defs = parser.parse("http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx?wsdl");

        for (PortType pt : defs.getPortTypes()) {

            if(pt.getName().contains("Soap")) {
                System.out.println(pt.getName());
                for (Operation op : pt.getOperations()) {
                    try {
                        System.out.println(" -" + op.getName());
                        for (Part part : op.getInput().getMessage().getParts()) {
                            System.out.println(part.getName() + " " + part.getElement().getAsJson());
                            JSONObject json = new JSONObject(part.getElement().getAsJson());
                            System.out.println(json);

//                            Map<String,String> out = new HashMap<>();
//                            Map<String,String> map = parse(json,out);
//                            System.out.println(map);
                        }
                        for (Part part : op.getOutput().getMessage().getParts()) {
                            System.out.println(part.getName() + " " + part.getElement().getAsJson());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("    ");

                }
            }
        }
    }

    @Test
    public void doParseService() {
        String url = "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx?wsdl";
        WSDL wsdl = new WSDL(url);

        WSDLParser parser = new WSDLParser();
        Definitions defs = parser.parse(url);

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

        System.out.println(wsdl);
    }

    private List<Parametro> getParametro(JSONObject parametroJson) {
        List<Parametro> parametroList = new ArrayList<>();

        Map<String, String> map = parse(parametroJson, new HashMap<>());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Parametro parametro = new Parametro();
            parametro.setNome(entry.getKey());
            parametro.setOpcional(entry.getValue().contains("?"));
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
}
