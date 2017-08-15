package com.saraceni.imgurclient;

import android.app.Application;

import com.saraceni.imgurclient.util.Utils;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

/**
 * Created by rafaelgontijo on 13/08/17.
 */
public class ImgurApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Increase Picasso's memory cache size
        Picasso picasso =  new Picasso.Builder(this).memoryCache(new LruCache(Utils.getMemoryCacheSize(this))).build();
        Picasso.setSingletonInstance(picasso);
    }
}
