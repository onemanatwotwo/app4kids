package com.android.example.childrenapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;

public class CountingActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView questionTextView;
    private EditText answerEditText;
    private Button submitButton;
    private int currentDayIndex;
    private int currentMonthIndex;
    private Random random;

    private boolean askedDayQ=false;

    private String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private String[] daysOfWeekTR = {"Pazar", "Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma", "Cumartesi"};
    private String[] monthsOfYear = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    private String[] monthsOfYearTR = {"Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran",
            "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);

        questionTextView = findViewById(R.id.questionTextView);
        answerEditText = findViewById(R.id.answerEditText);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        random = new Random();

        askQuestion();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submitButton) {
            checkAnswer();
        }
    }

    private void askQuestion() {
        int questionType = random.nextInt(2); // Randomly select the question type: 0 for day, 1 for month

        if (questionType == 0) {
            askDayQuestion();
            askedDayQ=true;
        } else {
            askMonthQuestion();
        }
    }

    private void askDayQuestion() {
        int randomIndex = random.nextInt(daysOfWeek.length);
        String randomDay = "";
        if(Locale.getDefault().getDisplayLanguage().equals("English"))
            randomDay = daysOfWeek[randomIndex];
        else{
            randomDay = daysOfWeekTR[randomIndex];
        }
        questionTextView.setText(R.string.questionDay);
        questionTextView.append(" "+randomDay+"?");
        answerEditText.setText("");
        currentDayIndex = randomIndex;
    }

    private void askMonthQuestion() {
        int randomIndex = random.nextInt(monthsOfYear.length);
        String randomMonth = "";
        if(Locale.getDefault().getDisplayLanguage().equals("English"))
            randomMonth = monthsOfYear[randomIndex];
        else{
            randomMonth = monthsOfYearTR[randomIndex];
        }
        questionTextView.setText(R.string.questionMonth);
        questionTextView.append(" "+randomMonth+"?");
        answerEditText.setText("");
        currentMonthIndex = randomIndex;
    }

    private int incorrectAttempts = 0;

    private void checkAnswer() {
        String userAnswer = answerEditText.getText().toString().trim();
        if(Locale.getDefault().getDisplayLanguage().equals("English")){
            if (askedDayQ) {
                int nextDayIndex = (currentDayIndex + 1) % daysOfWeek.length;
                String correctAnswer = daysOfWeek[nextDayIndex];

                if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                    Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
                    askedDayQ=false;
                    askQuestion();
                    incorrectAttempts = 0; // Reset incorrect attempts
                } else {
                    String hint = getString(R.string.hintDay) +" "+ daysOfWeek[nextDayIndex].substring(0, 2);
                    if (incorrectAttempts == 0) {
                        Toast.makeText(this, getString(R.string.incorrect) + hint, Toast.LENGTH_SHORT).show();
                        incorrectAttempts++;
                    } else {
                        Toast.makeText(this, getString(R.string.incorrect_dayis) + daysOfWeek[nextDayIndex] + ".", Toast.LENGTH_SHORT).show();
                        askedDayQ=false;
                        askQuestion();
                        incorrectAttempts = 0; // Reset incorrect attempts
                    }
                }
            } else {
                int nextMonthIndex = (currentMonthIndex + 1) % monthsOfYear.length;
                String correctAnswer = monthsOfYear[nextMonthIndex];

                if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                    Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
                    askQuestion();
                    incorrectAttempts = 0; // Reset incorrect attempts
                } else {
                    String hint = getString(R.string.hintMonth) + monthsOfYear[nextMonthIndex].substring(0, 2);
                    if (incorrectAttempts == 0) {
                        Toast.makeText(this, getString(R.string.incorrect) + hint, Toast.LENGTH_SHORT).show();
                        incorrectAttempts++;
                    }
                    else {
                        Toast.makeText(this, getString(R.string.incorrect_monthis) + monthsOfYear[nextMonthIndex] + ".", Toast.LENGTH_SHORT).show();
                        askQuestion();
                        incorrectAttempts = 0; // Reset incorrect attempts
                    }
                }
            }

        }
        else{
            if (askedDayQ) {
                int nextDayIndex = (currentDayIndex + 1) % daysOfWeekTR.length;
                String correctAnswer = daysOfWeekTR[nextDayIndex];

                if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                    Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
                    askedDayQ=false;
                    askQuestion();
                    incorrectAttempts = 0; // Reset incorrect attempts
                } else {
                    String hint = R.string.hintDay + daysOfWeekTR[nextDayIndex].substring(0, 2);
                    if (incorrectAttempts == 0) {
                        Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
                        incorrectAttempts++;
                    } else {
                        Toast.makeText(this, getString(R.string.incorrect_dayis) + daysOfWeekTR[nextDayIndex] + ".", Toast.LENGTH_SHORT).show();
                        askedDayQ=false;
                        askQuestion();
                        incorrectAttempts = 0; // Reset incorrect attempts
                    }
                }
            } else {
                int nextMonthIndex = (currentMonthIndex + 1) % monthsOfYearTR.length;
                String correctAnswer = monthsOfYearTR[nextMonthIndex];

                if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                    Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
                    askQuestion();
                    incorrectAttempts = 0; // Reset incorrect attempts
                } else {
                    String hint = getString(R.string.hintMonth) +" "+ monthsOfYearTR[nextMonthIndex].substring(0, 2);
                    if (incorrectAttempts == 0) {
                        Toast.makeText(this, getString(R.string.incorrect) + hint, Toast.LENGTH_SHORT).show();
                        incorrectAttempts++;
                    }
                    else {
                        Toast.makeText(this, getString(R.string.incorrect_monthis) + monthsOfYearTR[nextMonthIndex] + ".", Toast.LENGTH_SHORT).show();
                        askQuestion();
                        incorrectAttempts = 0; // Reset incorrect attempts
                    }
                }
            }

        }
    }



}
