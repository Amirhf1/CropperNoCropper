package com.fenchtose.nocropper;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Jay Rambhia on 11/2/2015.
 */
public class BitmapUtils {

    public static Bitmap addPadding(Bitmap bmp, int color) throws OutOfMemoryError {

        if (bmp == null) {
            return null;
        }

        Bitmap bitmap = null;

        try {
            int biggerParam = Math.max(bmp.getWidth(), bmp.getHeight());
            bitmap = Bitmap.createBitmap(biggerParam, biggerParam, bmp.getConfig());
            Canvas canvas = new Canvas(bitmap);
            canvas.drawColor(color);

            int top = bmp.getHeight() > bmp.getWidth() ? 0 : (bmp.getWidth() - bmp.getHeight()) / 2;
            int left = bmp.getWidth() > bmp.getHeight() ? 0 : (bmp.getHeight() - bmp.getWidth()) / 2;

            canvas.drawBitmap(bmp, left, top, null);
            return bitmap;
        } catch (OutOfMemoryError e) {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null;
            }

            throw e;
        }
    }

    public static Bitmap addPadding(Bitmap bmp, CropInfo info, int color) throws OutOfMemoryError {

        if (bmp == null) {
            return null;
        }

        Bitmap bitmap = null;

        try {
            int biggerParam = Math.max(info.width + 2*info.horizontalPadding, info.height + 2*info.verticalPadding);
            bitmap = Bitmap.createBitmap(biggerParam, biggerParam, bmp.getConfig());
            Canvas canvas = new Canvas(bitmap);
            canvas.drawColor(color);

            canvas.drawBitmap(bmp, info.horizontalPadding, info.verticalPadding, null);
            return bitmap;
        } catch (OutOfMemoryError e) {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null;
            }

            throw e;
        }
    }

}
