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

/**
 * QueryActivityのViewで入力されたScreenNameをもとに検索したタイムラインを表示するためのActivityです。
 * List表示用にListActivityを継承しています。
 */
public class TimeLineActivity extends ListActivity implements AsyncTaskCallback{

	private TweetAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		一行のTextだけのListであれば、ArrayAdapter<String>でよい
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String userName = extras.getString("userName");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_list_item_1);
		TwitterTimelineRequestTask task = new TwitterTimelineRequestTask(this);
		task.execute(userName);

		setListAdapter(adapter);
		getListView().setTextFilterEnabled(true);
		
		今回はリストに画像＋ScreenName＋ツイート内容を表示するため、独自にListViewのAdapterを定義。
		*/
		
		//前の画面で入力された
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String userName = extras.getString("screenName");
		
		//非同期にTwitterへアクセスする
		TwitterTimelineRequestTask task = new TwitterTimelineRequestTask(this);
		task.execute(userName);

		getListView().setTextFilterEnabled(true);
		
	}
	
	/**
	 * ListViewのレコードがクリックされた契機で実行されるメソッド
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Tweet tweet = (Tweet)l.getItemAtPosition(position);
		
		Intent intent = new Intent(TimeLineActivity.this,TweetDetailActivity.class);		
		intent.putExtra("tweet", tweet);
		startActivity(intent);
	}

	/**
	 * AsyncTask実行後にコールバックさせるためのメソッド
	 * （implementsしているAsyncTaskCallbackの実装メソッド）
	 */
	@Override
	public void onFinishTask(Object result) {
		//非同期でTwitterから取得したタイムラインのリストをListView用のAdapterにセットする
		adapter = new TweetAdapter(getApplicationContext(), R.layout.tweet, (List<Tweet>)result);
		setListAdapter(adapter);
	}
}
