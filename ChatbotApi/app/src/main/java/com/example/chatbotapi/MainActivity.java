package com.example.chatbotapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import apiDataModel.ChatbotRequest;
import apiDataModel.ChatbotResponse;
import apiHelper.ChatbotApiHelper;
import apiService.ChatbotApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ChatbotApiService chatService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter chatAdapter;
    private RecyclerView.LayoutManager layoutManager;
    EditText requestTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestTxt = (EditText) findViewById(R.id.sendTxt);

        recyclerView = (RecyclerView) findViewById(R.id.chatView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(false);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        chatService = ChatbotApiHelper.getAPIService();

        final List<ChatbotResponse> chatResponseLst = new ArrayList<>();
        chatAdapter = new ChatBotAdapter(chatResponseLst);
        recyclerView.setAdapter(chatAdapter);

        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v,
                                       int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(recyclerView.getAdapter().getItemCount() > 0) {
                                recyclerView.smoothScrollToPosition(
                                        recyclerView.getAdapter().getItemCount() - 1);
                            }
                        }
                    }, 100);
            }
        });

        Button sendBtn = (Button) findViewById(R.id.sndBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!requestTxt.getText().toString().isEmpty()) {
                    ChatbotRequest request = new ChatbotRequest();
                    request.setLang("en");
                    request.setQuery(requestTxt.getText().toString());
                    request.setSessionId("12345");
                    chatResponseLst.add(sendPost(request));
                    requestTxt.setText("");
                }
            }
        });
    }

    public ChatbotResponse sendPost(ChatbotRequest request) {
        final ChatbotResponse chatbotResponse = new ChatbotResponse();
        chatService.postChatRequest("20191129", request).enqueue(new Callback<ChatbotResponse>() {
            @Override
            public void onResponse(Call<ChatbotResponse> call, Response<ChatbotResponse> response) {

                if (response.isSuccessful()) {
                    System.out.println(response.body().getResult());
                    chatbotResponse.setResult(response.body().getResult());
                    chatAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ChatbotResponse> call, Throwable t) {
                System.out.println("the request is " + call.request());
                System.out.println(t.getMessage());
            }
        });
        System.out.println("updated chat response is "+chatbotResponse);
        return chatbotResponse;
    }
}
