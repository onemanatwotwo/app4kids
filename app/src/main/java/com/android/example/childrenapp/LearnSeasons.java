package com.android.example.childrenapp;

import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class LearnSeasons extends AppCompatActivity {
    private ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_seasons);
        backButton = findViewById(R.id.backImage);
        backButton.setOnClickListener(new View.OnClickListener() {
            Animation animation= AnimationUtils.loadAnimation(LearnSeasons.this,R.anim.button_animation);
            @Override
            public void onClick(View v) {
                backButton.startAnimation(animation);
                Intent intent = new Intent(LearnSeasons.this, Learn.class);
                startActivity(intent);
            }
        });
    }

}
