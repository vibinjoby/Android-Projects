package apiService;


import apiDataModel.ChatbotRequest;
import apiDataModel.ChatbotResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ChatbotApiService {

    @Headers({"Authorization: Bearer 39bf34a5d1fe44238825b43b0544e813"})
    @POST("query")
    Call<ChatbotResponse> postChatRequest(@Query("v") String vid, @Body ChatbotRequest post);

}
