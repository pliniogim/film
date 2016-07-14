package com.dlinkddns.pliniolgimenez.filmes;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;

/**
 * com.dlinkddns.pliniolgimenez.filmes
 * <p>
 * Filmes
 * <p>
 * Created by plinio on 14/07/2016.
 */

public class TargetPhoneGallery implements Target {
    private final WeakReference<ContentResolver> resolver;
    private final String name;
    private final String desc;

    public TargetPhoneGallery(ContentResolver r, String name, String desc) {
        this.resolver = new WeakReference<ContentResolver>(r);
        this.name = name;
        this.desc = desc;
    }

    @Override
    public void onPrepareLoad(Drawable arg0) {
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom arg1) {
        ContentResolver r = resolver.get();
        if (r != null) {
            MediaStore.Images.Media.insertImage(r, bitmap, name, desc);
        }
    }

    @Override
    public void onBitmapFailed(Drawable arg0) {
    }
}


