package jp.co.recruit.mtl.sample.adapter;

import java.util.List;

import jp.co.recruit.mtl.sample.R;
import jp.co.recruit.mtl.sample.async.ImageDownloadTask;
import jp.co.recruit.mtl.sample.dto.Tweet;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 写真＋ScreenName+Tweet内容というレコードのListViewを作るためのアダプタークラス
 * Tweetのテキスト１行だけの場合は、ArrayAdapter<String>を使うだけでよく、本クラスを実装する必要はない
 */
public class TweetAdapter extends ArrayAdapter<Tweet> {

	private List<Tweet> tweets;
	private LayoutInflater tweetLayout;

	public TweetAdapter(Context context, int textViewResourceId, List<Tweet> objects) {
		super(context, textViewResourceId, objects);
		this.tweets = objects;
		this.tweetLayout = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * ListViewにセットしたListに対して1行ずつ実行されるメソッド
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View tweetView = convertView;
		if (tweetView == null) {
			// 受け取ったビューがnullなら新しくビューを生成
			tweetView = tweetLayout.inflate(R.layout.tweet, null);
			
			//tweetView.setBackgroundColor(android.graphics.Color.alpha(android.graphics.Color.WHITE));
		}

		Tweet tweet = (Tweet) tweets.get(position);
		if (tweet != null) {
			// ScreenNameのViewオブジェクトを取得
			TextView screenName = (TextView) tweetView
					.findViewById(R.id.screennametext);
			screenName.setTypeface(Typeface.DEFAULT_BOLD);
			if (screenName != null) {
				screenName.setText(tweet.getScreenName());
			}

			// TweetのViewオブジェクトを取得
			TextView text = (TextView) tweetView.findViewById(R.id.tweettext);
			if (text != null) {
				text.setText(tweet.getText());
			}
			
			// Imageを表示
			ImageView imageView = (ImageView) tweetView.findViewById(R.id.image);
			if(imageView != null){
				ImageDownloadTask task = new ImageDownloadTask(imageView);
				task.execute(tweet.getImageUrl());
			}
		}
		return tweetView;
	}
}
