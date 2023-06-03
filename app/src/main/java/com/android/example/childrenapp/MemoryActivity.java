package com.android.example.childrenapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MemoryActivity extends AppCompatActivity {

    private TextView digitTextView;
    private EditText inputEditText;
    private Button forwardButton;
    private Button reverseButton;
    private List<Integer> forwardDigitList;
    private List<Integer> reverseDigitList;
    private boolean isShowingForward;
    private int currentIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.button_animation);

        digitTextView = findViewById(R.id.digitTextView);
        inputEditText = findViewById(R.id.inputEditText);
        forwardButton = findViewById(R.id.forwardButton);
        reverseButton = findViewById(R.id.reverseButton);

        forwardDigitList = new ArrayList<>();
        reverseDigitList = new ArrayList<>();


        reverseDigitList = generateRandomDigitList();
        forwardDigitList = generateRandomDigitList();
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setBackgroundColor(getResources().getColor(R.color.memoryback));
        submitButton.setTextColor(Color.BLACK);
        forwardButton.setBackgroundColor(getResources().getColor(R.color.memoryback));
        forwardButton.setTextColor(Color.BLACK);
        reverseButton.setBackgroundColor(getResources().getColor(R.color.memoryback));
        reverseButton.setTextColor(Color.BLACK);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButton.startAnimation(animation);
                String userInput = inputEditText.getText().toString().trim();
                try {
                    checkInput(isShowingForward ? forwardDigitList : reverseDigitList, userInput);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                enableInput();
                reverseButton.setEnabled(true);
                forwardButton.setEnabled(true);
                inputEditText.setText("");
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                forwardButton.startAnimation(animation);
                forwardButton.setEnabled(false);
                reverseButton.setEnabled(false);
                inputEditText.setText("");
                isShowingForward = true;
                showDigits(forwardDigitList);
            }
        });

        reverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reverseButton.startAnimation(animation);
                forwardButton.setEnabled(false);
                reverseButton.setEnabled(false);
                inputEditText.setText("");
                isShowingForward = false;
                showDigits(reverseDigitList);
            }
        });
    }

    private void showDigits(final List<Integer> digits) {
        currentIndex = 0;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentIndex < digits.size()) {
                    digitTextView.setText(String.valueOf(digits.get(currentIndex)));
                    currentIndex++;
                    handler.postDelayed(this, 1000); // Display each digit for 1 second
                } else {
                    digitTextView.setText("");
                    enableInput();
                }
            }
        }, 1000); // Start after a 1-second delay
    }

    private void enableInput() {
        inputEditText.setEnabled(true);
        inputEditText.requestFocus();
    }

    private void disableInput() {
        inputEditText.setEnabled(false);
    }

    private void checkInput(List<Integer> digits, String userInput) throws InterruptedException {
        if(isShowingForward){
            String temp="";
            for (int i=0;i<3;i++){
            temp=temp+(digits.get(i));
            }
            if(temp.equals(userInput)) {
                showToast(getString(R.string.welldone));
                forwardDigitList = generateRandomDigitList();
                return;
            }
            showToast(getString(R.string.tryagain));
            Thread.sleep(2000);
            showDigits(digits);
        }
        else{
            String temp="";
            for (int i=2;i>=0;i--){
                temp=temp+(digits.get(i));
            }
            if(temp.equals(userInput)) {
                showToast(getString(R.string.welldone));
                reverseDigitList = generateRandomDigitList();
                return;
            }
            showToast(getString(R.string.tryagain));
            Thread.sleep(2000);
            showDigits(digits);
        }


    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private List<Integer> generateRandomDigitList() {
        List<Integer> digitList = new ArrayList<>();
        Random random = new Random();
        int temp;
        for (int i = 0; i <3; i++) {
            temp=random.nextInt(10);
            if(!digitList.contains(temp))
                digitList.add(temp);
            else
                i--;
        }
        return digitList;
    }

}


