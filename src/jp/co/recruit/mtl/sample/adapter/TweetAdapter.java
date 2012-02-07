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
 * �ʐ^�{ScreenName+Tweet���e�Ƃ������R�[�h��ListView����邽�߂̃A�_�v�^�[�N���X
 * Tweet�̃e�L�X�g�P�s�����̏ꍇ�́AArrayAdapter<String>���g�������ł悭�A�{�N���X����������K�v�͂Ȃ�
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
	 * ListView�ɃZ�b�g����List�ɑ΂���1�s�����s����郁�\�b�h
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View tweetView = convertView;
		if (tweetView == null) {
			// �󂯎�����r���[��null�Ȃ�V�����r���[�𐶐�
			tweetView = tweetLayout.inflate(R.layout.tweet, null);
			
			//tweetView.setBackgroundColor(android.graphics.Color.alpha(android.graphics.Color.WHITE));
		}

		Tweet tweet = (Tweet) tweets.get(position);
		if (tweet != null) {
			// ScreenName��View�I�u�W�F�N�g���擾
			TextView screenName = (TextView) tweetView
					.findViewById(R.id.screennametext);
			screenName.setTypeface(Typeface.DEFAULT_BOLD);
			if (screenName != null) {
				screenName.setText(tweet.getScreenName());
			}

			// Tweet��View�I�u�W�F�N�g���擾
			TextView text = (TextView) tweetView.findViewById(R.id.tweettext);
			if (text != null) {
				text.setText(tweet.getText());
			}
			
			// Image��\��
			ImageView imageView = (ImageView) tweetView.findViewById(R.id.image);
			if(imageView != null){
				ImageDownloadTask task = new ImageDownloadTask(imageView);
				task.execute(tweet.getImageUrl());
			}
		}
		return tweetView;
	}
}
