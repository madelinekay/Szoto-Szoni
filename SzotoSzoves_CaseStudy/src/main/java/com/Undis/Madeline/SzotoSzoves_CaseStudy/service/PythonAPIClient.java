package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class PythonAPIClient {
    public static void getWord() {
        try {
//            flask app url
            URL url = new URL("http://127.0.0.1:5000/get_word");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

            System.out.println(content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}