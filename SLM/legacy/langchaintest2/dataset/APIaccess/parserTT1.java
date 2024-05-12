import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;
import java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.IOException;

public class parserTT1 {

    public static void main(String[] args) {
        // Declare the XML data as a string within the main method
        String xmlData = "<response><header><resultCode>00</resultCode><resultMsg>NORMAL SERVICE</resultMsg></header><body><items><item><acquYy>2020</acquYy><acquMm>06</acquMm><qualgbCd>T</qualgbCd><qualgbNm>국가기술자격</qualgbNm><rgnCd>001</rgnCd><rgnNm>서울</rgnNm><ageGrupCd>3</ageGrupCd><ageGrupNm>30대</ageGrupNm><genderCd>M</genderCd><genderNm>남성</genderNm><seriesCd>03</seriesCd><seriesNm>기사</seriesNm><jmCd>1320</jmCd><jmNm>정보처리기사</jmNm><acquCnt>11</acquCnt></item></items><numOfRows>10</numOfRows><pageNo>1</pageNo><totalCount>4</totalCount></body></response>";

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlData)));

            // Normalize the XML Structure
            doc.getDocumentElement().normalize();

            // XPath to find information
            XPath xPath = XPathFactory.newInstance().newXPath();

            // Expression to find all items
            String expression = "/response/body/items/item";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String acquYy = element.getElementsByTagName("acquYy").item(0).getTextContent();
                    String acquMm = element.getElementsByTagName("acquMm").item(0).getTextContent();
                    String qualgbNm = element.getElementsByTagName("qualgbNm").item(0).getTextContent();
                    String rgnNm = element.getElementsByTagName("rgnNm").item(0).getTextContent();
                    String genderNm = element.getElementsByTagName("genderNm").item(0).getTextContent();
                    String seriesNm = element.getElementsByTagName("seriesNm").item(0).getTextContent();
                    String jmNm = element.getElementsByTagName("jmNm").item(0).getTextContent();
                    String acquCnt = element.getElementsByTagName("acquCnt").item(0).getTextContent();

                    System.out.println("Year: " + acquYy + ", Month: " + acquMm + ", Qualification: " + qualgbNm +
                                       ", Region: " + rgnNm + ", Gender: " + genderNm + ", Series: " + seriesNm +
                                       ", Job: " + jmNm + ", Count: " + acquCnt);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | javax.xml.xpath.XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
