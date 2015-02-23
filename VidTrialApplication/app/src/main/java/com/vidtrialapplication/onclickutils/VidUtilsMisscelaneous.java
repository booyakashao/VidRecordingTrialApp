package com.vidtrialapplication.onclickutils;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wengu on 2/22/15.
 */
public class VidUtilsMisscelaneous {

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    public static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        String targetDirectory = "null";
        String extension = "null";
        String filePrefix = "null";

        switch(type) {
            case MEDIA_TYPE_IMAGE:
                targetDirectory = Environment.DIRECTORY_PICTURES;
                extension = ".jpg";
                filePrefix = "IMG_";
                break;
            case MEDIA_TYPE_VIDEO:
                targetDirectory = Environment.DIRECTORY_MOVIES;
                extension = ".mp4";
                filePrefix = "VID_";
                break;
        }


        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                targetDirectory), "VidTrialApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("VidTrialApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
            filePrefix + timeStamp + extension);

        return mediaFile;
    }


}
