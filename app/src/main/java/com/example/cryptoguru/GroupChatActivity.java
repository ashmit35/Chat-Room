package com.example.cryptoguru;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cryptoguru.Adapters.ChatAdapter;
import com.example.cryptoguru.Models.MessagesModel;
import com.example.cryptoguru.databinding.ActivityChatDetailBinding;
import com.example.cryptoguru.databinding.ActivityGroupChatBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class GroupChatActivity extends AppCompatActivity {

    ActivityGroupChatBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(GroupChatActivity.this, MainActivity.class);
                startActivity(inten);
                finish();
            }
        });

        final ArrayList<MessagesModel> messagesModels = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final String senderId = FirebaseAuth.getInstance().getUid();
        binding.userName.setText("Group Chat");
        final ChatAdapter adapter = new ChatAdapter(messagesModels,GroupChatActivity.this);
        binding.chatRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chatRecyclerView.setLayoutManager(layoutManager);

        database.getReference().child("Group Chat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messagesModels.clear();
                for(DataSnapshot i:snapshot.getChildren()){
                    MessagesModel model = i.getValue(MessagesModel.class);
                    messagesModels.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message  = binding.sendtext.getText().toString();
                final MessagesModel model = new MessagesModel(senderId,message);
                model.setTimeStamp(new Date().getTime());
                binding.sendtext.setText("");

                database.getReference().child("Group Chat")
                        .push()
                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //no more code needed as for now
                    }
                });
            }
        });
    }
}