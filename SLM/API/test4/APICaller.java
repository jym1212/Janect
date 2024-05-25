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

public class APICaller {
    public static void main(String[] args) {
        BufferedWriter writer = null;
        try {
            String baseUrl = "http://apis.data.go.kr/B490007/qualAcquPtcond/getQualAcquPtcond";
            String serviceKey = "iBnhEtxdvRgWB11T/LDEYLkaJ4HMmCecarGXtMain7K20fr/7JhIxc1Q6VYvXE9SzOGXjcCYgr22eBhvAxxtxA==";
            File outputFile = new File("xmlResult.txt");
            writer = new BufferedWriter(new FileWriter(outputFile, true));  // true to append, false to overwrite

            int currentPage = 1;
            int pageSize = 10;
            boolean fetchMore = true;

            while (fetchMore) {
                StringBuilder urlBuilder = new StringBuilder(baseUrl);
                urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8"));
                urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + pageSize);
                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + currentPage);
                urlBuilder.append("&" + URLEncoder.encode("dataFormat", "UTF-8") + "=xml");
                urlBuilder.append("&" + URLEncoder.encode("acquYy", "UTF-8") + "=2020");
                urlBuilder.append("&" + URLEncoder.encode("qualgbCd", "UTF-8") + "=T");
                urlBuilder.append("&" + URLEncoder.encode("rgnCd", "UTF-8") + "=001");
                urlBuilder.append("&" + URLEncoder.encode("ageGrupCd", "UTF-8") + "=3");
                urlBuilder.append("&" + URLEncoder.encode("genderCd", "UTF-8") + "=M");

                URL url = new URL(urlBuilder.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/xml");

                if (conn.getResponseCode() == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                    reader.close();
                    System.out.println("Successfully processed page " + currentPage);
                } else {
                    System.out.println("Failed to fetch page " + currentPage + ", HTTP Error Code: " + conn.getResponseCode());
                    fetchMore = false;  // Stop if there's an error
                }

                currentPage++;  // Increment page number
                fetchMore = currentPage <= 82;  // Update condition based on your logic or API response
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                    System.out.println("Output file closed successfully.");
                }
            } catch (IOException ex) {
                System.out.println("Error closing the file writer.");
                ex.printStackTrace();
            }
        }
    }
}