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
 * このActivityではScreenName、テキスト、ツイート者の画像をレコードとしたカスタムしたListViewを表示させます。
 */
public class CustomTimeLineActivity extends ListActivity implements AsyncTaskCallback{

	private TweetAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//今回はリストに画像＋ScreenName＋ツイート内容を表示するため、独自にListViewのAdapterを定義。
		
		//Intent経由で前の画面から渡されたScreenNameを取り出す
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String screenName = extras.getString("screenName");
		
		//非同期にTwitterへアクセスする
		TwitterTimelineRequestTask task = new TwitterTimelineRequestTask(this);
		task.execute(screenName);

		getListView().setTextFilterEnabled(true);
		
	}
	
	/**
	 * ListViewのレコードがクリックされた契機で実行されるメソッド
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Tweet tweet = (Tweet)l.getItemAtPosition(position);
		
		Intent intent = new Intent(CustomTimeLineActivity.this,TweetDetailActivity.class);		
		intent.putExtra("tweet", tweet);
		startActivity(intent);
	}

	/**
	 * AsyncTask実行後にコールバックさせるためのメソッド
	 * （implementsしているAsyncTaskCallbackの実装メソッド）
	 */
	@Override
	public void onFinishTask(List<Tweet> result) {
		//非同期でTwitterから取得したタイムラインのリストをListView用のAdapterにセットする
		adapter = new TweetAdapter(getApplicationContext(), R.layout.tweet, result);
		setListAdapter(adapter);
	}
}
