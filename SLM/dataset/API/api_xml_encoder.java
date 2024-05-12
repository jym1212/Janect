import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class cert_xml {

     // tag값의 정보를 가져오는 함수
    public static String getTagValue(String tag, Element eElement) {

                //결과를 저장할 result 변수 선언
                String result = "";

            NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

            result = nlList.item(0).getTextContent();

            return result;
    }

    // tag값의 정보를 가져오는 함수
    public static String getTagValue(String tag, String childTag, Element eElement) {

        //결과를 저장할 result 변수 선언
        String result = "";

        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

        for(int i = 0; i < eElement.getElementsByTagName(childTag).getLength(); i++) {

            //result += nlList.item(i).getFirstChild().getTextContent() + " ";
            result += nlList.item(i).getChildNodes().item(0).getTextContent() + " ";
        }

        return result;
    }

    public static void main(String[] args) {

        // 본인이 받은 api키를 추가
        String key = "iBnhEtxdvRgWB11T/LDEYLkaJ4HMmCecarGXtMain7K20fr/7JhIxc1Q6VYvXE9SzOGXjcCYgr22eBhvAxxtxA==";

        try{
            // parsing할 url 지정(API 키 포함해서)
            String url = "http://openapi.q-net.or.kr/api.xml?key="+key+"&";
            
            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(url);
            
            // 제일 첫번째 태그
            doc.getDocumentElement().normalize();
            
            // 파싱할 tag
            NodeList nList = doc.getElementsByTagName("certinfo");

            for(int temp = 0; temp < nList.getLength(); temp++){
                Node nNode = nList.item(temp);
            
                Element eElement = (Element) nNode;
                
                System.out.println(" : " + getTagValue("", eElement));
                System.out.println(" : " + getTagValue("", eElement));
                System.out.println(" : " + getTagValue("", eElement));
                System.out.println(" : " + getTagValue("", eElement));
                System.out.println(" : " + getTagValue("", eElement));
                System.out.println(" : " + getTagValue("", eElement));
                System.out.println(" : " + getTagValue("", eElement));

            }
            
        } catch (Exception e){  
            e.printStackTrace();
        }   
    }

}
