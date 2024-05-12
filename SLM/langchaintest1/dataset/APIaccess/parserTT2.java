// https://www.data.go.kr/data/15074415/openapi.do
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class parserTT2 {
    public static void main(String[] args) throws IOException {
        // Constructing the URL for the API request
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B490007/qualAcquPtcond/getQualAcquPtcond"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+"iBnhEtxdvRgWB11T/LDEYLkaJ4HMmCecarGXtMain7K20fr/7JhIxc1Q6VYvXE9SzOGXjcCYgr22eBhvAxxtxA=="); // Service Key
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); // Increase number of rows to 200 per page
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); // Page number
        urlBuilder.append("&" + URLEncoder.encode("dataFormat","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); // Data format
        urlBuilder.append("&" + URLEncoder.encode("acquYy","UTF-8") + "=" + URLEncoder.encode("2020", "UTF-8")); // Year of qualification acquisition
        // Removed specific qualification filter to fetch a broader dataset

        // Creating a URL object
        URL url = new URL(urlBuilder.toString());

        // Opening a connection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Setting the request method to GET
        conn.setRequestMethod("GET");

        // Setting the content type according to the requested data format
        conn.setRequestProperty("Accept", "application/xml"); // Set to XML since the format requested is XML

        // Printing the response code to check connection status
        System.out.println("Response code: " + conn.getResponseCode());

        // Reading the response from the server
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }

        // Building the response string
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        // Closing the buffered reader and disconnecting the connection
        rd.close();
        conn.disconnect();

        // Printing the response
        System.out.println(sb.toString());
    }
}
