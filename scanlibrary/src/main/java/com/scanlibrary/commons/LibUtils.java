package com.scanlibrary.commons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * <p>Lib Utils</p>
 * Used to global functions of the app.
 */
public class LibUtils {

    public interface ScannConstants {
        int PICKFILE_REQUEST_CODE = 1;
        int START_CAMERA_REQUEST_CODE = 2;
        String OPEN_INTENT_PREFERENCE = "selectContent";
        String IMAGE_BASE_PATH_EXTRA = "ImageBasePath";
        int OPEN_CAMERA = 4;
        int OPEN_MEDIA = 5;
        String SCANNED_RESULT = "scannedResult";
        String IMAGE_PATH = Environment
                .getExternalStorageDirectory().getPath() + "/scanSample";
        String SELECTED_BITMAP = "selectedBitmap";
    }

    public static boolean isDarkColor(String colorAsString) {
        int color = Color.parseColor(colorAsString);
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return !(darkness < 0.4); // It's a light color
    }

    public static Uri getUri(Context context, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap,
                Long.toString(Calendar.getInstance().getTimeInMillis()), null);
        return Uri.parse(path);
    }

    public static Bitmap getBitmap(Context context, Uri uri) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        return bitmap;
    }
}