package wust.dayin1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.EmailVerifyListener;
import cn.bmob.v3.listener.ResetPasswordListener;

import com.example.haier_machine.R;

public class FindPSWActivity extends Activity implements OnClickListener {

	private EditText et_email;
	private Button btn_find, btn_send;
	private TextView tv_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findpsw);
		initView();

	}

	private void initView() {
		et_email = (EditText) findViewById(R.id.et_email);

		btn_find = (Button) findViewById(R.id.btn_find);
		btn_find.setOnClickListener(this);
		btn_send = (Button) findViewById(R.id.btn_send);
		btn_send.setOnClickListener(this);
		tv_back = (TextView) findViewById(R.id.tv_back);
		tv_back.setOnClickListener(this);
	}

	@SuppressWarnings("static-access")
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.tv_back:
			finish();
			break;
		case R.id.btn_find:
			//ͨ��������������
			final String email = et_email.getText().toString();
			if (email.equals("")) {
				Toast.makeText(getApplicationContext(), "����д��������",
						Toast.LENGTH_SHORT).show();
			} else {
				BmobUser.getCurrentUser(getApplicationContext()).resetPassword(
						getApplicationContext(), email,
						new ResetPasswordListener() {

							@Override
							public void onSuccess() {
								finish();
								Toast.makeText(getApplicationContext(),
										"������������ɹ����뵽" + email + "��������������ò���",
										Toast.LENGTH_SHORT).show();

							}

							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO Auto-generated method stub

							}
						});
			}

			break;
		case R.id.btn_send:
			//�������е�����
			final String email2 = et_email.getText().toString();
			if (email2.equals("")) {
				Toast.makeText(getApplicationContext(), "����д��������",
						Toast.LENGTH_SHORT).show();
			} else {
				BmobUser.requestEmailVerify(getApplicationContext(), email2,
						new EmailVerifyListener() {

							@Override
							public void onSuccess() {
								finish();
								Toast.makeText(getApplicationContext(),
										"�����ʼ��ѷ��ͣ���ǰ����֤", Toast.LENGTH_SHORT)
										.show();
							}

							@Override
							public void onFailure(int arg0, String arg1) {
							}
						});
			}
		default:
			break;
		}
	}
}
