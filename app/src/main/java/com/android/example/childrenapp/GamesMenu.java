package com.android.example.childrenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GamesMenu extends AppCompatActivity {

    private ImageView multiplyButton;
    private ImageView eyesButton;
    private ImageView daysMonthsButton;
    private ImageView memoryTestButton;
    private ImageView SeasonsButton;
    private ImageView backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games_menu);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.button_animation);

        multiplyButton = findViewById(R.id.multiplyImage);
        eyesButton = findViewById(R.id.eyesImage);
        daysMonthsButton = findViewById(R.id.daysmonthsImage);
        memoryTestButton = findViewById(R.id.memoryImage);
        SeasonsButton = findViewById(R.id.seasonImage);
        backButton = findViewById(R.id.backImage);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton.startAnimation(animation);
                Intent intent = new Intent(GamesMenu.this, MainMenu.class);
                startActivity(intent);
            }
        });


        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiplyButton.startAnimation(animation);
                Intent intent = new Intent(GamesMenu.this, MultiplicationActivity.class);
                startActivity(intent);
            }
        });

        eyesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eyesButton.startAnimation(animation);
                Intent intent = new Intent(GamesMenu.this, BallActivity.class);
                startActivity(intent);
            }
        });

        daysMonthsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daysMonthsButton.startAnimation(animation);
                Intent intent = new Intent(GamesMenu.this, CountingActivity.class);
                startActivity(intent);
            }
        });

        memoryTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoryTestButton.startAnimation(animation);
                Intent intent = new Intent(GamesMenu.this, MemoryActivity.class);
                startActivity(intent);
            }
        });
        SeasonsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeasonsButton.startAnimation(animation);
                Intent intent = new Intent(GamesMenu.this, SeasonGuessingActivity.class);
                startActivity(intent);
            }
        });
    }
}

