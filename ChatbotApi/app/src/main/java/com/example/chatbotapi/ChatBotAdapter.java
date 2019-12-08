package com.example.chatbotapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apiDataModel.ChatMessages;

public class ChatBotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ChatMessages> chatResponseModel;
    private static int LEFT_CHAT = 1;
    private static int RIGHT_CHAT = 2;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class LeftChatViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView reqTxtView;


        public LeftChatViewHolder(View v) {
            super(v);
            view = v;
            reqTxtView = (TextView) view.findViewById(R.id.reqTxtView);
        }
    }

    class RightChatViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView respTxtView;


        public RightChatViewHolder(View v) {
            super(v);
            view = v;
            respTxtView = (TextView) view.findViewById(R.id.respTxtView);
        }
    }


    public ChatBotAdapter(List<ChatMessages> chatResponseModel) {
        this.chatResponseModel = chatResponseModel;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v;
        if(viewType == LEFT_CHAT) {
            v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.left_layout, parent, false);
            return new LeftChatViewHolder(v);
        } else {
            v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.right_layout, parent, false);
            return new RightChatViewHolder(v);
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (chatResponseModel.get(position) != null) {
            if ((getItemViewType(position) == LEFT_CHAT)) {
                ((LeftChatViewHolder) holder).reqTxtView.setText(chatResponseModel.get(position).getMessage());
            } else {
                ((RightChatViewHolder) holder).respTxtView.setText(chatResponseModel.get(position).getMessage());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return chatResponseModel.get(position).getSide();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return chatResponseModel.size();
    }
}
