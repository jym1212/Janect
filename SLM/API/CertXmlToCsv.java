
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.StringReader;

public class CertXmlToCsv {

    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        if (nlList.getLength() > 0) {
            return nlList.item(0).getTextContent();
        } else {
            return "";  // Handle cases where the tag might not exist
        }
    }

    private static String getTagValue(String tag, String childTag, Element eElement) {
        String result = "";
        try {
            NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
            for (int i = 0; i < nlList.getLength(); i++) {
                if (nlList.item(i).getNodeType() == Node.ELEMENT_NODE &&
                        nlList.item(i).getNodeName().equals(childTag)) {
                    result += nlList.item(i).getTextContent() + " ";
                }
            }
        } catch (NullPointerException e) {
            // Handle the case where the tag or childTag doesn't exist
        }
        return result.trim();
    }

    public static void main(String[] args) {
        // Manually input your XML data here (remember to wrap it in a root element)
        String xmlData = "<certinfos>" +
                         "<certinfo><certNm>정보처리기사</certNm><certNo>00000123</certNo><certLevelNm>기사</certLevelNm><implNm>한국산업인력공단</implNm><feeInfo><fee>필기: 19400원</fee><fee>실기: 22600원</fee></feeInfo></certinfo>" +
                         "<certinfo><certNm>컴퓨터활용능력 1급</certNm><certNo>00000456</certNo><certLevelNm>1급</certLevelNm><implNm>대한상공회의소</implNm><feeInfo></feeInfo></certinfo>" + // Removed practical fee tag for this example
                         "</certinfos>"; 

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new org.xml.sax.InputSource(new StringReader(xmlData)));

            doc.getDocumentElement().normalize();

            NodeList certInfoList = doc.getDocumentElement().getElementsByTagName("certinfo");
            System.out.println("자격명,자격종목코드,등급,시행처,필기 수수료,실기 수수료");

            for (int i = 0; i < certInfoList.getLength(); i++) {
                Node certInfoNode = certInfoList.item(i);

                if (certInfoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element certInfoElement = (Element) certInfoNode;

                    String certNm = getTagValue("certNm", certInfoElement);
                    String certNo = getTagValue("certNo", certInfoElement);
                    String certLevelNm = getTagValue("certLevelNm", certInfoElement);
                    String implNm = getTagValue("implNm", certInfoElement);

                    // Extract fee information (handle missing tags)
                    String fees = getTagValue("feeInfo", "fee", certInfoElement);
                    String writtenFee = "", practicalFee = "";
                    if (!fees.isEmpty()) {
                        String[] feeArray = fees.split(" ");
                        if (feeArray.length > 0 && feeArray[0].startsWith("필기:")) {
                            writtenFee = feeArray[0].split(":")[1];
                        }
                        // Check for the existence of practical fee
                        if (feeArray.length > 1 && feeArray[1].startsWith("실기:")) { 
                            practicalFee = feeArray[1].split(":")[1];
                        }
                    }

                    System.out.println(String.join(",", certNm, certNo, certLevelNm, implNm, writtenFee, practicalFee));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
