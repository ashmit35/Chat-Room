package com.example.cryptoguru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(){

            @Override
            public void run() {

                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally{

                    auth = FirebaseAuth.getInstance();

                    if(auth.getCurrentUser()!=null){
                        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent  = new Intent(SplashActivity.this,SignUpActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        };

        thread.start();


    }
}