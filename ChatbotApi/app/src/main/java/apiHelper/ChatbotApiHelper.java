package apiHelper;

import apiClient.ChatbotClient;
import apiService.ChatbotApiService;

public class ChatbotApiHelper {
    public static final String BASE_URL = "https://api.dialogflow.com/v1/";

    public static ChatbotApiService getAPIService() {

        return ChatbotClient.getClient(BASE_URL).create(ChatbotApiService.class);
    }
}
