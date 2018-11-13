package com.atimports.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.atimports.R;
import com.atimports.constantes.Constantes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public abstract class PhotoUtils {


    public static String saveImage(Bitmap myBitmap, Context context) throws Exception{

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.PNG, 90, bytes);
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + Constantes.IMAGE_DIRECTORY);

        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

            File f = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".png");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(context, new String[]{f.getPath()}, new String[]{"image/png"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();

    }



    public static String getURLForResource (int resourceId) {
        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resourceId).toString();
    }



}
