package com.android.example.childrenapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginSignupActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signupButton;

    private DatabaseHelper databaseHelper;
    private boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_signup_screen);

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);
        ImageButton en= findViewById(R.id.enBtn);
        ImageButton tr= findViewById(R.id.trBtn);
        LanguageManager lang= new LanguageManager(this);
        en.setOnClickListener(view->{
            lang.updateResource("en");
            recreate();
        });
        tr.setOnClickListener(view->{
            lang.updateResource("tr");
            recreate();
        });

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String suc=getResources().getString(R.string.loginsucc);
                String unsuc=getResources().getString(R.string.loginunsucc);

                if (validateInput(username, password)) {
                    if (loginUser(username, password)) {
                        // User logged in successfully
                        Toast.makeText(LoginSignupActivity.this, suc, Toast.LENGTH_LONG).show();
                        isLoggedIn = true;
                        navigateToMainMenu();
                    } else {
                        // Invalid login credentials
                        Toast.makeText(LoginSignupActivity.this, unsuc, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String suc=getResources().getString(R.string.signsucc);
                String unsuc=getResources().getString(R.string.signunsucc);

                if (validateInput(username, password)) {
                    if (signupUser(username, password)) {
                        // User signed up and logged in successfully
                        Toast.makeText(LoginSignupActivity.this, suc, Toast.LENGTH_LONG).show();
                        isLoggedIn = true;
                        navigateToMainMenu();
                    } else {
                        // Signup failed (username already exists)
                        Toast.makeText(LoginSignupActivity.this, unsuc, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isLoggedIn) {
            navigateToMainMenu();
        }
    }

    private boolean validateInput(String username, String password) {
        // Perform input validation
        if (username.isEmpty() || password.isEmpty()) {
            String suc=getResources().getString(R.string.enteruspas);
            Toast.makeText(LoginSignupActivity.this, suc, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean loginUser(String username, String password) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String selection = "username = ? AND password = ?";
        String[] selectionArgs = {username, password};

        int count = db.query("users", null, selection, selectionArgs, null, null, null).getCount();

        return count > 0;
    }

    private boolean signupUser(String username, String password) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);

        long result = db.insert("users", null, values);

        return result != -1;
    }

    private void navigateToMainMenu() {
        Intent intent = new Intent(LoginSignupActivity.this, MainMenu.class);
        startActivity(intent);
        finish();
    }
}




