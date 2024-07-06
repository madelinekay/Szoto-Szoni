package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.APIWordDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.WordDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.WordRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private ObjectMapper objectMapper;

    @Autowired
    public PythonAPIClient(WordRepository wordRepository, ObjectMapper objectMapper) {
        this.wordRepository = wordRepository;
        this.objectMapper = objectMapper;
    }
    public void serializeWord() {

    }
    public void getWord() {
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

            String jsonString = content.toString().trim();

            // Remove the outer quotes if they exist
            if (jsonString.startsWith("\"") && jsonString.endsWith("\"")) {
                jsonString = jsonString.substring(1, jsonString.length() - 1);
            }

            // Unescape the JSON string
            jsonString = jsonString.replace("\\n", "").replace("\\", "");

            System.out.println("Cleaned JSON: " + jsonString);

            APIWordDTO wordDTO = objectMapper.readValue(jsonString, APIWordDTO.class);
            System.out.println("APIWordDTO from Python API: " + wordDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}