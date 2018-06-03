package com.branch.www.screencapture;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.Pair;

/**
 * Created by Ryze on 2016-7-20.
 */
public class ScreenCaptureApplication extends Application {

    private static Context sContext;

    private Pair<Bitmap, String> captureData;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context application() {
        return sContext;
    }


    public Pair<Bitmap, String> getCaptureData() {
        return captureData;
    }

    public void setCaptureData(Pair<Bitmap, String> captureData) {
        this.captureData = captureData;
    }
}
