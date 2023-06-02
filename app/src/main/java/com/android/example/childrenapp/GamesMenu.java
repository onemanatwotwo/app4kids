package com.android.example.childrenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GamesMenu extends AppCompatActivity {

    private Button multiplyButton;
    private Button eyesButton;
    private Button daysMonthsButton;
    private Button memoryTestButton;
    private Button SeasonsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games_menu);

        multiplyButton = findViewById(R.id.multiplyButton);
        eyesButton = findViewById(R.id.eyesButton);
        daysMonthsButton = findViewById(R.id.daysMonthsButton);
        memoryTestButton = findViewById(R.id.memoryTestButton);
        SeasonsButton = findViewById(R.id.SeasonsButton);

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamesMenu.this, MultiplicationActivity.class);
                startActivity(intent);
            }
        });

        eyesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamesMenu.this, BallActivity.class);
                startActivity(intent);
            }
        });

        daysMonthsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamesMenu.this, CountingActivity.class);
                startActivity(intent);
            }
        });

        memoryTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamesMenu.this, MemoryActivity.class);
                startActivity(intent);
            }
        });
        SeasonsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamesMenu.this, SeasonGuessingActivity.class);
                startActivity(intent);
            }
        });
    }
}

