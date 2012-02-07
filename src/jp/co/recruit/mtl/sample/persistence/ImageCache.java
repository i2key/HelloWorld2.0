package jp.co.recruit.mtl.sample.persistence;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Twitterの各自のアイコン画像キャッシュするためのクラス
 */
public class ImageCache {  
	//HashMap<String,Bitmap>(); を HashMap<String,SoftReference<Bitmap>>(); としてメモリーリークに備えている
    private static HashMap<String,SoftReference<Bitmap>> cache = new HashMap<String,SoftReference<Bitmap>>();  
      
    public static Bitmap getImage(String key) {  
        if (cache.containsKey(key)) {  
            Log.d("cache", "cache hit!");  
            return cache.get(key).get();  
        }  
        return null;  
    }  
      
    public static void setImage(String key, Bitmap image) {  
    	//SoftReferenceにラップしてHashMapに格納
        cache.put(key, new SoftReference<Bitmap>(image));  
    }  
} 