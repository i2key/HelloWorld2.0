package jp.co.recruit.mtl.sample.async;

import java.util.ArrayList;
import java.util.List;

import jp.co.recruit.mtl.sample.dto.Tweet;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import android.content.Context;
import android.os.AsyncTask;

public class TwitterTimelineRequestTask extends
		AsyncTask<String, Void, List<Tweet>> {

	private Twitter twitter = null;
	private AsyncTaskCallback callback;
	
	public TwitterTimelineRequestTask(Context context) {
		this.callback = (AsyncTaskCallback)context;
	}
	
	//バックグラウンドで実行する処理
	@Override
	protected List<Tweet> doInBackground(String... params) {
		String userName = params[0];
		
		Paging paging = new Paging();
		if(twitter == null){
			twitter = new TwitterFactory().getInstance();
		}
		List<Tweet> list = new ArrayList<Tweet>();
		try {
			//OAuthは本勉強会の内容と離れるので、単純にPublicなものを取得します。
			ResponseList<twitter4j.Status> timeline = twitter.getUserTimeline(userName, paging);
			
			for(twitter4j.Status status : timeline){
				Tweet tweet = new Tweet();
				tweet.setId(status.getId());
				tweet.setScreenName(status.getUser().getScreenName());
				tweet.setImageUrl(status.getUser().getProfileImageURL().toString());
				tweet.setText(status.getText());
				tweet.setDate(status.getCreatedAt().toLocaleString());
				list.add(tweet);
			}
		} catch (TwitterException e) {
		    e.printStackTrace();
		    if(e.isCausedByNetworkIssue()){
		    	//ネットワーク接続エラー
		    	//エラー処理を書いてねー
		    }else{
		    	//それ以外の 異常
		    }
		}
		return list;
	}

	//メインスレッド(GUIスレッド)で走る処理
	protected void onPostExecute(List<Tweet> result) { 
		callback.onFinishTask(result);
	}
	
}
