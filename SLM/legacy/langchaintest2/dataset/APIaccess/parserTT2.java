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
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); // Number of rows per page
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); // Page number
        urlBuilder.append("&" + URLEncoder.encode("dataFormat","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); // Data format
        urlBuilder.append("&" + URLEncoder.encode("acquYy","UTF-8") + "=" + URLEncoder.encode("2020", "UTF-8")); // Year of qualification acquisition
        urlBuilder.append("&" + URLEncoder.encode("qualgbCd","UTF-8") + "=" + URLEncoder.encode("T", "UTF-8")); // Type of qualification
        urlBuilder.append("&" + URLEncoder.encode("rgnCd","UTF-8") + "=" + URLEncoder.encode("001", "UTF-8")); // Regional code
        urlBuilder.append("&" + URLEncoder.encode("ageGrupCd","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); // Age group code
        urlBuilder.append("&" + URLEncoder.encode("genderCd","UTF-8") + "=" + URLEncoder.encode("M", "UTF-8")); // Gender code
        urlBuilder.append("&" + URLEncoder.encode("seriesCd","UTF-8") + "=" + URLEncoder.encode("03", "UTF-8")); // Series code
        urlBuilder.append("&" + URLEncoder.encode("jmCd","UTF-8") + "=" + URLEncoder.encode("1320", "UTF-8")); // Qualification code
        urlBuilder.append("&" + URLEncoder.encode("jmNm","UTF-8") + "=" + URLEncoder.encode("정보처리기사", "UTF-8")); // Qualification name

        // Creating a URL object
        URL url = new URL(urlBuilder.toString());

        // Opening a connection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Setting the request method to GET
        conn.setRequestMethod("GET");

        // Setting the content type according to the requested data format
        conn.setRequestProperty("Accept", "application/json");

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
