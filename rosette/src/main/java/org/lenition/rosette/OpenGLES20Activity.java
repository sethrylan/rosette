package org.lenition.rosette;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import org.lenition.rosette.render.GL20Renderer;
import org.lenition.rosette.shapes.Square;
import org.lenition.rosette.shapes.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLES20Activity extends Activity {

    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }


    class MyGLSurfaceView extends GLSurfaceView {

        public MyGLSurfaceView(Context context){
            super(context);
            setEGLContextClientVersion(2);

            // this cause a nullpointerexception
//            setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

            // Set the Renderer for drawing on the GLSurfaceView
            setRenderer(new GL20Renderer());
        }
    }


}
