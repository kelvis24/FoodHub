package com.example.foodhub.server;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * NOT CURRENTLY USED. A class for handling images
 * @author Sumon Biswas
 * @see LruCache
 * @see ImageCache
 */
public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageCache {

    /**
     * Returns a default cache size
     */
    public static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        return maxMemory / 8;
    }

    /**
     * A default constructor
     */
    public LruBitmapCache() {
        this(getDefaultLruCacheSize());
    }

    /**
     * Constructs a LruBitmapCache with a specified size
     */
    public LruBitmapCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }

    /**
     * Get the size of the
     * @param key The key of the element in the map
     * @param value The value of the element in the map
     */
    @Override protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() /1024;
    }

    /**
     * "Gets" a bitmap
     * @param url A url
     */
    @Override public Bitmap getBitmap(String url) {
        return get(url);
    }

    /**
     * "Puts" a bitmap
     * @param url A url
     * @param bitmap A bitmap
     */
    @Override public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }

}
