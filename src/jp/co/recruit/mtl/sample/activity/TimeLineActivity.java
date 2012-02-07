package jp.co.recruit.mtl.sample.activity;

import java.util.List;

import jp.co.recruit.mtl.sample.R;
import jp.co.recruit.mtl.sample.adapter.TweetAdapter;
import jp.co.recruit.mtl.sample.async.AsyncTaskCallback;
import jp.co.recruit.mtl.sample.async.TwitterTimelineRequestTask;
import jp.co.recruit.mtl.sample.dto.Tweet;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class TimeLineActivity extends ListActivity implements AsyncTaskCallback{

	private TweetAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		��s��Text������List�ł���΁AArrayAdapter<String>�ł悢
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String userName = extras.getString("userName");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_list_item_1);
		TwitterTimelineRequestTask task = new TwitterTimelineRequestTask(this);
		task.execute(userName);

		setListAdapter(adapter);
		getListView().setTextFilterEnabled(true);
		
		����̓��X�g�ɉ摜�{ScreenName�{�c�C�[�g���e��\�����邽�߁A�Ǝ���ListView��Adapter���`�B
		*/
		
		//�O�̉�ʂœ��͂��ꂽ
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String userName = extras.getString("screenName");
		
		TwitterTimelineRequestTask task = new TwitterTimelineRequestTask(this);
		task.execute(userName);

		getListView().setTextFilterEnabled(true);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Tweet tweet = (Tweet)l.getItemAtPosition(position);
		
		Intent intent = new Intent(TimeLineActivity.this,TweetDetailActivity.class);		
		intent.putExtra("tweet", tweet);
		startActivity(intent);
	}

	@Override
	public void onFinishTask(Object result) {
		adapter = new TweetAdapter(getApplicationContext(), R.layout.tweet, (List<Tweet>)result);
		setListAdapter(adapter);
	}
}