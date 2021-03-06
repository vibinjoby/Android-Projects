package com.example.chatbotapi;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import apiDataModel.ChatMessages;
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

        final List<ChatMessages> chatResponseLst = new ArrayList<>();
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

        final Button sendBtn = (Button) findViewById(R.id.sndBtn);

        requestTxt.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            sendBtn.performClick();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!requestTxt.getText().toString().isEmpty()) {

                    ChatMessages reqMsg = new ChatMessages();
                    reqMsg.setSide(1);
                    reqMsg.setMessage(requestTxt.getText().toString());
                    chatResponseLst.add(reqMsg);
                    chatAdapter.notifyItemChanged(chatAdapter.getItemCount() - 1);


                    ChatbotRequest request = new ChatbotRequest();
                    request.setLang("en");
                    request.setQuery(requestTxt.getText().toString());
                    request.setSessionId("12345");
                    ChatMessages respMsg = new ChatMessages();
                    sendPost(request, respMsg);
                    chatResponseLst.add(respMsg);
                    requestTxt.setText("");
                }
            }
        });
    }

    public ChatbotResponse sendPost(ChatbotRequest request,final ChatMessages respMsg ) {
        final ChatbotResponse chatbotResponse = new ChatbotResponse();
        chatService.postChatRequest("20191129", request).enqueue(new Callback<ChatbotResponse>() {
            @Override
            public void onResponse(Call<ChatbotResponse> call, Response<ChatbotResponse> response) {

                if (response.isSuccessful()) {
                    System.out.println(response.body().getResult());
                    respMsg.setSide(2);
                    respMsg.setMessage(response.body().getResult().getFulfillment().getSpeech());
                    chatbotResponse.setResult(response.body().getResult());
                    chatAdapter.notifyItemChanged(chatAdapter.getItemCount() - 1);
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
