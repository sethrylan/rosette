package org.lenition.rosette.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class BouncingBallView extends View {
    private Ball ball;
    private Box box;
    private StatusMessage statusMsg;

    // For touch inputs - previous touch (x, y)
    private float previousX;
    private float previousY;

    // Constructor
    public BouncingBallView(Context context) {
        super(context);

        box = new Box(0xff00003f);  // ARGB
        ball = new Ball(Color.GREEN);
        statusMsg = new StatusMessage(Color.CYAN);

        // To enable keypad
        this.setFocusable(true);
        this.requestFocus();
        // To enable touch mode
        this.setFocusableInTouchMode(true);
    }

    // Called back to draw the view. Also called after invalidate().
    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the components
        box.draw(canvas);
        ball.draw(canvas);
        statusMsg.draw(canvas);

        // Update the position of the ball, including collision detection and reaction.
        ball.moveWithCollisionDetection(box);
        statusMsg.update(ball);

        // Delay
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) { }

        invalidate();  // Force a re-draw
    }

    // Called back when the view is first created or its size changes.
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the ball
        box.set(0, 0, w, h);
    }

    // Key-up event handler
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT: // Increase rightward speed
                ball.speedX++;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:  // Increase leftward speed
                ball.speedX--;
                break;
            case KeyEvent.KEYCODE_DPAD_UP:    // Increase upward speed
                ball.speedY--;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:  // Increase downward speed
                ball.speedY++;
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER: // Stop
                ball.speedX = 0;
                ball.speedY = 0;
                break;
            case KeyEvent.KEYCODE_A:    // Zoom in
                // Max radius is about 90% of half of the smaller dimension
                float maxRadius = (box.xMax > box.yMax) ? box.yMax / 2 * 0.9f  : box.xMax / 2 * 0.9f;
                if (ball.radius < maxRadius) {
                    ball.radius *= 1.05;   // Increase radius by 5%
                }
                break;
            case KeyEvent.KEYCODE_Z:    // Zoom out
                if (ball.radius > 20) {  // Minimum radius
                    ball.radius *= 0.95;  // Decrease radius by 5%
                }
                break;
        }
        return true;  // Event handled
    }

    // Touch-input handler
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        float currentY = event.getY();
        float deltaX, deltaY;
        float scalingFactor = 5.0f / ((box.xMax > box.yMax) ? box.yMax : box.xMax);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                // Modify rotational angles according to movement
                deltaX = currentX - previousX;
                deltaY = currentY - previousY;
                ball.speedX += deltaX * scalingFactor;
                ball.speedY += deltaY * scalingFactor;
        }
        // Save current x, y
        previousX = currentX;
        previousY = currentY;
        return true;  // Event handled
    }
}
