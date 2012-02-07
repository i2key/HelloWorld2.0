package jp.co.recruit.mtl.sample.activity;

import jp.co.recruit.mtl.sample.R;
import jp.co.recruit.mtl.sample.async.ImageDownloadTask;
import jp.co.recruit.mtl.sample.dto.Tweet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetDetailActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_detail);
        
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		Tweet tweet = (Tweet)extras.get("tweet");
		        
        ((TextView)findViewById(R.id.textView_text)).setText(tweet.getText());
        
        ImageView imageView = (ImageView)findViewById(R.id.image_detail);
		if(imageView != null){
			ImageDownloadTask task = new ImageDownloadTask(imageView);
			task.execute(tweet.getImageUrl());
		}
		
        ((TextView)findViewById(R.id.textView_screenname)).setText(tweet.getName()+"("+tweet.getScreenName()+")");
        ((TextView)findViewById(R.id.textView_date)).setText(tweet.getDate());
    }
   
}
