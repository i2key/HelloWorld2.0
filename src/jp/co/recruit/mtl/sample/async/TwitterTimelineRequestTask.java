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
	
	//�o�b�N�O���E���h�Ŏ��s���鏈��
	@Override
	protected List<Tweet> doInBackground(String... params) {
		String userName = params[0];
		
		Paging paging = new Paging();
		if(twitter == null){
			twitter = new TwitterFactory().getInstance();
		}
		List<Tweet> list = new ArrayList<Tweet>();
		try {
			//OAuth�͖{�׋���̓��e�Ɨ����̂ŁA�P����Public�Ȃ��̂��擾���܂��B
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
		    	//�l�b�g���[�N�ڑ��G���[
		    	//�G���[�����������Ăˁ[
		    }else{
		    	//����ȊO�� �ُ�
		    }
		}
		return list;
	}

	//���C���X���b�h(GUI�X���b�h)�ő��鏈��
	protected void onPostExecute(List<Tweet> result) { 
		callback.onFinishTask(result);
	}
	
}
