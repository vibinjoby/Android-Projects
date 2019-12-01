package com.example.chatbotapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apiDataModel.ChatbotResponse;

public class ChatBotAdapter extends RecyclerView.Adapter<ChatBotAdapter.ChatbotViewHolder> {
    private List<ChatbotResponse> chatResponseModel;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ChatbotViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView reqTxtView;
        public TextView respTxtView;


        public ChatbotViewHolder(View v) {
            super(v);
            view = v;
            reqTxtView = (TextView) view.findViewById(R.id.reqTxtView);
            respTxtView = (TextView) view.findViewById(R.id.respTxtView);
        }
    }


    public ChatBotAdapter(List<ChatbotResponse> quizPojoModel) {
        chatResponseModel = quizPojoModel;
    }


    @Override
    public ChatBotAdapter.ChatbotViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_layout, parent, false);
        ChatbotViewHolder vh = new ChatbotViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ChatbotViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (chatResponseModel.get(position).getResult() != null) {
            holder.reqTxtView.setText(chatResponseModel.get(position).getResult().getResolvedQuery());
            holder.respTxtView.setText(chatResponseModel.get(position).getResult().getFulfillment().getSpeech());
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return chatResponseModel.size();
    }
}
