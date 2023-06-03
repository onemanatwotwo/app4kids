package com.android.example.childrenapp;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Resources;
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
    private Button quitButton;
    private Animation fadein;
    private EditText guessEdit;


    private int[] images = {R.drawable.summer1,R.drawable.summer2,R.drawable.summer3,R.drawable.summer4,R.drawable.summer5, R.drawable.winter1,
            R.drawable.winter2,R.drawable.winter3,R.drawable.winter4,R.drawable.winter5,
            R.drawable.spring1,R.drawable.spring2,R.drawable.spring3,R.drawable.spring4,R.drawable.spring5, R.drawable.autumn1,
            R.drawable.autumn2,R.drawable.autumn3,R.drawable.autumn4,R.drawable.autumn5};

    private String correctSeason;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_guessing);
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.button_animation);

        imageView = findViewById(R.id.imageView);
        submitButton = findViewById(R.id.submitButton);
        quitButton = findViewById(R.id.quitButton); // Initialize the quit button


        displayRandomImage();

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                submitButton.startAnimation(animation);
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
        Resources res=getResources();
        String[] seasons = res.getStringArray(R.array.seasons);
        fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        int randomIndex = random.nextInt(20);
        imageView.setImageResource(images[randomIndex]);
        imageView.startAnimation(fadein);
        if(randomIndex<=4)
            correctSeason = seasons[2];
        else if(randomIndex<=9)
            correctSeason=seasons[0];
        else if (randomIndex<=14)
            correctSeason=seasons[3];
        else
            correctSeason=seasons[1];

    }

    private void checkGuess()  {
        String userGuess = ((EditText) findViewById(R.id.guessEditText)).getText().toString().toLowerCase();
        guessEdit=findViewById(R.id.guessEditText);

        if (userGuess.equals(correctSeason)) {
            View incorrectFeedbackView = findViewById(R.id.incorrectView);
            incorrectFeedbackView.setVisibility(View.GONE);
            View correctFeedbackView = findViewById(R.id.animationView);
            correctFeedbackView.setVisibility(View.VISIBLE);
        }
        else {
            View correctFeedbackView = findViewById(R.id.animationView);
            correctFeedbackView.setVisibility(View.GONE);
            View incorrectFeedbackView = findViewById(R.id.incorrectView);
            incorrectFeedbackView.setVisibility(View.VISIBLE);
        }
        guessEdit.setText("");
        displayRandomImage();
    }

    private void startAnotherActivity() {
        Intent intent = new Intent(this, GamesMenu.class);
        startActivity(intent);
    }
}
