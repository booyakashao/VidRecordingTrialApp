package com.vidtrialapplication.onclickutils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.vidtrialapplication.CameraLayouts.CameraPreview2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wengu on 2/22/15.
 */
public class VidUtilsMisscelaneous {

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final String LogTAG = "VidUtilitiesLog";

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


    public static boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

     //For SDK 21 and higher which means lollipop and higher
    public static void getCameraInstance(Context context) {
        String[] cameraList = new String[1];
        CameraManager manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);


        try {
            cameraList = manager.getCameraIdList();

            for(String id : cameraList) {
                CameraCharacteristics ch = manager.getCameraCharacteristics(id);
                if(ch.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_BACK) {
                    manager.openCamera(id, new CameraPreview2(), null);
                }
            }
        } catch(Exception e) {
            Log.e(LogTAG, "Unable to get camera list");
            Log.e(LogTAG, e.getMessage());
            e.printStackTrace();
        }

        for(String currentCamera : cameraList) {
            Log.i(LogTAG, currentCamera);
        }

    }


    //for all sdk under 21
    public static Camera getCameraInstance() {
        Camera currentCamera = null;

        try {
            currentCamera = Camera.open(0);
        } catch(Exception e) {
            Log.e(LogTAG, "Unable to get camera list");
            Log.e(LogTAG, e.getMessage());
            e.printStackTrace();
        }


        return currentCamera;
    }

    public static Camera.Size getMaxCameraSize(List<Camera.Size> cameraPreviewSizes) {
        Camera.Size largestCameraSize = null;

        for(Camera.Size currentCameraSize : cameraPreviewSizes) {
            if(largestCameraSize == null) {
                largestCameraSize = currentCameraSize;
            } else if(currentCameraSize.width > largestCameraSize.width || currentCameraSize.height > largestCameraSize.height) {
                largestCameraSize = currentCameraSize;
            }
        }

        return largestCameraSize;
    }
}
