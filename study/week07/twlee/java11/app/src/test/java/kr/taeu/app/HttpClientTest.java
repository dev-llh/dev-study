package kr.taeu.app;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientTest {

    @Test
    void old() throws IOException {
        URL url = new URL("http://www.google.com");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        StringBuilder responseData = getResponseData(httpURLConnection.getInputStream());
        System.out.println("responseData = " + responseData.toString());
    }

    public StringBuilder getResponseData(InputStream in) {
        if(in == null ) return null;

        StringBuilder sb = new StringBuilder();
        String line = "";

        try (InputStreamReader ir = new InputStreamReader(in);
             BufferedReader br = new BufferedReader(ir)){
            while( (line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    @Test
    void httpClient() throws IOException, InterruptedException {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://www.google.com"))
                    .build();

            String body = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
                    .body();

            System.out.println("body = " + body);
        }
    }
}
