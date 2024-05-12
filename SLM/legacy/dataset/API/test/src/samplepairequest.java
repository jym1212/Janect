import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
public class MovieSearchController {

    @GetMapping("/movie/search")
    public Movie movieSearch(
        @RequestParam(value = "search", required = false) String search, 
        @RequestParam(value = "api_key", required = false) String api_key,
        HttpServletResponse response) throws Exception {
        
        // Construct the URL
        String urlStr = "" + URLEncoder.encode(search, "UTF-8") + "&api_key=" + api_key;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        
        // Read the response
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        conn.disconnect();

        // Convert JSON string to Movie object
        Gson gson = new Gson();
        return gson.fromJson(result.toString(), Movie.class);
    }
}
