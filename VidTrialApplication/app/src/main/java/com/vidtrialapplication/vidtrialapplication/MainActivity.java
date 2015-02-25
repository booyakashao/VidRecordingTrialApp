package com.vidtrialapplication.vidtrialapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vidtrialapplication.onclickutils.VidAppOnClickListener;
import com.vidtrialapplication.onclickutils.VidUtilsMisscelaneous;


public class MainActivity extends ActionBarActivity {

    Button recordVideoButton;
    Button playbackVideoButton;
    TextView missingCameraNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference Buttons
        recordVideoButton = (Button) findViewById(R.id.recordVideo);
        playbackVideoButton = (Button) findViewById(R.id.playbackVideo);

        //Reference TextViews
        missingCameraNotification = (TextView) findViewById(R.id.cameraNotificationMessage);
        missingCameraNotification.setVisibility(View.GONE);


        //Check if there is a camera
        if(VidUtilsMisscelaneous.checkCameraHardware(this)) {

            //Add the proper action listeners
            recordVideoButton.setOnClickListener(new VidAppOnClickListener(this));
            playbackVideoButton.setOnClickListener(new VidAppOnClickListener(this));
        } else {
            recordVideoButton.setVisibility(View.GONE);
            playbackVideoButton.setVisibility(View.GONE);

            missingCameraNotification.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
