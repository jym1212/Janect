import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.xml.sax.InputSource;
import java.io.StringReader;

public class XMLDataToTextFile {
    public static void main(String[] args) {
        String xmlData = "<?xml version=...</response>";

        try {
            // Parse the XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlData)));

            // Normalize the XML Structure
            doc.getDocumentElement().normalize();

            // Prepare the writer
            File file = new File("output.txt");
            FileWriter fw = new FileWriter(file);
            PrintWriter writer = new PrintWriter(fw);

            // Extract data
            NodeList itemList = doc.getElementsByTagName("item");
            for (int i = 0; i < itemList.getLength(); i++) {
                Node node = itemList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String acquYy = element.getElementsByTagName("acquYy").item(0).getTextContent();
                    String acquMm = element.getElementsByTagName("acquMm").item(0).getTextContent();
                    String qualgbNm = element.getElementsByTagName("qualgbNm").item(0).getTextContent();
                    String rgnNm = element.getElementsByTagName("rgnNm").item(0).getTextContent();
                    String ageGrupNm = element.getElementsByTagName("ageGrupNm").item(0).getTextContent();
                    String genderNm = element.getElementsByTagName("genderNm").item(0).getTextContent();
                    String seriesNm = element.getElementsByTagName("seriesNm").item(0).getTextContent();
                    String jmNm = element.getElementsByTagName("jmNm").item(0).getTextContent();
                    String acquCnt = element.getElementsByTagName("acquCnt").item(0).getTextContent();

                    // Write formatted data to the text file
                    writer.println("Year: " + acquYy + ", Month: " + acquMm + ", Qualification Name: " + qualgbNm + 
                                   ", Region: " + rgnNm + ", Age Group: " + ageGrupNm + ", Gender: " + genderNm + 
                                   ", Series: " + seriesNm + ", Job Name: " + jmNm + ", Count: " + acquCnt);
                }
            }

            // Close writer
            writer.close();
            System.out.println("Data has been written to " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}