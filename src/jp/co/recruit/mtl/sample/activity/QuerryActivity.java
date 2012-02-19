package jp.co.recruit.mtl.sample.activity;

import jp.co.recruit.mtl.sample.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class QuerryActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //���C�A�E�g�̐ݒ�
        setContentView(R.layout.query);
        
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
			//�e�L�X�g�{�b�N�X���擾
			EditText screenName = (EditText)findViewById(R.id.editText1);
			//�`�F�b�N�{�b�N�X���擾
			CheckBox chkbox = (CheckBox)findViewById(R.id.checkBox1);

			//�`�F�b�N�{�b�N�X���`�F�b�N����Ă��Ȃ����SimpleTimeLineActivity�ɑJ��
			//�`�F�b�N�{�b�N�X���`�F�b�N����Ă�����CustomTimeLineActivity�ɑJ��
			Intent intent = new Intent(QuerryActivity.this,chkbox.isChecked() ? CustomTimeLineActivity.class : SimpleTimeLineActivity.class);

			//�����ɓ��͂��ꂽScreenName�����܂��B
			intent.putExtra("screenName", screenName.getText().toString());

			//�J�ڊJ�n
			startActivity(intent);
		}
    	
    }
}