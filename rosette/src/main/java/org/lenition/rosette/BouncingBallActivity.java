package org.lenition.rosette;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import org.lenition.rosette.view.BouncingBallView;

/**
 * see http://www.ntu.edu.sg/home/ehchua/programming/android/Android_2D.html
 */
public class BouncingBallActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View bouncingBallView = new BouncingBallView(this);
        setContentView(bouncingBallView);
    }
}
