package andy.ham;
import  andy.ham.SqliteHelper;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText login_name, login_psd;
	private SharedPreferences preferences;
	private CheckBox rememberPass;
	private SqliteHelper helper;
	public static SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//tv_title = (TextView) findViewById(R.id.tv_title);
		login_name = (EditText) findViewById(R.id.login_name);
		login_psd = (EditText) findViewById(R.id.login_psd);
		preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
		//rememberPass=(CheckBox) findViewById(R.id.pass);
		rememberPass.setOnCheckedChangeListener(onCheckedChangeListener);
		boolean isRemember=preferences.getBoolean("remember_password",false);
		if(isRemember) {
			String register_name = preferences.getString("register_name", "");
			String register_psd = preferences.getString("register_psd", "");
			//register_name.setText(register_name);
			//register_psd.setText(register_psd);
			rememberPass.setChecked(true);
		}
	}
	private OnCheckedChangeListener onCheckedChangeListener=new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
				case R.id.rd_man:
					sex=rg_man.getText().toString();
					break;
				case R.id.rd_not_man:
					sex=rg_woman.getText().toString();
					break;
			}
		}
	};
	public void login_btn(View v) {
		String name = login_name.getText().toString();
		String psd = login_psd.getText().toString();
		if (!name.equals(preferences.getString("name", ""))) {
			Toast.makeText(this, "用户名不存在", Toast.LENGTH_SHORT).show();
		} else if (!psd.equals(preferences.getString("psd", ""))) {
			Toast.makeText(this, "" + "密码错误", Toast.LENGTH_SHORT).show();
		}else {
//			跳入主界面
			helper=new SqliteHelper(this, "books.db", null, 1);
			db=helper.getWritableDatabase();
			startActivity(new Intent(this,LifeDiary.class));
			Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
	public void register_btn(View v) {
		startActivity(new Intent(this, RegisterActivity.class));
		Toast.makeText(this, "跳", Toast.LENGTH_SHORT).show();
		finish();
	}

}
