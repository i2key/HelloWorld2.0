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
 * QueryActivity��View�œ��͂��ꂽScreenName�����ƂɌ��������^�C�����C����\�����邽�߂�Activity�ł��B
 * List�\���p��ListActivity���p�����Ă��܂��B
 * ����Activity�ł̓c�C�[�g�̃e�L�X�g�݂̂����R�[�h�Ƃ���ListView��\�������܂��B
 */
public class SimpleTimeLineActivity extends ListActivity{

	private ArrayAdapter<String> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Intent�o�R�őO�̉�ʂ���n���ꂽScreenName�����o��
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String screenName = extras.getString("screenName");
		
		//Android3.0�ȍ~�ł́A�l�b�g���[�N�A�N�Z�X���̏����̓��C���X���b�h�ł̎��s�͐��������悤�ɂȂ��Ă��܂��B�iStrictMode�j
		//�������A�����ł́AListView����邽�߂̐����p�Ƃ��ĉǐ��������邽�߂�StrictMode��OFF�ɈӐ}�I�ɂ��Ă��܂��B
		//����͖{���͂���Ă͂����Ȃ����ƂȂ̂ŁATwitterTimeLineRequestTask�ł̔񓯊��ɂ��l�b�g���[�N�ڑ��̎������Q�l�ɂ��Ă��������B	
		//�Ȃ��ACustomTimeLineActivity�ł́A�ʏ�̔񓯊��ɂ��������s���Ă��܂��B
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
		
		//Twitter4j��Twitter�N���C�A���g���C���X�^���X��
		Twitter twitter = new TwitterFactory().getInstance();
		//OAuth�͖{�׋���̓��e�Ɨ����̂ŁA�P����Public�Ȃ��̂��擾���܂��B
		ResponseList<twitter4j.Status> timeline = null;
		try {
			//�^�C�����C�����擾
			timeline = twitter.getUserTimeline(screenName, new Paging());
		} catch (TwitterException e) {
			//�Ȃ񂩃G���[���������Ȃ��Ƃ�
			e.printStackTrace();
		}
		
		//�擾�����^�C�����C������c�C�[�g�̃e�L�X�g���������o���AList��
		List<String> tweetList = new ArrayList<String>();
		for(twitter4j.Status status : timeline){
			tweetList.add(status.getText());
		}
		
		//ArrayAdapter<String>��List��simple_list_item_1�`���ŁA�擾�����c�C�[�g�̃��X�g���Z�b�g
		adapter = new ArrayAdapter<String>(
					this, android.R.layout.simple_list_item_1,tweetList);	
		
		setListAdapter(adapter);						
	}
	
	/**
	 * ListView�̃��R�[�h���N���b�N���ꂽ�_�@�Ŏ��s����郁�\�b�h
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		//�N���b�N���ꂽ�s�̓��e���擾��������ɁB
		String text = (String)l.getItemAtPosition(position);
		StringBuffer buf = new StringBuffer();
		buf.append(position);
		buf.append("�s�ڂ��^�b�`����܂����B���e�́A�u");
		buf.append(text);
		buf.append("�v�ł��B");
		
		//List�̍s���N���b�N���ꂽ�C�x���g���g�[�X�g�ŕ\��
		Toast.makeText(this, buf.toString(), Toast.LENGTH_LONG).show();
	}
}
