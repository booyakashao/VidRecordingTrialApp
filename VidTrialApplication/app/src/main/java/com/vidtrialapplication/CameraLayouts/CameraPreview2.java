package com.vidtrialapplication.CameraLayouts;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;

/**
 * Created by wengu on 3/3/15.
 */
public class CameraPreview2 extends CameraDevice.StateCallback {

    @Override
    public void onOpened(CameraDevice camera) {
        try {
            CaptureRequest.Builder captureBuilder = camera.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisconnected(CameraDevice camera) {

    }

    @Override
    public void onError(CameraDevice camera, int error) {

    }
}
