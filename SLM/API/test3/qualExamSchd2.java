
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class qualExamSchd2 {
    public static void main(String[] args) {
        try {
            // Base URL for the API
            String baseUrl = "http://apis.data.go.kr/B490007/qualExamSchd/getQualExamSchdList";

            // URL encoding of the service key provided by the API
            String serviceKey = "iBnhEtxdvRgWB11T/LDEYLkaJ4HMmCecarGXtMain7K20fr/7JhIxc1Q6VYvXE9SzOGXjcCYgr22eBhvAxxtxA=="; // Replace this placeholder with your actual service key
            String encodedServiceKey = URLEncoder.encode(serviceKey, "UTF-8");

            // StringBuilder to build the request URL
            StringBuilder urlBuilder = new StringBuilder(baseUrl);
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + encodedServiceKey);
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("dataFormat", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("implYy", "UTF-8") + "=" + URLEncoder.encode("2023", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("qualgbCd", "UTF-8") + "=" + URLEncoder.encode("T", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("jmCd", "UTF-8") + "=" + URLEncoder.encode("7910", "UTF-8"));

            // Create a URL object
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");

            // Checking the response code to determine the status of the connection
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            BufferedReader reader;

            // Handling the response based on the status code
            if (responseCode >= 200 && responseCode <= 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }

            // Reading the response from the server
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Printing the complete response
            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
