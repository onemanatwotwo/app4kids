package com.android.example.childrenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Learn extends AppCompatActivity {

    private ImageView multiplyButton;
    private ImageView daysMonthsButton;
    private ImageView SeasonsButton;
    private ImageView backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_menu);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.button_animation);

        multiplyButton = findViewById(R.id.multiplyImage);
        daysMonthsButton = findViewById(R.id.daysmonthsImage);
        SeasonsButton = findViewById(R.id.seasonImage);
        backButton = findViewById(R.id.backImage);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton.startAnimation(animation);
                Intent intent = new Intent(Learn.this, MainMenu.class);
                startActivity(intent);
            }
        });


        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiplyButton.startAnimation(animation);
                Intent intent = new Intent(Learn.this, LearnMult.class);
                startActivity(intent);
            }
        });


        daysMonthsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daysMonthsButton.startAnimation(animation);
                Intent intent = new Intent(Learn.this, LearnDaysMonths.class);
                startActivity(intent);
            }
        });

        SeasonsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeasonsButton.startAnimation(animation);
                Intent intent = new Intent(Learn.this, LearnSeasons.class);
                startActivity(intent);
            }
        });
    }
}

