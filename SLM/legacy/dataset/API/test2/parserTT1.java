import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.StringReader;
import javax.xml.transform.stream.StreamSource;

public class XmlParser {

    public static void main(String[] args) {
        parseXmlResponse("<your_xml_string_here>");
    }

    public static void parseXmlResponse(String xmlData) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new StreamSource(new StringReader(xmlData)));
            doc.getDocumentElement().normalize();

            NodeList headerList = doc.getElementsByTagName("header");
            for (int i = 0; i < headerList.getLength(); i++) {
                Element header = (Element) headerList.item(i);
                String resultCode = header.getElementsByTagName("resultCode").item(0).getTextContent();
                System.out.println("Result Code: " + resultCode);
            }

            NodeList itemList = doc.getElementsByTagName("item");
            for (int i = 0; i < itemList.getLength(); i++) {
                Element item = (Element) itemList.item(i);
                String pilpscnt1 = item.getElementsByTagName("pilpscnt1").item(0).getTextContent();
                String pilrccnt1 = item.getElementsByTagName("pilrccnt1").item(0).getTextContent();
                System.out.println("Pilpscnt1: " + pilpscnt1 + ", Pilrccnt1: " + pilrccnt1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
