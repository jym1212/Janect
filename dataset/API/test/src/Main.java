import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedWriter;  // Import for writing to a file
import java.io.FileWriter; // Import for writing to a file


public class Main {
    public static void main(String[] args) {
        try {
            URL url = buildUrl();
            HttpURLConnection connection = createConnection(url);
            String response = getResponse(connection);
            System.out.println("Response code: " + connection.getResponseCode());
            System.out.println(response);
            connection.disconnect();
        } catch (IOException | URISyntaxException e) { // Catching both IOException and URISyntaxException
            e.printStackTrace();
        }
    }

    private static URL buildUrl() throws IOException, URISyntaxException { // Add throws URISyntaxException
        StringBuilder urlBuilder = new StringBuilder("http://openapi.q-net.or.kr/api/service/rest/InquiryStatSVC/getTotExamList");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=iBnhEtxdvRgWB11T/LDEYLkaJ4HMmCecarGXtMain7K20fr/7JhIxc1Q6VYvXE9SzOGXjcCYgr22eBhvAxxtxA==");
        urlBuilder.append("&" + URLEncoder.encode("grdCd","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("baseYY","UTF-8") + "=" + URLEncoder.encode("2020", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));

        URI uri = new URI(urlBuilder.toString().replace(" ", "%20")); // Ensure no illegal characters
        return uri.toURL();
    }

    private static HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        return conn;
    }

    private static String getResponse(HttpURLConnection connection) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300
             ? new BufferedReader(new InputStreamReader(connection.getInputStream()))
             : new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        // Save the response to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write(sb.toString());
            }

                
        return sb.toString();
    }
}
