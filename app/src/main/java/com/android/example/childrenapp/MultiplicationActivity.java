package com.android.example.childrenapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MultiplicationActivity extends AppCompatActivity {

    private TextView num1TextView;
    private TextView num2TextView;
    private TextView resultTextView;
    private EditText answerEditText;
    private Button multiplyButton;
    private Button checkButton;

    private int num1;
    private int num2;
    private int correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);

        num1TextView = findViewById(R.id.num1TextView);
        num2TextView = findViewById(R.id.num2TextView);
        resultTextView = findViewById(R.id.resultTextView);
        answerEditText = findViewById(R.id.answerEditText);
        multiplyButton = findViewById(R.id.multiplyButton);
        checkButton = findViewById(R.id.checkButton);

        if (savedInstanceState != null) {
            // Restore the state if available
            num1 = savedInstanceState.getInt("num1");
            num2 = savedInstanceState.getInt("num2");
            correctAnswer = savedInstanceState.getInt("correctAnswer");
            String savedResultText = savedInstanceState.getString("resultText");

            num1TextView.setText(String.valueOf(num1));
            num2TextView.setText(String.valueOf(num2));
            resultTextView.setText(savedResultText);
        } else {
            // Generate new random numbers if no saved state available
            generateRandomNumbers();
        }

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomNumbers();
                answerEditText.setText("");
                resultTextView.setText("");
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current state
        outState.putInt("num1", num1);
        outState.putInt("num2", num2);
        outState.putInt("correctAnswer", correctAnswer);
        outState.putString("resultText", resultTextView.getText().toString());
    }

    private void generateRandomNumbers() {
        Random random = new Random();
        num1 = random.nextInt(9) + 1; // Generate random number between 1 and 9
        num2 = random.nextInt(9) + 1;
        correctAnswer = num1 * num2;

        num1TextView.setText(String.valueOf(num1));
        num2TextView.setText(String.valueOf(num2));
    }

    private void checkAnswer() {
        String userAnswer = answerEditText.getText().toString();

        if (userAnswer.isEmpty()) {
            resultTextView.setText(R.string.enter_your_answer);
        } else {
            int answer = Integer.parseInt(userAnswer);

            if (answer == correctAnswer) {
                resultTextView.setText(R.string.correct);
                generateRandomNumbers();
                answerEditText.setText("");
            } else {
                String temp = String.valueOf(correctAnswer);
                resultTextView.setText(R.string.incorrect_guess);
                resultTextView.append(" ");
                resultTextView.append(temp);
                generateRandomNumbers();
                answerEditText.setText("");
            }
        }
    }
}
