package com.android.example.childrenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SeasonGuessingActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button submitButton;
    private Button quitButton; // New quit button

    private String[] seasons = {"summer", "winter", "spring", "autumn"};
    private int[] images = {R.drawable.summer_image, R.drawable.winter_image,
            R.drawable.spring_image, R.drawable.autumn_image};

    private String correctSeason;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_guessing);

        imageView = findViewById(R.id.imageView);
        submitButton = findViewById(R.id.submitButton);
        quitButton = findViewById(R.id.quitButton); // Initialize the quit button

        displayRandomImage();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnotherActivity();
            }
        });
    }

    private void displayRandomImage() {
        int randomIndex = random.nextInt(images.length);
        imageView.setImageResource(images[randomIndex]);
        correctSeason = seasons[randomIndex];
    }

    private void checkGuess() {
        String userGuess = ((EditText) findViewById(R.id.guessEditText)).getText().toString().toLowerCase();

        if (userGuess.equals(correctSeason)) {
            View correctFeedbackView = findViewById(R.id.correctFeedbackView);
            correctFeedbackView.setVisibility(View.VISIBLE);
            Animation correctAnimation = AnimationUtils.loadAnimation(this, R.anim.correct_animation);
            correctFeedbackView.startAnimation(correctAnimation);
        } else {
            View incorrectFeedbackView = findViewById(R.id.incorrectFeedbackView);
            incorrectFeedbackView.setVisibility(View.VISIBLE);
            Animation incorrectAnimation = AnimationUtils.loadAnimation(this, R.anim.incorrect_animation);
            incorrectFeedbackView.startAnimation(incorrectAnimation);
        }

        displayRandomImage();
    }

    private void startAnotherActivity() {
        Intent intent = new Intent(this, GamesMenu.class);
        startActivity(intent);
    }
}
