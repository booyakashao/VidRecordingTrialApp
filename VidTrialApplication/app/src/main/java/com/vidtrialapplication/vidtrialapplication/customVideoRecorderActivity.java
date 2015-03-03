package com.vidtrialapplication.vidtrialapplication;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.vidtrialapplication.CameraLayouts.CameraPreview;
import com.vidtrialapplication.onclickutils.VidUtilsMisscelaneous;


public class CustomVideoRecorderActivity extends ActionBarActivity {

    private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_video_recorder);

        /*
        // Create an instance of camera
        mCamera = VidUtilsMisscelaneous.getCameraInstance();

        if(mCamera == null) {
            Log.e("CustomVideoRecorder", "Camera Instance is null");
        } else {
            // Create preview of the camera
            mPreview = new CameraPreview(this, mCamera);
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(mPreview);
        }
        */

        VidUtilsMisscelaneous.getCameraInstance(this.getApplicationContext());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_video_recorder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
