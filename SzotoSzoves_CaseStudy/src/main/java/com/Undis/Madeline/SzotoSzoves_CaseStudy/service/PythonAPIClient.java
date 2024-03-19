package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.WordDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class PythonAPIClient {
    private WordRepository wordRepository;

    @Autowired
    public PythonAPIClient(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }
    public static void getWord() {
        try {
//            flask app url
            URL url = new URL("http://127.0.0.1:5000/get_word");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            RestTemplate restTemplate = new RestTemplate();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

            System.out.println(content.toString());
//            return restTemplate.getForObject(url, WordDTO.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}