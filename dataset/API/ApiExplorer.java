/* import java.net.URI; */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ApiExplorer {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.q-net.or.kr/api/service/rest/InquiryQualPassRateSVC/getList"); //URL
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=iBnhEtxdvRgWB11T%2FLDEYLkaJ4HMmCecarGXtMain7K20fr%2F7JhIxc1Q6VYvXE9SzOGXjcCYgr22eBhvAxxtxA%3D%3D"); //Service Key
        urlBuilder.append("&" + URLEncoder.encode("grdCd","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); //등급코드
        urlBuilder.append("&" + URLEncoder.encode("baseYY","UTF-8") + "=" + URLEncoder.encode("2020", "UTF-8")); //기준연도
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); //페이지 번호
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); //페이지당 데이터 수
        @SuppressWarnings("deprecation")
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println("Response Headers: " + conn.getHeaderFields());
        System.out.println(sb.toString());
    }
}

