package com.branch.www.screencapture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 *
 */
public class PreviewPictureActivity extends FragmentActivity implements GlobalScreenshot.onScreenShotListener {

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, PreviewPictureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    private ImageView mPreviewImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preview_layout);
        mPreviewImageView = (ImageView) findViewById(R.id.preview_image);
        GlobalScreenshot screenshot = new GlobalScreenshot(getApplicationContext());
        Bitmap bitmap = ((ScreenCaptureApplication) getApplication()).getCaptureData().first;

        Log.e("ryze", "预览图片");
        mPreviewImageView.setImageBitmap(bitmap);
        mPreviewImageView.setVisibility(View.GONE);
        if (bitmap != null) {
            screenshot.takeScreenshot(bitmap, this, true, true);
        }
    }

    @Override
    public void onStartShot() {

    }

    @Override
    public void onFinishShot(boolean success) {
        mPreviewImageView.setVisibility(View.VISIBLE);
        TextView textView = (TextView) findViewById(R.id.send);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setType("image/jpg");
                intent.setType("text/*");
                intent.putExtra(Intent.EXTRA_STREAM,
                        Uri.fromFile(new File(((ScreenCaptureApplication) getApplication()).getCaptureData().second)));
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享截图");
                intent.putExtra(Intent.EXTRA_TEXT, "分享截图");
                startActivity(Intent.createChooser(intent, getApplication().getString(R.string.app_name)));
            }
        });
    }
}
