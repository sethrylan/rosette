package org.lenition.rosette.instrumented;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import junit.framework.Assert;

import org.lenition.rosette.MainActivity;

@SmallTest
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private static final String TAG = "MainActivityTest";
    private MainActivity mAct;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        Log.v(TAG, "Setting up.");
        super.setUp();
        mAct = getActivity();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    }

    @Override
    public void tearDown() throws Exception {
        mAct.finish();
        super.tearDown();
    }

    public void test() {
        Assert.assertNotNull(mAct);
        Assert.assertEquals("Rosette", mAct.getTitle());
    }

}
