import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;

public class XmlParser {

    public static void main(String[] args) {
        String xmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
            + "<response>"
            + "<header>"
            + "<resultCode>00</resultCode>"
            + "<resultMsg>NORMAL SERVICE.</resultMsg>"
            + "</header>"
            + "<body>"
            + "<items>"
            + "<item>"
            + "<pilpscnt1>721229</pilpscnt1>"
            + "<pilpscnt2>758105</pilpscnt2>"
            + "<pilpscnt3>668114</pilpscnt3>"
            + "<pilpscnt4>649702</pilpscnt4>"
            + "<pilpscnt5>632502</pilpscnt5>"
            + "<pilpscnt6>16224372</pilpscnt6>"
            + "<pilrccnt1>1454625</pilrccnt1>"
            + "<pilrccnt2>1676466</pilrccnt2>"
            + "<pilrccnt3>1538327</pilrccnt3>"
            + "<pilrccnt4>1550877</pilrccnt4>"
            + "<pilrccnt5>1555921</pilrccnt5>"
            + "<pilrccnt6>43270216</pilrccnt6>"
            + "<silpacnt1>531203</silpacnt1>"
            + "<silpacnt2>586149</silpacnt2>"
            + "<silpacnt3>532980</silpacnt3>"
            + "<silpacnt4>525113</silpacnt4>"
            + "<silpacnt5>516253</silpacnt5>"
            + "<silpacnt6>13107201</silpacnt6>"
            + "<silrccnt1>1150807</silrccnt1>"
            + "<silrccnt2>1212193</silrccnt2>"
            + "<silrccnt3>1102122</silrccnt3>"
            + "<silrccnt4>1109429</silrccnt4>"
            + "<silrccnt5>1082471</silrccnt5>"
            + "<silrccnt6>27038920</silrccnt6>"
            + "</item>"
            + "</items>"
            + "</body>"
            + "</response>";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlResponse)));
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("header");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Element element = (Element) nList.item(temp);
                String resultCode = element.getElementsByTagName("resultCode").item(0).getTextContent();
                System.out.println("Result Code: " + resultCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
