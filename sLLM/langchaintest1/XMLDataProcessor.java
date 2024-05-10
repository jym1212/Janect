import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.ByteArrayInputStream;

public class XMLDataProcessor {

    public void processXML(String xmlData) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xmlData.getBytes("UTF-8")));

            NodeList items = doc.getElementsByTagName("item");
            System.out.println("Total items: " + items.getLength());

            for (int i = 0; i < items.getLength(); i++) {
                Node node = items.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String year = element.getElementsByTagName("acquYy").item(0).getTextContent();
                    String month = element.getElementsByTagName("acquMm").item(0).getTextContent();
                    String gender = element.getElementsByTagName("genderNm").item(0).getTextContent();
                    String count = element.getElementsByTagName("acquCnt").item(0).getTextContent();
                    
                    System.out.printf("Year: %s, Month: %s, Gender: %s, Count: %s\n", year, month, gender, count);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String xmlData = "<?xml version="1.0" encoding="UTF-8" standalone="yes"?><response><header><resultCode>00</resultCode><resultMsg>NORMAL SERVICE</resultMsg></header><body><items><item><acquYy>2020</acquYy><acquMm>06</acquMm><qualgbCd>T</qualgbCd><qualgbNm>국가기술자격</qualgbNm><rgnCd>001</rgnCd><rgnNm>서울</rgnNm><ageGrupCd>3</ageGrupCd><ageGrupNm>30대</ageGrupNm><genderCd>M</genderCd><genderNm>남성</genderNm><seriesCd>03</seriesCd><seriesNm>기사</seriesNm><jmCd>1320</jmCd><jmNm>정보처리기사</jmNm><acquCnt>11</acquCnt></item><item><acquYy>2020</acquYy><acquMm>08</acquMm><qualgbCd>T</qualgbCd><qualgbNm>국가기술자격</qualgbNm><rgnCd>001</rgnCd><rgnNm>서울</rgnNm><ageGrupCd>3</ageGrupCd><ageGrupNm>30대</ageGrupNm><genderCd>M</genderCd><genderNm>남성</genderNm><seriesCd>03</seriesCd><seriesNm>기사</seriesNm><jmCd>1320</jmCd><jmNm>정보처리기사</jmNm><acquCnt>71</acquCnt></item><item><acquYy>2020</acquYy><acquMm>11</acquMm><qualgbCd>T</qualgbCd><qualgbNm>국가기술자격</qualgbNm><rgnCd>001</rgnCd><rgnNm>서울</rgnNm><ageGrupCd>3</ageGrupCd><ageGrupNm>30대</ageGrupNm><genderCd>M</genderCd><genderNm>남성</genderNm><seriesCd>03</seriesCd><seriesNm>기사</seriesNm><jmCd>1320</jmCd><jmNm>정보처리기사</jmNm><acquCnt>49</acquCnt></item><item><acquYy>2020</acquYy><acquMm>12</acquMm><qualgbCd>T</qualgbCd><qualgbNm>국가기술자격</qualgbNm><rgnCd>001</rgnCd><rgnNm>서울</rgnNm><ageGrupCd>3</ageGrupCd><ageGrupNm>30대</ageGrupNm><genderCd>M</genderCd><genderNm>남성</genderNm><seriesCd>03</seriesCd><seriesNm>기사</seriesNm><jmCd>1320</jmCd><jmNm>정보처리기사</jmNm><acquCnt>33</acquCnt></item></items><numOfRows>10</numOfRows><pageNo>1</pageNo><totalCount>4</totalCount></body></response>"; // Your XML string goes here
        new XMLDataProcessor().processXML(xmlData);
    }
}
