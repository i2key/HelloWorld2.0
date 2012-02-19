package jp.co.recruit.mtl.sample.activity;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * QueryActivityのViewで入力されたScreenNameをもとに検索したタイムラインを表示するためのActivityです。
 * List表示用にListActivityを継承しています。
 * このActivityではツイートのテキストのみをレコードとしたListViewを表示させます。
 */
public class SimpleTimeLineActivity extends ListActivity{

	private ArrayAdapter<String> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Intent経由で前の画面から渡されたScreenNameを取り出す
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String screenName = extras.getString("screenName");
		
		//Android3.0以降では、ネットワークアクセス等の処理はメインスレッドでの実行は制限されるようになっています。（StrictMode）
		//しかし、ここでは、ListViewを作るための説明用として可読性をあげるためにStrictModeをOFFに意図的にしています。
		//これは本当はやってはいけないことなので、TwitterTimeLineRequestTaskでの非同期によるネットワーク接続の実装を参考にしてください。	
		//なお、CustomTimeLineActivityでは、通常の非同期による実装を行っています。
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
		
		//Twitter4jのTwitterクライアントをインスタンス化
		Twitter twitter = new TwitterFactory().getInstance();
		//OAuthは本勉強会の内容と離れるので、単純にPublicなものを取得します。
		ResponseList<twitter4j.Status> timeline = null;
		try {
			//タイムラインを取得
			timeline = twitter.getUserTimeline(screenName, new Paging());
		} catch (TwitterException e) {
			//なんかエラー処理かかないとね
			e.printStackTrace();
		}
		
		//取得したタイムラインからツイートのテキスト部分を取り出し、List化
		List<String> tweetList = new ArrayList<String>();
		for(twitter4j.Status status : timeline){
			tweetList.add(status.getText());
		}
		
		//ArrayAdapter<String>にListをsimple_list_item_1形式で、取得したツイートのリストをセット
		adapter = new ArrayAdapter<String>(
					this, android.R.layout.simple_list_item_1,tweetList);	
		
		setListAdapter(adapter);						
	}
	
	/**
	 * ListViewのレコードがクリックされた契機で実行されるメソッド
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		//クリックされた行の内容を取得し文字列に。
		String text = (String)l.getItemAtPosition(position);
		StringBuffer buf = new StringBuffer();
		buf.append(position);
		buf.append("行目がタッチされました。内容は、「");
		buf.append(text);
		buf.append("」です。");
		
		//Listの行がクリックされたイベントをトーストで表示
		Toast.makeText(this, buf.toString(), Toast.LENGTH_LONG).show();
	}
}
