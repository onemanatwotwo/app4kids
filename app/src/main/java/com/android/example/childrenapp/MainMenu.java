package com.android.example.childrenapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    private Button learnButton;
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        learnButton = findViewById(R.id.learnButton);
        playButton = findViewById(R.id.playButton);

        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Learn button click
                // Add your code here to navigate to the Learn activity or fragment
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Play button click
                Intent intent = new Intent(MainMenu.this, GamesMenu.class);
                startActivity(intent);
            }
        });
    }
}
