package com.akshatha.error_monitor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import okhttp3.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AIService {

    @Value("${groq.api.key}")
    private String apiKey;

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getFixSuggestion(String errorMessage) {
        try {
            String prompt = "You are a helpful software engineer. " +
                "Analyze this error and provide a brief, specific fix suggestion in 2-3 sentences: "
                + errorMessage;

            String requestBody = "{"
                + "\"model\": \"llama-3.1-8b-instant\","
                + "\"messages\": [{\"role\": \"user\", \"content\": \""
                + prompt.replace("\"", "\\\"") + "\"}],"
                + "\"max_tokens\": 150"
                + "}";

            Request request = new Request.Builder()
                .url("https://api.groq.com/openai/v1/chat/completions")
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(requestBody,
                    MediaType.parse("application/json")))
                .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            System.out.println("Groq Response: " + responseBody);

            JsonNode jsonNode = objectMapper.readTree(responseBody);

            if (jsonNode.has("error")) {
                return "AI Error: " + jsonNode.get("error").get("message").asText();
            }

            return jsonNode.get("choices").get(0)
                .get("message").get("content").asText();

        } catch (Exception e) {
            return "Unable to generate fix suggestion: " + e.getMessage();
        }
    }
}