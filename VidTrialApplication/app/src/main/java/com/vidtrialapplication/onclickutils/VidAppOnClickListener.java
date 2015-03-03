package com.vidtrialapplication.onclickutils;

import android.content.Intent;
import android.view.View;

import com.vidtrialapplication.vidtrialapplication.CustomVideoRecorderActivity;
import com.vidtrialapplication.vidtrialapplication.MainActivity;
import com.vidtrialapplication.vidtrialapplication.PlaybackActivity;
import com.vidtrialapplication.vidtrialapplication.R;
import com.vidtrialapplication.vidtrialapplication.RecorderActivity;

/**
 * Created by wengu on 2/22/15.
 */
public class VidAppOnClickListener implements View.OnClickListener {

    MainActivity caller;

    public VidAppOnClickListener(MainActivity activity) {
        this.caller = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recordVideo:
                Intent recordVideoIntent = new Intent(caller, RecorderActivity.class);
                caller.startActivity(recordVideoIntent);
                break;
            case R.id.playbackVideo:
                Intent playbackVideoIntent = new Intent(caller, PlaybackActivity.class);
                caller.startActivity(playbackVideoIntent);
                break;
            case R.id.customRecordVideo:
                Intent customVideoRecorderIntent = new Intent(caller, CustomVideoRecorderActivity.class);
                caller.startActivity(customVideoRecorderIntent);
                break;
        }
    }
}
