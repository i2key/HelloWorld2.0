package jp.co.recruit.mtl.sample.async;

import java.io.IOException;
import java.io.InputStream;

import jp.co.recruit.mtl.sample.persistence.ImageCache;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {  
    // アイコンを表示するビュー  
    private ImageView imageView;  
  
    // コンストラクタ  
    public ImageDownloadTask(ImageView imageView) {  
        this.imageView = imageView;  
    }  
  
    // バックグラウンドで実行する処理  
    @Override  
    protected Bitmap doInBackground(String... urls) {  
    	//キャッシュにあればそれを返す
        Bitmap image = ImageCache.getImage(urls[0]); 
        //キャッシュに無い場合はURLに画像を取得しにいく
        if (image == null) {  
            HttpGet httpRequest =  new HttpGet(urls[0]);  
            HttpClient httpclient = new DefaultHttpClient();   
            HttpResponse response;  
            try {  
                response = (HttpResponse) httpclient.execute(httpRequest);  
                HttpEntity entity = response.getEntity();   
                BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);   
                InputStream instream = bufHttpEntity.getContent();   
                image = BitmapFactory.decodeStream(instream);   
            } catch (ClientProtocolException e) {  
                e.printStackTrace();  
                return null;  
            } catch (IOException e) {  
                e.printStackTrace();  
                return null;  
            }
            ImageCache.setImage(urls[0], image);  
        }  
        return image;  
    }  
  
    // メインスレッドで実行する処理  
    @Override  
    protected void onPostExecute(Bitmap result) {  
        this.imageView.setImageBitmap(result);  
    }  
}  