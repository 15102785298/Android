package wust.dayin1.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;

import com.example.haier_machine.R;

public class PersonInfo extends Activity implements OnClickListener {
	private TextView account;
	private TextView tv_food;

	private Button btn_login, btn_community, btn_logout,btn_recommend,btn_leaderboard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personinfo);
		init();
		if (BmobUser.getCurrentUser(getApplicationContext()) == null) {
			btn_login.setVisibility(View.VISIBLE);
			btn_logout.setVisibility(View.GONE);
			account.setText("Î´µÇÂ½");
		} else {
			String account_ = BmobUser.getCurrentUser(getApplicationContext())
					.getUsername();
			account.setText(account_);
			btn_login.setVisibility(View.GONE);
			btn_logout.setVisibility(View.VISIBLE);
		}
	}

	@SuppressLint("CutPasteId")
	private void init() {

		account = (TextView) findViewById(R.id.tv_account);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_logout = (Button) findViewById(R.id.btn_logout);
		btn_community = (Button) findViewById(R.id.btn_community);
		btn_recommend = (Button) findViewById(R.id.btn_recommend);
		tv_food = (TextView) findViewById(R.id.tv_food);
		btn_leaderboard=(Button) findViewById(R.id.btn_leaderboard);
		tv_food.setOnClickListener(this);
		btn_community.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_logout.setOnClickListener(this);
		btn_recommend.setOnClickListener(this);
		btn_leaderboard.setOnClickListener(this);
	}

	@SuppressWarnings("static-access")
	@SuppressLint("ShowToast")
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_login:
			startActivity(new Intent(PersonInfo.this, LoginActivity.class));
			break;
		case R.id.btn_logout:
			BmobUser.getCurrentUser(this).logOut(this);
			btn_login.setVisibility(View.VISIBLE);
			btn_logout.setVisibility(View.GONE);
			account.setText("Î´µÇÂ½");
			break;
		case R.id.btn_community:
			Toast.makeText(getApplicationContext(), "ÔØÈëÖÐ...",
					Toast.LENGTH_LONG).show();
			startActivity(new Intent(PersonInfo.this, CommunityActivity.class));
			break;
		case R.id.tv_food:
			startActivity(new Intent(PersonInfo.this, CloudActivity.class));
			break;
		case R.id.btn_recommend:
			startActivity(new Intent(PersonInfo.this,ClassifyActivity.class));
			break;
		case R.id.btn_leaderboard:
			startActivity(new Intent(PersonInfo.this,leaderboardActivity.class));
			break;
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		startActivity(new Intent(PersonInfo.this, CloudActivity.class));
	}
}
