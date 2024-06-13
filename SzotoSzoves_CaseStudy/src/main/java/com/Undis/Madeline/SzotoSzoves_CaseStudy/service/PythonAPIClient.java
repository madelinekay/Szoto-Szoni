package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.fasterxml.jackson.databind.ObjectMapper;

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
            ObjectMapper mapper = new ObjectMapper();
//            todo research if below is possible
//            WordDTO wordDTO = mapper.readValue(in, WordDTO.class);
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();
            System.out.println(content);
//           ObjectMapper JsonParser parser = new JsonParser();
//            JsonNode node = parser.parse(json);
//            WordDTO wordDTO = mapper.treeToValue(node, WordDTO.class);
//            JsonNode jsonNode = mapper.readTree(String.valueOf(content));
//            WordDTO wordDTO = mapper.readValue(content.toString(), WordDTO.class);
//            WordDTO wordDTO = mapper.readValue(content, WordDTO.class);
//            System.out.println(wordDTO);
//            String json = "{\"name\":\"John Doe\"}";
//
//            Person person = mapper.readValue(json, Person.class);
//
//            System.out.println("Name: " + person.name);
//            System.out.println("Age: " + person.age); // Will print null since 'age' is not provided in the JSON
//

//            System.out.println(content.toString());
//            return restTemplate.getForObject(url, WordDTO.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}