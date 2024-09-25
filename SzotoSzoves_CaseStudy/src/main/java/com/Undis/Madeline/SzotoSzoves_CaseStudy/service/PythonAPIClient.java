package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.APIRootDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.APIWordDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.ChatGPTRoot;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.ChatGPTWord;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.ChatGPTRootRepository;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.ChatGPTWordRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PythonAPIClient {
    private ObjectMapper objectMapper;
    private ChatGPTWordRepository wordRepository;
    private ChatGPTRootRepository rootRepository;
    private RestTemplate restTemplate;

    @Value("${chatgpt.api.url}")
    private String chatGptApiUrl;

    @Value("${chatgpt.api.key}")
    private String chatGptApiKey;

    @PostConstruct
    public void init() {
        System.out.println("chatGptApiUrl: " + chatGptApiUrl);
        System.out.println("chatGptApiKey: " + chatGptApiKey);
    }

    @Autowired
    public PythonAPIClient(ChatGPTWordRepository wordRepository, ObjectMapper objectMapper, ChatGPTRootRepository rootRepository, RestTemplate restTemplate) {
        this.wordRepository = wordRepository;
        this.objectMapper = objectMapper;
        this.rootRepository = rootRepository;
        this.restTemplate = restTemplate;
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

    public void getWord() {
        try {
            // Create the request body
            String requestBody = "{\"model\": \"gpt-4-0125-preview\", \"response_format\": {\"type\": \"json_object\"}, \"messages\": [{\"role\": \"system\", \"content\": \"The user is learning Turkish. Here are some words they've already learned: - bir (one) - kitap (book) - yaz (summer)\"}, {\"role\": \"user\", \"content\": \"Give me a Turkish word, its English translation, difficulty level 1 through 5, and a breakdown of each root in the word and its translation. The word should be different from the ones the user already knows and every so often be related by roots or origin. Provide the response in the format of this JSON object: { \\\"name\\\": \\\"word\\\", \\\"english\\\": \\\"english\\\", \\\"difficulty\\\": difficulty, \\\"roots\\\": [{ \\\"name\\\": \\\"root\\\", \\\"english\\\": \\\"english\\\"}]}. Ensure the response is valid JSON.\"}]}";

            // Create the request headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + chatGptApiKey);

            // Create the request entity
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Make the API call
            ResponseEntity<String> responseEntity = restTemplate.exchange(chatGptApiUrl, HttpMethod.POST, requestEntity, String.class);

            // Check the response status
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                // Parse the response JSON
                String responseJson = responseEntity.getBody();
                System.out.println("Response JSON: " + responseJson);

                // Use ObjectMapper to parse the main response
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseJson);

                // Extract the "content" field inside the "message" node
                String content = rootNode.get("choices")
                        .get(0)
                        .get("message")
                        .get("content")
                        .asText();

                // Parse the stringified JSON inside the "content" field
                APIWordDTO wordDTO = mapper.readValue(content, APIWordDTO.class);

                System.out.println("APIWordDTO from chatGPT API: " + wordDTO);
                saveWordAndRoots(wordDTO);

            } else {
                System.out.println("Error calling chatGPT API, response code: " + responseEntity.getStatusCodeValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


//package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;
//import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.APIRootDTO;
//import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.APIWordDTO;
//import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.ChatGPTRoot;
//import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.ChatGPTWord;
//import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.ChatGPTRootRepository;
//import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.ChatGPTWordRepository;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//@Service
//public class PythonAPIClient {
//    private ObjectMapper objectMapper;
//    private ChatGPTWordRepository wordRepository;
//    private ChatGPTRootRepository rootRepository;
//
//    @Autowired
//    public PythonAPIClient(ChatGPTWordRepository wordRepository, ObjectMapper objectMapper, ChatGPTRootRepository rootRepository) {
//        this.wordRepository = wordRepository;
//        this.objectMapper = objectMapper;
//        this.rootRepository = rootRepository;
//    }
//
//    @Transactional
//    public void saveWordAndRoots(APIWordDTO apiWordDTO) {
//        // Create the ChatGPTWord entity
//        ChatGPTWord chatGPTWord = new ChatGPTWord(apiWordDTO.getName(), apiWordDTO.getEnglish(), "", apiWordDTO.getDifficulty());
//
//        // Create the ChatGPTRoot entities and add them to the word's roots list
//        List<ChatGPTRoot> chatGPTRoots = new ArrayList<>();
//        for (APIRootDTO apiRootDTO : apiWordDTO.getRoots()) {
//            ChatGPTRoot chatGPTRoot = new ChatGPTRoot(apiRootDTO.getName(), apiRootDTO.getEnglish());
//            rootRepository.save(chatGPTRoot);
//            chatGPTRoots.add(chatGPTRoot);
//        }
//        chatGPTWord.setRoots(chatGPTRoots);
//
//        // Save the word and its roots
//        wordRepository.save(chatGPTWord);
//        System.out.println(chatGPTWord);
//    }
//    public void sendDataToFlaskApp() {
//        try {
//            // URL of your Flask app endpoint to receive data
//            // 1. deploy python app to prod
//            // 2. change this url to read from config or ENV
//            // 3. set that to the python prod URL
//            URL url = new URL("http://127.0.0.1:5000/receive_data");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST"); // Using POST method to send data
//            conn.setDoOutput(true); // Allow output
//
//            // Data to be sent, you can customize this as needed
//            String data = "\"Hello World!\"";
//
//            // Send the request
//            OutputStream os = conn.getOutputStream();
//            os.write(data.getBytes());
//            os.flush();
//
//            int responseCode = conn.getResponseCode(); // Check the response code
//            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
//                System.out.println("Data sent to Flask app successfully!");
//            } else {
//                System.out.println("Error sending data to Flask app, response code: " + responseCode);
//            }
//
//            conn.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void getWord() {
//        try {
////            flask app url
//            URL url = new URL("http://127.0.0.1:5000/get_word");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            RestTemplate restTemplate = new RestTemplate();
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            ObjectMapper mapper = new ObjectMapper();
//            String inputLine;
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            in.close();
//            conn.disconnect();
//
//            String jsonString = content.toString().trim();
//
//            // Remove the outer quotes if they exist
//            if (jsonString.startsWith("\"") && jsonString.endsWith("\"")) {
//                jsonString = jsonString.substring(1, jsonString.length() - 1);
//            }
//
//            // Unescape the JSON string
//            jsonString = jsonString.replace("\\n", "").replace("\\", "");
//
//            System.out.println("Cleaned JSON: " + jsonString);
//
//            APIWordDTO wordDTO = objectMapper.readValue(jsonString, APIWordDTO.class);
//            System.out.println("APIWordDTO from Python API: " + wordDTO);
//            saveWordAndRoots(wordDTO);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}