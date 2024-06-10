import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;


public class qualExamSchd {
    public static void main(String[] args) {
        String baseUrl = "http://apis.data.go.kr/B490007/qualExamSchd/getQualExamSchdList"; // Base API URL
        String serviceKey = "0DDeORc+nKOdWVgyiWkeOjgv+c3Jf/vuki8lmthnNfsFPATdks7G+gSSXl0VK3DzEvXCP+Il36kw4vmYOgUWrg=="; // Actual service key
        String outputFile = "qualExamSchdResults.txt"; // File to save the results
        int pageNo = 1; // Start from page 1
        int numOfRows = 10; // Number of rows per page
        boolean morePages = true; // Control variable for loop

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            while (morePages) {
                // Build the full URL with parameters for the API call
                String encodedServiceKey = URLEncoder.encode(serviceKey, "UTF-8");
                StringBuilder urlBuilder = new StringBuilder(baseUrl);
                urlBuilder.append("?").append(URLEncoder.encode("serviceKey", "UTF-8")).append("=").append(encodedServiceKey);
                urlBuilder.append("&").append(URLEncoder.encode("numOfRows", "UTF-8")).append("=").append(URLEncoder.encode(String.valueOf(numOfRows), "UTF-8"));
                urlBuilder.append("&").append(URLEncoder.encode("pageNo", "UTF-8")).append("=").append(URLEncoder.encode(String.valueOf(pageNo), "UTF-8"));
                urlBuilder.append("&").append(URLEncoder.encode("dataFormat", "UTF-8")).append("=").append(URLEncoder.encode("xml", "UTF-8"));

                URL url = new URL(urlBuilder.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/xml");

                // Check the response code and process the data
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                    reader.close();
                    System.out.println("Successfully processed page " + pageNo);
                    pageNo++; // Increment page number to fetch next page
                } else {
                    System.out.println("No more data available or error fetching data.");
                    morePages = false; // Stop fetching if there is an error or no more data
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
