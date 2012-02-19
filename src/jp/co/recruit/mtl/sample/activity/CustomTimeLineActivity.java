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
 * QueryActivity��View�œ��͂��ꂽScreenName�����ƂɌ��������^�C�����C����\�����邽�߂�Activity�ł��B
 * List�\���p��ListActivity���p�����Ă��܂��B
 * ����Activity�ł�ScreenName�A�e�L�X�g�A�c�C�[�g�҂̉摜�����R�[�h�Ƃ����J�X�^������ListView��\�������܂��B
 */
public class CustomTimeLineActivity extends ListActivity implements AsyncTaskCallback{

	private TweetAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//����̓��X�g�ɉ摜�{ScreenName�{�c�C�[�g���e��\�����邽�߁A�Ǝ���ListView��Adapter���`�B
		
		//Intent�o�R�őO�̉�ʂ���n���ꂽScreenName�����o��
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String screenName = extras.getString("screenName");
		
		//�񓯊���Twitter�փA�N�Z�X����
		TwitterTimelineRequestTask task = new TwitterTimelineRequestTask(this);
		task.execute(screenName);

		getListView().setTextFilterEnabled(true);
		
	}
	
	/**
	 * ListView�̃��R�[�h���N���b�N���ꂽ�_�@�Ŏ��s����郁�\�b�h
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
	 * AsyncTask���s��ɃR�[���o�b�N�����邽�߂̃��\�b�h
	 * �iimplements���Ă���AsyncTaskCallback�̎������\�b�h�j
	 */
	@Override
	public void onFinishTask(List<Tweet> result) {
		//�񓯊���Twitter����擾�����^�C�����C���̃��X�g��ListView�p��Adapter�ɃZ�b�g����
		adapter = new TweetAdapter(getApplicationContext(), R.layout.tweet, result);
		setListAdapter(adapter);
	}
}
