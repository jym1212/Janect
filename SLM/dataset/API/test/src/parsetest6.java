import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

public class Main {
    public static void main(String[] args) {
        try {
            URL url = buildUrl();
            HttpURLConnection connection = createConnection(url);
            String response = getResponse(connection);
            System.out.println("Response code: " + connection.getResponseCode());
            System.out.println(response);
            writeToFile(response, "output.txt"); // Separate method for writing response to a file
            connection.disconnect();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static URL buildUrl() throws URISyntaxException, IOException {
        String baseUrl = "http://openapi.q-net.or.kr/api/service/rest/InquiryStatSVC/getTotExamList";
        String queryParams = String.format("serviceKey=%s&grdCd=%s&baseYY=%s&pageNo=%s&numOfRows=%s",
                URLEncoder.encode("iBnhEtxdvRgWB11T/LDEYLkaJ4HMmCecarGXtMain7K20fr/7JhIxc1Q6VYvXE9SzOGXjcCYgr22eBhvAxxtxA==", "UTF-8"),
                URLEncoder.encode("10", "UTF-8"),
                URLEncoder.encode("2020", "UTF-8"),
                URLEncoder.encode("1", "UTF-8"),
                URLEncoder.encode("1", "UTF-8"));
        URI uri = new URI(baseUrl + "?" + queryParams);
        return uri.toURL();
    }

    private static HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        return conn;
    }

    private static String getResponse(HttpURLConnection connection) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getResponseCode() >= 200 && connection.getResponseCode() <= 299 ?
                        connection.getInputStream() : connection.getErrorStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }

    private static void writeToFile(String data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

