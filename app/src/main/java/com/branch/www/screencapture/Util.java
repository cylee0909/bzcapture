package com.branch.www.screencapture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cylee on 2018/6/3.
 */

public class Util {
    public static void breakUpBmp(Bitmap bmp) {
        if (bmp != null) {
            Paint p1 = new Paint();
            Bitmap raw = BitmapFactory.decodeResource(
                    ScreenCaptureApplication.application().getResources(), R.drawable.t);
            Bitmap t = Bitmap.createBitmap(raw.getWidth(), raw.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas tc = new Canvas(t);

            Matrix matrix = new Matrix();
            matrix.postRotate((float) (Math.random() - 0.5) * 90, t.getWidth() / 2f, t.getHeight() / 2f);
            tc.drawBitmap(raw, matrix, null);


            p1.setTextSize(48);
            p1.setStrokeWidth(1);
            p1.setStyle(Paint.Style.STROKE);
            p1.setColor(0xFFFF0000);
            tc.save();
            tc.rotate((float) (Math.random() - 0.5) * 90,t.getWidth() / 2f, t.getHeight() / 2f);
            tc.drawText( new SimpleDateFormat("hhmmss").format(new Date()),
                    (float) Math.random() * 10, t.getHeight() / 2, p1);
            tc.restore();

            Paint paint = new Paint();
            Canvas canvas = new Canvas(bmp);
            paint.setShader(new BitmapShader(t, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
            paint.setAlpha(160);
            canvas.drawRect(0, 0, bmp.getWidth(), bmp.getHeight(), paint);
        }
    }
}
