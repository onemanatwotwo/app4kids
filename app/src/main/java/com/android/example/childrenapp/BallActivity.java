package com.android.example.childrenapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.core.content.res.ResourcesCompat;

public class BallActivity extends Activity {
    private BallView ballView;
    private int screenWidth;
    private int screenHeight;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        // Create the ball view
        ballView = new BallView(this, screenWidth, screenHeight);

        RelativeLayout container = findViewById(R.id.container);
        container.addView(ballView);
        back=findViewById(R.id.imageView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BallActivity.this, GamesMenu.class);
                startActivity(intent);
            }
        });






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
