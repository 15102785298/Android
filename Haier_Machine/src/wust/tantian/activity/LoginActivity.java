package wust.tantian.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

import com.example.haier_machine.R;

public class LoginActivity extends Activity implements OnClickListener {
	private CheckBox cb;
	private Button btn_login, btn_register;
	private EditText account;
	private EditText password;
	private SharedPreferences pref;
	private TextView tv_findPSW;
	private TextView tv_back;
	Editor edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Bmob.initialize(this, "73382553d14b234ba62a55fc86a85c1f");
		init();
		edit = pref.edit();
		String name = pref.getString("username", "");
		if (name.equals("")) {
			cb.setChecked(false);
		} else {
			cb.setChecked(true);
			account.setText(name);
		}
	}

	private void init() {
		pref = getSharedPreferences("userInfo", MODE_PRIVATE);
		cb = (CheckBox) findViewById(R.id.cb_savename);
		tv_back = (TextView) findViewById(R.id.tv_back);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_register = (Button) findViewById(R.id.btn_register);
		account = (EditText) findViewById(R.id.edit_account);
		password = (EditText) findViewById(R.id.edit_password);
		btn_login.setOnClickListener(this);
		btn_register.setOnClickListener(this);
		tv_back.setOnClickListener(this);
		tv_findPSW = (TextView) findViewById(R.id.tv_findPSW);
		tv_findPSW.setOnClickListener(this);
		if (this.getIntent().getExtras() == null) {

		} else {
			account.setText(this.getIntent().getExtras().getString("userName"));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			if (account.equals("")) {
				Toast.makeText(LoginActivity.this, " ‰»Îƒ˙µƒ’À∫≈", Toast.LENGTH_LONG)
						.show();
				return;
			} else if (password.equals("")) {
				Toast.makeText(LoginActivity.this, " ‰»Îƒ˙µƒ√‹¬Î", Toast.LENGTH_LONG)
						.show();
				return;
			} else {
				BmobUser user = new BmobUser();
				user.setUsername(account.getText().toString());
				user.setPassword(password.getText().toString().trim());
				user.login(this, new SaveListener() {

					@Override
					public void onSuccess() {
						if (cb.isChecked()) {
							edit = pref.edit();
							edit.putString("username", account.getText()
									.toString());
							edit.commit();
						} else {
							edit.remove("username");
							edit.commit();
						}
						Intent i = new Intent(LoginActivity.this,
								CloudActivity.class);
						i.putExtra("rst", "ok");
						startActivity(i);
						Toast.makeText(LoginActivity.this, "µ«¬Ω≥…π¶",
								Toast.LENGTH_LONG).show();
					}

					@Override
					public void onFailure(int arg0, String arg1) {
						Toast.makeText(LoginActivity.this, "√‹¬ÎªÚ’À∫≈¥ÌŒÛ",
								Toast.LENGTH_LONG).show();
					}
				});
			}
			break;

		case R.id.btn_register:
			startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
			break;
		case R.id.tv_findPSW:
			startActivity(new Intent(LoginActivity.this, FindPSWActivity.class));
			overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
			break;
		case R.id.tv_back:
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		startActivity(new Intent(LoginActivity.this, CloudActivity.class));
	}
}
