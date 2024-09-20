package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.APIRootDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.APIWordDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.ChatGPTRoot;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.ChatGPTWord;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.ChatGPTRootRepository;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.ChatGPTWordRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



@Service
public class PythonAPIClient {
    private ObjectMapper objectMapper;
    private ChatGPTWordRepository wordRepository;
    private ChatGPTRootRepository rootRepository;

    @Autowired
    public PythonAPIClient(ChatGPTWordRepository wordRepository, ObjectMapper objectMapper, ChatGPTRootRepository rootRepository) {
        this.wordRepository = wordRepository;
        this.objectMapper = objectMapper;
        this.rootRepository = rootRepository;
    }

    @Transactional
    public void saveWordAndRoots(APIWordDTO apiWordDTO) {
        // Create the ChatGPTWord entity
        ChatGPTWord chatGPTWord = new ChatGPTWord(apiWordDTO.getName(), apiWordDTO.getEnglish(), "", apiWordDTO.getDifficulty());

        // Create the ChatGPTRoot entities and add them to the word's roots list
        List<ChatGPTRoot> chatGPTRoots = new ArrayList<>();
        for (APIRootDTO apiRootDTO : apiWordDTO.getRoots()) {
            ChatGPTRoot chatGPTRoot = new ChatGPTRoot(apiRootDTO.getName(), apiRootDTO.getEnglish());
            rootRepository.save(chatGPTRoot);
            chatGPTRoots.add(chatGPTRoot);
        }
        chatGPTWord.setRoots(chatGPTRoots);

        // Save the word and its roots
        wordRepository.save(chatGPTWord);
        System.out.println(chatGPTWord);
    }
    public void sendDataToFlaskApp() {
        try {
            // URL of your Flask app endpoint to receive data
            // 1. deploy python app to prod
            // 2. change this url to read from config or ENV
            // 3. set that to the python prod URL
            URL url = new URL("http://127.0.0.1:5000/receive_data");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST"); // Using POST method to send data
            conn.setDoOutput(true); // Allow output

            // Data to be sent, you can customize this as needed
            String data = "\"Hello World!\"";

            // Send the request
            OutputStream os = conn.getOutputStream();
            os.write(data.getBytes());
            os.flush();

            int responseCode = conn.getResponseCode(); // Check the response code
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                System.out.println("Data sent to Flask app successfully!");
            } else {
                System.out.println("Error sending data to Flask app, response code: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getWord() {
        try {
//            flask app url
            URL url = new URL("http://127.0.0.1:5000/get_word");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            RestTemplate restTemplate = new RestTemplate();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ObjectMapper mapper = new ObjectMapper();
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
            saveWordAndRoots(wordDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}