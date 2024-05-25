import java.net.URLEncoder;  // Import for URLEncoder
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;


public class APIController {
    public static void main(String[] args) {
        try {
            String baseUrl = "http://apis.data.go.kr/B490007/qualAcquPtcond/getQualAcquPtcond";
            String serviceKey = "iBnhEtxdvRgWB11T/LDEYLkaJ4HMmCecarGXtMain7K20fr/7JhIxc1Q6VYvXE9SzOGXjcCYgr22eBhvAxxtxA=="; // Your actual service key
            StringBuilder urlBuilder = new StringBuilder(baseUrl);
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=10");
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=1");
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

            System.out.println("URL: " + url.toString());
            System.out.println("Response Code: " + conn.getResponseCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            System.out.println("Response: " + response.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}