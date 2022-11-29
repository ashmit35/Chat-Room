package com.example.cryptoguru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cryptoguru.Adapters.FragmentAdapter;
import com.example.cryptoguru.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
// to get SHA-1 .... (./gradlew signingReport) write this on terminal and press ctrl + Enter
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();

        //viewPager
        binding.viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        binding.tablayout.setupWithViewPager(binding.viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cryptogurumenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case (R.id.settings):
                Intent a = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(a);
                break;
            case (R.id.logout):
                Intent intent = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);
                auth.signOut();
                finish();
                break;

            case (R.id.groupChat):
                Intent intent1 = new Intent(MainActivity.this,GroupChatActivity.class);
                startActivity(intent1);
        }
        return true;
    }
}