package com.android.example.childrenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.example.childrenapp.SeasonGuessingActivity;
import com.android.example.childrenapp.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int seasonTracker=1;
    private ImageView seasonImageView;
    private TextView seasonTextView;
    private Animation fadeInAnimation;
    private Animation fadeOutAnimation;

    private enum Season {
        AUTUMN,
        WINTER,
        SUMMER,
        SPRING
    }

    private Season currentSeason = Season.SPRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        seasonImageView = findViewById(R.id.seasonImageView);
        seasonTextView = findViewById(R.id.seasonTextView);


        // Load animations
        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        // Set initial season
        setSeason();
    }

    private void setSeason() {
        // Set image and text based on the current season
        switch (currentSeason) {
            case AUTUMN:
                seasonImageView.setImageResource(R.drawable.autumn_image);
                seasonTextView.setText(getString(R.string.autumn_text));
                currentSeason = Season.WINTER;
                seasonTracker++;
                break;
            case WINTER:
                seasonImageView.setImageResource(R.drawable.winter_image);
                seasonTextView.setText(getString(R.string.winter_text));
                currentSeason = Season.SUMMER;
                seasonTracker++;// Update current season to Summer
                break;
            case SUMMER:
                seasonImageView.setImageResource(R.drawable.summer_image);
                seasonTextView.setText(getString(R.string.summer_text));
                currentSeason = Season.SPRING;
                seasonTracker++;// Update current season to Spring
                break;
            case SPRING:
                seasonImageView.setImageResource(R.drawable.spring_image);
                seasonTextView.setText(getString(R.string.spring_text));
                currentSeason = Season.AUTUMN;
                seasonTracker++;// Update current season to Autumn
                break;
        }

        // Apply animations
        seasonImageView.startAnimation(fadeInAnimation);
        seasonTextView.startAnimation(fadeInAnimation);

        // Delay for 3 seconds and then fade out
        seasonImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(seasonTracker<4)
                    setSeason();
                else{
                    Intent intent = new Intent(MainActivity.this, SeasonGuessingActivity.class);
                    startActivity(intent);
                }
            }
        },3000);

    }
}
