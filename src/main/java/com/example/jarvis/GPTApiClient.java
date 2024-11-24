package com.example.jarvis;

import okhttp3.*;
import org.json.JSONObject;

public class GPTApiClient {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "your-openai-api-key"; // Replace with your API key

    public static String getAIResponse(String input) {
        try {
            OkHttpClient client = new OkHttpClient();
            JSONObject payload = new JSONObject();
            payload.put("model", "gpt-4");
            payload.put("messages", new JSONObject[]{new JSONObject().put("role", "user").put("content", input)});
            payload.put("max_tokens", 150);

            RequestBody body = RequestBody.create(payload.toString(), MediaType.get("application/json"));
            Request request = new Request.Builder()
                    .url(API_URL)
                    .header("Authorization", "Bearer " + API_KEY)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new Exception("Error: " + response.code());
            JSONObject jsonResponse = new JSONObject(response.body().string());
            return jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content").trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "I'm sorry, I couldn't process your request.";
        }
    }
              }
