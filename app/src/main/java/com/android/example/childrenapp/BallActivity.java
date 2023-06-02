package com.android.example.childrenapp;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class BallActivity extends Activity {
    private BallView ballView;
    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the screen dimensions
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        // Create the ball view
        ballView = new BallView(this, screenWidth, screenHeight);

        // Set the ball view as the content view of the activity
        setContentView(R.layout.activity_ball);
        FrameLayout container = findViewById(R.id.container);
        container.addView(ballView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ballView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ballView.pause();
    }
}
