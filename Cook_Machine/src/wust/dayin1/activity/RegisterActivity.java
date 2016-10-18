package wust.dayin1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

import com.example.haier_machine.R;
	
/**
 * 注册界面
 */
public class RegisterActivity extends Activity implements OnClickListener {

	private EditText account;
	private EditText password, confirmPassword;
	private EditText email;
	private Button register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		account = (EditText) findViewById(R.id.edit_account);
		password = (EditText) findViewById(R.id.edit_password);
		confirmPassword = (EditText) findViewById(R.id.edit_confirmPassword);
		email = (EditText) findViewById(R.id.edit_email);
		register = (Button) findViewById(R.id.btn_register);
		register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_register:
			final String account_ = account.getText().toString();
			final String password_ = password.getText().toString();
			final String email_ = email.getText().toString();
			if (account_.equals("") || password_.equals("")
					|| email_.equals("")) {
				Toast.makeText(this, "请将资料填写完整", Toast.LENGTH_LONG).show();
			} else if (!check()) {
				Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_LONG).show();
			} else {
				final BmobUser user = new BmobUser();
				user.setUsername(account_);
				user.setPassword(password_);
				user.setEmail(email_);
				user.signUp(this, new SaveListener() {

					@Override
					public void onSuccess() {
						Intent i = new Intent(RegisterActivity.this,
								LoginActivity.class);
						Bundle bundle = new Bundle();
						bundle.putString("userName", account.getText()
								.toString());
						i.putExtras(bundle);
						startActivity(i);
						Toast.makeText(RegisterActivity.this, "注册成功，请在邮箱中激活",
								Toast.LENGTH_LONG).show();
					}

					@Override
					public void onFailure(int arg0, String arg1) {
						Toast.makeText(RegisterActivity.this, "邮箱已被使用",
								Toast.LENGTH_LONG).show();
					}
				});
			}
			break;

		default:
			break;
		}
	}

	private boolean check() {
		if (!password.getText().toString()
				.equals(confirmPassword.getText().toString()))
			return false;
		return true;
	}
}
