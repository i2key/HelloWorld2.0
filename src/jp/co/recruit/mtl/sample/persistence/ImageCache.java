package jp.co.recruit.mtl.sample.persistence;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Twitter�̊e���̃A�C�R���摜�L���b�V�����邽�߂̃N���X
 */
public class ImageCache {  
	//HashMap<String,Bitmap>(); �� HashMap<String,SoftReference<Bitmap>>(); �Ƃ��ă������[���[�N�ɔ����Ă���
    private static HashMap<String,SoftReference<Bitmap>> cache = new HashMap<String,SoftReference<Bitmap>>();  
      
    public static Bitmap getImage(String key) {  
        if (cache.containsKey(key)) {  
            Log.d("cache", "cache hit!");  
            return cache.get(key).get();  
        }  
        return null;  
    }  
      
    public static void setImage(String key, Bitmap image) {  
    	//SoftReference�Ƀ��b�v����HashMap�Ɋi�[
        cache.put(key, new SoftReference<Bitmap>(image));  
    }  
} 