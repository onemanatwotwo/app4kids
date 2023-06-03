package com.android.example.childrenapp;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BallView extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean isRunning = false;
    private int screenWidth;
    private int screenHeight;
    private float ballX=0;
    private float ballY=0;
    private float ballSpeedX = 10;
    private float ballSpeedY = 0;
    private float ballRadius = 50;
    private Paint ballPaint;

    public BallView(Context context, int screenWidth, int screenHeight) {
        super(context);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        // Set up the ball paint
        ballPaint = new Paint();
        ballPaint.setColor(Color.RED);

        // Set initial ball position
        ballX = ballRadius;
        ballY = 0;
    }

    @Override
    public void run() {
        while (isRunning) {
            if (!getHolder().getSurface().isValid()) {
                continue;
            }

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawColor(Color.WHITE);
            canvas.drawCircle(ballX, ballY, ballRadius, ballPaint);
            getHolder().unlockCanvasAndPost(canvas);

            // Update ball position
            ballX += ballSpeedX;
            ballY += ballSpeedY;

            // Reverse ball direction if it hits the screen edges
            if (ballX > screenWidth - ballRadius || ballX < ballRadius) {
                ballSpeedX *= -1;
                if(ballX > screenWidth - ballRadius)
                    ballSpeedY = (float) (Math.tan(Math.toRadians(-10)) * ballSpeedX);
                if(ballX < ballRadius)
                    ballSpeedY=0;
            }
            if (ballY > screenHeight - ballRadius || ballY < ballRadius) {
                ballX=50;
                ballY=50;
                ballSpeedY=0;
                ballSpeedX=10;
            }

            // Delay for smooth animation
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void resume() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        isRunning = false;
        while (true) {
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
