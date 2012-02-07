package jp.co.recruit.mtl.sample.activity;

import jp.co.recruit.mtl.sample.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class QuerryActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //���C�A�E�g�̐ݒ�
        setContentView(R.layout.input_search_condition);
        
        //UI�̐���
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new ButtonClickListener());
    }
    
    /**
     * �{�^���C�x���g�p�̃N���b�N���X�i�[�̎����i�֋X�㖳���N���X�ł͂Ȃ��C���i�[�N���X�����Ă���j
     */
    class ButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			EditText input = (EditText)findViewById(R.id.editText1);
			//intent QuerryActivity����TimeLineActivity�ɑJ�ڂ�����
			Intent intent = new Intent(QuerryActivity.this,TimeLineActivity.class);
			intent.putExtra("screenName", input.getText().toString());
			startActivity(intent);
		}
    	
    }
}