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
        
        //レイアウトの設定
        setContentView(R.layout.query);
        
        //UIの生成
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new ButtonClickListener());
    }
    
    /**
     * ボタンイベント用のクリックリスナーの実装（便宜上無名クラスではなくインナークラス化している）
     */
    class ButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			//テキストボックスを取得
			EditText screenName = (EditText)findViewById(R.id.editText1);
			//チェックボックスを取得
			CheckBox chkbox = (CheckBox)findViewById(R.id.checkBox1);

			//チェックボックスがチェックされていなければSimpleTimeLineActivityに遷移
			//チェックボックスがチェックされていたらCustomTimeLineActivityに遷移
			Intent intent = new Intent(QuerryActivity.this,chkbox.isChecked() ? CustomTimeLineActivity.class : SimpleTimeLineActivity.class);

			//引数に入力されたScreenNameを入れます。
			intent.putExtra("screenName", screenName.getText().toString());

			//遷移開始
			startActivity(intent);
		}
    	
    }
}