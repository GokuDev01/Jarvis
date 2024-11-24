package com.example.jarvis;

import okhttp3.*;
import org.json.JSONObject;

public class ClaudeApiClient {

    private static final String API_URL = "https://api.anthropic.com/v1/complete";
    private static final String API_KEY = "your-anthropic-api-key";

    public static String getAIResponse(String input) {
        try {
            OkHttpClient client = new OkHttpClient();

            // Create request payload
            JSONObject json = new JSONObject();
            json.put("prompt", input);
            json.put("model", "claude-v1");
            json.put("max_tokens_to_sample", 150);

            // Build the request
            Request request = new Request.Builder()
                    .url(API_URL)
                    .header("Authorization", "Bearer " + API_KEY)
                    .post(RequestBody.create(json.toString(), MediaType.get("application/json")))
                    .build();

            // Execute and handle the response
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new Exception("Error: " + response.code());
            JSONObject jsonResponse = new JSONObject(response.body().string());
            return jsonResponse.getString("completion").trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing Claude API request.";
        }
    }
                                             }
