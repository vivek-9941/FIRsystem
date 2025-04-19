package org.fir.firsystem.GenAi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class GroqApiService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public GroqApiService() {
        this.restTemplate = new RestTemplate();
    }

    public String callGroqApi(String apiKey, String model, String content) {
        try {
            // Construct JSON payload using a Map

            
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", model);
            requestBody.put("messages", new Object[]{Map.of("role", "user", "content", content)});

            // Convert request body to JSON string
            String requestBodyJson = objectMapper.writeValueAsString(requestBody);

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create HTTP request
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBodyJson, headers);

            // Send POST request
            ResponseEntity<String> response = restTemplate.exchange(
                    "https://api.groq.com/openai/v1/chat/completions",
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            // Parse JSON response to extract only content
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            return rootNode.path("choices").get(0).path("message").path("content").asText();

        } catch (Exception e) {
            throw new RuntimeException("Failed to call API", e);
        }
    }
}