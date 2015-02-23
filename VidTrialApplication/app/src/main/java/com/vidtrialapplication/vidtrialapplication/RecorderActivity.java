package com.vidtrialapplication.vidtrialapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.VideoView;

import com.vidtrialapplication.onclickutils.VidUtilsMisscelaneous;

import java.io.File;


public class RecorderActivity extends ActionBarActivity {

    static final int REQUEST_VIDEO_CAPTURE = 1;
    VideoView videoView;
    private Uri videoUri;

    public void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        videoUri = VidUtilsMisscelaneous.getOutputMediaFileUri(VidUtilsMisscelaneous.MEDIA_TYPE_VIDEO);

        takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
        takeVideoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            videoUri = data.getData();

            Toast uriToast = Toast.makeText(this.getApplicationContext(), videoUri.getEncodedPath(), Toast.LENGTH_LONG);
            uriToast.show();

            videoView.setVideoURI(videoUri);


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);
        videoView = (VideoView) findViewById(R.id.videoView);
        dispatchTakeVideoIntent();
        videoView.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recorder, menu);
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


    @Override
    public void onBackPressed() {
        File toDeleteVideo = new File(videoUri.getEncodedPath());
        if(toDeleteVideo.delete()) {
            Toast deleteToast = Toast.makeText(this, videoUri.getEncodedPath() + " deleted successfully", Toast.LENGTH_SHORT);
            deleteToast.show();
        } else {
            Toast deleteToast = Toast.makeText(this, videoUri.getEncodedPath() + " failed delete", Toast.LENGTH_SHORT);
            deleteToast.show();
        }
        super.onBackPressed();
    }

}
