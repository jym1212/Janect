import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.IOException;

public class XMLParserDOM {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><response><header><resultCode>00</resultCode><resultMsg>NORMAL SERVICE.</resultMsg></header><body><items><item><pilpscnt1>721229</pilpscnt1><pilpscnt2>758105</pilpscnt2><pilpscnt3>668114</pilpscnt3><pilpscnt4>649702</pilpscnt4><pilpscnt5>632502</pilpscnt5><pilpscnt6>16224372</pilpscnt6><pilrccnt1>1454625</pilrccnt1><pilrccnt2>1676466</pilrccnt2><pilrccnt3>1538327</pilrccnt3><pilrccnt4>1550877</pilrccnt4><pilrccnt5>1555921</pilrccnt5><pilrccnt6>43270216</pilrccnt6><silpacnt1>531203</silpacnt1><silpacnt2>586149</silpacnt2><silpacnt3>532980</silpacnt3><silpacnt4>525113</silpacnt4><silpacnt5>516253</silpacnt5><silpacnt6>13107201</silpacnt6><silrccnt1>1150807</silrccnt1><silrccnt2>1212193</silrccnt2><silrccnt3>1102122</silrccnt3><silrccnt4>1109429</silrccnt4><silrccnt5>1082471</silrccnt5><silrccnt6>27038920</silrccnt6></item></items></body></response>";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xmlData));
        Document document = builder.parse(is);

        document.getDocumentElement().normalize();
        System.out.println("Root element: " + document.getDocumentElement().getNodeName());

        NodeList itemsList = document.getElementsByTagName("item");
        for (int i = 0; i < itemsList.getLength(); i++) {
            Node item = itemsList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                System.out.println("pilpscnt1: " + element.getElementsByTagName("pilpscnt1").item(0).getTextContent());
                System.out.println("pilpscnt2: " + element.getElementsByTagName("pilpscnt2").item(0).getTextContent());
                System.out.println("pilpscnt3: " + element.getElementsByTagName("pilpscnt3").item(0).getTextContent());
                System.out.println("pilpscnt4: " + element.getElementsByTagName("pilpscnt4").item(0).getTextContent());
                System.out.println("pilpscnt5: " + element.getElementsByTagName("pilpscnt5").item(0).getTextContent());
                System.out.println("pilpscnt6: " + element.getElementsByTagName("pilpscnt6").item(0).getTextContent());
                System.out.println("pilpscnt7: " + element.getElementsByTagName("pilpscnt7").item(0).getTextContent());
                // Retrieve other elements similarly
            }
        }
    }
}
