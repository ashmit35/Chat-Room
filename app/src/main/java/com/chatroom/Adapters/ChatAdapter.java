package com.chatroom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chatroom.Models.MessagesModel;
import com.chatroom.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    private ArrayList<MessagesModel> messagesModels;
    private Context context;
    private final int SENDER_VIEW_TYPE = 1;
    private final int RECEIVER_VIEW_TYPE = 2;
    private String receiverId;

    public ChatAdapter(ArrayList<MessagesModel> messagesModels,Context context){
        this.messagesModels = messagesModels;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        try{
            if(messagesModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
                return SENDER_VIEW_TYPE;
            }else{
                return RECEIVER_VIEW_TYPE;
            }
        }catch (Exception e){
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new SenderViewHolder(view);
        }

        else{
            View view = LayoutInflater.from(context).inflate(R.layout.sample_receiver,parent,false);
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessagesModel messagesModel = messagesModels.get(position);
        if(holder.getClass()==SenderViewHolder.class){
            ((SenderViewHolder) holder).senderMsg.setText(messagesModel.getMessage());
        }
        else{
            ((ReceiverViewHolder)holder).receiverMsg.setText(messagesModel.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messagesModels.size();
    }
    // there will be 2 viewHolder because there is sender and receiver message

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{
        private TextView receiverMsg;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverMsg = itemView.findViewById(R.id.receiverText);
        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder{
        private TextView senderMsg;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.senderText);
        }
    }

}
