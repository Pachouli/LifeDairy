package andy.ham;
//import  andy.ham.SqliteHelper;
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
	private SharedPreferences.Editor editor;
	private SharedPreferences preferences;
	private CheckBox rememberPass;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		login_name = (EditText) findViewById(R.id.login_name);
		login_psd = (EditText) findViewById(R.id.login_psd);
		preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
		boolean isRemember=preferences.getBoolean("remember_password",false);
		if(isRemember) {
			String name = preferences.getString("login_name", "");
			String psd = preferences.getString("login_psd", "");
			login_name.setText(name);
			login_psd.setText(psd);
			rememberPass.setChecked(true);
		}
	}
	public void login_btn(View v) {
		String name = login_name.getText().toString();
		String psd = login_psd.getText().toString();
		if (!name.equals(preferences.getString("name", ""))) {
			Toast.makeText(this, "用户名不存在", Toast.LENGTH_SHORT).show();
		} else if (!psd.equals(preferences.getString("psd", ""))) {
			Toast.makeText(this, "" + "密码错误", Toast.LENGTH_SHORT).show();
		}else {
//			跳入主界面
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
